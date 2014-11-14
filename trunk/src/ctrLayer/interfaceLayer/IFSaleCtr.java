package ctrLayer.interfaceLayer;

import java.util.ArrayList;

import ctrLayer.exceptionLayer.CarDoesntExistException;
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
	
	public void addCar(String regNr) throws CarDoesntExistException;
	
	public ArrayList<Product> searchProductsByItemNumber(String itemNumber);
	
	public void createPartSale(Product product, double amount);

}
