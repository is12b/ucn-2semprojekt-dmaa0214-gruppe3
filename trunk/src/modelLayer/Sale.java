package modelLayer;

import java.util.ArrayList;
import java.util.Date;

/**
 * Class for Sale.
 *
 * @author Group 3, dmaa0214, UCN
 */
public class Sale {

	private Date date;
	private Date paymentDeadline;
	private boolean paid;
	private String description;
	private int mileage;
	private int id;
	private ArrayList<PartSale> partSales;
	private Customer customer;
	private Car car;
	
	/**
	 * Instantiates a new sale.
	 */
	public Sale() {
		partSales = new ArrayList<PartSale>();
	}

	/**
	 * Gets the date.
	 *
	 * @return the date
	 */
	public Date getDate() {
		return date;
	}

	/**
	 * Sets the date.
	 *
	 * @param date the new date
	 */
	public void setDate(Date date) {
		this.date = date;
	}

	/**
	 * Gets the payment deadline.
	 *
	 * @return the payment deadline
	 */
	public Date getPaymentDeadline() {
		return paymentDeadline;
	}

	/**
	 * Sets the payment deadline.
	 *
	 * @param paymentDeadline the new payment deadline
	 */
	public void setPaymentDeadline(Date paymentDeadline) {
		this.paymentDeadline = paymentDeadline;
	}

	/**
	 * Checks if is paid.
	 *
	 * @return true, if is paid
	 */
	public boolean isPaid() {
		return paid;
	}

	/**
	 * Sets the paid.
	 *
	 * @param paid the new paid
	 */
	public void setPaid(boolean paid) {
		this.paid = paid;
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
	 * Get method for partSales
	 * @return the partSales
	 */
	public ArrayList<PartSale> getPartSales() {
		return partSales;
	}

	/**
	 * Add method for partSales
	 * @param partSale the partSale to add
	 */
	public void addPartSale(PartSale partSale) {
		this.partSales.add(partSale);
	}
	
	/**
	 * Remove method for partSales
	 * @param partSale the partSale to remove
	 */
	public void removePartSale(PartSale partSale) {
		this.partSales.remove(partSale);
	}

	/**
	 * Set method for partSales
	 * @param partSales the partSales to set
	 */
	public void setPartSales(ArrayList<PartSale> partSales) {
		this.partSales = partSales;
	}

	/**
	 * Get method for customer
	 * @return the customer
	 */
	public Customer getCustomer() {
		return customer;
	}

	/**
	 * Set method for customer
	 * @param customer the customer to set
	 */
	public void setCustomer(Customer customer) {
		this.customer = customer;
	}

	/**
	 * Get method for car
	 * @return the car
	 */
	public Car getCar() {
		return car;
	}

	/**
	 * Set method for car
	 * @param car the car to set
	 */
	public void setCar(Car car) {
		this.car = car;
	}
	
	/**
	 * Method to get The total price of the Sale
	 * @return the total price of the sale
	 */
	public double getTotalPrice() {
		double total = 0;
		
		for (PartSale partSale : partSales) {
			total += (partSale.getUnitPrice() * partSale.getAmount());
		}
		return total;
	}
	
}
