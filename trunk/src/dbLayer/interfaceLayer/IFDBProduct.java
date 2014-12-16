package dbLayer.interfaceLayer;

import java.util.ArrayList;

import modelLayer.Product;
import exceptions.DBException;

/**
 * Interface for DBProduct.
 *
 * @author Group 3, dmaa0214, UCN
 */
public interface IFDBProduct {
	
	/**
	 * Search products by item number.
	 *
	 * @param ItemNumber the item number
	 * @return a list of products
	 */
	public ArrayList<Product> searchProductsByItemNumber(String ItemNumber);
	
	/**
	 * Search products by name.
	 *
	 * @param name the name
	 * @return a list of products
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
	 * @throws {@link DBException} If there is an error in inserting
	 */	
	public int insertProduct(Product product) throws DBException;
	
	/**
	 * Update product in the database.
	 *
	 * @param product the product
	 * @return numbers of affected rows or -1 if it's fail
	 * @throws {@link DBException} If there is an error in updatering or none is updated
	 */
	public int updateProduct(Product product) throws DBException;
	
	/**
	 * Delete product from the database..
	 *
	 * @param product the product
	 * @return numbers of affected rows or -1 if it's fail
	 * @throws {@link DBException} If there is an error in deleting or none is deleted
	 */
	public int deleteProduct(Product product) throws DBException;
}
