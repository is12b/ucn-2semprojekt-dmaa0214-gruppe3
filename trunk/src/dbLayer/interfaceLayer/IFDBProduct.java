package dbLayer.interfaceLayer;

import java.util.ArrayList;

import modelLayer.Product;

/**
 * Class for IFDBProduct.
 *
 * @author Group 3, dmaa0214, UCN
 */
public interface IFDBProduct {
	
	/**
	 * Search products by item number.
	 *
	 * @param ItemNumber the item number
	 * @return the array list
	 */
	public ArrayList<Product> searchProductsByItemNumber(String ItemNumber);
	
	/**
	 * Search products by name.
	 *
	 * @param name the name
	 * @return the array list
	 */
	public ArrayList<Product> searchProductsByName(String name);
	
	/**
	 * Gets the product by id.
	 *
	 * @param id the id
	 * @return the product by id
	 */
	public Product getProductByID(int id);
	
	/**
	 * Insert product into the database.
	 *
	 * @param product the product
	 * @return numbers of affected rows or -1 if it's fail
	 */	
	public int insertProduct(Product product);
	
	/**
	 * Update product in the database.
	 *
	 * @param product the product
	 * @return numbers of affected rows or -1 if it's fail
	 */
	public int updateProduct(Product product);
	
	/**
	 * Delete product from the database..
	 *
	 * @param product the product
	 * @return numbers of affected rows or -1 if it's fail
	 */
	public int deleteProduct(Product product);
}
