package exceptions;


/**
 * Class for DBNotFoundException
 *
 * @author Group 3, dmaa0214, UCN
 *
 */
public class DBNotFoundException extends DBException {

	private static final long serialVersionUID = 1L;

	/**
	 * Constructor for DBNotFoundException objects.
	 *
	 * @param obj One word, that describes the object working with.
	 * @param stateCode type of sql request: 
	 *  				<br> 1 for insert,
	 * 					<br> 2 for update,
	 * 					<br> 3 for delete.
	 */
	public DBNotFoundException(String obj, int stateCode) {
		super(obj);
		String message = "";
		if(stateCode == 1) {
			message = obj + " blev ikke indsat i databasen";
		}
		else if (stateCode == 2) {
			message = obj + " blev ikke fundet i databasen og kunne derfor ikke opdates";
		}
		else if (stateCode == 3) {
			message = obj + " blev ikke fundet i databasen og kunne derfor ikke slettes";
		}
		setMessage(message);
	}

	/**
	 * Constructor for DBNotFoundException objects.
	 *
	 * @param message
	 */
	public DBNotFoundException(String message) { // NO_UCD (unused code)
		super(message);
	}
	
}
