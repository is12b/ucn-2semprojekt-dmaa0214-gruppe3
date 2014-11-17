package ctrLayer.exceptionLayer;

/**
 * Class for UnknownException
 *
 * @author Group 3, dmaa0214, UCN
 *
 */
public class ASDException extends Exception {

	private static final long serialVersionUID = 1L;
//	private String message;

	/**
	 * Constructor for UnknownException objects.
	 *
	 * @param message the detail message.
	 */
	public ASDException(String message) {
		super("Der er sket en ukendt systemfejl.\nBeskrivelse:\n" + message);
	}
	
//	@Override
//	public String getMessage() {
//		return message;
//	}
//	
//	/**
//	 * Set method for message
//	 * @param message the message to set
//	 */
//	protected void setMessage(String message) {
//		if (message != null && !message.isEmpty()) {
//			this.message = message;
//		} else {
//			this.message = "Ukendt SystemFejl";
//		}
//	}
	
}
