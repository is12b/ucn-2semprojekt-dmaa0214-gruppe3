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
	private JTextField txtCarRegNr;
	private JTextField txtCarVin;

	/**
	 * Create the panel.
	 */
	public OrderPanel(MainGUI parent) {
		this.parent = parent;
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
		panel.add(btnCarSearch, "1, 1");
		
		JButton btnCarClear = new JButton("Ryd");
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
		
		JPanel panel_8 = new JPanel();
		panel_8.setBorder(new TitledBorder(UIManager.getBorder("TitledBorder.border"), "Kunde", TitledBorder.LEADING, TitledBorder.TOP, null, new Color(0, 0, 0)));
		panel_6.add(panel_8, "1, 1, fill, fill");
		panel_8.setLayout(new FormLayout(new ColumnSpec[] {
				FormFactory.DEFAULT_COLSPEC,
				FormFactory.RELATED_GAP_COLSPEC,
				ColumnSpec.decode("default:grow"),},
			new RowSpec[] {
				FormFactory.DEFAULT_ROWSPEC,
				FormFactory.NARROW_LINE_GAP_ROWSPEC,
				FormFactory.DEFAULT_ROWSPEC,}));
		
		JLabel lblNewLabel_6 = new JLabel("Navn:");
		panel_8.add(lblNewLabel_6, "1, 1");
		
		JLabel lblName = new JLabel("Mikkel");
		panel_8.add(lblName, "3, 1");
		
		JLabel lblNewLabel_4 = new JLabel("Telefon:");
		panel_8.add(lblNewLabel_4, "1, 3");
		
		JLabel lblNewLabel_7 = new JLabel("88888888");
		panel_8.add(lblNewLabel_7, "3, 3");
		
		JPanel panel_11 = new JPanel();
		panel_11.setBorder(new TitledBorder(null, "Bil", TitledBorder.LEADING, TitledBorder.TOP, null, null));
		panel_6.add(panel_11, "3, 1, 1, 2, fill, fill");
		panel_11.setLayout(new FormLayout(new ColumnSpec[] {
				FormFactory.RELATED_GAP_COLSPEC,
				FormFactory.DEFAULT_COLSPEC,
				FormFactory.RELATED_GAP_COLSPEC,
				FormFactory.DEFAULT_COLSPEC,},
			new RowSpec[] {
				FormFactory.DEFAULT_ROWSPEC,
				FormFactory.RELATED_GAP_ROWSPEC,
				FormFactory.DEFAULT_ROWSPEC,
				FormFactory.RELATED_GAP_ROWSPEC,
				FormFactory.DEFAULT_ROWSPEC,}));
		
		JLabel lblNewLabel_9 = new JLabel("Regnr:");
		panel_11.add(lblNewLabel_9, "2, 1");
		
		JLabel lblNewLabel_8 = new JLabel("FA21981");
		panel_11.add(lblNewLabel_8, "4, 1");
		
		JLabel lblStelnr = new JLabel("Stelnr:");
		panel_11.add(lblStelnr, "2, 3");
		
		JLabel lblDetvedlasse = new JLabel("DetVedLasseNok");
		panel_11.add(lblDetvedlasse, "4, 3");
		
		JLabel lblNewLabel_12 = new JLabel("Kilometer:");
		panel_11.add(lblNewLabel_12, "2, 5");
		
		JLabel lblNewLabel_13 = new JLabel("400000");
		panel_11.add(lblNewLabel_13, "4, 5");
		
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
		
		parent.setDefaultButton(btnCommit);

	}

}
