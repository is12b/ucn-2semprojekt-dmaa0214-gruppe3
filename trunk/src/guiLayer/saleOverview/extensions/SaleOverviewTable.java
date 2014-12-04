package guiLayer.saleOverview.extensions;

import java.awt.Color;
import java.awt.Component;
import java.util.Vector;

import javax.swing.JComponent;
import javax.swing.JLabel;
import javax.swing.JTable;
import javax.swing.ListSelectionModel;
import javax.swing.table.TableCellRenderer;
import javax.swing.table.TableColumnModel;
import javax.swing.table.TableModel;

/**
 * Class for SaleOverviewTable
 *
 * @author Group 3, dmaa0214, UCN
 *
 */
public class SaleOverviewTable extends JTable {

	/**
	 * Constructor for SaleOverviewTable objects.
	 *
	 * @param dm
	 */
	public SaleOverviewTable(TableModel dm) {
		super(dm);
		// TODO Auto-generated constructor stub
	}

	@Override
    public TableCellRenderer getCellRenderer(int row, int column) {
        //Class<?> editingClass = null;
        int modelColumn = convertColumnIndexToModel(column);
        if (modelColumn == 4) {
            Class<? extends Object> rowClass = getModel().getValueAt(row, modelColumn).getClass();
            TableCellRenderer renderer;
            if (rowClass.equals(String.class)) {
            	TableCellRenderer passedPaidRenderer = new TableCellRenderer() {
					@Override
					public Component getTableCellRendererComponent(JTable table, Object value,
							boolean isSelected, boolean hasFocus, int row, int column) {
						Component comp = getDefaultRenderer(rowClass).getTableCellRendererComponent(table, value, isSelected, hasFocus, row, modelColumn);
						//Component comp = new JLabel((String) value);
						comp.setForeground(Color.RED);
						System.out.println("her?");
						return comp;
					}
				};
				
            	renderer = (TableCellRenderer) passedPaidRenderer;
            } else {
            	renderer = getDefaultRenderer(rowClass);
            }
            return renderer;
        } else {
            return super.getCellRenderer(row, column);
        }
    }
}
