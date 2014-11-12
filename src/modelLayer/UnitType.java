package modelLayer;

/**
 * Class for UnitType.
 *
 * @author Group 3, dmaa0214, UCN
 */
public class UnitType {

	private String description;
	private String shortDescription;
	private String oldShortDescription;
	private boolean decimalAllowed = false;
	
	/**
	 * Instantiates a new unit type.
	 *
	 * @param shortDescription the short description
	 */
	public UnitType(String shortDescription) {
		this.shortDescription = shortDescription;
		this.oldShortDescription = null;
	}

	/**
	 * Gets the description.
	 *
	 * @return the description
	 */
	public String getDescription() {
		return description;
	}

	/**
	 * Sets the description.
	 *
	 * @param description the new description
	 */
	public void setDescription(String description) {
		this.description = description;
	}

	/**
	 * Gets the short description.
	 *
	 * @return the short description
	 */
	public String getShortDescription() {
		return shortDescription;
	}

	/**
	 * Sets the short description.
	 *
	 * @param shortDescription the new short description
	 */
	public void setShortDescription(String shortDescription) {
		this.oldShortDescription = this.shortDescription;
		this.shortDescription = shortDescription;
	}

	/**
	 * Checks if is decimalAllowed.
	 *
	 * @return true, if is decimal allowed
	 */
	public boolean isDecimalAllowed() {
		return decimalAllowed;
	}

	/**
	 * Sets the decimalAllowed.
	 *
	 * @param decimalAllowed the new decimal allowed
	 */
	public void setDecimalAllowed(boolean decimalAllowed) {
		this.decimalAllowed = decimalAllowed;
	}

	/**
	 * Get method for oldShortDescription
	 * @return the oldShortDescription
	 */
	public String getOldShortDescription() {
		return oldShortDescription;
	}

	/**
	 * Set oldShortDescription to null
	 */
	public void setOldShortDescriptionToNull() {
		this.oldShortDescription = null;
	}
	
}
