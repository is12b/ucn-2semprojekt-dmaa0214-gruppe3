package guiLayer.product;

import guiLayer.exceptions.SubmitException;
import guiLayer.extensions.JTextFieldLimit;
import guiLayer.extensions.Utilities;
import guiLayer.product.models.UnitTypeComboBoxModel;

import java.awt.BorderLayout;
import java.awt.CardLayout;
import java.awt.Dimension;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JFormattedTextField;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.border.EmptyBorder;
import javax.swing.JLabel;

import java.awt.Font;

import javax.swing.SwingConstants;

import com.jgoodies.forms.layout.FormLayout;
import com.jgoodies.forms.layout.ColumnSpec;
import com.jgoodies.forms.factories.FormFactory;
import com.jgoodies.forms.layout.RowSpec;

import ctrLayer.ProductCtr;
import ctrLayer.exceptionLayer.ObjectNotExistException;
import ctrLayer.interfaceLayer.IFProductCtr;
import dbLayer.exceptions.DBException;

import javax.swing.JComboBox;

import modelLayer.Product;
import modelLayer.UnitType;

import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.text.DecimalFormat;
import java.text.ParseException;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

/**
 * Dialog for create or edit a product
 *
 * @author Group 3, dmaa0214, UCN
 *
 */
public class CreateProductDialog extends JDialog {

	private static final long serialVersionUID = 1L;
	private JFormattedTextField txtPrice;
	private JTextFieldLimit txtName;
	private JTextFieldLimit txtDesc;
	private JTextFieldLimit txtBrand;
	private JTextFieldLimit txtItemNumber;
	private UnitTypeComboBoxModel cmbModel;
	private JComboBox<String> cmbUnitType;
	private JLabel lblTitle;
	private JPanel cardPanel;
	private Product product;
	private JButton btnCreate;
	private JButton btnEdit;
	private static DecimalFormat decimalFormat;

	
	
	/**
	 * Constructor for make an editing Dialog
	 * 
	 * @param parent the parent of the dialog
	 * @param product the product to edit
	 */
	public CreateProductDialog(ProductPanel parent, Product product) {
		
		setTitle("Ændre Produkt #" + product.getId());
		this.product = product;
		buildDialog();
		CardLayout cl = (CardLayout)(cardPanel.getLayout());
		cl.show(cardPanel, "editing");
		lblTitle.setText("Ændre Produkt #" + product.getId());
		
		txtName.setText(product.getName());
		txtDesc.setText(product.getDescription());
		txtBrand.setText(product.getBrand());
		txtItemNumber.setText(product.getItemNumber());
		//Number tempPrice = product.getPrice();
		txtPrice.setValue(product.getPrice());
		cmbUnitType.setSelectedItem(product.getUnitType().toString());
		
		pack();
		setVisible(true);

		setLocationRelativeTo(parent);
		getRootPane().setDefaultButton(btnEdit);
		
	}

	/**
	 * Constructor for make an creating Dialog.
	 * 
	 * @param parent the parent of the dialog
	 */
	public CreateProductDialog(ProductPanel parent) {
				
		setTitle("Opret Produkt");
		buildDialog();
		
		pack();
		setVisible(true);
		
		setLocationRelativeTo(parent);
		
		getRootPane().setDefaultButton(btnCreate);
	}

