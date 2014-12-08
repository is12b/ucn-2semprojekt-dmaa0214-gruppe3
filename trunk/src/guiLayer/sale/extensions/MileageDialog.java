package guiLayer.sale.extensions;

import guiLayer.extensions.JTextFieldLimit;
import guiLayer.sale.OrderPanel;

import java.awt.BorderLayout;
import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import com.jgoodies.forms.factories.FormFactory;
import com.jgoodies.forms.layout.ColumnSpec;
import com.jgoodies.forms.layout.FormLayout;
import com.jgoodies.forms.layout.RowSpec;

/**
 * Class for MileageDialog
 *
 * @author Group 3, dmaa0214, UCN
 *
 */
public class MileageDialog extends JDialog {
	private static final long serialVersionUID = 1L;
	private final JPanel contentPanel = new JPanel();
	private JTextFieldLimit txtMileage;
	private OrderPanel parent;

	/**
	 * Create the dialog.
	 */
	public MileageDialog(OrderPanel parent) {
		this.parent = parent;
		setTitle("Kilometerstand");
		setBounds(100, 100, 246, 104);
		getContentPane().setLayout(new BorderLayout());
		contentPanel.setBorder(new EmptyBorder(5, 5, 5, 5));
		getContentPane().add(contentPanel, BorderLayout.CENTER);
		contentPanel.setLayout(new FormLayout(new ColumnSpec[] {
				FormFactory.DEFAULT_COLSPEC,
				FormFactory.RELATED_GAP_COLSPEC,
				ColumnSpec.decode("default:grow"),},
			new RowSpec[] {
				FormFactory.DEFAULT_ROWSPEC,}));
		{
			JLabel lblNewLabel = new JLabel("Kilometerstand:");
			contentPanel.add(lblNewLabel, "1, 1, left, default");
		}
		{
			txtMileage = new JTextFieldLimit(20, true);
			txtMileage.setText(String.valueOf(parent.getMileage()));
			contentPanel.add(txtMileage, "3, 1, fill, default");
			txtMileage.setColumns(10);
		}
		{
			JPanel buttonPane = new JPanel();
			buttonPane.setLayout(new FlowLayout(FlowLayout.RIGHT));
			getContentPane().add(buttonPane, BorderLayout.SOUTH);
			{
				JButton okButton = new JButton("Gem");
				okButton.addActionListener(new ActionListener() {
					public void actionPerformed(ActionEvent e) {
						saveMileage();
					}
				});
				okButton.setActionCommand("OK");
				buttonPane.add(okButton);
				getRootPane().setDefaultButton(okButton);
			}
			{
				JButton cancelButton = new JButton("Annuller");
				cancelButton.addActionListener(new ActionListener() {
					public void actionPerformed(ActionEvent e) {
						MileageDialog.this.dispose();
					}
				});
				cancelButton.setActionCommand("Cancel");
				buttonPane.add(cancelButton);
			}
		}
	}

	/**
	 * 
	 */
	private void saveMileage() {
		
		if(txtMileage.getValue() != -1){
			parent.setMileage(txtMileage.getValue());
		}else{
			parent.setMileage(0);
		}
		
		this.dispose();
	}
	
}
