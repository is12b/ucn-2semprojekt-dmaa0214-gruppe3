package ctrLayer.exceptionLayer;

/**
 * Class for UpdateException
 *
 * @author Group 3, dmaa0214, UCN
 *
 */
public class UpdateException extends Exception {

	/**
	 * Constructor for UpdateException.java objects.
	 *
	 * @param string
	 */
	public UpdateException(String obj, boolean exists) {
		super(obj + getError(exists));	
	}
	
	private static String getError(boolean exists){
		String retString = " kunne ikke ændres";
		
		if(!exists){
			retString = " eksistere ikke";
		}
		return retString;
	}
	
}
