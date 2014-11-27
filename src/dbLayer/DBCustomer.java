package dbLayer;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

import com.microsoft.sqlserver.jdbc.SQLServerException;

import modelLayer.Car;
import modelLayer.Customer;
import modelLayer.Product;
import modelLayer.UnitType;
import dbLayer.exceptions.DBException;
import dbLayer.interfaceLayer.IFDBCar;
import dbLayer.interfaceLayer.IFDBCustomer;
import dbLayer.interfaceLayer.IFDBPostalcode;
import dbLayer.interfaceLayer.IFDBUnitType;

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
	public int insertCustomer(Customer customer) {
		//TODO
		int rc = -1;
		final String fields = "(name, phoneNumber, address, postalCode, cvr, hidden)";
		String query = "INSERT INTO CUSTOMER " + fields + " VALUES (?,?,?,?,?,?)";
		
		try {
			IFDBPostalcode dbPost = new DBPostalCode();
			int postResult = dbPost.insertPostalCode(customer.getPostalCode(), customer.getCity());
			if(postResult != 1) {
				throw new DBException("");
			}
			PreparedStatement stmt = con.prepareStatement(query);
			stmt.setQueryTimeout(5);
			updateFields(customer, stmt);
			rc = stmt.executeUpdate();
			stmt.close();
		}
		catch(Exception e) {
			e.printStackTrace();
			System.out.println("Error - insertCustomer - DBCustomer");
		}
		return rc;
	}

	@Override
	public int updateCustomer(Customer customer) {
		int rc = -1;
		final String allFields = 
				"name = ?, " + 
						"phoneNumber = ?, " + 
						"address = ?, " + 
						"postalCode = ?, " + 
						"cvr = ?, " + 
						"hidden = ? "; 
		String query = "UPDATE CUSTOMER SET " + allFields + "WHERE customerID = ?" 	;

		try {
			PreparedStatement stmt = con.prepareStatement(query);
			stmt.setQueryTimeout(5);
			updateFields(customer, stmt);
			rc = stmt.executeUpdate();
			stmt.close();
		}
		catch(Exception e) {
			e.printStackTrace();
			System.out.println("Error - updateCustomer - DBCustomer");
		}
		return rc;
	}

	/**
	 * @param stmt
	 * @throws SQLException 
	 */
	private void updateFields(Customer customer, PreparedStatement stmt) { 
		//TODO -- City??
		try {
			stmt.setString(1, customer.getName());
			stmt.setString(2, customer.getPhoneNumber());
			stmt.setString(3, customer.getAddress());
			stmt.setInt(4, customer.getPostalCode());
			stmt.setInt(5, customer.getCvr());
			stmt.setBoolean(6, customer.getHidden());
		} catch (Exception e) {
			System.out.println("Error - updateFields - DBCustomer");
			e.printStackTrace();
		}
	}

	@Override
	public int deleteCustomer(Customer customer) {
		int rc = -1;
		String query = "DELETE FROM CUSTOMER WHERE CUSTOMERID = " + customer.getId();

		try {
			Statement stmt = con.createStatement();
			stmt.setQueryTimeout(5);
			rc = stmt.executeUpdate(query);
			stmt.close();
			DBConnection.commitTransaction();
			//TODO Delete if possible, else set hidden
		}
		catch(Exception e) {
			e.printStackTrace();
			System.out.println("Error - deleteCustomer - DBCustomer");
		}
		return rc;
	}

	private String buildQuery(String wQuery) {
		// city , id
		final String allFields = "CustomerID, name, phoneNumber, address, postalCode, cvr, hidden";
		String query = "SELECT " + allFields + " FROM Customer";

		if(!wQuery.isEmpty()){
			query += " WHERE " + wQuery;
		}

		System.out.println("DBCustomer - buildQuery: " + query); // TODO Delete when done testing

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
			System.out.println(query);
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
	public Customer getCustomerByRegNr(String regNr) {
		IFDBCar dbCar = new DBCar();
		Car car = dbCar.getCarByRegNr(regNr, false);
		return car.getOwner();
	}
}
