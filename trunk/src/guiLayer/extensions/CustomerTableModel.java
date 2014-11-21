package guiLayer.extensions;

import java.util.ArrayList;
import javax.swing.table.AbstractTableModel;
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
		this.customers = customers;
	}

	@Override
	public int getColumnCount() {
		return 2;
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
			value = "FISK";
			//value = c.get();
			//TODO
		}

		return value;
	}

	@Override
	public String getColumnName(int collIndex) {

		String value = "??";

		if (collIndex == 0) {
			value = "ID";
		} else if (collIndex == 1) {
			value = "Name";
		} else if (collIndex == 2) {
			value = "Amount";
		} else if (collIndex == 3) {
			value = "Unit Price";
		}
		return value;
	}

}
