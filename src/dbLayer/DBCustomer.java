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
import modelLayer.Postalcode;
import modelLayer.Product;
import modelLayer.UnitType;
import dbLayer.interfaceLayer.IFDBCar;
import dbLayer.interfaceLayer.IFDBCustomer;
import dbLayer.interfaceLayer.IFDBUnitType;

/**
 * Class for DBCustomerProto
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
	public ArrayList<Customer> getCustomersByPhone(String phoneNumber) {
		return miscWhere("phoneNumber = '" + phoneNumber + "'", false);
	}

	@Override
	public ArrayList<Customer> getCustomersByName(String name) {
		return miscWhere("name LIKE '%" + name + "%'", false);
	}

	@Override
	public Customer getCustomerByCvr(String cvr) {
		return singleWhere("cvr = " + cvr, false);
	}

	@Override
	public int insertCustomer(Customer Customer) {
		// TODO Auto-generated method stub
		final String fields = "(name, phoneNumber, address, postalCode, cvr, hidden)";
		String query = "INSERT INTO CUSTOMER " + fields + " VALUES (?,?,?,?,?,?)";
		return 0;
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
			System.out.println("Error - updateCustomer - DBCustomerProto");
		}
		return rc;
	}

	/**
	 * @param stmt
	 * @throws SQLException 
	 */
	private void updateFields(Customer customer, PreparedStatement stmt) { 
		//TODO
		try {
			stmt.setString(1, customer.getName());
			stmt.setString(2, customer.getPhoneNumber());
			stmt.setString(3, customer.getAddress());
			stmt.setInt(4, customer.getPostalCode());
			stmt.setInt(5, customer.getCvr());
			stmt.setBoolean(6, customer.getHidden());
		} catch (Exception e) {
			System.out.println("Error - updateFields - DBCustomerProto");
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
			System.out.println("Error - deleteCustomer - DBCustomerProto");
		}
		return rc;
	}

	private String buildQuery(String wQuery) {
		// city , id
		final String allFields = "name, phoneNumber, address, postalCode, cvr, hidden";
		String query = "SELECT " + allFields + " FROM Customer";

		if(!wQuery.isEmpty()){
			query += " WHERE " + wQuery;
		}

		System.out.println("DBCustomerProto - buildQuery: " + query); // TODO Delete when done testing

		return query;
	}

	private Customer buildCustomer(ResultSet rs) {
		Customer customer = new Customer();

		try{
			customer.setName(rs.getString("name"));
			customer.setPhoneNumber(rs.getString("phoneNumber"));
			customer.setAddress(rs.getString("address"));
			customer.setCvr(rs.getInt("cvr"));
			customer.setHidden(rs.getBoolean("hidden"));
			DBPostalCode dbPost = new DBPostalCode();
			String city = dbPost.getCity(rs.getInt("postalCode"));
			customer.setPostalCode(rs.getInt("postalCode")); //TODO Korrekt?
			//customer.setCity(rs.getString(city)); //TODO
		}catch(Exception e){
			System.out.println("DBCustomerProto - buildCustomer - Exception");
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
						IFDBCar dbCar = new DBCar();
						customer.setCars(dbCar.getCars(customer, false));
					}
				}
			}
			stmt.close();

		}catch(Exception e){
			System.out.println("DBCustomerProto - miscWhere - Exception");
			e.printStackTrace();
		}
		return customers;
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
					IFDBCar dbCar = new DBCar();
					customer.setCars(dbCar.getCars(customer, false));
				}
			}
		} catch (Exception e) {
			System.out.println("DBCustomerProto - singleWhere - Exception");
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