	/**
	 * Create the dialog.
	 * @param productPanel 
	 */
	private void buildDialog() {
		setModalityType(DEFAULT_MODALITY_TYPE);
		
		setMinimumSize(new Dimension(300, 280));
		
		getContentPane().setLayout(new BorderLayout());
		
		JPanel topPanel = new JPanel();
		topPanel.setBorder(new EmptyBorder(3, 3, 3, 3));
		getContentPane().add(topPanel, BorderLayout.NORTH);
		
		lblTitle = new JLabel("Opret Produkt");
		lblTitle.setHorizontalAlignment(SwingConstants.CENTER);
		lblTitle.setFont(new Font("Tahoma", Font.BOLD, 14));
		topPanel.add(lblTitle);
		
		JPanel mainPanel = new JPanel();
		mainPanel.setBorder(new EmptyBorder(0, 5, 0, 5));
		getContentPane().add(mainPanel, BorderLayout.CENTER);
		mainPanel.setLayout(new FormLayout(new ColumnSpec[] {
				FormFactory.DEFAULT_COLSPEC,
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
				FormFactory.DEFAULT_ROWSPEC,}));
		
		JLabel lblName = new JLabel("Navn:");
		mainPanel.add(lblName, "1, 2, right, default");
		
		txtName = new JTextFieldLimit(200,false);
		lblName.setLabelFor(txtName);
		mainPanel.add(txtName, "3, 2, fill, default");
		txtName.setColumns(10);
		
		JLabel lblDesc = new JLabel("Beskrivelse:");
		mainPanel.add(lblDesc, "1, 4, right, default");
		
		txtDesc = new JTextFieldLimit(300,false);
		lblDesc.setLabelFor(txtDesc);
		mainPanel.add(txtDesc, "3, 4, fill, default");
		txtDesc.setColumns(10);
		
		JLabel lblUnitType = new JLabel("Enhedstype:");
		mainPanel.add(lblUnitType, "1, 6, right, default");
		
		JPanel panel = new JPanel();
		mainPanel.add(panel, "3, 6, fill, fill");
		panel.setLayout(new FormLayout(new ColumnSpec[] {
				ColumnSpec.decode("default:grow"),
				FormFactory.RELATED_GAP_COLSPEC,
				FormFactory.DEFAULT_COLSPEC,},
			new RowSpec[] {
				FormFactory.DEFAULT_ROWSPEC,}));
		cmbModel = new UnitTypeComboBoxModel();
		cmbUnitType = new JComboBox<String>(cmbModel);
		panel.add(cmbUnitType, "1, 1");
		lblUnitType.setLabelFor(cmbUnitType);
		
		JButton btnUnitType = new JButton("+");
		btnUnitType.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				openUnitTypeGUI();
			}
		});
		btnUnitType.setFont(new Font("Tahoma", Font.PLAIN, 10));
		panel.add(btnUnitType, "3, 1");
		
		JLabel lblPrice = new JLabel("Pris:");
		mainPanel.add(lblPrice, "1, 8, right, default");
			    
	    decimalFormat = new DecimalFormat("#,###.##");
	    
		txtPrice = new JFormattedTextField(decimalFormat);
		txtPrice.setFocusLostBehavior(JFormattedTextField.COMMIT);
		lblPrice.setLabelFor(txtPrice);
		mainPanel.add(txtPrice, "3, 8, fill, default");
		txtPrice.setColumns(10);
		
		JLabel lblBrand = new JLabel("Fabrikat:");
		mainPanel.add(lblBrand, "1, 10, right, default");
		
		txtBrand = new JTextFieldLimit(100,false);
		mainPanel.add(txtBrand, "3, 10, fill, default");
		txtBrand.setColumns(10);
		
		JLabel lblItemNumber = new JLabel("Varenummer:");
		mainPanel.add(lblItemNumber, "1, 12, right, default");
		
		txtItemNumber = new JTextFieldLimit(50,false);
		lblItemNumber.setLabelFor(txtItemNumber);
		mainPanel.add(txtItemNumber, "3, 12, fill, top");
		txtItemNumber.setColumns(10);
		
		JPanel bottomPanel = new JPanel();
		bottomPanel.setBorder(new EmptyBorder(5, 5, 5, 5));
		getContentPane().add(bottomPanel, BorderLayout.SOUTH);
		bottomPanel.setLayout(new FormLayout(new ColumnSpec[] {
				ColumnSpec.decode("default:grow"),
				ColumnSpec.decode("default:grow"),
				FormFactory.GROWING_BUTTON_COLSPEC,
				ColumnSpec.decode("default:grow"),
				ColumnSpec.decode("default:grow"),},
			new RowSpec[] {
				RowSpec.decode("23px"),}));
		
		JButton btnClose = new JButton("Luk");
		btnClose.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				pressedClose();
			}
		});
		bottomPanel.add(btnClose, "2, 1, fill, top");
		
		btnCreate = new JButton("Opret");
		btnCreate.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				create();
			}
		});
		
		btnEdit = new JButton("Gem Ændringer");
		btnEdit.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				edit();
			}
		});
		
		cardPanel = new JPanel();
		
		cardPanel.setLayout(new CardLayout(0, 0));
		cardPanel.add(btnCreate, "creating");
		cardPanel.add(btnEdit, "editing");
		
		bottomPanel.add(cardPanel, "4, 1, fill, top");

		addWindowListener(new WindowAdapter() {
			@Override
			public void windowClosing(WindowEvent e) {
				pressedClose();
			}
		});
		
		refreshUnitTypes();
	}

	/**
	 * 
	 */
	protected void edit() {
		try {
			String name = Utilities.getTextFromReqField(txtName, "Navn");
			UnitType unitType = cmbModel.getSelectedUnitType();
			if (unitType == null) {
				throw new SubmitException("Enhedstype skal vælges", cmbUnitType);
			}
			double price = getPrice();
			
			String brand = txtBrand.getText().trim();
			String desc = txtDesc.getText().trim();
			String itemNumber = txtItemNumber.getText().trim();
			IFProductCtr pCtr = new ProductCtr();
			try {
				pCtr.updateProduct(product, brand, name, desc, itemNumber, price, unitType);
				
			} catch (ObjectNotExistException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (DBException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		} catch (SubmitException e) {
			e.showError();
		}
	}

	private void pressedClose() {
		if(isAllFieldsEmpty()) {
			kill();
		} else {
			int c = Utilities.showWarning(this, "Er du sikker på du vil lukke vinduet, uden at gemme?");
			//System.out.println("c: "+ c + ": yes: "+JOptionPane.YES_OPTION);
			if (c == JOptionPane.YES_OPTION) {
				kill();
			}
		}
	}
	
	/*
	private void clear() {
		txtName.setText("");
		txtDesc.setText("");
		txtBrand.setText("");
		txtItemNumber.setText("");
		cmbUnitType.setSelectedIndex(-1);
	}
	*/
	
	private boolean isFieldEmpty(JTextField field) {
		return field.getText().trim().isEmpty();
	}
	
	private boolean isAllFieldsEmpty() {
		return (isFieldEmpty(txtName) && isFieldEmpty(txtBrand) && isFieldEmpty(txtDesc)
				&& isFieldEmpty(txtItemNumber) && isFieldEmpty(txtPrice) &&
				cmbModel.getSelectedUnitType() == null);
	}

	private void openUnitTypeGUI() {
		UnitTypeDialog utDialog = new UnitTypeDialog(this);
		if (utDialog.isAnyThingChanged()) {
			refreshUnitTypes();
		}
		utDialog.setVisible(false);
		utDialog.dispose();
	}
	
	private void create() {
		try {
			String name = Utilities.getTextFromReqField(txtName, "Navn");
			UnitType unitType = cmbModel.getSelectedUnitType();
			if (unitType == null) {
				throw new SubmitException("Enhedstype skal vælges", cmbUnitType);
			}
			double price = getPrice();
			
			String brand = txtBrand.getText().trim();
			String desc = txtDesc.getText().trim();
			String itemNumber = txtItemNumber.getText().trim();
					
			IFProductCtr pCtr = new ProductCtr();
			try {
				this.product = pCtr.createProduct(brand, name, desc, itemNumber, price, unitType);
				setVisible(false);
			} catch (DBException e) {
				Utilities.showError(this, e.getMessage());
			}
		} catch (SubmitException e) {
			Utilities.showError(this, e.getMessage());
		}
		
		
	}
	
	private boolean isEditing() {
		return (product != null);
	}
	
	private void kill() {
		setVisible(false);
		this.dispose();
	}
	
	public Product getProduct() {
		return product;
	}
	
	private double getPrice() throws SubmitException {
		double price = -1;
		try {
			String priceStr = Utilities.getTextFromReqField(txtPrice, "Prisen");
			 price = decimalFormat.parse(priceStr).doubleValue();
		} catch (ParseException e) {
			throw new SubmitException("Prisen er ikke angivet i korrekt format", txtPrice);
		}
		return price;
	}

	private void refreshUnitTypes() {
		IFProductCtr pCtr = new ProductCtr();
		cmbModel.update(pCtr.getAllUnitTypes());;
	}
	
}
