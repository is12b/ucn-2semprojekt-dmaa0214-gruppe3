package guiLayer.saleOverview.models;

import guiLayer.extensions.Utilities;

import java.util.ArrayList;
import java.util.Date;

import javafx.collections.SetChangeListener;

import javax.swing.table.AbstractTableModel;

import modelLayer.Sale;
/**
 * Class for SaleOverviewTableModel
 *
 * @author Group 3, dmaa0214, UCN
 *
 */
public class SaleOverviewTableModel extends AbstractTableModel {

	private static final long serialVersionUID = 1L;
	private ArrayList<Sale> sales;
	
	/**
	 * Constructor for SaleOverviewTableModel objects.
	 *
	 */
	public SaleOverviewTableModel() {
		this.sales = new ArrayList<Sale>();
	}

	public void refresh(ArrayList<Sale> sList) {
		if(sList != null) {
			this.sales = sList;
		} else {
			this.sales = new ArrayList<Sale>();
		}
		fireTableDataChanged();
	}
	
	@Override
	public int getRowCount() {
		return this.sales.size();
	}

	@Override
	public int getColumnCount() {
		return 6;
	}
	
	@Override
	public String getColumnName(int collIndex) {
		
		String value = "??";
		
		if (collIndex == 0) {
			value = "Faktura Nr.";
		} else if (collIndex == 1) {
			value = "Kunde";
		} else if (collIndex == 2) {
			value = "Bil";
		} else if (collIndex == 3) {
			value = "Dato";
		} else if (collIndex == 4) {
			value = "Betalt";
		} else if (collIndex == 5) {
			value = "Total";
		}
		
		return value;
	}

	@Override
	public Class<?> getColumnClass(int columnIndex) {
		Object obj = getValueAt(0, columnIndex);
		Class<?> columnClass;
		if (obj != null) {
			columnClass = getValueAt(0, columnIndex).getClass();
		} else {
			columnClass = super.getColumnClass(columnIndex);
		}
        return columnClass;
    }
	
	@Override
	public Object getValueAt(int rowIndex, int columnIndex) {
		Object value = null;
		if(sales.size() != 0) {
			Sale s = sales.get(rowIndex);
		
			if (columnIndex == 0) {
				value = s.getId();
			} else if (columnIndex == 1) {
				value = s.getCustomer();
			} else if (columnIndex == 2) {
				value = s.getCar();
			} else if (columnIndex == 3) {
				value = s.getDate();
			} else if (columnIndex == 4) {
				if(s.isPaid()) {
					value = s.isPaid();
				} else {
					//System.out.println("paydeadLine: "+s.getPaymentDeadline() + " || today: " + new Date());
					if (s.getPaymentDeadline().after(new Date())) {
						value = s.isPaid();
					} else {
						value = "Overskredet";
					}
					
				}
			} else if (columnIndex == 5) {
				value = Utilities.getAsMoney(s.getTotalPrice());
			}
		}
		return value;
	}
	
	public Sale getSaleAt(int rowIndex) {
		Sale s = sales.get(rowIndex);
		return s;
	}

}