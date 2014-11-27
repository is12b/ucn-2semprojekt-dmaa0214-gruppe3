package guiLayer;

import guiLayer.extensions.CustomerTableModel;
import guiLayer.extensions.TabbedPanel;

import javax.swing.JPanel;

import com.jgoodies.forms.layout.FormLayout;
import com.jgoodies.forms.layout.ColumnSpec;
import com.jgoodies.forms.layout.RowSpec;
import com.jgoodies.forms.factories.FormFactory;

import ctrLayer.CustomerCtr;
import ctrLayer.interfaceLayer.IFCustomerCtr;

import java.awt.GridBagLayout;

import javax.swing.border.TitledBorder;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableModel;
import javax.swing.JButton;

import java.awt.FlowLayout;

import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JTextField;
import javax.swing.JTable;
import javax.swing.JScrollPane;

import modelLayer.Customer;

import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.ArrayList;

/**
 * Class for CustomerPanel
 *
 * @author Group 3, dmaa0214, UCN
 *
 */
public class CustomerPanel extends TabbedPanel {

	private MainGUI parent;
	private JTextField txtRegNr;
	private JTextField txtPhone;
	private JTextField txtName;
	private JTextField txtCvr;
	private JTable table;
	private CustomerTableModel model;
	private ArrayList<Customer> customers;

	/**
	 * Create the panel.
	 */
	public CustomerPanel(MainGUI parent) {
		setLayout(new FormLayout(new ColumnSpec[] {
				FormFactory.RELATED_GAP_COLSPEC,
				ColumnSpec.decode("max(103dlu;default)"),
				FormFactory.RELATED_GAP_COLSPEC,
				ColumnSpec.decode("default:grow"),},
				new RowSpec[] {
				FormFactory.RELATED_GAP_ROWSPEC,
				RowSpec.decode("default:grow"),}));

		JPanel panel_1 = new JPanel();
		add(panel_1, "2, 2, fill, fill");
		panel_1.setLayout(new FormLayout(new ColumnSpec[] {
				FormFactory.RELATED_GAP_COLSPEC,
				ColumnSpec.decode("default:grow"),},
				new RowSpec[] {
				FormFactory.RELATED_GAP_ROWSPEC,
				RowSpec.decode("default:grow"),
				FormFactory.RELATED_GAP_ROWSPEC,
				RowSpec.decode("default:grow"),}));

		JPanel panelSearch = new JPanel();
		panelSearch.setBorder(new TitledBorder(null, "S\u00F8g Kunde", TitledBorder.LEADING, TitledBorder.TOP, null, null));
		panel_1.add(panelSearch, "2, 2, fill, fill");
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

		txtRegNr = new JTextField();
		panelSearch.add(txtRegNr, "4, 2, fill, default");
		txtRegNr.setColumns(10);

		JLabel lblPhone = new JLabel("Tlf");
		panelSearch.add(lblPhone, "2, 4, right, default");

		txtPhone = new JTextField();
		txtPhone.setText("");
		panelSearch.add(txtPhone, "4, 4, fill, default");
		txtPhone.setColumns(10);

		JLabel lblName = new JLabel("Navn");
		panelSearch.add(lblName, "2, 6, right, default");

		txtName = new JTextField();
		txtName.setText("");
		panelSearch.add(txtName, "4, 6, fill, default");
		txtName.setColumns(10);

		JLabel lblCvr = new JLabel("CVR");
		panelSearch.add(lblCvr, "2, 8, right, default");

		txtCvr = new JTextField();
		txtCvr.setText("");
		panelSearch.add(txtCvr, "4, 8, fill, default");
		txtCvr.setColumns(10);

		JPanel panel_4 = new JPanel();
		panelSearch.add(panel_4, "2, 10, 3, 1, fill, fill");

		JButton btnSearch = new JButton("S\u00F8g");

		btnSearch.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				searchCustomer();
			}
		});

		panel_4.add(btnSearch);

		JPanel panelCreate = new JPanel();
		panelCreate.setBorder(new TitledBorder(null, "Opret Kunde", TitledBorder.LEADING, TitledBorder.TOP, null, null));
		panel_1.add(panelCreate, "2, 4, fill, fill");
		panelCreate.setLayout(new FlowLayout(FlowLayout.CENTER, 5, 5));

		JButton btnCreate = new JButton("Opret");
		btnCreate.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				createCustomer();
			}
		});
		panelCreate.add(btnCreate);

		JScrollPane scrollPane = new JScrollPane();
		add(scrollPane, "4, 2, fill, fill");

		table = new JTable();
		model = new CustomerTableModel();
		table.setModel(model);
		scrollPane.setViewportView(table);

		table.addMouseListener(new MouseAdapter() {
			public void mouseClicked(MouseEvent e) {
				if (e.getClickCount() == 2) {
					JTable target = (JTable)e.getSource();
					int row = target.getSelectedRow();
					Customer customer = customers.get(row);
					new CustomerInfoDialog(customer);
				}
			}
		});

		customers = new ArrayList<Customer>();
	}

	@Override
	public void setFocus() {
		txtRegNr.requestFocusInWindow();
	}

	private void searchCustomer() {

		IFCustomerCtr cCtr = new CustomerCtr();

		final boolean searchReg = !txtRegNr.getText().isEmpty();
		final boolean searchPhone = !txtPhone.getText().isEmpty();
		final boolean searchName = !txtName.getText().isEmpty();
		final boolean searchCvr = !txtCvr.getText().isEmpty();

		final String regNr = txtRegNr.getText().trim();
		final String phone = txtPhone.getText().trim();
		final String name = txtName.getText();
		final String cvr = txtCvr.getText().trim();

		if(searchReg) {
			customers.add(cCtr.getCustomerByRegNr(regNr));
		}
		else if(searchPhone) {
			customers = cCtr.searchCustomersByPhone(phone);
		}
		else if(searchName) {
			customers = cCtr.searchCustomersByName(name);
		}
		else if(searchCvr) {
			customers.add(cCtr.getCustomerByCvr(cvr));
		}
		updateTable();
	}

	private void createCustomer() {
		JDialog createPopup = new CreateCustomerDialog();

	}

	private void updateTable() {
		model.refresh(customers);
		model.fireTableDataChanged();
	}
}
