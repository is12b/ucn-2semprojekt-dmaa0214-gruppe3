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
	 * @return the array list
	 */
	public ArrayList<Product> searchProducts(String ItemNumber);
}
