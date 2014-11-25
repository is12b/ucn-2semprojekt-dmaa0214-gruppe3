package ctrLayer.interfaceLayer;

import java.util.ArrayList;

import ctrLayer.exceptionLayer.PriceFormatException;
import dbLayer.exceptions.DBException;
import modelLayer.Product;
import modelLayer.UnitType;

/**
 * Class for IFProductCtr
 *
 * @author Group 3, dmaa0214, UCN
 *
 */
public interface IFProductCtr {
	
	public Product getProductByID(int id);
	
	public ArrayList<Product> searchProductsByName(String name);
	
	public ArrayList<Product> searchProductsByItemNumber(String itemNumber);
	
	public Product createProduct(String brand, String name, String description,
			String itemNumber, double price, UnitType unitType)
			throws DBException, PriceFormatException;
	
	public ArrayList<UnitType> getAllUnitTypes();
	
	public void updateProduct(Product product, String brand, String name, String description,
			String itemNumber, double price, UnitType unitType) throws DBException, PriceFormatException, NullPointerException;
	
	public void deleteProduct(Product product) throws DBException, NullPointerException; 

}
