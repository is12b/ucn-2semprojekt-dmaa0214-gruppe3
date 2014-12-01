package guiLayer.Order;

import guiLayer.MainGUI;
import guiLayer.extensions.DocumentListenerChange;
import guiLayer.extensions.TabbedPanel;

import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JToolBar;

import com.jgoodies.forms.layout.FormLayout;
import com.jgoodies.forms.layout.ColumnSpec;
import com.jgoodies.forms.layout.RowSpec;
import com.jgoodies.forms.factories.FormFactory;

import ctrLayer.CustomerCtr;
import ctrLayer.SaleCtr;
import ctrLayer.interfaceLayer.IFSaleCtr;

import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;
import javax.swing.JTextField;
import javax.swing.JLabel;

import java.awt.GridBagLayout;

import javax.swing.JButton;

import java.awt.Component;
import java.awt.Dialog.ModalityType;
import java.awt.GridBagConstraints;
import java.awt.Insets;

import javax.swing.border.MatteBorder;

import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

import javax.swing.border.TitledBorder;
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;
import javax.swing.UIManager;

import modelLayer.Car;
import modelLayer.Customer;
import modelLayer.Sale;

/**
 * Class for OrderPanel
 *
 * @author Group 3, dmaa0214, UCN
 *
 */
public class OrderPanel extends TabbedPanel {
	private JTextField txtCustomerName;
	private JTextField txtCustomerPhone;
	private JTextField txtCustomerCVR;
	private JTextField txtProductName;
	private JTextField txtProductNumber;
	private JTable table;
	private MainGUI parent;
	private JTextField txtCarRegNr;
	private JTextField txtCarVin;
	private ArrayList<JTextField> customerFields;
	private ArrayList<JTextField> carFields;
	private ArrayList<JTextField> productFields;
	private IFSaleCtr sCtr;
	private JLabel lblName;
	private JLabel lblPhone;
	private JLabel lblRegNr;
	private JLabel lblVin;
	private JLabel lblMileAge;
	private JPanel carPanel;
	private JPanel customerPanel;
	private JLabel lblCvr;
	private Sale sale;

	/**
	 * Constructors
	 */
	
	public OrderPanel(MainGUI parent) {
		this.parent = parent;
		sCtr = new SaleCtr();
		sale = sCtr.createSale();
		customerFields = new ArrayList<JTextField>();
		carFields = new ArrayList<JTextField>();
		productFields = new ArrayList<JTextField>();
		buildPanel();
	}
	
	/**
	 * Panels
	 */

