package dbLayer;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.sql.Types;
import java.util.ArrayList;

import modelLayer.Car;
import modelLayer.Customer;
import dbLayer.interfaceLayer.IFDBCar;
import dbLayer.interfaceLayer.IFDBCustomer;
import dbLayer.interfaceLayer.IFDBPostalcode;
import exceptions.DBException;
import exceptions.DBNotFoundException;
import exceptions.ObjectNotExistException;

/**
 * Class for DBCustomer
 *
 * @author Group 3, dmaa0214, UCN
 *
 */
public class DBCustomer implements IFDBCustomer {

	private Connection con;

	public DBCustomer() {
		con = DBConnection.getInstance().getDBCon();
	}

	@Override
	public Customer getCustomerByCar(Car car) {
		return singleWhere("CustomerID = " + car.getOwner().getId(), false);
	}

	@Override
	public Customer getCustomerByID(int id, boolean retAsso) {
		return singleWhere("customerID = " + id, retAsso);
	}

	@Override
	public ArrayList<Customer> getCustomersByPhone(String phoneNumber, boolean retAsso) {
		return miscWhere("phoneNumber = '" + phoneNumber + "'", retAsso);
	}

	@Override
	public ArrayList<Customer> getCustomersByName(String name, boolean retAsso) {
		return miscWhere("name LIKE '%" + name + "%'", retAsso);
	}

	@Override
	public Customer getCustomerByCvr(String cvr, boolean retAsso) {
		return singleWhere("cvr = " + cvr, retAsso);
	}

	@Override
	public int insertCustomer(Customer customer) throws DBException {
		int rc = -1;
		final String fields = "(name, phoneNumber, address, postalCode, cvr, email, hidden)";
		String query = "INSERT INTO CUSTOMER " + fields + " VALUES (?,?,?,?,?,?,?)";

		try {
			DBConnection.startTransaction();
			
			PreparedStatement stmt = con.prepareStatement(query, Statement.RETURN_GENERATED_KEYS);
			stmt.setQueryTimeout(5);
			updateFields(customer, stmt);
			IFDBPostalcode dbPost = new DBPostalCode();
			dbPost.insertPostalCode(customer.getPostalCode(), customer.getCity());
			rc = stmt.executeUpdate();
			
			ResultSet genRs = stmt.getGeneratedKeys();
			if(genRs.next()){
				customer.setId(genRs.getInt(1));
			}
			
			stmt.close();
			
			insertCars(customer);
			
			DBConnection.commitTransaction();
		
		} catch(SQLException e) {
			System.out.println("DBCustomer.insertCustomer() - " + e);
			System.out.println(e.getMessage());
			DBConnection.rollBackTransaction();
			throw new DBException("Kunden", e);
		}
		
		if (rc == 0){
			throw new DBNotFoundException("Kunden", 1);
		}
		
		return rc;
	}

	private void insertCars(Customer customer) throws DBException {
		ArrayList<Car> cars = customer.getCars();
		
		if (cars != null && cars.size() != 0) {
			IFDBCar dbCar = new DBCar();
			for (Car car : cars) {
				dbCar.insertCar(car, false);
			}
		}
	}

	@Override
	public int updateCustomer(Customer customer) throws DBException{
		int rc = -1;
		final String allFields = 
				"name = ?, " + 
						"phoneNumber = ?, " + 
						"address = ?, " + 
						"postalCode = ?, " + 
						"cvr = ?, " + 
						"email = ?, " + 
						"hidden = ? "; 
		String query = "UPDATE CUSTOMER SET " + allFields + "WHERE customerID = ?" 	;

		try {
			PreparedStatement stmt = con.prepareStatement(query);
			stmt.setQueryTimeout(5);
			updateFields(customer, stmt);
			stmt.setInt(8, customer.getId());
			IFDBPostalcode dbPost = new DBPostalCode();
			dbPost.insertPostalCode(customer.getPostalCode(), customer.getCity());
			rc = stmt.executeUpdate();
			stmt.close();
		} catch(SQLException e) {
			throw new DBException("Kunden", e);
		}
		
		if(rc == 0){
			throw new DBNotFoundException("Kunden", 2);
		}
		return rc;
	}

