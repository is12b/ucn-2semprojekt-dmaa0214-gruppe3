package guiLayer;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JToolBar;

import com.jgoodies.forms.layout.FormLayout;
import com.jgoodies.forms.layout.ColumnSpec;
import com.jgoodies.forms.layout.RowSpec;
import com.jgoodies.forms.factories.FormFactory;

import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;
import javax.swing.JTextField;
import javax.swing.JLabel;

import java.awt.GridBagLayout;

import javax.swing.JButton;

import java.awt.GridBagConstraints;
import java.awt.Insets;

import javax.swing.border.MatteBorder;

import java.awt.Color;

import javax.swing.border.TitledBorder;
import javax.swing.UIManager;

/**
 * Class for OrderPanel
 *
 * @author Group 3, dmaa0214, UCN
 *
 */
public class OrderPanel extends JPanel {
	private JTextField txtCustomerName;
	private JTextField txtCustomerPhone;
	private JTextField txtCustomerCVR;
	private JTextField txtProductName;
	private JTextField txtProductNumber;
	private JTable table;
	private MainGUI parent;

	/**
	 * Create the panel.
	 */
	public OrderPanel(MainGUI parent) {
		this.parent = parent;
		setLayout(new FormLayout(new ColumnSpec[] {
				FormFactory.LABEL_COMPONENT_GAP_COLSPEC,
				ColumnSpec.decode("max(409dlu;default):grow"),
				FormFactory.LABEL_COMPONENT_GAP_COLSPEC,
				ColumnSpec.decode("150dlu"),},
			new RowSpec[] {
				FormFactory.LINE_GAP_ROWSPEC,
				RowSpec.decode("default:grow"),
				FormFactory.RELATED_GAP_ROWSPEC,
				RowSpec.decode("max(41dlu;default)"),
				FormFactory.LINE_GAP_ROWSPEC,}));
		
		JScrollPane scrollPane = new JScrollPane();
		add(scrollPane, "2, 2, fill, fill");
		
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
		
		JPanel panel = new JPanel();
		add(panel, "4, 2, fill, fill");
		panel.setLayout(new FormLayout(new ColumnSpec[] {
				ColumnSpec.decode("max(149dlu;default)"),},
			new RowSpec[] {
				FormFactory.DEFAULT_ROWSPEC,
				FormFactory.RELATED_GAP_ROWSPEC,
				FormFactory.DEFAULT_ROWSPEC,
				FormFactory.RELATED_GAP_ROWSPEC,
				RowSpec.decode("default:grow"),}));
		
		JPanel panel_3 = new JPanel();
		panel_3.setBorder(new TitledBorder(UIManager.getBorder("TitledBorder.border"), "S\u00F8g Kunde", TitledBorder.LEADING, TitledBorder.TOP, null, new Color(0, 0, 0)));
		panel.add(panel_3, "1, 1, fill, fill");
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
		
		JPanel panel_4 = new JPanel();
		panel_3.add(panel_4, "1, 7, 3, 1, fill, fill");
		panel_4.setLayout(new FormLayout(new ColumnSpec[] {
				FormFactory.GROWING_BUTTON_COLSPEC,
				ColumnSpec.decode("26px"),
				FormFactory.GROWING_BUTTON_COLSPEC,},
			new RowSpec[] {
				RowSpec.decode("23px"),}));
		
		JButton btnCustomerSearch = new JButton("S\u00F8g");
		panel_4.add(btnCustomerSearch, "1, 1, fill, center");
		
		JButton btnCustomerClear = new JButton("Ryd");
		panel_4.add(btnCustomerClear, "3, 1, fill, center");
		
		JPanel panel_2 = new JPanel();
		panel_2.setBorder(new TitledBorder(null, "S\u00F8g Produkt", TitledBorder.LEADING, TitledBorder.TOP, null, null));
		panel.add(panel_2, "1, 3, fill, fill");
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
		panel_5.add(btnNewButton, "3, 1");
		
		JPanel panel_1 = new JPanel();
		panel.add(panel_1, "1, 5, fill, fill");
		
		JPanel panel_6 = new JPanel();
		add(panel_6, "2, 4, fill, fill");
		panel_6.setLayout(new FormLayout(new ColumnSpec[] {
				ColumnSpec.decode("default:grow"),
				FormFactory.RELATED_GAP_COLSPEC,
				FormFactory.DEFAULT_COLSPEC,
				FormFactory.RELATED_GAP_COLSPEC,
				FormFactory.DEFAULT_COLSPEC,},
			new RowSpec[] {
				FormFactory.DEFAULT_ROWSPEC,
				FormFactory.RELATED_GAP_ROWSPEC,
				FormFactory.DEFAULT_ROWSPEC,}));
		
		JPanel panel_8 = new JPanel();
		panel_6.add(panel_8, "1, 1, fill, fill");
		
		JPanel panel_7 = new JPanel();
		panel_6.add(panel_7, "5, 1, fill, fill");
		panel_7.setLayout(new FormLayout(new ColumnSpec[] {
				ColumnSpec.decode("44px"),
				FormFactory.LABEL_COMPONENT_GAP_COLSPEC,
				ColumnSpec.decode("24px"),},
			new RowSpec[] {
				RowSpec.decode("14px"),
				FormFactory.NARROW_LINE_GAP_ROWSPEC,
				RowSpec.decode("14px"),
				FormFactory.NARROW_LINE_GAP_ROWSPEC,
				RowSpec.decode("14px"),}));
		
		JLabel lblSubtotal = new JLabel("Subtotal:");
		panel_7.add(lblSubtotal, "1, 1, left, center");
		
		JLabel lblNewLabel = new JLabel("1000");
		panel_7.add(lblNewLabel, "3, 1, center, center");
		
		JLabel lblMoms = new JLabel("Moms:");
		panel_7.add(lblMoms, "1, 3, left, center");
		
		JLabel label = new JLabel("250");
		panel_7.add(label, "3, 3, center, center");
		
		JLabel lblTotal = new JLabel("Total:");
		panel_7.add(lblTotal, "1, 5, left, center");
		
		JLabel label_1 = new JLabel("1250");
		panel_7.add(label_1, "3, 5, center, center");
		
		JPanel panel_9 = new JPanel();
		panel_6.add(panel_9, "1, 3, 5, 1, fill, fill");
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
		
		parent.setDefaultButton(btnCommit);

	}

}
