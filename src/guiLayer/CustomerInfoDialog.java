package guiLayer;

import guiLayer.extensions.JTextFieldLimit;

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

import ctrLayer.CustomerCtr;
import ctrLayer.interfaceLayer.IFCustomerCtr;
import dbLayer.DBCustomer;
import dbLayer.interfaceLayer.IFDBCustomer;

import javax.swing.border.TitledBorder;
import javax.swing.JLabel;
import javax.swing.JTextField;

import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

/**
 * Class for CustomerInfoDialog
 *
 * @author Group 3, dmaa0214, UCN
 *
 */
public class CustomerInfoDialog extends JDialog {

	private final JPanel contentPanel = new JPanel();
	private Customer customer;
	private JTextFieldLimit txtName;
	private JTextFieldLimit txtPost;
	private JTextFieldLimit txtCity;
	private JTextFieldLimit txtPhone;
	private JTextFieldLimit txtAddress;
	private JTextFieldLimit txtCvr;
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
					ColumnSpec.decode("default:grow"),
					FormFactory.RELATED_GAP_COLSPEC,
					ColumnSpec.decode("default:grow"),},
					new RowSpec[] {
					FormFactory.RELATED_GAP_ROWSPEC,
					FormFactory.DEFAULT_ROWSPEC,
					FormFactory.RELATED_GAP_ROWSPEC,
					FormFactory.DEFAULT_ROWSPEC,
					FormFactory.RELATED_GAP_ROWSPEC,
					FormFactory.DEFAULT_ROWSPEC,
					FormFactory.RELATED_GAP_ROWSPEC,
					FormFactory.DEFAULT_ROWSPEC,
					FormFactory.RELATED_GAP_ROWSPEC,
					FormFactory.DEFAULT_ROWSPEC,
					FormFactory.RELATED_GAP_ROWSPEC,
					FormFactory.DEFAULT_ROWSPEC,
					FormFactory.RELATED_GAP_ROWSPEC,
					FormFactory.DEFAULT_ROWSPEC,}));
			{
				JLabel lblName = new JLabel("Navn:");
				panel.add(lblName, "2, 2, left, default");
			}
			{
				txtName = new JTextFieldLimit(60, false);
				txtName.setText(customer.getName());
				panel.add(txtName, "4, 2, fill, default");
				txtName.setColumns(10);
			}
			{
				JLabel lblPostCode = new JLabel("Postnummer:");
				panel.add(lblPostCode, "2, 4, left, default");
			}
			{
				txtPost = new JTextFieldLimit(10, true);
				txtPost.setText("" + customer.getPostalCode());
				panel.add(txtPost, "4, 4, fill, default");
				txtPost.setColumns(10);
			}
			{
				JLabel lblCity = new JLabel("By:");
				panel.add(lblCity, "2, 6, left, default");
			}
			{
				txtCity = new JTextFieldLimit(50, false);
				txtCity.setText(customer.getCity());
				panel.add(txtCity, "4, 6, fill, default");
				txtCity.setColumns(10);
			}
			{
				JLabel lblPhone = new JLabel("Tlf:");
				panel.add(lblPhone, "2, 8, left, default");
			}
			{
				txtPhone = new JTextFieldLimit(18, false);
				txtPhone.setText(customer.getPhoneNumber());
				panel.add(txtPhone, "4, 8, fill, default");
				txtPhone.setColumns(10);
			}
			{
				JLabel lblAddress = new JLabel("Adresse:");
				panel.add(lblAddress, "2, 10, left, default");
			}
			{
				txtAddress = new JTextFieldLimit(60, false);
				txtAddress.setText(customer.getAddress());
				panel.add(txtAddress, "4, 10, fill, default");
				txtAddress.setColumns(10);
			}
			{
				JLabel lblCvr = new JLabel("CVR-nr:");
				panel.add(lblCvr, "2, 12, left, default");
			}
			{
				txtCvr = new JTextFieldLimit(20, true);
				txtCvr.setText("" + customer.getCvr());
				panel.add(txtCvr, "4, 12, fill, default");
				txtCvr.setColumns(10);
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
				okButton.addActionListener(new ActionListener() {
					public void actionPerformed(ActionEvent e) {
						okay();
					}
				});
				buttonPane.add(okButton, "1, 2, center, center");
				getRootPane().setDefaultButton(okButton);
			}
			{
				JButton cancelButton = new JButton("Annuller");
				cancelButton.addActionListener(new ActionListener() {
					public void actionPerformed(ActionEvent e) {
						closeWindow();
					}
				});
				buttonPane.add(cancelButton, "2, 2, center, center");
			}

			this.customer = customer;
			this.setVisible(true);
		}


	}

	private void okay() {
		updateCustomer();
		closeWindow();
	}

	/**
	 * 
	 */
	private void closeWindow() {
		this.setVisible(false);
		this.dispose();
	}

	/**
	 * @return 
	 * 
	 */
	private void updateCustomer() {
		IFCustomerCtr cCtr = new CustomerCtr();
		cCtr.updateCustomer(customer, txtName.getText(), txtPhone.getText(), txtAddress.getText(), Integer.parseInt(txtPost.getText()), Integer.parseInt(txtCvr.getText()), false);

	}

}
