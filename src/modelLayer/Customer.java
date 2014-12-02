package modelLayer;

import java.util.ArrayList;

// TODO: Auto-generated Javadoc
/**
 * Class for Customer.
 *
 * @author Group 3, dmaa0214, UCN
 */
public class Customer implements Cloneable{
	
	private String name;
	private String phoneNumber;
	private String address;
	private String city;
	private int postalCode;
	private int cvr; 
	private int id;
	private boolean hidden = false;
	private ArrayList<Car> cars;
	
	/**
	 * Instantiates a new customer.
	 */
	public Customer() {
		
	}

	/**
	 * Constructor for Customer objects.
	 *
	 * @param name
	 * @param phoneNumber
	 * @param address
	 * @param postalCode
	 * @param cvr
	 * @param hidden
	 */
	public Customer(String name, String phoneNumber, String address,
			int postalCode, int cvr, boolean hidden) {
		this.name = name;
		this.phoneNumber = phoneNumber;
		this.address = address;
		this.postalCode = postalCode;
		this.cvr = cvr;
		this.hidden = hidden;
	}

	/**
	 * Instantiates a new car.
	 * 
	 * @param id the id of the customer.
	 */
	public Customer(int id) {
		this.id = id;
	}
	
	/**
	 * Gets the name.
	 *
	 * @return the name
	 */
	public String getName() {
		return name;
	}

	/**
	 * Sets the name.
	 *
	 * @param name the new name
	 */
	public void setName(String name) {
		this.name = name;
	}

	/**
	 * Gets the phone number.
	 *
	 * @return the phone number
	 */
	public String getPhoneNumber() {
		return phoneNumber;
	}

	/**
	 * Sets the phone number.
	 *
	 * @param phoneNumber the new phone number
	 */
	public void setPhoneNumber(String phoneNumber) {
		this.phoneNumber = phoneNumber;
	}

	/**
	 * Gets the address.
	 *
	 * @return the address
	 */
	public String getAddress() {
		return address;
	}

	/**
	 * Sets the address.
	 *
	 * @param address the new address
	 */
	public void setAddress(String address) {
		this.address = address;
	}

	/**
	 * Gets the city.
	 *
	 * @return the city
	 */
	public String getCity() {
		return city;
	}

	/**
	 * Sets the city.
	 *
	 * @param city the new city
	 */
	public void setCity(String city) {
		this.city = city;
	}

	/**
	 * Gets the postal code.
	 *
	 * @return the postal code
	 */
	public int getPostalCode() {
		return postalCode;
	}

	/**
	 * Sets the postal code.
	 *
	 * @param postalCode the new postal code
	 */
	public void setPostalCode(int postalCode) {
		this.postalCode = postalCode;
	}

	/**
	 * Gets the cvr.
	 *
	 * @return the cvr
	 */
	public int getCvr() {
		return cvr;
	}

	/**
	 * Sets the cvr.
	 *
	 * @param cvr the new cvr
	 */
	public void setCvr(int cvr) {
		this.cvr = cvr;
	}

	/**
	 * Gets the id.
	 *
	 * @return the id
	 */
	public int getId() {
		return id;
	}

	/**
	 * Sets the id.
	 *
	 * @param id the new id
	 */
	public void setId(int id) {
		this.id = id;
	}

	/**
	 * Checks if is hidden.
	 *
	 * @return true, if is hidden
	 */
	public boolean isHidden() {
		return hidden;
	}

	/**
	 * Sets the hidden.
	 *
	 * @param hidden the new hidden
	 */
	public void setHidden(boolean hidden) {
		this.hidden = hidden;
	}
	
	public boolean getHidden() {
		return hidden;
	}
	
	public void addCar(Car car) {
		cars.add(car);
	}
	
	public void removeCar(Car car) {
		cars.remove(car);
	}
	
	public ArrayList<Car> getCars() {
		return cars;
	}
	
	public void setCars(ArrayList<Car> cars) {
		this.cars = cars;
	}
	
	/**
	 * Method to set values in Object back to some values from a clone of it.
	 * @param clone the backup clone of the object
	 */
	public void setToClone(Customer clone) {
		this.id = clone.getId();
		this.name = clone.getName();
		this.phoneNumber = clone.getPhoneNumber();
		this.address = clone.getAddress();
		this.city = clone.getCity();
		this.postalCode = clone.getPostalCode();
		this.cvr = clone.getCvr();
		this.hidden = clone.getHidden();
		this.cars = clone.getCars();
	}
	
	@Override
	public Customer clone() throws CloneNotSupportedException {
		return (Customer) super.clone();
	}
	
	public String toString(){
		String retString = "";
		
		if(cvr == 0){
			retString = name + " - " + phoneNumber;
		} else {
			retString = cvr + " - " + name + " - " + phoneNumber;
		}
		
		return retString;
	}

}
