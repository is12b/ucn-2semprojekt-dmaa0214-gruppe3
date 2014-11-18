package dbLayer;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;

import com.microsoft.sqlserver.jdbc.SQLServerException;

import modelLayer.Car;
import modelLayer.Customer;
import modelLayer.Postalcode;
import modelLayer.Product;
import modelLayer.UnitType;
import dbLayer.interfaceLayer.IFDBCustomer;
import dbLayer.interfaceLayer.IFDBUnitType;

/**
 * Class for DBCustomerProto
 *
 * @author Group 3, dmaa0214, UCN
 *
 */
public class DBCustomerProto implements IFDBCustomer {

	private Connection con;

	public DBCustomerProto() {
		con = DBConnection.getInstance().getDBCon();
	}

	@Override
	public Customer getCustomer(Car car) {
		return car.getOwner();
	}

	@Override
	public Customer getCustomerByID(int id, boolean retAsso) {
		return singleWhere("customerID = " + id, retAsso); //TODO MANGLER ASSOCIATION
	}

	@Override
	public ArrayList<Customer> getCustomersByPhone(String phoneNumber) {
		return miscWhere("phoneNumber = '" + phoneNumber + "'");
	}

	@Override
	public ArrayList<Customer> getCustomersByName(String name) {
		return miscWhere("name LIKE '%" + name + "%'");
	}

	@Override
	public Customer getCustomerByCvr(String cvr) {
		return singleWhere("cvr = " + cvr, false);
	}

	@Override
	public int insertCustomer(Customer Customer) {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public int updateCustomer(Customer customer) {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public int deleteCustomer(Customer customer) {
		// TODO Auto-generated method stub
		return 0;
	}

	private String buildQuery(String wQuery) {
		final String allFields = "name, phoneNumber, address, city, postalCode, cvr, id, hidden";
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
			customer.setPostalCode(rs.getInt("postCode")); //TODO Korrekt?
			customer.setCity(rs.getString("city")); // TODO Korrekt?
		}catch(Exception e){
			System.out.println("DBCustomerProto - buildCustomer - Exception");
			e.printStackTrace();
		}

		return customer;
	}

	private ArrayList<Customer> miscWhere(String wQuery) {
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
					//TODO retAsso					
				}
			}
		} catch (Exception e) {
			System.out.println("DBCustomerProto - singleWhere - Exception");
			e.printStackTrace();
		}		

		return customer; 
	}

}
