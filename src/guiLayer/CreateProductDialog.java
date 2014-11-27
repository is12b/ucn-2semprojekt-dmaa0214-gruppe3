package guiLayer;

import guiLayer.exceptions.SubmitException;
import guiLayer.models.UnitTypeComboBoxModel;

import java.awt.BorderLayout;
import java.awt.CardLayout;
import java.awt.Dimension;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JFormattedTextField;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JLabel;

import java.awt.Font;

import javax.swing.SwingConstants;

import com.jgoodies.forms.layout.FormLayout;
import com.jgoodies.forms.layout.ColumnSpec;
import com.jgoodies.forms.factories.FormFactory;
import com.jgoodies.forms.layout.RowSpec;

import ctrLayer.ProductCtr;
import ctrLayer.interfaceLayer.IFProductCtr;
import dbLayer.exceptions.DBException;

import javax.swing.JTextField;
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
 * Class for CreateProductDialog
 *
 * @author Group 3, dmaa0214, UCN
 *
 */
public class CreateProductDialog extends JDialog {

	private static final long serialVersionUID = 1L;
	private ProductPanel parent;
	private JFormattedTextField txtPrice;
	private JTextField txtName;
	private JTextField txtDesc;
	private JTextField txtBrand;
	private JTextField txtItemNumber;
	private UnitTypeComboBoxModel cmbModel;
	private JComboBox<String> cmbUnitType;
	private JLabel lblTitle;
	private JPanel cardPanel;
	private Product product;
	private static DecimalFormat decimalFormat;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		try {
			//Product p = new Product(1);
			//p.setUnitType(new UnitType("stk", "Styk", false));
			CreateProductDialog dialog = new CreateProductDialog(null);
			dialog.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	public CreateProductDialog(ProductPanel parent, Product product) {
		
		setTitle("Ændre Produkt #" + product.getId());
		this.parent = parent;
		this.product = product;
		buildDialog();
		CardLayout cl = (CardLayout)(cardPanel.getLayout());
		cl.show(cardPanel, "editing");
		lblTitle.setText("Ændre Produkt #" + product.getId());
		
		txtName.setText(product.getName());
		txtDesc.setText(product.getDescription());
		txtBrand.setText(product.getBrand());
		txtItemNumber.setText(product.getItemNumber());
		txtPrice.setText(Double.toString(product.getPrice()));
		cmbUnitType.setSelectedItem(product.getUnitType().toString());
		
		//TODO change cardlayout

		
	}

	/**
	 * Create the dialog.
	 * @param productPanel 
	 */
	public CreateProductDialog(ProductPanel parent) {
				
		setTitle("Opret Produkt");
		this.parent = parent;
		buildDialog();

		
	}

	/**
	 * 
	 */
	private void buildDialog() {
		
		setModal(true);
		setLocationRelativeTo(this.parent);
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
		
		txtName = new JTextField();
		lblName.setLabelFor(txtName);
		mainPanel.add(txtName, "3, 2, fill, default");
		txtName.setColumns(10);
		
		JLabel lblDesc = new JLabel("Beskrivelse:");
		mainPanel.add(lblDesc, "1, 4, right, default");
		
		txtDesc = new JTextField();
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
		
		txtBrand = new JTextField();
		mainPanel.add(txtBrand, "3, 10, fill, default");
		txtBrand.setColumns(10);
		
		JLabel lblItemNumber = new JLabel("Varenummer:");
		mainPanel.add(lblItemNumber, "1, 12, right, default");
		
		txtItemNumber = new JTextField();
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
		
		JButton btnCreate = new JButton("Opret");
		btnCreate.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				create();
			}
		});
		
		JButton btnEdit = new JButton("Opret");
		btnCreate.addActionListener(new ActionListener() {
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
		pack();
		setVisible(true);
	}

	/**
	 * 
	 */
	protected void edit() {
		// TODO Auto-generated method stub
		
	}

	private void pressedClose() {
		if(isAllFieldsEmpty()) {
			close();
		} else {
			int c = Methods.showWarning(this, "Er du sikker på du vil lukke vinduet, uden at gemme?");
			if (c == JOptionPane.YES_OPTION) {
				close();
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
			String name = Methods.getTextFromReqField(txtName, "Navn");
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
				pCtr.createProduct(brand, name, desc, itemNumber, price, unitType);
				close();
			} catch (DBException e) {
				Methods.showError(this, e.getMessage());
			}
		} catch (SubmitException e) {
			e.showError();
		}
		
		
	}
	
	private boolean isEditing() {
		return (product != null);
	}
	
	private void close() {
		setVisible(false);
		this.dispose();
	}
	
	private double getPrice() throws SubmitException {
		double price = -1;
		try {
			String priceStr = Methods.getTextFromReqField(txtPrice, "Prisen");
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