	private void buildPanel() {
		setLayout(new FormLayout(new ColumnSpec[] {
				FormFactory.LABEL_COMPONENT_GAP_COLSPEC,
				FormFactory.DEFAULT_COLSPEC,
				FormFactory.RELATED_GAP_COLSPEC,
				ColumnSpec.decode("max(409dlu;default):grow"),},
			new RowSpec[] {
				FormFactory.LINE_GAP_ROWSPEC,
				RowSpec.decode("default:grow"),
				FormFactory.RELATED_GAP_ROWSPEC,
				RowSpec.decode("max(41dlu;default)"),
				FormFactory.LINE_GAP_ROWSPEC,}));
		
		JPanel panel_10 = new JPanel();
		add(panel_10, "2, 2, fill, fill");
		
		JScrollPane scrollPane = new JScrollPane();
		add(scrollPane, "4, 2, fill, fill");
		
		table = new JTable();
		table.setModel(new DefaultTableModel(
			new Object[][] {
				{null, null, null, null, null, null, null},
				{null, null, null, null, null, null, null},
				{null, null, null, null, null, null, null},
				{null, null, null, null, null, null, null},
				{null, null, null, null, null, null, null},
				{null, null, null, null, null, null, null},
			},
			new String[] {
				"New column", "New column", "New column", "New column", "New column", "New column", "New column"
			}
		));
		scrollPane.setViewportView(table);
		
		panel_10.setLayout(new FormLayout(new ColumnSpec[] {
				ColumnSpec.decode("max(149dlu;default)"),},
			new RowSpec[] {
				FormFactory.DEFAULT_ROWSPEC,
				FormFactory.RELATED_GAP_ROWSPEC,
				FormFactory.DEFAULT_ROWSPEC,
				FormFactory.RELATED_GAP_ROWSPEC,
				FormFactory.DEFAULT_ROWSPEC,
				FormFactory.RELATED_GAP_ROWSPEC,
				RowSpec.decode("default:grow"),}));
		JPanel panel_3 = new JPanel();
		panel_3.setBorder(new TitledBorder(UIManager.getBorder("TitledBorder.border"), "S\u00F8g Kunde", TitledBorder.LEADING, TitledBorder.TOP, null, new Color(0, 0, 0)));
		panel_10.add(panel_3, "1, 3, fill, fill");
		panel_3.setLayout(new FormLayout(new ColumnSpec[] {
				FormFactory.DEFAULT_COLSPEC,
				FormFactory.RELATED_GAP_COLSPEC,
				ColumnSpec.decode("max(93dlu;default):grow"),},
			new RowSpec[] {
				FormFactory.DEFAULT_ROWSPEC,
				FormFactory.RELATED_GAP_ROWSPEC,
				FormFactory.DEFAULT_ROWSPEC,
				FormFactory.RELATED_GAP_ROWSPEC,
				FormFactory.DEFAULT_ROWSPEC,
				FormFactory.RELATED_GAP_ROWSPEC,
				FormFactory.DEFAULT_ROWSPEC,}));
		
		JLabel lblNavn = new JLabel("Navn:");
		panel_3.add(lblNavn, "1, 1, left, default");
		
		txtCustomerName = new JTextField();
		panel_3.add(txtCustomerName, "3, 1, fill, default");
		txtCustomerName.setColumns(10);
		
		JLabel lblNewLabel_1 = new JLabel("Telefon:");
		panel_3.add(lblNewLabel_1, "1, 3, left, default");
		
		txtCustomerPhone = new JTextField();
		panel_3.add(txtCustomerPhone, "3, 3, fill, default");
		txtCustomerPhone.setColumns(10);
		
		JLabel lblNewLabel_2 = new JLabel("CVR:");
		panel_3.add(lblNewLabel_2, "1, 5, left, default");
		
		txtCustomerCVR = new JTextField();
		panel_3.add(txtCustomerCVR, "3, 5, fill, default");
		txtCustomerCVR.setColumns(10);
	
		customerFields.add(txtCustomerName);
		customerFields.add(txtCustomerCVR);
		customerFields.add(txtCustomerPhone);
		
		JPanel panel_4 = new JPanel();
		panel_3.add(panel_4, "1, 7, 3, 1, fill, fill");
		panel_4.setLayout(new FormLayout(new ColumnSpec[] {
				FormFactory.GROWING_BUTTON_COLSPEC,
				ColumnSpec.decode("26px"),
				FormFactory.GROWING_BUTTON_COLSPEC,},
			new RowSpec[] {
				RowSpec.decode("23px"),}));
		
		JButton btnCustomerSearch = new JButton("S\u00F8g");
		btnCustomerSearch.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				customerSearch();
			}
		});
		panel_4.add(btnCustomerSearch, "1, 1, fill, center");
		
		JButton btnCustomerClear = new JButton("Ryd");
		btnCustomerClear.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				clearCustomerSearch();
			}
		});
		panel_4.add(btnCustomerClear, "3, 1, fill, center");
		
		JPanel panel_2 = new JPanel();
		panel_2.setBorder(new TitledBorder(null, "S\u00F8g Produkt", TitledBorder.LEADING, TitledBorder.TOP, null, null));
		panel_10.add(panel_2, "1, 5, fill, fill");
		panel_2.setLayout(new FormLayout(new ColumnSpec[] {
				FormFactory.DEFAULT_COLSPEC,
				FormFactory.RELATED_GAP_COLSPEC,
				ColumnSpec.decode("default:grow"),},
			new RowSpec[] {
				FormFactory.DEFAULT_ROWSPEC,
				FormFactory.RELATED_GAP_ROWSPEC,
				FormFactory.DEFAULT_ROWSPEC,
				FormFactory.RELATED_GAP_ROWSPEC,
				FormFactory.DEFAULT_ROWSPEC,}));
		
		JLabel lblNewLabel_3 = new JLabel("Navn:");
		panel_2.add(lblNewLabel_3, "1, 1, left, default");
		
		txtProductName = new JTextField();
		panel_2.add(txtProductName, "3, 1, fill, default");
		txtProductName.setColumns(10);
		
		JLabel lblNewLabel_5 = new JLabel("Nummer:");
		panel_2.add(lblNewLabel_5, "1, 3, left, default");
		
		txtProductNumber = new JTextField();
		panel_2.add(txtProductNumber, "3, 3, fill, default");
		txtProductNumber.setColumns(10);
		
		productFields.add(txtProductName);
		productFields.add(txtProductNumber);
		
		JPanel panel_5 = new JPanel();
		panel_2.add(panel_5, "1, 5, 3, 1, fill, fill");
		panel_5.setLayout(new FormLayout(new ColumnSpec[] {
				FormFactory.GROWING_BUTTON_COLSPEC,
				ColumnSpec.decode("default:grow"),
				FormFactory.GROWING_BUTTON_COLSPEC,},
			new RowSpec[] {
				FormFactory.DEFAULT_ROWSPEC,}));
		
		JButton btnNewButton_1 = new JButton("S\u00F8g");
		panel_5.add(btnNewButton_1, "1, 1");
		
		JButton btnNewButton = new JButton("Ryd");
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				clearProductSearch();
			}
		});
		panel_5.add(btnNewButton, "3, 1");
		
		JPanel panel_1 = new JPanel();
		panel_1.setBorder(new TitledBorder(null, "S\u00F8g Bil", TitledBorder.LEADING, TitledBorder.TOP, null, null));
		panel_10.add(panel_1, "1, 1, fill, fill");
		panel_1.setLayout(new FormLayout(new ColumnSpec[] {
				FormFactory.DEFAULT_COLSPEC,
				FormFactory.RELATED_GAP_COLSPEC,
				ColumnSpec.decode("default:grow"),},
			new RowSpec[] {
				FormFactory.DEFAULT_ROWSPEC,
				FormFactory.RELATED_GAP_ROWSPEC,
				FormFactory.DEFAULT_ROWSPEC,
				FormFactory.RELATED_GAP_ROWSPEC,
				RowSpec.decode("default:grow"),}));
		
		JLabel lblNewLabel_10 = new JLabel("Regnr:");
		panel_1.add(lblNewLabel_10, "1, 1, left, default");
		
		txtCarRegNr = new JTextField();
		panel_1.add(txtCarRegNr, "3, 1, fill, default");
		txtCarRegNr.setColumns(10);
		
		JLabel lblNewLabel_11 = new JLabel("Stelnr:");
		panel_1.add(lblNewLabel_11, "1, 3, left, default");
		
		txtCarVin = new JTextField();
		panel_1.add(txtCarVin, "3, 3, fill, default");
		txtCarVin.setColumns(10);
		
		carFields.add(txtCarRegNr);
		carFields.add(txtCarVin);
		
		JPanel panel = new JPanel();
		panel_1.add(panel, "1, 5, 3, 1, fill, fill");
		panel.setLayout(new FormLayout(new ColumnSpec[] {
				FormFactory.GROWING_BUTTON_COLSPEC,
				FormFactory.RELATED_GAP_COLSPEC,
				FormFactory.DEFAULT_COLSPEC,
				FormFactory.RELATED_GAP_COLSPEC,
				FormFactory.GROWING_BUTTON_COLSPEC,},
			new RowSpec[] {
				FormFactory.DEFAULT_ROWSPEC,}));
		
		JButton btnCarSearch = new JButton("S\u00F8g");
		btnCarSearch.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				carSearch();
			}
		});
		panel.add(btnCarSearch, "1, 1");
		
		JButton btnCarClear = new JButton("Ryd");
		btnCarClear.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				clearCarSearch();
			}
		});
		panel.add(btnCarClear, "5, 1");
		
		JPanel panel_6 = new JPanel();
		add(panel_6, "2, 4, 3, 1, fill, fill");
		panel_6.setLayout(new FormLayout(new ColumnSpec[] {
				ColumnSpec.decode("max(103dlu;default)"),
				FormFactory.RELATED_GAP_COLSPEC,
				FormFactory.DEFAULT_COLSPEC,
				FormFactory.RELATED_GAP_COLSPEC,
				ColumnSpec.decode("default:grow"),
				FormFactory.RELATED_GAP_COLSPEC,
				ColumnSpec.decode("max(66dlu;default)"),},
			new RowSpec[] {
				RowSpec.decode("default:grow"),
				FormFactory.DEFAULT_ROWSPEC,
				FormFactory.DEFAULT_ROWSPEC,}));
		
		customerPanel = new JPanel();
		customerPanel.setBorder(new TitledBorder(UIManager.getBorder("TitledBorder.border"), "Kunde", TitledBorder.LEADING, TitledBorder.TOP, null, new Color(0, 0, 0)));
		panel_6.add(customerPanel, "1, 1, fill, fill");
		customerPanel.setLayout(new FormLayout(new ColumnSpec[] {
				FormFactory.DEFAULT_COLSPEC,
				FormFactory.RELATED_GAP_COLSPEC,
				ColumnSpec.decode("default:grow"),},
			new RowSpec[] {
				FormFactory.DEFAULT_ROWSPEC,
				FormFactory.NARROW_LINE_GAP_ROWSPEC,
				FormFactory.DEFAULT_ROWSPEC,
				FormFactory.NARROW_LINE_GAP_ROWSPEC,
				FormFactory.DEFAULT_ROWSPEC,}));
		
		JLabel lblNewLabel_6 = new JLabel("Navn:");
		customerPanel.add(lblNewLabel_6, "1, 1");
		
		lblName = new JLabel("Mikkel");
		customerPanel.add(lblName, "3, 1");
		
		JLabel lblNewLabel_4 = new JLabel("Telefon:");
		customerPanel.add(lblNewLabel_4, "1, 3");
		
		lblPhone = new JLabel("88888888");
		customerPanel.add(lblPhone, "3, 3");
		
		JLabel lblNewLabel_7 = new JLabel("CVR:");
		customerPanel.add(lblNewLabel_7, "1, 5");
		
		lblCvr = new JLabel("");
		customerPanel.add(lblCvr, "3, 5");
		
		carPanel = new JPanel();
		carPanel.setBorder(new TitledBorder(null, "Bil", TitledBorder.LEADING, TitledBorder.TOP, null, null));
		panel_6.add(carPanel, "3, 1, fill, fill");
		carPanel.setLayout(new FormLayout(new ColumnSpec[] {
				FormFactory.RELATED_GAP_COLSPEC,
				FormFactory.DEFAULT_COLSPEC,
				FormFactory.RELATED_GAP_COLSPEC,
				FormFactory.DEFAULT_COLSPEC,},
			new RowSpec[] {
				FormFactory.DEFAULT_ROWSPEC,
				FormFactory.NARROW_LINE_GAP_ROWSPEC,
				FormFactory.DEFAULT_ROWSPEC,
				FormFactory.NARROW_LINE_GAP_ROWSPEC,
				FormFactory.DEFAULT_ROWSPEC,}));
		
		JLabel lblNewLabel_9 = new JLabel("Regnr:");
		carPanel.add(lblNewLabel_9, "2, 1");
		
		lblRegNr = new JLabel("FA21981");
		carPanel.add(lblRegNr, "4, 1");
		
		JLabel lblStelnr = new JLabel("Stelnr:");
		carPanel.add(lblStelnr, "2, 3");
		
		lblVin = new JLabel("DetVedLasseNok");
		carPanel.add(lblVin, "4, 3");
		
		JLabel lblNewLabel_12 = new JLabel("Kilometer:");
		carPanel.add(lblNewLabel_12, "2, 5");
		
		lblMileAge = new JLabel("400000");
		carPanel.add(lblMileAge, "4, 5");
		
		JPanel panel_7 = new JPanel();
		panel_7.setBorder(new TitledBorder(null, "", TitledBorder.LEADING, TitledBorder.TOP, null, null));
		panel_6.add(panel_7, "7, 1, fill, fill");
		panel_7.setLayout(new FormLayout(new ColumnSpec[] {
				ColumnSpec.decode("44px"),
				FormFactory.LABEL_COMPONENT_GAP_COLSPEC,
				ColumnSpec.decode("24px:grow"),},
			new RowSpec[] {
				RowSpec.decode("14px"),
				FormFactory.NARROW_LINE_GAP_ROWSPEC,
				RowSpec.decode("14px"),
				FormFactory.NARROW_LINE_GAP_ROWSPEC,
				RowSpec.decode("14px"),}));
		
		JLabel lblSubtotal = new JLabel("Subtotal:");
		panel_7.add(lblSubtotal, "1, 1, left, center");
		
		JLabel lblNewLabel = new JLabel("1000");
		panel_7.add(lblNewLabel, "3, 1, right, center");
		
		JLabel lblMoms = new JLabel("Moms:");
		panel_7.add(lblMoms, "1, 3, left, center");
		
		JLabel label = new JLabel("250");
		panel_7.add(label, "3, 3, right, center");
		
		JLabel lblTotal = new JLabel("Total:");
		panel_7.add(lblTotal, "1, 5, left, center");
		
		JLabel label_1 = new JLabel("1250");
		panel_7.add(label_1, "3, 5, right, center");
		
		JPanel panel_9 = new JPanel();
		panel_6.add(panel_9, "1, 3, 7, 1, fill, fill");
		panel_9.setLayout(new FormLayout(new ColumnSpec[] {
				FormFactory.DEFAULT_COLSPEC,
				FormFactory.RELATED_GAP_COLSPEC,
				ColumnSpec.decode("default:grow"),
				FormFactory.RELATED_GAP_COLSPEC,
				FormFactory.DEFAULT_COLSPEC,},
			new RowSpec[] {
				FormFactory.RELATED_GAP_ROWSPEC,
				FormFactory.DEFAULT_ROWSPEC,}));
		
		JButton btnClear = new JButton("Ryd Faktura");
		panel_9.add(btnClear, "1, 2");
		
		JButton btnCommit = new JButton("Opret Faktura");
		panel_9.add(btnCommit, "5, 2");
		
		customerPanel.setVisible(false);
		carPanel.setVisible(false);
		
		parent.setDefaultButton(btnCommit);
		
		populateTextFields();
		
	}

	/**
	 * TextFields
	 */
	
	@Override
	public void setFocus() {
		txtCarRegNr.requestFocusInWindow();
	}

	private void populateTextFields() {
		addDocumentListener(customerFields);
		addDocumentListener(carFields);
		addDocumentListener(productFields);
	}

	private void addDocumentListener(ArrayList<JTextField> fields) {
		for(JTextField f : fields){
			f.getDocument().addDocumentListener(new DocumentListenerChange(fields, f));
		}
	}
	
	/**
	 * Customer
	 */
	
	private void customerSearch() {
		ArrayList<Customer> customers = new ArrayList<Customer>();
		
		if(txtCustomerCVR.isEnabled()){
			customers.add(sCtr.getCustomerByCvr(txtCustomerCVR.getText(), true));
		}else if(txtCustomerName.isEnabled()){
			customers = sCtr.searchCustomersByName(txtCustomerName.getText(), true);
		}else if(txtCustomerPhone.isEnabled()){
			customers = sCtr.searchCustomersByPhone(txtCustomerPhone.getText(), true);
		}
		
		createCustomerDialog(customers);	
		
	}
	
	private void createCustomerDialog(ArrayList<Customer> customers) {
		if(customers != null){
			if(customers.size() > 1){			
				CustomerDialog cDialog = new CustomerDialog(customers, this);
				cDialog.setModalityType(ModalityType.APPLICATION_MODAL);
				cDialog.setVisible(true);
			}else{
				if(customers.get(0).getCars() == null || customers.get(0).getCars().size() == 0){
					setCustomer(customers.get(0));
					setCar(null);
				} else if(customers.get(0).getCars().size() > 1){
					CustomerDialog cDialog = new CustomerDialog(customers, this);
					cDialog.setModalityType(ModalityType.APPLICATION_MODAL);
					cDialog.setVisible(true);
				} else if(customers.get(0).getCars().size() == 1){
					setCar(customers.get(0).getCars().get(0));
				}
			}
		}else{
			//TODO Error intet fundet
		}
	}

		

	public void setCustomer(Customer c){
		System.out.println("Customer Added");
		//TODO
		if(c != null){
			sCtr.setCustomer(c);
			populateCustomerPanel(c);
		}
	}
	

	private void populateCustomerPanel(Customer c) {
		lblName.setText(c.getName());
		lblPhone.setText(c.getPhoneNumber());
		lblCvr.setText(String.valueOf(c.getCvr()));
		customerPanel.setVisible(true);
	}

	
	private void clearCustomerSearch(){
		customerFields.forEach(c -> c.setText(""));
	}
	
	/**
	 * Car
	 */
	
	private void carSearch(){
		Car c = null;
				
		if(txtCarRegNr.isEnabled()){
			c = sCtr.getCarByRegNr(txtCarRegNr.getText(), true);
		}else if(txtCarVin.isEnabled()){
			c = sCtr.getCarByVin(txtCarVin.getText(), true);
		}
		
		setCar(c);
	}
	
	public void setCar(Car c){
		System.out.println("Car added");
		if(c != null){
			sCtr.setCar(c);
			setCustomer(c.getOwner());
			populateCarPanel(c);
		}else{
			clearCarPanel();
		}
	}
	
	
	private void populateCarPanel(Car c){
		lblRegNr.setText(c.getRegNr());
		lblVin.setText(c.getVin());
		lblMileAge.setText(String.valueOf(c.getMileage()));
		carPanel.setVisible(true);
	}
	
	
	private void clearCarPanel(){
		lblRegNr.setText("");
		lblVin.setText("");
		lblMileAge.setText("");
		carPanel.setVisible(false);
	}


	private void clearCarSearch(){
		carFields.forEach(c -> c.setText(""));
	}

	/**
	 * Product
	 */
	
	private void clearProductSearch(){
		productFields.forEach(p -> p.setText(""));
	}

}
