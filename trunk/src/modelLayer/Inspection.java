
package modelLayer;

public class Inspection implements Cloneable{

	private String date;
	private String result;
	private String km;
	private String regNr;
	private String url;
	private int id;
	
	public Inspection() {
		
	}

	
	
	public int getId() {
		return id;
	}



	public void setId(int id) {
		this.id = id;
	}



	/**
	 * @return the date
	 */
	public String getDate() {
		return date;
	}

	/**
	 * @param date the date to set
	 */
	public void setDate(String date) {
		this.date = date;
	}

	/**
	 * @return the result
	 */
	public String getResult() {
		return result;
	}

	/**
	 * @param result the result to set
	 */
	public void setResult(String result) {
		this.result = result;
	}

	/**
	 * @return the km
	 */
	public String getKm() {
		return km;
	}

	/**
	 * @param km the km to set
	 */
	public void setKm(String km) {
		this.km = km;
	}

	/**
	 * @return the regNr
	 */
	public String getRegNr() {
		return regNr;
	}

	/**
	 * @param regNr the regNr to set
	 */
	public void setRegNr(String regNr) {
		this.regNr = regNr;
	}

	/**
	 * @return the url
	 */
	public String getUrl() {
		return url;
	}

	/**
	 * @param url the url to set
	 */
	public void setUrl(String url) {
		this.url = url;
	}

	/* (non-Javadoc)
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString() {
		return "Inspection [date=" + date + ", result=" + result + ", km=" + km
				+ ", regNr=" + regNr + ", url=" + url + "]";
	}
	
	/**
	 * Method to set values in Object back to some values from a clone of it.
	 * @param clone the backup clone of the object
	 */
	public void setToClone(Inspection clone) {
		this.date = clone.getDate();
		this.result = clone.getResult();
	}
	
	@Override
	public Inspection clone() throws CloneNotSupportedException {
		return (Inspection) super.clone();
	}

}
