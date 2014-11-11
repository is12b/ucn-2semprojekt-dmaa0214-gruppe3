package modelLayer;

import modelLayer.Product;

public class PartSale {

	private int quantity;
	private double unitPrice;
	private Product product;
	private int id;
	
	public PartSale() {
		
	}

	public int getQuantity() {
		return quantity;
	}

	public void setQuantity(int quantity) {
		this.quantity = quantity;
	}

	public double getUnitPrice() {
		return unitPrice;
	}

	public void setUnitPrice(double unitPrice) {
		this.unitPrice = unitPrice;
	}

	public Product getProd() {
		return product;
	}

	public void setProd(Product product) {
		this.product = product;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}
	
	
}
