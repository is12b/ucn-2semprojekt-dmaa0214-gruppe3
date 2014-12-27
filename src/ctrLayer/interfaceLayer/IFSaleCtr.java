package ctrLayer.interfaceLayer;

import exceptions.DBException;
import exceptions.EmailException;
import exceptions.ObjectNotExistException;
import exceptions.SubmitException;

import java.util.ArrayList;

import modelLayer.Car;
import modelLayer.Customer;
import modelLayer.PartSale;
import modelLayer.Product;
import modelLayer.Sale;

/**
 * Interface for IFSaleCtr.
 *
 * @author Group 3, dmaa0214, UCN
 */
public interface IFSaleCtr {
	
	
	/**
	 * Create a sale object.
	 * @return the sale created
	 */
	public Sale createSale();
	
	/**
	 * Sets the car object on the sale.
	 * @param car the new car
	 */
	public void setCar(Car car);
	
	/**
	 * Sets the customer object on the sale.
	 *
	 * @param customer the new customer
	 */
	public void setCustomer(Customer customer);
	
	/**
	 * Search products by name.
	 *
	 * @param name the name
	 * @return list of found products
	 * @throws ObjectNotExistException if no products found
	 */
	public ArrayList<Product> searchProductsByName(String name) throws ObjectNotExistException;
	
	/**
	 * Search products by item number.
	 *
	 * @param itemNumber the item number
	 * @return list of found products
	 * @throws ObjectNotExistException if no products found
	 */
	public ArrayList<Product> searchProductsByItemNumber(String itemNumber) throws ObjectNotExistException;
	
	/**
	 * Create a partSale object on the sale.
	 *
	 * @param product the product to add
	 * @param amount the amount
	 * @param unitPrice the unit price
	 */
	public void createPartSale(Product product, double amount, double unitPrice);
	
	/**
	 * Remove a partSale object from the sale.
	 *
	 * @param pSale the sale
	 */
	public void removePartSale(PartSale pSale);
	
	/**
	 * Gets a customer object by cvr.
	 *
	 * @param cvr the cvr to look for
	 * @return the found Customer object
	 * @throws ObjectNotExistException If no Customer is found
	 */
	public Customer getCustomerByCvr(String cvr) throws ObjectNotExistException;

	/**
	 * Search customers by name.
	 *
	 * @param name the name to look for
	 * @return list of found customers
	 * @throws ObjectNotExistException If no Customers is found
	 */
	public ArrayList<Customer> searchCustomersByName(String name) throws ObjectNotExistException;

	/**
	 * Search customers by phone.
	 *
	 * @param phone the phone to look for
	 * @return list of found customers
	 * @throws ObjectNotExistException If no Customer is found
	 */
	public ArrayList<Customer> searchCustomersByPhone(String phone) throws ObjectNotExistException;

	/**
	 * Get a car object by reg nr.
	 *
	 * @param regNr the reg nr to look for
	 * @return the found car
	 * @throws ObjectNotExistException If no Car is found
	 */
	
	public Car getCarByRegNr(String regNr) throws ObjectNotExistException;
	
	/**
	 * Gets the car by vin.
	 *
	 * @param vin the vin to look for
	 * @return the found car
	 * @throws ObjectNotExistException If no Car is found
	 */
	public Car getCarByVin(String vin) throws ObjectNotExistException;
	
	/**
	 * Adds a description.
	 *
	 * @param desc the desc
	 */
	public void addDescription(String desc);
	
	/**
	 * Gets the description.
	 *
	 * @return the description
	 */
	public String getDescription();
		
	/**
	 * Commit a sale.
	 *
	 * @param paid if a sale is paid
	 * @return the sale object
	 * @throws SubmitException wrong input submitted by user
	 * @throws DBException If a exception happen in DBLayer
	 */
	public Sale commit(boolean paid) throws SubmitException, DBException;

	/**
	 * Adds the mileage.
	 *
	 * @param mileage the mileage
	 */
	public void addMileage(int mileage);

	/**
	 * Gets the mileage.
	 *
	 * @return the mileage
	 */
	public int getMileage();

	public Sale getSaleByID(int id) throws ObjectNotExistException;
	
	public ArrayList<Sale> getAllSales() throws ObjectNotExistException;

	public ArrayList<Sale> getSaleByCarRegNr(String regNr) throws ObjectNotExistException;

	public ArrayList<Sale> getSaleByCarVIN(String vin) throws ObjectNotExistException;

	public ArrayList<Sale> getSaleByCusName(String name) throws ObjectNotExistException;

	public ArrayList<Sale> getSaleByCusPhone(String phone) throws ObjectNotExistException;

	public ArrayList<Sale> getSaleByCusCVR(String cvr) throws ObjectNotExistException;
	
	// Iteration 2
	
	public void sendEmailWithInvoice(Sale sale) throws ObjectNotExistException, EmailException;
}
