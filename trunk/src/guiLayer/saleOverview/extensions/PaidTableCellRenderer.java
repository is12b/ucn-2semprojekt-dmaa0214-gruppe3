package guiLayer.saleOverview.extensions;

import java.awt.Component;
import java.awt.Label;

import javax.swing.Action;
import javax.swing.Icon;
import javax.swing.JCheckBox;
import javax.swing.JComponent;
import javax.swing.JLabel;
import javax.swing.JTable;
import javax.swing.table.TableCellRenderer;

/**
 * Class for PaidTableCellRenderer
 *
 * @author Group 3, dmaa0214, UCN
 *
 */
public class PaidTableCellRenderer extends JLabel implements
		TableCellRenderer {

	/**
	 * Constructor for PaidTableCellRenderer objects.
	 *
	 */
	public PaidTableCellRenderer() {
		// TODO Auto-generated constructor stub
	}

	@Override
	public Component getTableCellRendererComponent(JTable table, Object value,
			boolean isSelected, boolean hasFocus, int row, int column) {
		JComponent renderer;
		System.out.println(">"+value.toString()+"<");
		if (value.toString().equals("true")) {
			//renderer = getMo
			renderer = (JComponent) new JCheckBox("",true);//(JComponent) table.getDefaultRenderer(Boolean.class);
			renderer.setOpaque(true);
		} else {
			renderer = (JComponent) new JLabel("ALARM");
		}
		
		// TODO Auto-generated method stub
		return renderer;
	}

}
