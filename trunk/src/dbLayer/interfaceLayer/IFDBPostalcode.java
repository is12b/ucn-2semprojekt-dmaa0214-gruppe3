package dbLayer.interfaceLayer;

import dbLayer.exceptions.DBException;

/**
 * Interface for DBPostalcode
 *
 * @author Group 3, dmaa0214, UCN
 *
 */
public interface IFDBPostalcode {

	public String getCity(int postCode);
	
	public int insertPostalCode(int postCode, String city) throws DBException;
	
	//public int deletePostalCode(int postCode);
}
