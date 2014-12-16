package ctrLayer.interfaceLayer;

import java.util.ArrayList;

import modelLayer.Product;
import modelLayer.UnitType;
import exceptions.DBException;
import exceptions.ObjectNotExistException;

/**
 * Interface for ProductCtr
 *
 * @author Group 3, dmaa0214, UCN
 *
 */
public interface IFProductCtr {
	
	public Product getProductByID(int id) throws ObjectNotExistException;
	
	/**
	 * Search products by name
	 * @param name the name to look for
	 * @return list of found products
	 * @throws ObjectNotExistException if no products found
	 */
	public ArrayList<Product> searchProductsByName(String name) throws ObjectNotExistException;
	
	public ArrayList<Product> searchProductsByItemNumber(String itemNumber) throws ObjectNotExistException;
	
	public Product createProduct(String brand, String name, String description,
			String itemNumber, double price, UnitType unitType)
			throws DBException;
	
	public ArrayList<UnitType> getAllUnitTypes();
	
	public void updateProduct(Product product, String brand, String name, String description,
			String itemNumber, double price, UnitType unitType) throws DBException, ObjectNotExistException;
	
	public void deleteProduct(Product product) throws DBException, ObjectNotExistException; 

}
