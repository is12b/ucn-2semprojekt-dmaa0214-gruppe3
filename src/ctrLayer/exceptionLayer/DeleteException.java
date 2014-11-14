package ctrLayer.exceptionLayer;

/**
 * Class for DeleteException
 *
 * @author Group 3, dmaa0214, UCN
 *
 */
public class DeleteException extends Exception {

	/**
	 * Constructor for DeleteException.java objects.
	 * @param exists 
	 *
	 * @param string
	 */
	public DeleteException(String obj, boolean exists) {
		super(obj + getError(exists));	
	}
	
	private static String getError(boolean exists){
		String retString = " kunne ikke fjernes";
		
		if(!exists){
			retString = " eksistere ikke";
		}
		return retString;
	}
	
}
