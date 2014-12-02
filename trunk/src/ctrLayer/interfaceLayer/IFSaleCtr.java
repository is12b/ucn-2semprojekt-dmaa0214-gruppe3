package ctrLayer.interfaceLayer;

import guiLayer.exceptions.SubmitException;

import java.util.ArrayList;

import ctrLayer.exceptionLayer.ObjectNotExistException;
import modelLayer.Car;
import modelLayer.Customer;
import modelLayer.Product;
import modelLayer.Sale;

/**
 * Class for IFSaleCtr
 *
 * @author Group 3, dmaa0214, UCN
 *
 */
public interface IFSaleCtr {
	
	public Sale createSale();
	
	public void setCar(Car car);
	
	public void setCustomer(Customer customer);
	
	public ArrayList<Product> searchProductsByName(String name) throws ObjectNotExistException;
	
	public ArrayList<Product> searchProductsByItemNumber(String itemNumber) throws ObjectNotExistException;
	
	public void createPartSale(Product product, double amount, double unitPrice);
	
	/**
	 * @param cvr
	 * @return
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
	 * @throws ObjectNotExistException 
	 */
	
	public Car getCarByRegNr(String regNr, boolean retAsso) throws ObjectNotExistException;
	
	public Car getCarByVin(String vin, boolean retAsso) throws ObjectNotExistException;
	
	public void addDescription(String desc);
	
	public String getDescription();
	
	public void setPaid(boolean paid);
	
	public void commit() throws SubmitException;

	/**
	 * @param mileage
	 */
	public void addMileage(int mileage);

	/**
	 * @return
	 */
	public int getMileage();


}
