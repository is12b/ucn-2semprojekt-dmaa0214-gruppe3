package guiLayer.customer.models;

import java.util.ArrayList;

import javax.swing.table.AbstractTableModel;

import modelLayer.Car;
import modelLayer.Customer;


/**
 * Class for CustomerTableModel
 *
 * @author Group 3, dmaa0214, UCN
 *
 */
public class CustomerTableModel extends AbstractTableModel {

	private static final long serialVersionUID = 1L;
	private ArrayList<Customer> customers; 

	public CustomerTableModel() {
		customers = new ArrayList<Customer>();
	}

	public CustomerTableModel(ArrayList<Customer> customers) {
		this.customers = customers;
	}

	public void refresh(ArrayList<Customer> customers) {
		if (customers == null) {
			customers = new ArrayList<Customer>();
		}
		this.customers = customers;
		fireTableDataChanged();
	}

	@Override
	public int getColumnCount() {
		return 3;
	}

	@Override
	public int getRowCount() {
		return customers.size();
	}

	@Override
	public Object getValueAt(int rowIndex, int collIndex) {
		Customer c = customers.get(rowIndex);
		Object value = null;
		if (collIndex == 0) {
			value = c.getName();
		} else if (collIndex == 1) {
			value = c.getPhoneNumber();
		} else if (collIndex == 2) {
			ArrayList<Car> cars = c.getCars();
			if(cars == null || cars.size() == 0) {
				value = "-";
			} else if(cars.size() == 1) {
				value = cars.get(0).getRegNr();
			} else {
				value = cars.size() + " biler";
			}
		}
		return value;
	}

	@Override
	public String getColumnName(int collIndex) {

		String value = "??";

		if (collIndex == 0) {
			value = "Navn";
		} else if (collIndex == 1) {
			value = "Tlf";
		} else if (collIndex == 2) {
			value = "Bil";
		}
		return value;
	}

	public Customer getCustomerAt(int rowIndex) {
		Customer c = customers.get(rowIndex);
		return c;
	}
	
}
