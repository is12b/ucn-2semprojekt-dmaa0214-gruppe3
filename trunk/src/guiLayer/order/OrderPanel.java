package guiLayer.order;

import guiLayer.MainGUI;
import guiLayer.PDFViewerDialog;
import guiLayer.exceptions.BuildingPDFException;
import guiLayer.exceptions.SubmitException;
import guiLayer.extensions.DocumentListenerChange;
import guiLayer.extensions.TabbedPanel;
import guiLayer.extensions.Utilities;
import guiLayer.order.extensions.MileageDialog;
import guiLayer.order.extensions.OrderTableModel;

import java.awt.Color;
import java.awt.Dialog.ModalityType;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.ArrayList;

import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.SwingUtilities;
import javax.swing.UIManager;
import javax.swing.border.TitledBorder;

import modelLayer.Car;
import modelLayer.Customer;
import modelLayer.PartSale;
import modelLayer.Product;
import modelLayer.Sale;

import com.jgoodies.forms.factories.FormFactory;
import com.jgoodies.forms.layout.ColumnSpec;
import com.jgoodies.forms.layout.FormLayout;
import com.jgoodies.forms.layout.RowSpec;

import ctrLayer.SaleCtr;
import ctrLayer.exceptionLayer.ObjectNotExistException;
import ctrLayer.interfaceLayer.IFSaleCtr;

/**
 * Class for OrderPanel
 *
 * @author Group 3, dmaa0214, UCN
 *
 */
public class OrderPanel extends TabbedPanel {
	private static final long serialVersionUID = 1L;
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
	private OrderTableModel oTableModel;
	private JLabel lblSubTotal;
	private JLabel lblTax;
	private JLabel lblTotal;
	private JCheckBox chkPaid;
	private JButton btnDesc;
	private JButton btnMileage;

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
		add(panel_10, "1, 2, fill, fill");
		
		JScrollPane scrollPane = new JScrollPane();
		add(scrollPane, "3, 2, fill, fill");
		
