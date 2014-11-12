package dbLayer;

import java.util.ArrayList;

import modelLayer.Product;

/**
 * Class for IFDBProduct.
 *
 * @author Group 3, dmaa0214, UCN
 */
public interface IFDBProduct {
	
	/**
	 * Search products.
	 *
	 * @param ItemNumber the item number
	 * @return List of found products
	 */
	public ArrayList<Product> searchProducts(String ItemNumber);
	
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