	/**
	 * @param stmt
	 * @throws SQLException 
	 */
	private void updateFields(Customer customer, PreparedStatement stmt) { 
		try {
			stmt.setString(1, customer.getName());
			stmt.setString(2, customer.getPhoneNumber());
			stmt.setString(3, customer.getAddress());
			stmt.setInt(4, customer.getPostalCode());
			
			int cvr = customer.getCvr();
			if (cvr == -1 || cvr == 0) {
				stmt.setNull(5, Types.INTEGER);
			} else {
				stmt.setInt(5, cvr);
			}
			
			String email = customer.getEmail();
			if (email == null || email.isEmpty()) {
				stmt.setNull(6, Types.VARCHAR);
			} else {
				stmt.setString(6, email);
			}
			
			stmt.setBoolean(7, customer.getHidden());
		} catch (Exception e) {
			System.out.println("Error - updateFields - DBCustomer");
			e.printStackTrace();
		}
	}

	@Override
	public int deleteCustomer(Customer customer) throws DBException { //TODO transaction? bilen skal vel slettes (fors�ges) f�rst? hidden skal s�ttes tilbage ved fejl?
		int rc = -1;
		String query = "DELETE FROM CUSTOMER WHERE CUSTOMERID = " + customer.getId();

		try {
			Statement stmt = con.createStatement();
			stmt.setQueryTimeout(5);
			rc = stmt.executeUpdate(query);
			stmt.close();
		}catch(SQLException e){    //Foreign key error
			if(e.getErrorCode() == 547){
				customer.setHidden(true);
				rc = updateCustomer(customer);
			} else {
				throw new DBException("Kunden", e);
			}
		}
		return rc;
	}

	private String buildQuery(String wQuery) {
		final String allFields = "CustomerID, name, phoneNumber, address, postalCode, cvr, email, hidden";
		String query = "SELECT " + allFields + " FROM Customer";

		if(!wQuery.isEmpty()){
			query += " WHERE " + wQuery;
		}
		
		return query;
	}

	private Customer buildCustomer(ResultSet rs) {
		Customer customer = new Customer();

		try{
			customer.setId(rs.getInt("CustomerID"));
			customer.setName(rs.getString("name"));
			customer.setPhoneNumber(rs.getString("phoneNumber"));
			customer.setAddress(rs.getString("address"));
			customer.setCvr(rs.getInt("cvr"));
			customer.setEmail(rs.getString("email"));
			customer.setHidden(rs.getBoolean("hidden"));
			DBPostalCode dbPost = new DBPostalCode();
			String city = dbPost.getCity(rs.getInt("postalCode")); 
			customer.setPostalCode(rs.getInt("postalCode")); 
			customer.setCity(city);


		}catch(Exception e){
			System.out.println("DBCustomer - buildCustomer - Exception");
			e.printStackTrace();
		}

		return customer;
	}

	private ArrayList<Customer> miscWhere(String wQuery, boolean retAsso) {
		ArrayList<Customer> customers = new ArrayList<Customer>();

		try{
			String query = buildQuery(wQuery);
			Statement stmt = con.createStatement();
			stmt.setQueryTimeout(5);
			ResultSet rs = stmt.executeQuery(query);

			while(rs.next()){
				Customer customer = buildCustomer(rs);
				if(customer != null) {
					customers.add(customer);

					if(retAsso) {
						customer.setCars(getCars(customer));
					}
				}
			}
			stmt.close();

		}catch(Exception e){
			System.out.println("DBCustomer - miscWhere - Exception");
			e.printStackTrace();
		}
		return customers;
	}

	/**
	 * @param customer
	 * @return
	 */
	private ArrayList<Car> getCars(Customer customer) {
		IFDBCar dbCar = new DBCar();
		ArrayList<Car> cars = dbCar.getCars(customer, false);

		for(Car c : cars){
			c.setOwner(customer);
		}

		return cars;
	}

	private Customer singleWhere(String wQuery, boolean retAsso) {
		Customer customer = null;

		try {
			String query = buildQuery(wQuery);
			Statement stmt = con.createStatement();
			stmt.setQueryTimeout(5);
			ResultSet rs = stmt.executeQuery(query);
			if (rs.next()) {
				customer = buildCustomer(rs);
				stmt.close();

				if(retAsso) {
					customer.setCars(getCars(customer));
				}
			}
		} catch (Exception e) { 
			System.out.println("DBCustomer - singleWhere - Exception");
			e.printStackTrace();
		}		

		return customer; 
	}

	@Override
	public Customer getCustomerByRegNr(String regNr) throws ObjectNotExistException {
		IFDBCar dbCar = new DBCar();
		Customer customer = null;
		
		try {
			Car car = dbCar.getCarByRegNr(regNr, false);
			customer = car.getOwner();
		} catch (NullPointerException e) {
			throw new ObjectNotExistException("Ingen kunde fundet");
		}
		
		return customer;
	}
}
