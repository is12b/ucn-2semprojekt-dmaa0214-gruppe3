package ctrLayer.interfaceLayer;

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
	
	public void addCar(String regNr);
	
	public Product searchProduct(String itemNumber);
	
	public void createPartSale(Product product, double amount);

}
