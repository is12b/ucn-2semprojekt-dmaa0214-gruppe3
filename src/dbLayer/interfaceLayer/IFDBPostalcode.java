package dbLayer.interfaceLayer;

/**
 * Interface for DBPostalcode
 *
 * @author Group 3, dmaa0214, UCN
 *
 */
public interface IFDBPostalcode {

	public String getCity(int postCode);
	
	public int insertPostalCode(int postCode, String city);
	
	//public int deletePostalCode(int postCode);
}
