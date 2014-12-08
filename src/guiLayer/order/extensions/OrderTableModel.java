package guiLayer.order.extensions;

import guiLayer.extensions.Utilities;

import java.util.ArrayList;

import javax.swing.table.AbstractTableModel;

import modelLayer.PartSale;

/**
 * Class for ProductTableModel
 *
 * @author Group 3, dmaa0214, UCN
 *
 */
public class OrderTableModel extends AbstractTableModel {
	private static final long serialVersionUID = 1L;
	private ArrayList<PartSale> partSales;
	
	/**
	 * Constructor for ProductTableModel objects.
	 *
	 */
	public OrderTableModel() {
		this.partSales = new ArrayList<PartSale>();
	}

	public void refresh(ArrayList<PartSale> pList) {
		if(pList != null) {
			this.partSales = pList;
		} else {
			this.partSales = new ArrayList<PartSale>();
		}
	}
	
	@Override
	public int getRowCount() {
		return this.partSales.size();
	}

	@Override
	public int getColumnCount() {
		return 8;
	}
	
	@Override
	public String getColumnName(int collIndex) {
		
		String value = "??";
		
		if (collIndex == 0) {
			value = "VareID";
		} else if (collIndex == 1) {
			value = "VareNummer";
		} else if (collIndex == 2) {
			value = "Navn";
		} else if (collIndex == 3) {
			value = "Beskrivelse";
		} else if(collIndex == 4){
			value = "Antal";
		} else if (collIndex == 5) {
			value = "Pris";
		} else if (collIndex == 6) {
			value = "Enhed";
		} else if (collIndex == 7){
			value = "Total";
		}
		
		return value;
	}

	@Override
	public Object getValueAt(int rowIndex, int columnIndex) {
		PartSale p = partSales.get(rowIndex);
		Object value = null;
		if (columnIndex == 0) {
			value = p.getProduct().getId();
		} else if (columnIndex == 1) {
			value = p.getProduct().getItemNumber();
		} else if (columnIndex == 2) {
			value = p.getProduct().getName();
		} else if (columnIndex == 3) {
			value = p.getProduct().getDescription();
		} else if (columnIndex == 4) {
			value = p.getAmount();
		} else if (columnIndex == 5) {
			value = Utilities.getAsMoney(p.getUnitPrice());
		} else if (columnIndex == 6) {
			value = p.getProduct().getUnitType().getShortDescription();
		} else if (columnIndex == 7) {
			value = Utilities.getAsMoney(p.getAmount() * p.getUnitPrice());
		}
		return value;
	}

	public PartSale getPartSaleAt(int rowIndex) {
		PartSale pSale = partSales.get(rowIndex);
		return pSale;
	}
}
