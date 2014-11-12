package dbLayer;

import java.util.ArrayList;

import modelLayer.Car;
import modelLayer.Customer;
import modelLayer.Sale;

/**
 * Class for IFDBSale.
 *
 * @author Group 3, dmaa0214, UCN
 */
public interface IFDBSale {
	
	/**
	 * Gets the sales.
	 *
	 * @param car the car
	 * @return the sales
	 */
	public ArrayList<Sale> getSales(Car car);
	
	/**
	 * Gets the sales.
	 *
	 * @param customer the customer
	 * @return the sales
	 */
	public ArrayList<Sale> getSales(Customer customer);
	
	/**
	 * Gets the sale.
	 *
	 * @param id the id
	 * @return the sale
	 */
	public Sale getSale(int id);
	
	/**
	 * Gets the all sales.
	 *
	 * @return the all sales
	 */
	public ArrayList<Sale> getAllSales();
}
