package ctrLayer.interfaceLayer;

import exceptions.ObjectNotExistException;
import exceptions.SubmitException;

import java.util.ArrayList;

import modelLayer.Car;
import modelLayer.Customer;
import modelLayer.PartSale;
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
	
	public void removePartSale(PartSale pSale);
	
	/**
	 * @param cvr
	 * @return
	 */
	public Customer getCustomerByCvr(String cvr) throws ObjectNotExistException;

	/**
	 * @param name
	 * @return
	 */
	public ArrayList<Customer> searchCustomersByName(String name) throws ObjectNotExistException;

	/**
	 * @param phone
	 * @return
	 */
	public ArrayList<Customer> searchCustomersByPhone(String phone) throws ObjectNotExistException;

	/**
	 * @param regNr
	 * @return
	 * @throws ObjectNotExistException 
	 */
	
	public Car getCarByRegNr(String regNr) throws ObjectNotExistException;
	
	public Car getCarByVin(String vin) throws ObjectNotExistException;
	
	public void addDescription(String desc);
	
	public String getDescription();
		
	public Sale commit(boolean paid) throws SubmitException;

	/**
	 * @param mileage
	 */
	public void addMileage(int mileage);

	/**
	 * @return
	 */
	public int getMileage();

	
	public Sale getSaleByID(int id) throws ObjectNotExistException;
	
	public ArrayList<Sale> getAllSales() throws ObjectNotExistException;
	
	public ArrayList<Sale> getSaleByCarRegNr(String regNr) throws ObjectNotExistException;
	
	public ArrayList<Sale> getSaleByCarVIN(String vin) throws ObjectNotExistException;
	
	public ArrayList<Sale> getSaleByCusName(String name) throws ObjectNotExistException;
	
	public ArrayList<Sale> getSaleByCusPhone(String phone) throws ObjectNotExistException;
	
	public ArrayList<Sale> getSaleByCusCVR(String cvr) throws ObjectNotExistException;
	
	
}
