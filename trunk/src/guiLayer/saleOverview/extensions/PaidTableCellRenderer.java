package guiLayer.saleOverview.extensions;

import java.awt.Color;
import java.awt.Component;
import javax.swing.JCheckBox;
import javax.swing.JComponent;
import javax.swing.JLabel;
import javax.swing.JTable;
import javax.swing.SwingConstants;
import javax.swing.table.DefaultTableCellRenderer;

/**
 * Class for PaidTableCellRenderer
 *
 * @author Group 3, dmaa0214, UCN
 *
 */
public class PaidTableCellRenderer extends DefaultTableCellRenderer {
	
	private static final long serialVersionUID = 1L;

	/**
	 * Constructor for PaidTableCellRenderer objects.
	 *
	 */
	public PaidTableCellRenderer() {
		
	}

	@Override
	public Component getTableCellRendererComponent(JTable table, Object value,
			boolean isSelected, boolean hasFocus, int row, int column) {
		String str = (String) value;
		
		JComponent comp;
        if (str.equals("true") || str.equals("false")) {
        	comp = getCheckbox(Boolean.parseBoolean(str));
        	comp.setBackground(table.getBackground());
        } else {
        	comp = getLabel(str);
        }
        if (isSelected) {
			comp.setBackground(table.getSelectionBackground());
        }
		return comp;
	}

	private JComponent getLabel(String str) {
		JLabel comp = new JLabel(str);
		comp.setOpaque(true);
		comp.setBackground(Color.RED);
		comp.setHorizontalAlignment(SwingConstants.CENTER);
		return (JComponent) comp;
	}

	private JComponent getCheckbox(boolean selected) {
		JCheckBox comp = new JCheckBox("",selected);
		comp.setHorizontalAlignment(SwingConstants.CENTER);
		return (JComponent) comp;
	}

}
