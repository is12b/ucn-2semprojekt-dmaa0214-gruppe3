package dbLayer.exceptions;

import java.sql.SQLException;

/**
 * ExceptionClass for DBException
 *
 * @author Group 3, dmaa0214, UCN
 *
 */
public class DBException extends SQLException {

	private static final long serialVersionUID = 1L;
	private String message;

	/**
	 * Constructor for DBException objects.
	 *
	 * @param obj One word, that describes the object working with.
	 * @param stateCode type of sql request: 
	 *  				<br> 1 for insert,
	 * 					<br> 2 for update,
	 * 					<br> 3 for delete.
	 */
	public DBException(String obj, int stateCode) {
		if(stateCode == 1) {
			message = obj + " blev ikke indsat i databasen";
		}
		else if (stateCode == 2) {
			message = obj + " blev ikke fundet i databasen og kunne derfor ikke opdates";
		}
		else if (stateCode == 3) {
			message = obj + " blev ikke fundet i databasen og kunne derfor ikke slettes";
		}
		else {
			message = "Ukendt databasefejl";
		}
	}

	/**
	 * Constructor for DBException objects.
	 *
	 * @param obj One word, that describes the object working with.
	 * @param sqlException the {@link SQLException} that was caught
	 */
	public DBException(String obj, SQLException sqlException) {
		super(sqlException.getMessage(), sqlException.getSQLState(), sqlException.getErrorCode());
		setMessage(obj);
	}

	@Override
	public String getMessage() {
		return message;
	}
	
	private void setMessage(String obj) {
		System.out.println("code: " + getErrorCode() + ", sqlState: " + getSQLState() + ", message: " + super.getMessage());
		
		if (getErrorCode() == 2627) { //Violation of PRIMARY KEY constraint, sqlState: 23000, error code: 2627
			message = obj + " eksisterer allerede i databasen";
		} else if (getErrorCode() == 515) { //Cannot insert the value NULL into column, sqlState: 23000, error code: 515
			message = obj + " blev ikke oprettet i databasen, fordi en værdi, som skal angives ikke var angivet";
		} else if (getErrorCode() == 8152) { //String or binary data would be truncated, sqlState: 22001, code: 8152
			message = obj + " blev ikke oprettet, fordi en værdi var for lang til at blive indsat i databasen";
		} else {
			message = "code: " + getErrorCode() + ", sqlState: " + getSQLState() + ", message: " + super.getMessage();
			System.out.println(message);			
		}
	}
	
}
