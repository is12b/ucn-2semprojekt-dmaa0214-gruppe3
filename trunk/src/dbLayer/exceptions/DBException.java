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
	 * @param message the detail message.
	 */
	public DBException(String message) {
		super(message);
		setMessage(message);
	}
	
	/**
	 * Constructor for DBException objects.
	 *
	 * @param obj One word, that describes the object working with.
	 * @param sqlException the {@link SQLException} that was caught
	 */
	public DBException(String obj, SQLException sqlException) {
		super(sqlException.getMessage(), sqlException.getSQLState(), sqlException.getErrorCode());
		setMessageFromSQL(obj);
	}


	@Override
	public String getMessage() {
		return message;
	}
	
	/**
	 * Set method for message
	 * @param message the message to set
	 */
	protected void setMessage(String message) {
		if (message != null && !message.isEmpty()) {
			this.message = message;
		} else {
			this.message = "Ukendt DatabaseFejl";
		}
	}
	
	private void setMessageFromSQL(String obj) {
		System.out.println("code: " + getErrorCode() + ", sqlState: " + getSQLState() + ", message: " + super.getMessage());
		String ret = "";
		if (getErrorCode() == 2627) { //Violation of PRIMARY KEY constraint, sqlState: 23000, error code: 2627
			ret = obj + " eksisterer allerede i databasen";
		} else if (getErrorCode() == 515) { //Cannot insert the value NULL into column, sqlState: 23000, error code: 515
			ret = obj + " blev ikke oprettet/ændret i databasen, fordi en værdi, som skal angives ikke var angivet";
		} else if (getErrorCode() == 8152) { //String or binary data would be truncated, sqlState: 22001, code: 8152
			ret = obj + " blev ikke oprettet/ændret, fordi en værdi var for lang til at blive indsat i databasen";
		} else if (getErrorCode() == 547) { //code: 547, sqlState: 23000, message: The DELETE statement conflicted with the REFERENCE constraint
			ret = obj + " kan ikke slettes, da andre dele af databasen er afhængig af " + obj.toLowerCase();
		} else {
			ret = "code: " + getErrorCode() + ", sqlState: " + getSQLState() + ", message: " + super.getMessage();
			System.out.println(ret);			
		}
		setMessage(ret);
	}
	
}
