package guiLayer.customer;

import guiLayer.MainGUI;
import guiLayer.extensions.JTextFieldLimit;
import guiLayer.extensions.Utilities;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.SwingConstants;
import javax.swing.UIManager;
import javax.swing.border.EmptyBorder;
import javax.swing.border.TitledBorder;

import com.jgoodies.forms.factories.FormFactory;
import com.jgoodies.forms.layout.ColumnSpec;
import com.jgoodies.forms.layout.FormLayout;
import com.jgoodies.forms.layout.RowSpec;

import ctrLayer.CustomerCtr;
import exceptions.DBException;
import exceptions.SubmitException;

/**
 * Class for CreateCustomerDialog
 *
 * @author Group 3, dmaa0214, UCN
 *
 */
public class CreateCustomerDialog extends JDialog {

	private static final long serialVersionUID = 1L;
	private final JPanel contentPanel = new JPanel();
	private JTextFieldLimit txtName;
	private JTextFieldLimit txtPhone;
	private JTextFieldLimit txtAddress;
	private JTextFieldLimit txtPostCode;
	private JTextFieldLimit txtCity;
	private JTextFieldLimit txtCvr;
	private JTextFieldLimit txtRegNr;
	private JTextFieldLimit txtVin;
	private JTextFieldLimit txtManufact;
	private JTextFieldLimit txtModel;
	private MainGUI parent;
	private JButton btnCreate;
	private JTextFieldLimit txtEmail;

	public CreateCustomerDialog(MainGUI parent) {
		setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
		this.parent = parent;
		buildDialog();
	}

