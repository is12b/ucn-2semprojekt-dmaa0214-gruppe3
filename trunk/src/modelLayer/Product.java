package modelLayer;

/**
 * Class for Product.
 *
 * @author Group 3, dmaa0214, UCN
 */
public class Product {

	private String brand;
	private String name;
	private String description;
	private String itemNumber;
	private double price;
	private int id;
	private boolean hidden = false;
	private UnitType unitType;
	
	/**
	 * Instantiates a new product.
	 */
	public Product() {

	}
	
	/**
	 * Instantiates a new product.
	 * 
	 * @param id The id of the product
	 */
	public Product(int id) {
		this.id = id;
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
	 * Gets the price.
	 *
	 * @return the price
	 */
	public double getPrice() {
		return price;
	}

	/**
	 * Sets the price.
	 *
	 * @param price the new price
	 */
	public void setPrice(double price) {
		this.price = price;
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
	 * Gets the item number.
	 *
	 * @return the item number
	 */
	public String getItemNumber() {
		return itemNumber;
	}

	/**
	 * Sets the item number.
	 *
	 * @param itemNumber the new item number
	 */
	public void setItemNumber(String itemNumber) {
		this.itemNumber = itemNumber;
	}

	/**
	 * Gets the unit type.
	 *
	 * @return the unit type
	 */
	public UnitType getUnitType() {
		return unitType;
	}

	/**
	 * Sets the unit type.
	 *
	 * @param unitType the new unit type
	 */
	public void setUnitType(UnitType unitType) {
		this.unitType = unitType;
	}
	
}
