package ctrLayer.interfaceLayer;

import java.util.ArrayList;

import modelLayer.Car;
import modelLayer.Customer;
import exceptions.DBException;
import exceptions.ObjectNotExistException;
import exceptions.SubmitException;

/**
 * Class for IFCustomerCtr
 *
 * @author Group 3, dmaa0214, UCN
 *
 */
public interface IFCustomerCtr {

	public Customer updateCustomer(Customer customer, String name, String phoneNumber, String address, String city,  int postalCode, int cvr, String email, boolean hidden) throws ObjectNotExistException, DBException;
	
	public void deleteCustomer(Customer customer) throws ObjectNotExistException, DBException; // NO_UCD (unused code)

	/**
	 * @param cvr
	 * @return
	 * @throws ObjectNotExistException 
	 */
	public Customer getCustomerByCvr(String cvr, boolean retAsso) throws ObjectNotExistException;

	/**
	 * @param name
	 * @return
	 */
	public ArrayList<Customer> searchCustomersByName(String name, boolean retAsso) throws ObjectNotExistException;

	/**
	 * @param phone
	 * @return
	 */
	public ArrayList<Customer> searchCustomersByPhone(String phone, boolean retAsso) throws ObjectNotExistException;

	/**
	 * @param regNr
	 * @return
	 */
	public Customer getCustomerByRegNr(String regNr) throws ObjectNotExistException;

	// Iteration 2
	/**
	 * @param name
	 * @param phoneNumber
	 * @param address
	 * @param postalCode
	 * @param city
	 * @param cvr
	 * @param hidden
	 * @return
	 * @throws DBException
	 */
	public Customer createCustomer(String name, String phoneNumber,
			String address, int postalCode, String city, int cvr, String email,
			boolean hidden, Car car, String carRegNr, String carVIN,
			String carBrand, String carModel, int carMileage, int carYear)
			throws SubmitException, DBException;
	
	
	/**
	 * Method for get a new car object with all scraped data.
	 * 
	 * @param regOrVin the regNr or vin number of the car
	 * @return a car object
	 */
	public Car getCarData(String regOrVin) throws ObjectNotExistException;
	
}
