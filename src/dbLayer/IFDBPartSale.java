package dbLayer;

import java.util.ArrayList;

import modelLayer.PartSale;
import modelLayer.Sale;

/**
 * Class for IFDBPartSale.
 *
 * @author Group 3, dmaa0214, UCN
 */
public interface IFDBPartSale {
	
	/**
	 * Gets the part sales.
	 *
	 * @param sale the sale
	 * @return the part sales
	 */
	public ArrayList<PartSale> getPartSales(Sale sale);
}
