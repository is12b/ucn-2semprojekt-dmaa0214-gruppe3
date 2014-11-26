package dbLayer.interfaceLayer;

import java.util.ArrayList;

import modelLayer.Car;
import modelLayer.Customer;

/**
 * Class for IFDBCustomer.
 *
 * @author Group 3, dmaa0214, UCN
 */
public interface IFDBCustomer {

	/**
	 * Gets a customer.
	 *
	 * @param car the car
	 * @return the customer
	 */
	public Customer getCustomerByCar(Car car);
	
	/**
	 * Gets a customer by id.
	 *
	 * @param id the id of the car
	 * @param retAsso If true: include associations to other objects
	 * @return the customer
	 */
	public Customer getCustomerByID(int id, boolean retAsso);
	
	/**
	 * Gets all customers by phoneNumber.
	 *
	 * @param phoneNumber the phone number
	 * @return the customers by phoneNumber
	 */
	public ArrayList<Customer> getCustomersByPhone(String phoneNumber);
	
	/**
	 * Gets the customers by name.
	 *
	 * @param name the name
	 * @return the customers by name
	 */
	public ArrayList<Customer> getCustomersByName(String name);
	
	/**
	 * Gets the customer.
	 *
	 * @param cvr the cvr
	 * @return the customer
	 */
	public Customer getCustomerByCvr(String cvr);
	
	/**
	 * Insert Customer into the database.
	 *
	 * @param customer the Customer
	 * @return numbers of affected rows or -1 if it's fail
	 */
	public int insertCustomer(Customer customer);
	
	/**
	 * Update Customer in the database.
	 *
	 * @param customer the Customer
	 * @return numbers of affected rows or -1 if it's fail
	 */
	public int updateCustomer(Customer customer);
	
	/**
	 * Delete Customer from the database..
	 *
	 * @param customer the Customer
	 * @return numbers of affected rows or -1 if it's fail
	 */
	public int deleteCustomer(Customer customer);
	
}
