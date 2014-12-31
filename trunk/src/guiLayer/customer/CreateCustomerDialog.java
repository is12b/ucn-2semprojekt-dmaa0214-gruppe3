package guiLayer.customer;

import exceptions.DBException;
import exceptions.ObjectNotExistException;
import exceptions.SubmitException;
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
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.SwingConstants;
import javax.swing.UIManager;
import javax.swing.border.EmptyBorder;
import javax.swing.border.TitledBorder;

import modelLayer.Car;

import com.jgoodies.forms.factories.FormFactory;
import com.jgoodies.forms.layout.ColumnSpec;
import com.jgoodies.forms.layout.FormLayout;
import com.jgoodies.forms.layout.RowSpec;

import ctrLayer.CustomerCtr;
import ctrLayer.interfaceLayer.IFCustomerCtr;

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
	private JTextFieldLimit txtBrand;
	private JTextFieldLimit txtModel;
	private MainGUI parent;
	private JButton btnCreate;
	private JTextFieldLimit txtEmail;
	private Car car;
	private JTextFieldLimit txtYear;
	private JTextFieldLimit txtMileage;

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
			JPanel carPanel = new JPanel();
			carPanel.setBorder(new TitledBorder(UIManager.getBorder("TitledBorder.border"), "Biloplysninger", TitledBorder.RIGHT, TitledBorder.TOP, null, new Color(0, 0, 0)));
			contentPanel.add(carPanel, "4, 2, fill, fill");
			carPanel.setLayout(new FormLayout(new ColumnSpec[] {
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
				carPanel.add(lblRegNr, "2, 2, left, default");
			}
			{
				txtRegNr = new JTextFieldLimit(10, false);
				txtRegNr.setText("");
				carPanel.add(txtRegNr, "4, 2, fill, top");
				txtRegNr.setColumns(10);
			}
			{
				JLabel lblVin = new JLabel("Stelnr.");
				carPanel.add(lblVin, "2, 4, left, default");
			}
			{
				txtVin = new JTextFieldLimit(100, false);
				carPanel.add(txtVin, "4, 4, fill, default");
				txtVin.setColumns(10);
			}
			{
				JButton btnGetCarInfo = new JButton("Hent biloplysninger");
				btnGetCarInfo.addActionListener(new ActionListener() {
					public void actionPerformed(ActionEvent e) {
						getCarInfo();
					}
				});
				carPanel.add(btnGetCarInfo, "2, 6, 3, 1, center, center");
				{
					JLabel lblBrand = new JLabel("Fabrikant");
					carPanel.add(lblBrand, "2, 8, left, default");
				}
				{
					txtBrand = new JTextFieldLimit(30, false);
					carPanel.add(txtBrand, "4, 8, fill, default");
					txtBrand.setColumns(10);
				}
				{
					JLabel lblModel = new JLabel("Model");
					carPanel.add(lblModel, "2, 10, left, default");
				}
				{
					txtModel = new JTextFieldLimit(40, false);
					carPanel.add(txtModel, "4, 10, fill, default");
					txtModel.setColumns(10);
				}
				{
					JLabel lblYear = new JLabel("\u00C5rgang");
					carPanel.add(lblYear, "2, 12, left, default");
				}
				{
					txtYear = new JTextFieldLimit(6, true);
					carPanel.add(txtYear, "4, 12, fill, default");
					txtYear.setColumns(10);
				}
				{
					JLabel lblMileage = new JLabel("Km. stand");
					carPanel.add(lblMileage, "2, 14, left, default");
				}
				{
					txtMileage = new JTextFieldLimit(8, true);
					carPanel.add(txtMileage, "4, 14, fill, default");
					txtMileage.setColumns(10);
				}
			}
		}
		{

			this.setVisible(true);
		}
		parent.setDefaultButton(btnCreate);
	}

	private void getCarInfo() {
		IFCustomerCtr cCtr = new CustomerCtr();
		String regNr = txtRegNr.getText().trim();
		String vin = txtVin.getText().trim();
		Car car = null;
		try {
			if (!regNr.isEmpty()) {
				car = cCtr.getCarData(regNr);
			} else if (!vin.isEmpty()) {
				car = cCtr.getCarData(vin);
			} else {
				throw new SubmitException("Regnr. eller stelnr. skal udfyldes for at bruge denne funktion", txtRegNr);
			}
			
			if (confirmCarInfo(car)) {
				this.car = car;
				setCarInfo(car);
			}
			
			
		} catch (ObjectNotExistException e) {
			if (!regNr.isEmpty()) {
				txtRegNr.requestFocusInWindow();
			} else if (!vin.isEmpty()) {
				txtVin.requestFocusInWindow();
			}
			Utilities.showError(this,e.getMessage());
		} catch (SubmitException e) {
			e.showError();
		}
	}

	/**
	 * @param car2
	 * @return
	 */
	private boolean confirmCarInfo(Car car) {
		String msg = "Bekræft venligst at den fundne bil er den ønskede:"
				+ "\n"
				+ "\nMærke: " + car.getBrand() + ", " + car.getModel()
				+ "\nFørste Reg.dato: " + car.getYear()
				+ "\nRegnr.: " + car.getRegNr()
				+ "\nStelnr. : " + car.getVin();
		int c = JOptionPane.showConfirmDialog(this, msg, "Bekræft", JOptionPane.YES_NO_OPTION);
		boolean ret = false;
		if (c == JOptionPane.YES_OPTION) {
			ret = true;
		}
		return ret;
	}

	private void setCarInfo(Car car) {
		if (car != null) {
			txtRegNr.setText(car.getRegNr());
			txtVin.setText(car.getVin());
			txtBrand.setText(car.getBrand());
			txtModel.setText(car.getModel());
			if (car.getYear() > 0) {
				txtYear.setText(String.valueOf(car.getYear()));
			}
			/*
			ArrayList<Inspection> inspecs = car.getInspections();
			System.out.println("inspecs: " + inspecs);
			if (inspecs != null && inspecs.size() != 0) {
				String mileage = inspecs.get(0).getKm();
				System.out.println(inspecs.get(0));
				System.out.println(mileage);
				mileage = mileage.replace(".", "");
				System.out.println(">"+mileage+"<");
				txtMileage.setText(mileage);
			}*/
		}
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
			
			String regNr = txtRegNr.getText().trim();
			String vin = txtVin.getText().trim();
			String brand = txtBrand.getText().trim();
			String model = txtModel.getText().trim();
			int year = txtYear.getValue();
			int mileage = txtMileage.getValue();
			
			IFCustomerCtr cCtr = new CustomerCtr();
			cCtr.createCustomer(name, phone, address, postalCode, city, cvr, email, false, car, regNr, vin, brand, model, mileage, year);
			
			Utilities.showInformation(this, "Kunden er oprettet", "Kunde oprettet");
			
			this.dispose();
		} catch (SubmitException e) {
			e.showError(this);
		} catch (DBException e) {
			Utilities.showError(this, e.getMessage());
		}

	}

}
