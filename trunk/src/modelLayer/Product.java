package modelLayer;

public class Product {

	private String brand;
	private String name;
	private String description;
	private String itemNumber;
	private double price;
	private int id;
	private boolean hidden = false;
	private UnitType unitType;
	
	public Product() {

	}

	public Product(String brand, String name, String description,
		String itemNumber, double price, boolean hidden, UnitType unitType) {
		this.brand = brand;
		this.name = name;
		this.description = description;
		this.itemNumber = itemNumber;
		this.price = price;
		this.hidden = hidden;
		this.unitType = unitType;
	}


	public String getBrand() {
		return brand;
	}

	public void setBrand(String brand) {
		this.brand = brand;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public double getPrice() {
		return price;
	}

	public void setPrice(double price) {
		this.price = price;
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

	public String getItemNumber() {
		return itemNumber;
	}

	public void setItemNumber(String itemNumber) {
		this.itemNumber = itemNumber;
	}

	public UnitType getUnitType() {
		return unitType;
	}

	public void setUnitType(UnitType unitType) {
		this.unitType = unitType;
	}
	
}
