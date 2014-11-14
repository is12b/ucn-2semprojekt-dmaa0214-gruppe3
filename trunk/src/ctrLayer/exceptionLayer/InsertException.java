package ctrLayer.exceptionLayer;

/**
 * Class for InsertException
 *
 * @author Group 3, dmaa0214, UCN
 *
 */
public class InsertException extends Exception {

	/**
	 * Constructor for InsertException.java objects.
	 *
	 * @param string
	 */
	public InsertException(String obj) {
		super("Kunne ikke oprette " + obj);
	}
}
