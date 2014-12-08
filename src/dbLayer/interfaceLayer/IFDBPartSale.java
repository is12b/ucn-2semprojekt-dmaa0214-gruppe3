package dbLayer.interfaceLayer;

import java.util.ArrayList;

import dbLayer.exceptions.DBException;
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
	 * @param retAsso If true: include associations to other objects
	 * @return the part sales
	 */
	public ArrayList<PartSale> getPartSales(Sale sale, boolean retAsso);
	
	/**
	 * Insert a PartSale for a Sale into the database.
	 *
	 * @param sale the Sale, who owns the partSale
	 * @param partSale the partSale to add.  
	 * @return numbers of affected rows or -1 if it's fail
	 */
	public int insertPartSale(Sale sale, PartSale partSale) throws DBException;
	
	/**
	 * Update PartSale in the database.
	 *
	 * @param partSale the PartSale
	 * @return numbers of affected rows or -1 if it's fail
	 */
	public int updatePartSale(PartSale partSale) throws DBException; // NO_UCD (unused code)
	
	/**
	 * Delete PartSale from the database..
	 *
	 * @param partSale the PartSale
	 * @return numbers of affected rows or -1 if it's fail
	 */
	public int deletePartSale(PartSale partSale) throws DBException; // NO_UCD (unused code)

}
