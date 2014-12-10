package guiLayer.sale.models;

import guiLayer.extensions.Utilities;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.concurrent.TimeUnit;

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
				value = getPaidCell(s);
			} else if (columnIndex == 5) {
				value = Utilities.getAsMoney(s.getTotalPrice());
			}
		}
		return value;
	}
	
	private String getPaidCell(Sale s) {
		String value = null;
		if(s.isPaid()) {
			value = "true";
		} else {
			Date bankDeadline = getDateAfterXDays(2, s.getPaymentDeadline());
			long days = daysSinceDeadline(bankDeadline);
			if (days <= 0 ) {
				value = "false";
			} else {
				value = daysSinceDeadline(days);
			}
			
		}
		return value;
	}
	
	private long daysSinceDeadline(Date date) {
		Date dayAfterTomorrow = getDateAfterXDays(2, null);
		long diff = dayAfterTomorrow.getTime() - date.getTime();
		long days = TimeUnit.DAYS.convert(diff, TimeUnit.MILLISECONDS);
		return days;
	}
	
	private String daysSinceDeadline(long days) {
		String ret = "";
		ret = days + " Dag";
		if(days == 1) {
			ret += "e";
		}
		ret += " siden";
		return ret;
	}
	
	private Date getDateAfterXDays(int x, Date from) {
		Date day = from;
		if (day == null) {
			day = new Date();
		}
		Calendar c = Calendar.getInstance(); 
		c.setTime(day); 
		c.add(Calendar.DATE, x);
		day = c.getTime();
		return day;
	}

	public Sale getSaleAt(int rowIndex) {
		Sale s = sales.get(rowIndex);
		return s;
	}

}