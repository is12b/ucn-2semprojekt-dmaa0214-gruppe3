package guiLayer;

import exceptions.ObjectNotExistException;
import guiLayer.extensions.CustomerTableModel;
import guiLayer.extensions.DocumentListenerChange;
import guiLayer.extensions.JTextFieldLimit;
import guiLayer.extensions.IFTabbedPanel;
import guiLayer.extensions.Utilities;

import java.awt.Color;
import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.ArrayList;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.UIManager;
import javax.swing.border.TitledBorder;

import modelLayer.Customer;

import com.jgoodies.forms.factories.FormFactory;
import com.jgoodies.forms.layout.ColumnSpec;
import com.jgoodies.forms.layout.FormLayout;
import com.jgoodies.forms.layout.RowSpec;

import ctrLayer.CustomerCtr;
import ctrLayer.interfaceLayer.IFCustomerCtr;

/**
 * Class for CustomerPanel
 *
 * @author Group 3, dmaa0214, UCN
 *
 */
public class CustomerPanel extends JPanel implements IFTabbedPanel  {
	private static final long serialVersionUID = 1L;
	private MainGUI parent;
	private JTextFieldLimit txtRegNr;
	private JTextFieldLimit txtPhone;
	private JTextFieldLimit txtName;
	private JTextFieldLimit txtCvr;
	private JTable table;
	private CustomerTableModel model;
	private ArrayList<Customer> customers;
	private ArrayList<JTextField> customerFields;
	private JButton btnSearch;

	public CustomerPanel(MainGUI parent) {
		buildPanel();
		this.parent = parent;
		customerFields = new ArrayList<JTextField>();
		customerFields.add(txtName);
		customerFields.add(txtPhone);
		customerFields.add(txtRegNr);
		customerFields.add(txtCvr);
		addDocumentListener(customerFields);
	}

	@Override
	public void setFocus() {
		txtRegNr.requestFocusInWindow();
		parent.setDefaultButton(btnSearch);
	}

