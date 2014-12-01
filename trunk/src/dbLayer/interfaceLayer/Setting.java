package dbLayer.interfaceLayer;

/**
 * Class for Setting
 *
 * @author Group 3, dmaa0214, UCN
 *
 */
public class Setting {
	private String key;
	private String value;
	
	public Setting(){
		
	}
	
	public Setting(String key, String value){
		this.key = key;
		this.value = value;
	}

	/**
	 * Get method for key
	 * @return the key
	 */
	public String getKey() {
		return key;
	}

	/**
	 * Set method for key
	 * @param key the key to set
	 */
	public void setKey(String key) {
		this.key = key;
	}

	/**
	 * Get method for value
	 * @return the value
	 */
	public String getValue() {
		return value;
	}

	/**
	 * Set method for value
	 * @param value the value to set
	 */
	public void setValue(String value) {
		this.value = value;
	}
	
	
	
}
