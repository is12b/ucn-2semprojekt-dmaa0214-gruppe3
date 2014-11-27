package guiLayer;

import java.awt.BorderLayout;
import java.awt.FlowLayout;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import modelLayer.Customer;
import com.jgoodies.forms.layout.FormLayout;
import com.jgoodies.forms.layout.ColumnSpec;
import com.jgoodies.forms.layout.RowSpec;
import com.jgoodies.forms.factories.FormFactory;
import javax.swing.border.TitledBorder;
import javax.swing.JLabel;

/**
 * Class for CustomerInfoDialog
 *
 * @author Group 3, dmaa0214, UCN
 *
 */
public class CustomerInfoDialog extends JDialog {

	private final JPanel contentPanel = new JPanel();
	private Customer customer;

	/**
	 * Create the dialog.
	 */
	public CustomerInfoDialog(Customer customer) {
		setTitle("Kundeinformation");
		setBounds(100, 100, 450, 300);
		getContentPane().setLayout(new BorderLayout());
		contentPanel.setBorder(new EmptyBorder(5, 5, 5, 5));
		getContentPane().add(contentPanel, BorderLayout.CENTER);
		contentPanel.setLayout(new FormLayout(new ColumnSpec[] {
				FormFactory.RELATED_GAP_COLSPEC,
				ColumnSpec.decode("default:grow"),
				FormFactory.RELATED_GAP_COLSPEC,
				ColumnSpec.decode("default:grow"),},
			new RowSpec[] {
				FormFactory.RELATED_GAP_ROWSPEC,
				RowSpec.decode("default:grow"),}));
		{
			JPanel panel = new JPanel();
			panel.setBorder(new TitledBorder(null, "Kundeinformation", TitledBorder.LEADING, TitledBorder.TOP, null, null));
			contentPanel.add(panel, "2, 2, fill, fill");
			panel.setLayout(new FormLayout(new ColumnSpec[] {
					FormFactory.RELATED_GAP_COLSPEC,
					FormFactory.DEFAULT_COLSPEC,},
				new RowSpec[] {
					FormFactory.RELATED_GAP_ROWSPEC,
					FormFactory.DEFAULT_ROWSPEC,
					FormFactory.RELATED_GAP_ROWSPEC,
					FormFactory.DEFAULT_ROWSPEC,
					FormFactory.RELATED_GAP_ROWSPEC,
					FormFactory.DEFAULT_ROWSPEC,}));
			{
				JLabel lblName = new JLabel("Name: " + customer.getName());
				panel.add(lblName, "2, 2");
			}
			{
				JLabel lblPostCode = new JLabel("Post Code: " + customer.getPostalCode());
				panel.add(lblPostCode, "2, 4");
			}
			{
				JLabel lblCity = new JLabel("City: " + customer.getCity());
				panel.add(lblCity, "2, 6");
			}
		}
		{
			JPanel panel = new JPanel();
			panel.setBorder(new TitledBorder(null, "Bilinformation", TitledBorder.LEADING, TitledBorder.TOP, null, null));
			contentPanel.add(panel, "4, 2, fill, fill");
			panel.setLayout(new FormLayout(new ColumnSpec[] {},
				new RowSpec[] {}));
		}
		{
			JPanel buttonPane = new JPanel();
			getContentPane().add(buttonPane, BorderLayout.SOUTH);
			buttonPane.setLayout(new FormLayout(new ColumnSpec[] {
					ColumnSpec.decode("left:pref:grow"),
					ColumnSpec.decode("left:pref:grow"),},
				new RowSpec[] {
					FormFactory.LINE_GAP_ROWSPEC,
					RowSpec.decode("23px"),}));
			{
				JButton okButton = new JButton("OK");
				okButton.setActionCommand("OK");
				buttonPane.add(okButton, "1, 2, center, center");
				getRootPane().setDefaultButton(okButton);
			}
			{
				JButton cancelButton = new JButton("Annuller");
				cancelButton.setActionCommand("Cancel");
				buttonPane.add(cancelButton, "2, 2, center, center");
			}
			
			this.customer = customer;
			this.setVisible(true);
		}
	}

}
