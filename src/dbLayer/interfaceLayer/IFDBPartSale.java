package dbLayer.interfaceLayer;

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
	
	/**
	 * Insert PartSale into the database.
	 *
	 * @param partSale the PartSale
	 * @return numbers of affected rows or -1 if it's fail
	 */
	public int insertPartSale(PartSale partSale);
	
	/**
	 * Update PartSale in the database.
	 *
	 * @param partSale the PartSale
	 * @return numbers of affected rows or -1 if it's fail
	 */
	public int updatePartSale(PartSale partSale);
	
	/**
	 * Delete PartSale from the database..
	 *
	 * @param partSale the PartSale
	 * @return numbers of affected rows or -1 if it's fail
	 */
	public int deletePartSale(PartSale partSale);
}
