package modelLayer;


/**
 * Class for PartSale.
 *
 * @author Group 3, dmaa0214, UCN
 */
public class PartSale {

	private double amount;
	private double price;
	private Product product;
	private int id;
	
	/**
	 * Instantiates a new part sale.
	 */
	public PartSale() {
	}

	public PartSale(double amount, Product product, double price) {
		this.amount = amount;
		this.product = product;
		this.price = price;
	}

	/**
	 * Gets the amount.
	 *
	 * @return the amount
	 */
	public double getAmount() {
		return amount;
	}

	/**
	 * Sets the amount.
	 *
	 * @param amount the new amount
	 */
	public void setAmount(double amount) {
		this.amount = amount;
	}

	/**
	 * Gets the unit price.
	 *
	 * @return the unit price
	 */
	public double getPrice() {
		return price;
	}

	/**
	 * Sets the unit price.
	 *
	 * @param price the new unit price
	 */
	public void setPrice(double price) {
		this.price = price;
	}

	/**
	 * Gets the product.
	 *
	 * @return the product
	 */
	public Product getProduct() {
		return product;
	}

	/**
	 * Sets the product.
	 *
	 * @param product the new product
	 */
	public void setProduct(Product product) {
		this.product = product;
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
	
}
