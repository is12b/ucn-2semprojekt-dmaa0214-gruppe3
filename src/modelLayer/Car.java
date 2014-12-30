package modelLayer;

import java.util.ArrayList;

/**
 * Class for Car.
 *
 * @author Group 3, dmaa0214, UCN
 */
public class Car implements Cloneable{
	
	private String brand;
	private String model;
	private String regNr;
	private String vin;
	private int mileage;
	private int id;
	private boolean hidden = false;
	private int year;
	private Customer owner;
	private ArrayList<Inspection> inspections = new ArrayList<Inspection>();;
	private CarExtra extra;
	
	/**
	 * Instantiates a new car.
	 */
	public Car() {
		inspections = new ArrayList<Inspection>();
	}

	public CarExtra getExtra() {
		return extra;
	}

	public void setExtra(CarExtra extra) {
		this.extra = extra;
	}

	public void setInspections(ArrayList<Inspection> inspections) {
		this.inspections = inspections;
	}
	
	/**
	 * @return the inspections
	 */
	public ArrayList<Inspection> getInspections() {
		return inspections;
	}
	
	public void addInspection(Inspection i) {
		inspections.add(i);
	}

	/**
	 * Instantiates a new car.
	 * 
	 * @param id the id of the car.
	 */
	public Car(int id) {
		this.id = id;
		inspections = new ArrayList<Inspection>();
	}
	
	/**
	 * Constructor for Car.java objects.
	 *
	 * @param brand
	 * @param model
	 * @param regNr
	 * @param vin
	 * @param mileage
	 * @param year
	 * @param owner
	 */
	public Car(String brand, String model, String regNr, String vin, int mileage, int year, Customer owner) {
		inspections = new ArrayList<Inspection>();
		this.brand = brand;
		this.model = model;
		this.regNr = regNr;
		this.vin = vin;
		this.mileage = mileage;
		this.year = year;
		this.owner = owner;
	}



	/**
	 * Gets the brand.
	 *
	 * @return the brand
	 */
	public String getBrand() {
		return brand;
	}

	/**
	 * Sets the brand.
	 *
	 * @param brand the new brand
	 */
	public void setBrand(String brand) {
		this.brand = brand;
	}

	/**
	 * Gets the model.
	 *
	 * @return the model
	 */
	public String getModel() {
		return model;
	}

	/**
	 * Sets the model.
	 *
	 * @param model the new model
	 */
	public void setModel(String model) {
		this.model = model;
	}

	/**
	 * Gets the reg nr.
	 *
	 * @return the reg nr
	 */
	public String getRegNr() {
		return regNr;
	}

	/**
	 * Sets the reg nr.
	 *
	 * @param regNr the new reg nr
	 */
	public void setRegNr(String regNr) {
		this.regNr = regNr;
	}

	/**
	 * Gets the vin.
	 *
	 * @return the vin
	 */
	public String getVin() {
		return vin;
	}

	/**
	 * Sets the vin.
	 *
	 * @param vin the new vin
	 */
	public void setVin(String vin) {
		this.vin = vin;
	}

	/**
	 * Gets the mileage.
	 *
	 * @return the mileage
	 */
	public int getMileage() {
		return mileage;
	}

	/**
	 * Sets the mileage.
	 *
	 * @param mileage the new mileage
	 */
	public void setMileage(int mileage) {
		this.mileage = mileage;
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

	/**
	 * Gets the year.
	 *
	 * @return the year
	 */
	public int getYear() {
		return year;
	}

	/**
	 * Sets the year.
	 *
	 * @param year the new year
	 */
	public void setYear(int year) {
		this.year = year;
	}

	/**
	 * Get method for owner
	 * @return the owner
	 */
	public Customer getOwner() {
		return owner;
	}

	/**
	 * Set method for owner
	 * @param owner the owner to set
	 */
	public void setOwner(Customer owner) {
		this.owner = owner;
	}
	
	public String toString(){
		
		String retString = "";
		if(regNr != null && !regNr.trim().isEmpty()){
			retString = regNr;
		}
		if(!retString.isEmpty()) {
			retString += " - ";
		}
		if(vin != null && !vin.trim().isEmpty()){
			retString += vin;
		}
		
		if(brand != null && !brand.trim().isEmpty()){
			retString += " - " + brand;
		}
		
		if(model != null && !model.trim().isEmpty()){
			retString += " - " + model;
		}
		
		if(year != 0){
			retString += " - " + year;
		}
		
		return retString;
	}
	
	/**
	 * Method to set values in Object back to some values from a clone of it.
	 * @param clone the backup clone of the object
	 */
	public void setToClone(Car clone) {
		this.brand = clone.getBrand();
		this.model = clone.getModel();
		this.regNr = clone.getRegNr();
		this.vin = clone.getVin();
		this.mileage = clone.getMileage();
		this.year = clone.getYear();
		this.owner = clone.getOwner();
	}
	
	@Override
	public Car clone() throws CloneNotSupportedException {
		return (Car) super.clone();
	}
	

}