	private void buildDialog() {
		setModalityType(ModalityType.APPLICATION_MODAL);
		setTitle("Opret kunde");
		setBounds(100, 100, 450, 300);
		getContentPane().setLayout(new BorderLayout());
		contentPanel.setBorder(new EmptyBorder(5, 5, 5, 5));
		getContentPane().add(contentPanel, BorderLayout.CENTER);
		contentPanel.setLayout(new FormLayout(new ColumnSpec[] {
				FormFactory.RELATED_GAP_COLSPEC,
				ColumnSpec.decode("center:default:grow"),
				FormFactory.RELATED_GAP_COLSPEC,
				ColumnSpec.decode("center:default:grow"),},
				new RowSpec[] {
				FormFactory.RELATED_GAP_ROWSPEC,
				RowSpec.decode("default:grow"),}));
		{
			JPanel kundePanel = new JPanel();
			kundePanel.setBorder(new TitledBorder(UIManager.getBorder("TitledBorder.border"), "Kundeinformation", TitledBorder.RIGHT, TitledBorder.TOP, null, new Color(0, 0, 0)));
			contentPanel.add(kundePanel, "2, 2, fill, fill");
			kundePanel.setLayout(new FormLayout(new ColumnSpec[] {
					FormFactory.RELATED_GAP_COLSPEC,
					ColumnSpec.decode("50dlu"),
					FormFactory.RELATED_GAP_COLSPEC,
					ColumnSpec.decode("default:grow"),},
				new RowSpec[] {
					FormFactory.RELATED_GAP_ROWSPEC,
					RowSpec.decode("fill:default"),
					FormFactory.RELATED_GAP_ROWSPEC,
					RowSpec.decode("fill:default"),
					FormFactory.RELATED_GAP_ROWSPEC,
					RowSpec.decode("fill:default"),
					FormFactory.RELATED_GAP_ROWSPEC,
					RowSpec.decode("fill:default"),
					FormFactory.RELATED_GAP_ROWSPEC,
					RowSpec.decode("fill:default"),
					FormFactory.RELATED_GAP_ROWSPEC,
					RowSpec.decode("fill:default"),
					FormFactory.RELATED_GAP_ROWSPEC,
					RowSpec.decode("fill:default"),
					FormFactory.RELATED_GAP_ROWSPEC,
					FormFactory.DEFAULT_ROWSPEC,}));
			{
				JLabel lblName = new JLabel("Navn");
				lblName.setHorizontalAlignment(SwingConstants.LEFT);
				kundePanel.add(lblName, "2, 2, left, default");
			}
			{
				txtName = new JTextFieldLimit(50, false);
				kundePanel.add(txtName, "4, 2, fill, top");
				txtName.setColumns(10);
			}
			{
				JLabel lblPhone = new JLabel("Tlf");
				kundePanel.add(lblPhone, "2, 4, left, default");
			}
			{
				txtPhone = new JTextFieldLimit(18, false);
				kundePanel.add(txtPhone, "4, 4, fill, top");
				txtPhone.setColumns(10);
			}
			{
				JLabel lblPostCode = new JLabel("Postnummer");
				kundePanel.add(lblPostCode, "2, 6, left, default");
			}
			{
				txtPostCode = new JTextFieldLimit(18, true);
				txtPostCode.setText("");
				kundePanel.add(txtPostCode, "4, 6, fill, top");
				txtPostCode.setColumns(10);
			}
			{
				JLabel lblCity = new JLabel("By");
				kundePanel.add(lblCity, "2, 8, left, default");
			}
			{
				txtCity = new JTextFieldLimit(50, false);
				txtCity.setText("");
				kundePanel.add(txtCity, "4, 8, fill, top");
				txtCity.setColumns(10);
			}
			{
				JLabel lblAddress = new JLabel("Adresse");
				kundePanel.add(lblAddress, "2, 10, left, default");
			}
			{
				txtAddress = new JTextFieldLimit(50, false);
				kundePanel.add(txtAddress, "4, 10, fill, top");
				txtAddress.setColumns(10);
			}
			{
				JLabel lblCvr = new JLabel("CVR-nr");
				lblCvr.setHorizontalAlignment(SwingConstants.LEFT);
				kundePanel.add(lblCvr, "2, 12, left, default");
			}
			{
				txtCvr = new JTextFieldLimit(20, true);
				txtCvr.setText("");
				kundePanel.add(txtCvr, "4, 12, fill, default");
				txtCvr.setColumns(10);
			}
			{
				btnCreate = new JButton("Opret");
				btnCreate.addActionListener(new ActionListener() {
					public void actionPerformed(ActionEvent e) {
						createCustomer();
					}
				});
				{
					JLabel lblEmail = new JLabel("Email");
					kundePanel.add(lblEmail, "2, 14, left, default");
				}
				{
					txtEmail = new JTextFieldLimit(100, false);
					kundePanel.add(txtEmail, "4, 14, fill, default");
					txtEmail.setColumns(10);
				}
				kundePanel.add(btnCreate, "2, 16, 3, 1, default, center");
			}
		}
		{
			JPanel bilPanel = new JPanel();
			bilPanel.setBorder(new TitledBorder(UIManager.getBorder("TitledBorder.border"), "Biloplysninger", TitledBorder.RIGHT, TitledBorder.TOP, null, new Color(0, 0, 0)));
			contentPanel.add(bilPanel, "4, 2, fill, fill");
			bilPanel.setLayout(new FormLayout(new ColumnSpec[] {
					FormFactory.RELATED_GAP_COLSPEC,
					ColumnSpec.decode("min(50dlu;default):grow"),
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
					FormFactory.DEFAULT_ROWSPEC,
					FormFactory.RELATED_GAP_ROWSPEC,
					FormFactory.DEFAULT_ROWSPEC,}));
			{
				JLabel lblRegNr = new JLabel("Regnr.");
				bilPanel.add(lblRegNr, "2, 2, left, default");
			}
			{
				txtRegNr = new JTextFieldLimit(10, false);
				txtRegNr.setText("");
				bilPanel.add(txtRegNr, "4, 2, fill, top");
				txtRegNr.setColumns(10);
			}
			{
				JLabel lblVin = new JLabel("Stelnr.");
				bilPanel.add(lblVin, "2, 4, left, default");
			}
			{
				txtVin = new JTextFieldLimit(100, false);
				bilPanel.add(txtVin, "4, 4, fill, default");
				txtVin.setColumns(10);
			}
			{
				JLabel lblManufact = new JLabel("Fabrikant");
				bilPanel.add(lblManufact, "2, 6, left, default");
			}
			{
				txtManufact = new JTextFieldLimit(30, false);
				bilPanel.add(txtManufact, "4, 6, fill, default");
				txtManufact.setColumns(10);
			}
			{
				JLabel lblModel = new JLabel("Model");
				bilPanel.add(lblModel, "2, 8, left, default");
			}
			{
				txtModel = new JTextFieldLimit(40, false);
				bilPanel.add(txtModel, "4, 8, fill, default");
				txtModel.setColumns(10);
			}
			{
				JButton btnGetCarInfo = new JButton("Hent biloplysninger");
				bilPanel.add(btnGetCarInfo, "2, 16, 3, 1, default, center");
			}
		}
		{

			this.setVisible(true);
		}
		parent.setDefaultButton(btnCreate);
	}

	private void createCustomer() {

		try {
			
			String name = Utilities.getTextFromReqField(txtName, "Navnet");
			String phone = Utilities.getTextFromReqField(txtPhone, "Tlf");
			
			int postalCode = txtPostCode.getValue();
			if (postalCode == -1) {
				throw new SubmitException("Postnummeret skal angives", txtPostCode);
			}
			
			String city = Utilities.getTextFromReqField(txtCity, "Byen");
			String address = Utilities.getTextFromReqField(txtAddress, "Adressen");
			
			int cvr = txtCvr.getValue();
			if (cvr != -1 && txtCvr.getText().length() < 4) {
				throw new SubmitException("CVRnummeret er for kort", txtCvr);
			}

			String email = txtEmail.getEmail();
			
			CustomerCtr cCtr = new CustomerCtr();
			cCtr.createCustomer(name, phone, address, postalCode, city, cvr, email, false);
			
			Utilities.showInformation(this, "Kunden er oprettet", "Kunde oprettet");
			
			this.dispose();
		} catch (SubmitException e) {
			e.showError();
		} catch (DBException e) {
			Utilities.showError(this, e.getMessage());
		}

	}

}
