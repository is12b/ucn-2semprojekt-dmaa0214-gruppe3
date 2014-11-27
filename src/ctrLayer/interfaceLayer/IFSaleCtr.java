package ctrLayer.interfaceLayer;

import java.util.ArrayList;

import ctrLayer.exceptionLayer.CarDoesntExistException;
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
	
	public void addCar(Car car);
	
	public ArrayList<Product> searchProductsByName(String name);
	
	public ArrayList<Product> searchProductsByItemNumber(String itemNumber);
	
	public void createPartSale(Product product, double amount);
	
	/**
	 * @param cvr
	 * @return
	 */
	public Customer getCustomerByCvr(String cvr, boolean retAsso);

	/**
	 * @param name
	 * @return
	 */
	public ArrayList<Customer> searchCustomersByName(String name, boolean retAsso);

	/**
	 * @param phone
	 * @return
	 */
	public ArrayList<Customer> searchCustomersByPhone(String phone, boolean retAsso);

	/**
	 * @param regNr
	 * @return
	 */
	
	public Car getCarByRegNr(String regNr, boolean retAsso) throws NullPointerException;
	
	public Car getCarByVin(String vin, boolean retAsso) throws NullPointerException;

}
