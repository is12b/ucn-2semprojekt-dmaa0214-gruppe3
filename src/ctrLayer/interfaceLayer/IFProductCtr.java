package ctrLayer.interfaceLayer;

import java.util.ArrayList;

import modelLayer.Product;

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
	
	public Product createProduct(String brand, String name); //TODO
	
	public void updateProduct(Product product);
	
	public void deleteProduct(Product product); 

}
