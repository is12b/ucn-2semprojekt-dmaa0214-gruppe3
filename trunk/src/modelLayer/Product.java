package modelLayer;

public class Product {

	private String brand;
	private String name;
	private String description;
	private String itemNr;
	private double price;
	private int id;
	private boolean hidden = false;
	
	public Product() {

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

	public String getItemNr() {
		return itemNr;
	}

	public void setItemNr(String itemNr) {
		this.itemNr = itemNr;
	}
	
}
