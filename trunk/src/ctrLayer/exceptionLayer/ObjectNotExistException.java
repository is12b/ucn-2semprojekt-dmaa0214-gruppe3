package ctrLayer.exceptionLayer;

/**
 * Class for ObjectNotExistException
 *
 * @author Group 3, dmaa0214, UCN
 *
 */
public class ObjectNotExistException extends Exception {

	private static final long serialVersionUID = 1L;

	
	/**
	 * Constructor for ObjectNotExistException objects.
	 *
	 * @param message the detail message.
	 */
	public ObjectNotExistException(String message) {
		super(message);
	}
	
	@Override
	public String getMessage() {
		String message = super.getMessage();
		if (message != null && !message.trim().isEmpty()) {
			
		} else {
			message = "Der er sket en ukendt systemfejl.";
		}
		return message;
	}
	
}
