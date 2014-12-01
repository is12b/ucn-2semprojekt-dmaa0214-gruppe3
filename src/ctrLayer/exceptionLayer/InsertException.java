package ctrLayer.exceptionLayer;

/**
 * Class for InsertException
 *
 * @author Group 3, dmaa0214, UCN
 *
 */
public class InsertException extends Exception {
	private static final long serialVersionUID = 1L;

	/**
	 * Constructor for InsertException.java objects.
	 *
	 * @param string
	 */
	public InsertException(String obj) {
		super("Kunne ikke oprette " + obj);
	}
}
