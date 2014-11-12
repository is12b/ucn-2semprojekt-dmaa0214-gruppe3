package modelLayer;

public class UnitType {

	private String description;
	private String shortDescription;
	private boolean decimalAllowed = false;
	
	public UnitType() {
		
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public String getShortDescription() {
		return shortDescription;
	}

	public void setShortDescription(String shortDescription) {
		this.shortDescription = shortDescription;
	}

	public boolean isDecimalAllowed() {
		return decimalAllowed;
	}

	public void setDecimalAllowed(boolean decimalAllowed) {
		this.decimalAllowed = decimalAllowed;
	}
	
	
	
}
