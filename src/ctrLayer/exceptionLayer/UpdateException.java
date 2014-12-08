package ctrLayer.exceptionLayer;

/**
 * Class for UpdateException
 *
 * @author Group 3, dmaa0214, UCN
 *
 */
public class UpdateException extends Exception {

	private static final long serialVersionUID = 1L;
	
	/**
	 * Constructor for UpdateException objects.
	 *
	 * @param string
	 */
	public UpdateException(String obj, boolean exists) {
		super(obj + getError(exists));	
	}
	
	public UpdateException(String obj, int rc) { // NO_UCD (unused code)
		super(obj + getError(rc));
	}
	
	private static String getError(int rc) {
		String retString = " kunne ikke ændres i databasen";
		if(rc == -1){
			retString = " eksisterer ikke ikke i databasen";	
		}
		return retString;
	}

	private static String getError(boolean exists){
		String retString = " kunne ikke ændres";
		
		if(!exists){
			retString = " eksisterer ikke ikke i databasen";
		}
		return retString;
	}
	
}
