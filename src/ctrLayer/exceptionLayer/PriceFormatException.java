package ctrLayer.exceptionLayer;

/**
 * ExceptionClass for PriceFormatException, indicate an exception in the priceformat.
 *
 * @author Group 3, dmaa0214, UCN
 *
 */
public class PriceFormatException extends Exception {

	private static final long serialVersionUID = 1L;

	/**
	 * Constructor for PriceFormatException objects.
	 *
	 * @param message the detail message.
	 */
	public PriceFormatException(String message) {
		super(message);
	}

}
