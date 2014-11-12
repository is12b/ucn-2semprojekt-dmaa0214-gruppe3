package modelLayer;

public class Car {
	
	private String brand;
	private String model;
	private String regNr;
	private String vin;
	private int mileage;
	private int id;
	private boolean hidden = false;
	private int year;
	
	public Car() {
		
	}

	public Car(String brand, String model, String regNr, String vin,
		int mileage, int year) {
		this.brand = brand;
		this.model = model;
		this.regNr = regNr;
		this.vin = vin;
		this.mileage = mileage;
		this.year = year;
	}

	public String getBrand() {
		return brand;
	}

	public void setBrand(String brand) {
		this.brand = brand;
	}

	public String getModel() {
		return model;
	}

	public void setModel(String model) {
		this.model = model;
	}

	public String getRegNr() {
		return regNr;
	}

	public void setRegNr(String regNr) {
		this.regNr = regNr;
	}

	public String getVin() {
		return vin;
	}

	public void setVin(String vin) {
		this.vin = vin;
	}

	public float getMileage() {
		return mileage;
	}

	public void setMileage(int mileage) {
		this.mileage = mileage;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public boolean isHidden() {
		return hidden;
	}

	public void setHidden(boolean hidden) {
		this.hidden = hidden;
	}

	public int getYear() {
		return year;
	}

	public void setYear(int year) {
		this.year = year;
	}
	

}
