package guiLayer;

import guiLayer.exceptions.SubmitException;
import guiLayer.extensions.UnitTypeComboBoxModel;

import java.awt.BorderLayout;
import java.awt.Dimension;

import javax.swing.JButton;
import javax.swing.JDialog;
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

import javax.swing.JTextField;
import javax.swing.JComboBox;

import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

/**
 * Class for AddProductDialog
 *
 * @author Group 3, dmaa0214, UCN
 *
 */
public class AddProductDialog extends JDialog {

	private static final long serialVersionUID = 1L;
	private ProductPanel parent;
	private JTextField txtPrice;
	private JTextField txtName;
	private JTextField txtDesc;
	private JTextField txtBrand;
	private JTextField txtItemNumber;
	private UnitTypeComboBoxModel cmbModel;
	private JComboBox<String> cmbUnitType;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		try {
			AddProductDialog dialog = new AddProductDialog(null);
			dialog.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
			dialog.setVisible(true);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	/**
	 * Create the dialog.
	 * @param productPanel 
	 */
	public AddProductDialog(ProductPanel parent) {
		
		setTitle("Opret Produkt");
		this.parent = parent;
		buildDialog();

		setLocationRelativeTo(parent);
		setVisible(true);
	}

	/**
	 * 
	 */
	private void buildDialog() {
		setModal(true);
		setMinimumSize(new Dimension(300, 280));
		
		getContentPane().setLayout(new BorderLayout());
		
		JPanel topPanel = new JPanel();
		topPanel.setBorder(new EmptyBorder(3, 3, 3, 3));
		getContentPane().add(topPanel, BorderLayout.NORTH);
		
		JLabel lblTitle = new JLabel("Opret Produkt");
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
		
		txtPrice = new JTextField();
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
		bottomPanel.add(btnClose, "2, 1, fill, top");
		
		JButton btnCreate = new JButton("Opret");
		btnCreate.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				create();
			}
		});
		bottomPanel.add(btnCreate, "4, 1, fill, top");
			
		refreshUnitTypes();
		pack();
	}

	
	private void openUnitTypeGUI() {
		UnitTypeDialog utDialog = new UnitTypeDialog(this);
		if (utDialog.isAnyThingChanged()) {
			refreshUnitTypes();
			System.out.println("true");
		}
		utDialog.dispose();
	}

	private void create() {
		try {
			Methods.checkReqTextField(txtName, "Navn");
			if (cmbModel.getSelectedUnitType() == null) {
				throw new SubmitException("Enhedstype skal vælges", cmbUnitType);
			}
			Methods.checkReqTextField(txtPrice, "Prisen");
		} catch (SubmitException e) {
			e.showError();
		}
		
	}

	private void refreshUnitTypes() {
		IFProductCtr pCtr = new ProductCtr();
		cmbModel.update(pCtr.getAllUnitTypes());;
	}
	
}
