package guiLayer;

import guiLayer.models.ProductTableModel;

import java.awt.Dimension;

import javax.swing.JPanel;

import com.jgoodies.forms.layout.FormLayout;
import com.jgoodies.forms.layout.ColumnSpec;
import com.jgoodies.forms.layout.RowSpec;
import com.jgoodies.forms.factories.FormFactory;

import ctrLayer.ProductCtr;
import ctrLayer.interfaceLayer.IFProductCtr;

import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.border.TitledBorder;
import javax.swing.JButton;

import java.awt.FlowLayout;
import java.util.ArrayList;

import javax.swing.JTextField;
import javax.swing.JLabel;

import modelLayer.Product;

import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

/**
 * Class for ProductGUI
 *
 * @author Group 3, dmaa0214, UCN
 *
 */
public class ProductPanel extends JPanel {

	private static final long serialVersionUID = 1L;
	private JTextField txtID;
	private JTextField txtItemNumber;
	private JTextField txtName;
	private ProductTableModel model;
	
	public ProductPanel() {
		buildPanel();
	}

	private void buildPanel() {
		setMinimumSize(new Dimension(200,200));
		setLayout(new FormLayout(new ColumnSpec[] {
				ColumnSpec.decode("max(200px;default):grow"),
				FormFactory.RELATED_GAP_COLSPEC,
				ColumnSpec.decode("default:grow"),},
			new RowSpec[] {
				FormFactory.RELATED_GAP_ROWSPEC,
				RowSpec.decode("default:grow"),}));
		
		JPanel sidePanel = new JPanel();
		add(sidePanel, "1, 2, fill, fill");
		sidePanel.setLayout(new FormLayout(new ColumnSpec[] {
				ColumnSpec.decode("default:grow"),},
			new RowSpec[] {
				RowSpec.decode("default:grow"),
				RowSpec.decode("default:grow"),}));
		
		JPanel searchPanel = new JPanel();
		searchPanel.setBorder(new TitledBorder(null, "S\u00F8g produkt", TitledBorder.LEADING, TitledBorder.TOP, null, null));
		sidePanel.add(searchPanel, "1, 1, fill, fill");
		searchPanel.setLayout(new FormLayout(new ColumnSpec[] {
				FormFactory.RELATED_GAP_COLSPEC,
				FormFactory.DEFAULT_COLSPEC,
				FormFactory.RELATED_GAP_COLSPEC,
				ColumnSpec.decode("default:grow"),
				FormFactory.RELATED_GAP_COLSPEC,},
			new RowSpec[] {
				FormFactory.RELATED_GAP_ROWSPEC,
				FormFactory.DEFAULT_ROWSPEC,
				FormFactory.RELATED_GAP_ROWSPEC,
				FormFactory.DEFAULT_ROWSPEC,
				FormFactory.RELATED_GAP_ROWSPEC,
				FormFactory.DEFAULT_ROWSPEC,
				FormFactory.RELATED_GAP_ROWSPEC,
				RowSpec.decode("default:grow"),}));
		
		JLabel lblID = new JLabel("ID:");
		searchPanel.add(lblID, "2, 2, right, default");
		
		txtID = new JTextField();
		lblID.setLabelFor(txtID);
		searchPanel.add(txtID, "4, 2, fill, default");
		txtID.setColumns(10);
		
		JLabel lblItemNumber = new JLabel("Varenummer:");
		lblItemNumber.setLabelFor(lblItemNumber);
		searchPanel.add(lblItemNumber, "2, 4, right, default");
		
		txtItemNumber = new JTextField();
		searchPanel.add(txtItemNumber, "4, 4, fill, default");
		txtItemNumber.setColumns(10);
		
		JLabel lblNavn = new JLabel("Navn:");
		searchPanel.add(lblNavn, "2, 6, right, default");
		
		txtName = new JTextField();
		searchPanel.add(txtName, "4, 6, fill, default");
		txtName.setColumns(10);
		
		JPanel buttonPanel = new JPanel();
		searchPanel.add(buttonPanel, "2, 8, 3, 1, fill, fill");
		buttonPanel.setLayout(new FormLayout(new ColumnSpec[] {
				FormFactory.RELATED_GAP_COLSPEC,
				FormFactory.DEFAULT_COLSPEC,
				FormFactory.RELATED_GAP_COLSPEC,
				FormFactory.GROWING_BUTTON_COLSPEC,
				FormFactory.RELATED_GAP_COLSPEC,
				FormFactory.DEFAULT_COLSPEC,},
			new RowSpec[] {
				FormFactory.RELATED_GAP_ROWSPEC,
				FormFactory.DEFAULT_ROWSPEC,}));
		
		JButton btnClear = new JButton("Ryd");
		buttonPanel.add(btnClear, "2, 2");
		
		JButton btnSearch = new JButton("S\u00F8g");
		btnSearch.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				search();
			}
		});
		buttonPanel.add(btnSearch, "6, 2");
		
		JPanel createPanel = new JPanel();
		createPanel.setBorder(new TitledBorder(null, "Opret produkt", TitledBorder.LEADING, TitledBorder.TOP, null, null));
		sidePanel.add(createPanel, "1, 2, fill, fill");
		createPanel.setLayout(new FlowLayout(FlowLayout.CENTER, 5, 5));
		
		JButton btnOpret = new JButton("Opret nyt");
		createPanel.add(btnOpret);
		
		JPanel mainPanel = new JPanel();
		add(mainPanel, "3, 2, fill, fill");
		mainPanel.setLayout(new FormLayout(new ColumnSpec[] {
				ColumnSpec.decode("default:grow"),},
			new RowSpec[] {
				RowSpec.decode("default:grow"),}));
		
		JScrollPane scrollPane = new JScrollPane();
		mainPanel.add(scrollPane, "1, 1, fill, fill");
		
		model = new ProductTableModel();
		
		JTable table = new JTable(model);
		
		scrollPane.setViewportView(table);
	}
	
	private void search() {
		IFProductCtr pCtr = new ProductCtr();
		ArrayList<Product> pList = pCtr.searchProductsByName(txtName.getText().trim());
		updateModel(pList);
	}

	private void updateModel(ArrayList<Product> pList) {
		model.refresh(pList);
		model.fireTableDataChanged();
	}

}
