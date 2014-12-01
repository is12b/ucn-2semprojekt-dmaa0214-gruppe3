package guiLayer.extensions;

import java.awt.Color;

import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.border.MatteBorder;

import com.jgoodies.forms.factories.FormFactory;
import com.jgoodies.forms.layout.ColumnSpec;
import com.jgoodies.forms.layout.FormLayout;
import com.jgoodies.forms.layout.RowSpec;
import java.awt.BorderLayout;
import javax.swing.SwingConstants;

public class ProductListPanel extends JPanel {
	private boolean isSelected;
	private JPanel listPanel;

	/**
	 * Create the panel.
	 */
	public ProductListPanel(String itemNumber, String name, String desc, double price,
			String shortDescription, boolean isSelected) {
		this.isSelected = isSelected;
		setLayout(new FormLayout(
				new ColumnSpec[] { ColumnSpec.decode("default:grow"), },
				new RowSpec[] { RowSpec.decode("max(29dlu;default):grow"), }));
		listPanel = new JPanel();

		if (!isSelected) {
			listPanel.setBorder(new MatteBorder(2, 2, 2, 2, new Color(
					100, 0, 0)));
		} else {
			listPanel.setBorder(new MatteBorder(0, 0, 1, 0, new Color(
					0, 0, 0)));
		}

		add(listPanel, "1, 1, fill, fill");
		listPanel.setLayout(new FormLayout(new ColumnSpec[] {
				FormFactory.LABEL_COMPONENT_GAP_COLSPEC,
				ColumnSpec.decode("default:grow"),
				FormFactory.DEFAULT_COLSPEC,
				ColumnSpec.decode("default:grow"),
				FormFactory.LABEL_COMPONENT_GAP_COLSPEC,},
			new RowSpec[] {
				FormFactory.NARROW_LINE_GAP_ROWSPEC,
				RowSpec.decode("default:grow"),
				FormFactory.RELATED_GAP_ROWSPEC,
				RowSpec.decode("default:grow"),
				FormFactory.NARROW_LINE_GAP_ROWSPEC,
				RowSpec.decode("default:grow"),
				FormFactory.NARROW_LINE_GAP_ROWSPEC,}));
				
				JLabel lblNewLabel = new JLabel(name);
				listPanel.add(lblNewLabel, "2, 2, right, default");
				
				JLabel label = new JLabel(" / ");
				listPanel.add(label, "3, 2");
				
				JLabel lblNewLabel_2 = new JLabel(itemNumber);
				listPanel.add(lblNewLabel_2, "4, 2, left, default");
		
				JLabel lblNewLabel_1 = new JLabel(desc);
				lblNewLabel_1.setHorizontalAlignment(SwingConstants.CENTER);
				listPanel.add(lblNewLabel_1, "2, 4, 3, 1, center, default");
				
				JLabel lblNewLabel_3 = new JLabel(price + " pr. " + shortDescription);
				listPanel.add(lblNewLabel_3, "2, 6, 3, 1, center, default");

	}

}
