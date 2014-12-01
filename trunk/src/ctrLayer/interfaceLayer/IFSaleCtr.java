package ctrLayer.interfaceLayer;

import java.util.ArrayList;

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
	
	public ArrayList<Product> searchProductsByName(String name) throws NullPointerException;
	
	public ArrayList<Product> searchProductsByItemNumber(String itemNumber) throws NullPointerException;
	
	public void createPartSale(Product product, double amount, double unitPrice);
	
	/**
	 * @param cvr
	 * @return
	 */
	public Customer getCustomerByCvr(String cvr, boolean retAsso) throws NullPointerException;

	/**
	 * @param name
	 * @return
	 */
	public ArrayList<Customer> searchCustomersByName(String name, boolean retAsso) throws NullPointerException;

	/**
	 * @param phone
	 * @return
	 */
	public ArrayList<Customer> searchCustomersByPhone(String phone, boolean retAsso) throws NullPointerException;

	/**
	 * @param regNr
	 * @return
	 */
	
	public Car getCarByRegNr(String regNr, boolean retAsso) throws NullPointerException;
	
	public Car getCarByVin(String vin, boolean retAsso) throws NullPointerException;


}