	private void buildPanel() {
		setLayout(new FormLayout(new ColumnSpec[] {
				ColumnSpec.decode("center:max(147dlu;pref)"),
				FormFactory.RELATED_GAP_COLSPEC,
				ColumnSpec.decode("default:grow"),},
			new RowSpec[] {
				FormFactory.RELATED_GAP_ROWSPEC,
				RowSpec.decode("default:grow"),}));

		JPanel panel_1 = new JPanel();
		add(panel_1, "1, 2, fill, fill");
		panel_1.setLayout(new FormLayout(new ColumnSpec[] {
				ColumnSpec.decode("default:grow"),},
			new RowSpec[] {
				FormFactory.DEFAULT_ROWSPEC,
				FormFactory.RELATED_GAP_ROWSPEC,
				FormFactory.DEFAULT_ROWSPEC,
				FormFactory.RELATED_GAP_ROWSPEC,
				RowSpec.decode("default:grow"),}));

		JPanel panelSearch = new JPanel();
		panelSearch.setBorder(new TitledBorder(UIManager.getBorder("TitledBorder.border"), "S\u00F8g Kunde", TitledBorder.LEFT, TitledBorder.TOP, null, new Color(0, 0, 0)));
		panel_1.add(panelSearch, "1, 1, fill, fill");
		panelSearch.setLayout(new FormLayout(new ColumnSpec[] {
				FormFactory.RELATED_GAP_COLSPEC,
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
				RowSpec.decode("default:grow"),}));

		JLabel lblRegNr = new JLabel("RegNR");
		panelSearch.add(lblRegNr, "2, 2, right, default");

		txtRegNr = new JTextFieldLimit(20, false);
		panelSearch.add(txtRegNr, "4, 2, fill, default");
		txtRegNr.setColumns(10);

		JLabel lblPhone = new JLabel("Tlf");
		panelSearch.add(lblPhone, "2, 4, right, default");

		txtPhone = new JTextFieldLimit(14, false);
		txtPhone.setText("");
		panelSearch.add(txtPhone, "4, 4, fill, default");
		txtPhone.setColumns(10);

		JLabel lblName = new JLabel("Navn");
		panelSearch.add(lblName, "2, 6, right, default");

		txtName = new JTextFieldLimit(50, false);
		txtName.setText("");
		panelSearch.add(txtName, "4, 6, fill, default");
		txtName.setColumns(10);

		JLabel lblCvr = new JLabel("CVR");
		panelSearch.add(lblCvr, "2, 8, right, default");

		txtCvr = new JTextFieldLimit(50, true);
		txtCvr.setText("");
		panelSearch.add(txtCvr, "4, 8, fill, default");
		txtCvr.setColumns(10);

		JPanel panel_4 = new JPanel();
		panelSearch.add(panel_4, "2, 10, 3, 1, fill, fill");
		panel_4.setLayout(new FormLayout(new ColumnSpec[] {
				FormFactory.GROWING_BUTTON_COLSPEC,
				FormFactory.GROWING_BUTTON_COLSPEC,},
			new RowSpec[] {
				RowSpec.decode("23px"),}));
		
		JButton btnClear = new JButton("Ryd");
		btnClear.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				clearFields();
			}
		});
		panel_4.add(btnClear, "1, 1, center, center");

		btnSearch = new JButton("S\u00F8g");

		panel_4.add(btnSearch, "2, 1, center, top");

		JPanel panelCreate = new JPanel();
		panelCreate.setBorder(new TitledBorder(UIManager.getBorder("TitledBorder.border"), "Opret Kunde", TitledBorder.LEFT, TitledBorder.TOP, null, new Color(0, 0, 0)));
		panel_1.add(panelCreate, "1, 3, fill, fill");
		panelCreate.setLayout(new FlowLayout(FlowLayout.CENTER, 5, 5));

		JButton btnCreate = new JButton("Opret");
		btnCreate.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				createCustomer();
			}
		});
		panelCreate.add(btnCreate);

		JScrollPane scrollPane = new JScrollPane();
		add(scrollPane, "3, 2, fill, fill");

		table = new JTable();
		model = new CustomerTableModel();
		table.setModel(model);
		scrollPane.setViewportView(table);

		table.addMouseListener(new MouseAdapter() {
			public void mouseClicked(MouseEvent e) {
				mouseClickCust(e);
			}
		});
		
		btnSearch.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				searchCustomer();
			}
		});

		customers = new ArrayList<Customer>();
	}

	private void searchCustomer() {

		IFCustomerCtr cCtr = new CustomerCtr();
		
		try{
			if(txtRegNr.isEnabled()) {
				customers.add(cCtr.getCustomerByRegNr(txtRegNr.getText().trim()));
			}
			else if(txtPhone.isEnabled()) {
				customers = cCtr.searchCustomersByPhone(txtPhone.getText().trim(), true);
			}
			else if(txtName.isEnabled()) {
				customers = cCtr.searchCustomersByName(txtName.getText(), true);
			}
			else if(txtCvr.isEnabled()) {
				customers.add(cCtr.getCustomerByCvr(txtCvr.getText().trim(), true));
			}
		}catch(ObjectNotExistException e){
			Utilities.showError(this, e.getMessage());
		}
		updateTable();
	}

	private void createCustomer() {
		new CreateCustomerDialog(parent);
	}

	private void updateTable() {
		model.refresh(customers);
		model.fireTableDataChanged();
	}
	
	/**
	 * @param e
	 */
	private void mouseClickCust(MouseEvent e) {
		if (e.getClickCount() == 2) {
			JTable target = (JTable)e.getSource();
			int row = target.getSelectedRow();
			Customer customer = customers.get(row);
			new CustomerInfoDialog(customer);
			updateTable();
		}
	}
	
	/**
	 * Misc
	 */
	
	private void addDocumentListener(ArrayList<JTextField> fields) {
		for(JTextField f : fields){
			f.getDocument().addDocumentListener(new DocumentListenerChange(fields, f));
		}
	}
	
	private void clearFields() {
		customerFields.forEach(c -> c.setText(""));
	}
}
