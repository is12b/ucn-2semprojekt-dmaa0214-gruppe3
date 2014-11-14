package modelLayer;

/**
 * Class for Postalcode
 *
 * @author Group 3, dmaa0214, UCN
 *
 */
public class Postalcode {
	private int postalcode;
	private String city;
	
	public Postalcode(int postalcode, String city){
		this.postalcode= postalcode;
		this.city= city;
	}

	/**
	 * Get method for postalcode
	 * @return the postalcode
	 */
	public int getPostalcode() {
		return postalcode;
	}

	/**
	 * Set method for postalcode
	 * @param postalcode the postalcode to set
	 */
	public void setPostalcode(int postalcode) {
		this.postalcode = postalcode;
	}

	/**
	 * Get method for city
	 * @return the city
	 */
	public String getCity() {
		return city;
	}

	/**
	 * Set method for city
	 * @param city the city to set
	 */
	public void setCity(String city) {
		this.city = city;
	}

}
