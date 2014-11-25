package ctrLayer.interfaceLayer;

import java.util.ArrayList;

import ctrLayer.exceptionLayer.ProductDoesntExistException;
import modelLayer.Product;
import modelLayer.UnitType;

/**
 * Class for IFProductCtr
 *
 * @author Group 3, dmaa0214, UCN
 *
 */
public interface IFProductCtr {
	
	public Product getProductByID(int id) throws ProductDoesntExistException;
	
	public ArrayList<Product> searchProductsByName(String name);
	
	public ArrayList<Product> searchProductsByItemNumber(String itemNumber);
	
	public Product createProduct(String brand, String name, String description,
			String itemNumber, double price, boolean hidden, UnitType unitType);
	
	public ArrayList<UnitType> getAllUnitTypes();
	
	public void updateProduct(Product product);
	
	public void deleteProduct(Product product); 

}