		table = new JTable();
		table.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				tableMouseListener(e);
			}
		});
		oTableModel = new OrderTableModel();
		table.setModel(oTableModel);
		table.getColumnModel().getColumn(0).setPreferredWidth(20);
		table.getColumnModel().getColumn(4).setPreferredWidth(10);
		table.getColumnModel().getColumn(6).setPreferredWidth(20);
		scrollPane.setViewportView(table);
		
		panel_10.setLayout(new FormLayout(new ColumnSpec[] {
				ColumnSpec.decode("max(149dlu;default):grow"),},
			new RowSpec[] {
				FormFactory.DEFAULT_ROWSPEC,
				FormFactory.RELATED_GAP_ROWSPEC,
				FormFactory.DEFAULT_ROWSPEC,
				FormFactory.RELATED_GAP_ROWSPEC,
				FormFactory.DEFAULT_ROWSPEC,
				FormFactory.RELATED_GAP_ROWSPEC,
				RowSpec.decode("default:grow"),
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
				ColumnSpec.decode("20px"),
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
				RowSpec.decode("default:grow"),
				FormFactory.RELATED_GAP_ROWSPEC,
				FormFactory.DEFAULT_ROWSPEC,}));
		
		JLabel lblNewLabel_3 = new JLabel("Navn:");
		panel_2.add(lblNewLabel_3, "1, 1, left, default");
		
		txtProductName = new JTextField();
		panel_2.add(txtProductName, "3, 1, fill, default");
		txtProductName.setColumns(10);
		
		JPanel panel_12 = new JPanel();
		panel_2.add(panel_12, "1, 3, 3, 1, fill, fill");
		panel_12.setLayout(new FormLayout(new ColumnSpec[] {
				FormFactory.DEFAULT_COLSPEC,
				FormFactory.RELATED_GAP_COLSPEC,
				ColumnSpec.decode("default:grow"),},
			new RowSpec[] {
				FormFactory.DEFAULT_ROWSPEC,}));
		
		JLabel lblNewLabel_5 = new JLabel("Varenummer:");
		panel_12.add(lblNewLabel_5, "1, 1");
		
		txtProductNumber = new JTextField();
		panel_12.add(txtProductNumber, "3, 1");
		txtProductNumber.setColumns(10);
		productFields.add(txtProductNumber);
		
		productFields.add(txtProductName);
		
		JPanel panel_5 = new JPanel();
		panel_2.add(panel_5, "1, 5, 3, 1, fill, fill");
		panel_5.setLayout(new FormLayout(new ColumnSpec[] {
				FormFactory.GROWING_BUTTON_COLSPEC,
				ColumnSpec.decode("20px"),
				FormFactory.GROWING_BUTTON_COLSPEC,},
			new RowSpec[] {
				FormFactory.DEFAULT_ROWSPEC,}));
		
		JButton btnProductSearch = new JButton("S\u00F8g");
		btnProductSearch.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				searchProduct();
			}
		});
		panel_5.add(btnProductSearch, "1, 1");
		
		JButton btnProductClear = new JButton("Ryd");
		btnProductClear.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				clearProductSearch();
			}
		});
		panel_5.add(btnProductClear, "3, 1");
		
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
				ColumnSpec.decode("20px"),
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
		panel.add(btnCarClear, "3, 1");
		
		JPanel panel_11 = new JPanel();
		panel_10.add(panel_11, "1, 7, fill, fill");
		panel_11.setLayout(new FormLayout(new ColumnSpec[] {
				FormFactory.RELATED_GAP_COLSPEC,
				ColumnSpec.decode("88dlu"),
				FormFactory.RELATED_GAP_COLSPEC,
				FormFactory.DEFAULT_COLSPEC,},
			new RowSpec[] {
				FormFactory.DEFAULT_ROWSPEC,}));
		
		btnDesc = new JButton("Tilf\u00F8j Beskrivelse");
		btnDesc.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				createDescriptionDialog();
			}
		});
		
		btnMileage = new JButton("Tilf\u00F8j Kilometerstand");
		btnMileage.setVisible(false);
		btnMileage.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				createMileageDialog();
			}
		});
		panel_11.add(btnMileage, "2, 1");
		panel_11.add(btnDesc, "4, 1");
		
		JPanel panel_6 = new JPanel();
		add(panel_6, "1, 4, 3, 1, fill, fill");
		panel_6.setLayout(new FormLayout(new ColumnSpec[] {
				ColumnSpec.decode("max(103dlu;default)"),
				FormFactory.RELATED_GAP_COLSPEC,
				ColumnSpec.decode("max(90dlu;default)"),
				FormFactory.RELATED_GAP_COLSPEC,
				ColumnSpec.decode("default:grow"),
				FormFactory.RELATED_GAP_COLSPEC,
				ColumnSpec.decode("max(66dlu;default)"),
				FormFactory.LABEL_COMPONENT_GAP_COLSPEC,},
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
		
		lblName = new JLabel("");
		customerPanel.add(lblName, "3, 1");
		
		JLabel lblNewLabel_4 = new JLabel("Telefon:");
		customerPanel.add(lblNewLabel_4, "1, 3");
		
		lblPhone = new JLabel("");
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
		
		lblRegNr = new JLabel("");
		carPanel.add(lblRegNr, "4, 1");
		
		JLabel lblStelnr = new JLabel("Stelnr:");
		carPanel.add(lblStelnr, "2, 3");
		
		lblVin = new JLabel("");
		carPanel.add(lblVin, "4, 3");
		
		JLabel lblNewLabel_12 = new JLabel("Kilometer:");
		carPanel.add(lblNewLabel_12, "2, 5");
		
		lblMileAge = new JLabel("");
		carPanel.add(lblMileAge, "4, 5");
		
		JPanel panel_8 = new JPanel();
		panel_6.add(panel_8, "5, 1, fill, fill");
		panel_8.setLayout(new FormLayout(new ColumnSpec[] {
				FormFactory.RELATED_GAP_COLSPEC,
				ColumnSpec.decode("default:grow"),
				FormFactory.RELATED_GAP_COLSPEC,
				FormFactory.DEFAULT_COLSPEC,},
			new RowSpec[] {
				FormFactory.DEFAULT_ROWSPEC,}));
		
		chkPaid = new JCheckBox("Betalt");
		panel_8.add(chkPaid, "4, 1");
		
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
		
		lblSubTotal = new JLabel("0");
		panel_7.add(lblSubTotal, "3, 1, right, center");
		
		JLabel lblMoms = new JLabel("Moms:");
		panel_7.add(lblMoms, "1, 3, left, center");
		
		lblTax = new JLabel("0");
		panel_7.add(lblTax, "3, 3, right, center");
		
		JLabel lblTotall = new JLabel("Total:");
		panel_7.add(lblTotall, "1, 5, left, center");
		
		lblTotal = new JLabel("0");
		panel_7.add(lblTotal, "3, 5, right, center");
		
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
		btnClear.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				clearOrder();
			}
		});
		panel_9.add(btnClear, "1, 2");
		
		JButton btnCommit = new JButton("Opret Faktura");
		btnCommit.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				commit();
			}
		});
		panel_9.add(btnCommit, "5, 2");
		
		parent.setDefaultButton(btnCommit);
		
		populateTextFields();
		
	}

	private void tableMouseListener(MouseEvent e) {
		int rowNumber = table.rowAtPoint(e.getPoint());
		int modelRowNum = table.convertRowIndexToModel(rowNumber);
        table.setRowSelectionInterval(rowNumber, rowNumber);
        if (SwingUtilities.isLeftMouseButton(e) && e.getClickCount() == 2) {
        	PartSale pSale = oTableModel.getPartSaleAt(modelRowNum);
        	
        	PartSaleDialog pDialog = new PartSaleDialog(pSale, this);
			pDialog.setModalityType(ModalityType.APPLICATION_MODAL);
			pDialog.setVisible(true);
			
			refresh();
        }else if(SwingUtilities.isRightMouseButton(e) && e.getClickCount() == 2){
        	PartSale pSale = oTableModel.getPartSaleAt(modelRowNum);
        	sCtr.removePartSale(pSale);
        	
        	refresh();

        }
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
		
		try{
			if(txtCustomerCVR.isEnabled()){
				if(!txtCustomerCVR.getText().isEmpty()){
					customers.add(sCtr.getCustomerByCvr(txtCustomerCVR.getText(), true));
				}
			}else if(txtCustomerName.isEnabled()){
				if(!txtCustomerName.getText().isEmpty()){
					customers = sCtr.searchCustomersByName(txtCustomerName.getText(), true);
				}
			}else if(txtCustomerPhone.isEnabled()){
				if(!txtCustomerPhone.getText().isEmpty()){
					customers = sCtr.searchCustomersByPhone(txtCustomerPhone.getText(), true);
				}
			}
		}catch(ObjectNotExistException e){
			Utilities.showError(this, e.getMessage());
		}
		
		if(customers != null && !(customers.size() == 0)){
			createCustomerDialog(customers);
		}
		
	}
	
	private void createCustomerDialog(ArrayList<Customer> customers) {
		if(customers != null){
			if(customers.size() > 1){			
				CustomerDialog cDialog = new CustomerDialog(customers, this);
				cDialog.setModalityType(ModalityType.APPLICATION_MODAL);
				cDialog.setVisible(true);
			}else{
				if(customers.get(0).getCars() == null || customers.get(0).getCars().size() == 0){
					int c = Utilities.showConfirm(this, "Vil du tilføje " + customers.get(0).getName() + " til faktura?", "Tilføj Kunde");
					
					if(c == JOptionPane.YES_OPTION){
						setCustomer(customers.get(0));
						setCar(null);
					}
				} else if(customers.get(0).getCars().size() > 1){
					CustomerDialog cDialog = new CustomerDialog(customers, this);
					cDialog.setModalityType(ModalityType.APPLICATION_MODAL);
					cDialog.setVisible(true);
				} else if(customers.get(0).getCars().size() == 1){
					setCar(customers.get(0).getCars().get(0));
				}
			}
		}else{
			Utilities.showInformation(this, "Ingen resulater fundet", "Ingen Resultater");
		}
	}

		

	public void setCustomer(Customer c){
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
		
		try{
			if(txtCarRegNr.isEnabled()){
				if(!txtCarRegNr.getText().isEmpty()){
					c = sCtr.getCarByRegNr(txtCarRegNr.getText(), true);
				}
			}else if(txtCarVin.isEnabled()){
				if(!txtCarVin.getText().isEmpty()){
					c = sCtr.getCarByVin(txtCarVin.getText(), true);
				}
			}
		}catch(ObjectNotExistException e){
			Utilities.showError(this, e.getMessage());
		}
		
		if(c != null){
			setCar(c);
		}
	}
	
	public void setCar(Car c){
		System.out.println("Car added");
		if(c != null){
			sCtr.setCar(c);
			btnMileage.setVisible(true);
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
	}
	
	
	private void clearCarPanel(){
		lblRegNr.setText("");
		lblVin.setText("");
		lblMileAge.setText("");
	}


	private void clearCarSearch(){
		carFields.forEach(c -> c.setText(""));
	}

	/**
	 * Product
	 */
	
	private void searchProduct() {
		ArrayList<Product> products = new ArrayList<Product>();
		
		try{
			if(txtProductName.isEnabled()){
				if(!txtProductName.getText().isEmpty()){
					products = sCtr.searchProductsByName(txtProductName.getText());
				}
			}else if(txtProductNumber.isEnabled()){
				if(!txtProductNumber.getText().isEmpty()){
					products = sCtr.searchProductsByItemNumber(txtProductNumber.getText());
				}
			}
		}catch(ObjectNotExistException e){
			Utilities.showError(this, e.getMessage());
		}
		
		createProductDialog(products);
	}

	private void createProductDialog(final ArrayList<Product> products) {
		if(products != null){
			if(products.size() > 1){
				ProductDialog pDialog = new ProductDialog(products, this);
				pDialog.setModalityType(ModalityType.APPLICATION_MODAL);
				pDialog.setVisible(true);
			}else if(products.size() == 1){
				PartSaleDialog pDialog = new PartSaleDialog(products.get(0), this);
				pDialog.setModalityType(ModalityType.APPLICATION_MODAL);
				pDialog.setVisible(true);
			}
		}
	}

	private void clearProductSearch(){
		productFields.forEach(p -> p.setText(""));
	}
	
	/**
	 * PartSale
	 */
	
	void addPartSale(Product product, double amount, double unitPrice){
		sCtr.createPartSale(product, amount, unitPrice);
		refresh();
	}	

	/**
	 * Price
	 */
	
	private void updatePrice(){
		double subTotal = 0;
		ArrayList<PartSale> pSales = sale.getPartSales();
		for(PartSale p : pSales){
			subTotal += p.getAmount() * p.getUnitPrice();
		}
		
		setPrice(subTotal);
	}
	
	private void setPrice(double subTotal){
		lblSubTotal.setText(String.valueOf(subTotal));
		double tax = subTotal * 0.25;
		lblTax.setText(String.valueOf(tax));
		lblTotal.setText(String.valueOf(tax + subTotal));
	}
	
	/**
	 * Description
	 */
	
	private void createDescriptionDialog(){
		DescriptionDialog dDialog = new DescriptionDialog(this);
		dDialog.setVisible(true);
	}
	
	public void setDescription(String desc){
		sCtr.addDescription(desc);
		toggleDesc();
	}
	
	public String getDescription(){
		return sCtr.getDescription();
	}
	
	private void toggleDesc(){
		final String descChange = "Ret Beskrivelse";
		final String descAdd = "Tilføj Beskrivelse";
		if(getDescription().trim().isEmpty()){
			btnDesc.setText(descAdd);
		}else{
			btnDesc.setText(descChange);
		}
	}
	
	/**
	 * Mileage
	 */
	
	private void createMileageDialog() {
		MileageDialog mDialog = new MileageDialog(this);
		mDialog.setModalityType(ModalityType.APPLICATION_MODAL);
		mDialog.setVisible(true);
	}
	
	public void setMileage(int mileage){
		if(mileage != 0){
			btnMileage.setText("Ret Kilometerstand");
		}else{
			btnMileage.setText("Tilføj Kilometerstand");
		}
		sCtr.addMileage(mileage);
		
	}
	
	public int getMileage() {
		return sCtr.getMileage();
	}
	
	/**
	 * Commit
	 */
	
	private void commit() {
		int c = Utilities.showConfirm(this, "Er du sikker på du vil Oprette Faktura?", "Opret Faktura?");
		
		if(c == JOptionPane.YES_OPTION){
			try {
				sCtr.setPaid(chkPaid.isSelected());
				Sale s = sCtr.commit();

				new PDFViewerDialog(this, s.getId());
				
				clearOrder();
			} catch (SubmitException e) {
				Utilities.showError(this, e.getMessage());
				e.printStackTrace();
			} catch (BuildingPDFException e) {
				Utilities.showError(this, e.getMessage());
				e.printStackTrace();
			}
		}
	}
	
	/**
	 * Misc
	 */
	
	private void clearOrder(){
		parent.recreateOrderPanel();
	}
	
	private void refresh() {
		oTableModel.refresh(sale.getPartSales());
		oTableModel.fireTableDataChanged();
		updatePrice();
	}
}
