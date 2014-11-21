package guiLayer.models;

import guiLayer.Methods;

import java.util.ArrayList;

import javax.swing.table.AbstractTableModel;

import modelLayer.Product;

/**
 * Class for ProductTableModel
 *
 * @author Group 3, dmaa0214, UCN
 *
 */
public class ProductTableModel extends AbstractTableModel {

	private static final long serialVersionUID = 1L;
	private ArrayList<Product> products;
	
	/**
	 * Constructor for ProductTableModel objects.
	 *
	 */
	public ProductTableModel() {
		this.products = new ArrayList<Product>();
	}

	public void refresh(ArrayList<Product> pList) {
		this.products = pList;
	}
	
	@Override
	public int getRowCount() {
		return this.products.size();
	}

	@Override
	public int getColumnCount() {
		return 7;
	}
	
	@Override
	public String getColumnName(int collIndex) {
		
		String value = "??";
		
		if (collIndex == 0) {
			value = "ID";
		} else if (collIndex == 1) {
			value = "VareNummer";
		} else if (collIndex == 2) {
			value = "Brand";
		} else if (collIndex == 3) {
			value = "Navn";
		} else if (collIndex == 4) {
			value = "Beskrivelse";
		} else if (collIndex == 5) {
			value = "Enhed";
		} else if (collIndex == 6) {
			value = "Pris";
		}
		
		return value;
	}

	@Override
	public Object getValueAt(int rowIndex, int columnIndex) {
		Product p = products.get(rowIndex);
		Object value = null;
		if (columnIndex == 0) {
			value = p.getId();
		} else if (columnIndex == 1) {
			value = p.getItemNumber();
		} else if (columnIndex == 2) {
			value = p.getBrand();
		} else if (columnIndex == 3) {
			value = p.getName();
		} else if (columnIndex == 4) {
			value = Methods.shortenString(p.getDescription(), 50);
		} else if (columnIndex == 5) {
			value = p.getUnitType().getShortDescription();
		} else if (columnIndex == 6) {
			value = p.getPrice();
		}
		return value;
	}

}
