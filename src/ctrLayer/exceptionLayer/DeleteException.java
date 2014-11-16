package ctrLayer.exceptionLayer;

/**
 * Class for DeleteException
 *
 * @author Group 3, dmaa0214, UCN
 *
 */
public class DeleteException extends Exception {

	private static final long serialVersionUID = 1L;
	
	/**
	 * Constructor for DeleteException.java objects.
	 * @param exists 
	 *
	 * @param string
	 */
	public DeleteException(String obj, boolean exists) {
		super(obj + getError(exists));	
	}
	
	public DeleteException(String obj, int rc) {
		super(obj + getError(rc));
	}
	
	private static String getError(int rc) {
		String retString = " kunne ikke slettes fra databasen";
		if(rc == -1){
			retString = " eksisterer ikke ikke i databasen";	
		}
		return retString;
	}
	
	private static String getError(boolean exists){
		String retString = " kunne ikke fjernes";
		
		if(!exists){
			retString = " eksistere ikke i databasen";
		}
		return retString;
	}
	
}
