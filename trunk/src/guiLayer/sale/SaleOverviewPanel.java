package guiLayer.sale;

import exceptions.BuildingPDFException;
import exceptions.ObjectNotExistException;
import guiLayer.MainGUI;
import guiLayer.PDFViewerDialog;
import guiLayer.extensions.JTextFieldLimit;
import guiLayer.extensions.TabbedPanel;
import guiLayer.extensions.Utilities;
import guiLayer.sale.extensions.PaidTableCellRenderer;
import guiLayer.sale.models.SaleOverviewTableModel;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.ArrayList;

import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.ListSelectionModel;
import javax.swing.SwingConstants;
import javax.swing.SwingUtilities;
import javax.swing.UIManager;
import javax.swing.border.TitledBorder;
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;
import javax.swing.table.DefaultTableCellRenderer;

import modelLayer.Sale;

import com.jgoodies.forms.factories.FormFactory;
import com.jgoodies.forms.layout.ColumnSpec;
import com.jgoodies.forms.layout.FormLayout;
import com.jgoodies.forms.layout.RowSpec;

import ctrLayer.SaleCtr;

/**
 * Class for SaleOverviewPanel
 *
 * @author Group 3, dmaa0214, UCN
 *
 */
public class SaleOverviewPanel extends TabbedPanel {

	private static final long serialVersionUID = 1L;
	private MainGUI parent;
	private JTable table;
	private JTextFieldLimit txtSaleID;
	private JTextFieldLimit txtRegNr;
	private JTextFieldLimit txtVIN;
	private JTextFieldLimit txtCusName;
	private JTextFieldLimit txtPhone;
	private JTextFieldLimit txtCVR;
	private JButton btnSaleSearch;
	private JButton btnCarSearch;
	private JButton btnCusSearch;
	private JButton btnCusClear;
	private JButton btnCarClear;
	private JButton btnSaleClear;
	private SaleOverviewTableModel model;
	private JCheckBox chbAll;

	/**
	 * Constructor for SaleOverviewPanel objects.
	 *
	 */
	public SaleOverviewPanel(MainGUI parent) {
		this.parent = parent;
		buildPanel();
	}

	private void buildPanel() {
		setMinimumSize(new Dimension(200,200));
		setLayout(new FormLayout(new ColumnSpec[] {
				ColumnSpec.decode("220px"),
				FormFactory.RELATED_GAP_COLSPEC,
				ColumnSpec.decode("default:grow"),},
			new RowSpec[] {
				FormFactory.RELATED_GAP_ROWSPEC,
				RowSpec.decode("default:grow"),}));
		
		JPanel sidePanel = new JPanel();
		add(sidePanel, "1, 2, fill, fill");
		sidePanel.setLayout(new FormLayout(new ColumnSpec[] {
				ColumnSpec.decode("144px:grow"),},
			new RowSpec[] {
				FormFactory.DEFAULT_ROWSPEC,
				FormFactory.RELATED_GAP_ROWSPEC,
				FormFactory.DEFAULT_ROWSPEC,
				FormFactory.RELATED_GAP_ROWSPEC,
				FormFactory.DEFAULT_ROWSPEC,
				FormFactory.RELATED_GAP_ROWSPEC,
				RowSpec.decode("default:grow"),}));
		
		JPanel saleSearchPanel = new JPanel();
		saleSearchPanel.setBorder(new TitledBorder(UIManager.getBorder("TitledBorder.border"), "S\u00F8g via Fakturaoplysninger", TitledBorder.LEADING, TitledBorder.TOP, null, new Color(0, 0, 0)));
		sidePanel.add(saleSearchPanel, "1, 1, fill, fill");
		saleSearchPanel.setLayout(new FormLayout(new ColumnSpec[] {
				FormFactory.RELATED_GAP_COLSPEC,
				ColumnSpec.decode("default:grow"),
				FormFactory.RELATED_GAP_COLSPEC,
				ColumnSpec.decode("default:grow"),
				FormFactory.RELATED_GAP_COLSPEC,},
			new RowSpec[] {
				FormFactory.DEFAULT_ROWSPEC,
				FormFactory.RELATED_GAP_ROWSPEC,
				FormFactory.DEFAULT_ROWSPEC,
				FormFactory.RELATED_GAP_ROWSPEC,
				RowSpec.decode("default:grow"),}));
		
		JLabel lblSaleID = new JLabel("Faktura Nummer");
		saleSearchPanel.add(lblSaleID, "2, 1, right, default");
		
		txtSaleID = new JTextFieldLimit(10, true);
		lblSaleID.setLabelFor(txtSaleID);
		saleSearchPanel.add(txtSaleID, "4, 1, fill, default");
		txtSaleID.setColumns(10);
		
		chbAll = new JCheckBox("(Meget kr\u00E6vende)");
				
		saleSearchPanel.add(chbAll, "4, 3");
		
		JLabel lblAll = new JLabel("Vis alle");
		saleSearchPanel.add(lblAll, "2, 3, right, default");
		lblAll.setLabelFor(chbAll);
		
		JPanel saleBtnsPanel = new JPanel();
		saleSearchPanel.add(saleBtnsPanel, "2, 5, 3, 1, fill, fill");
		saleBtnsPanel.setLayout(new FormLayout(new ColumnSpec[] {
				FormFactory.RELATED_GAP_COLSPEC,
				ColumnSpec.decode("default:grow"),
				FormFactory.RELATED_GAP_COLSPEC,
				ColumnSpec.decode("default:grow"),
				FormFactory.RELATED_GAP_COLSPEC,
				ColumnSpec.decode("default:grow"),
				FormFactory.RELATED_GAP_COLSPEC,},
			new RowSpec[] {
				FormFactory.DEFAULT_ROWSPEC,}));
		
		btnSaleClear = new JButton("Ryd");
		btnSaleClear.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				clearSearchBySale();
			}
		});
		saleBtnsPanel.add(btnSaleClear, "2, 1");
		
		btnSaleSearch = new JButton("S\u00F8g");
		btnSaleSearch.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				searchBySale();
			}
		});
		saleBtnsPanel.add(btnSaleSearch, "6, 1");
		
		JPanel carSearchPanel = new JPanel();
		carSearchPanel.setBorder(new TitledBorder(null, "S\u00F8g via Biloplysninger", TitledBorder.LEADING, TitledBorder.TOP, null, null));
		sidePanel.add(carSearchPanel, "1, 3, fill, fill");
		carSearchPanel.setLayout(new FormLayout(new ColumnSpec[] {
				FormFactory.RELATED_GAP_COLSPEC,
				ColumnSpec.decode("default:grow"),
				FormFactory.RELATED_GAP_COLSPEC,
				ColumnSpec.decode("default:grow"),
				FormFactory.RELATED_GAP_COLSPEC,},
			new RowSpec[] {
				FormFactory.DEFAULT_ROWSPEC,
				FormFactory.RELATED_GAP_ROWSPEC,
				FormFactory.DEFAULT_ROWSPEC,
				FormFactory.RELATED_GAP_ROWSPEC,
				RowSpec.decode("default:grow"),}));
		
		JLabel lblRegNr = new JLabel("Reg. Nummer");
		carSearchPanel.add(lblRegNr, "2, 1, right, default");
		
		txtRegNr = new JTextFieldLimit(10);
		lblRegNr.setLabelFor(txtRegNr);
		carSearchPanel.add(txtRegNr, "4, 1");
		txtRegNr.setColumns(10);
		
		JLabel lblVIN = new JLabel("Stelnummer");
		carSearchPanel.add(lblVIN, "2, 3, right, default");
		
		txtVIN = new JTextFieldLimit(100);
		lblVIN.setLabelFor(txtVIN);
		carSearchPanel.add(txtVIN, "4, 3, fill, top");
		txtVIN.setColumns(10);
		
		JPanel carBtnsPanel = new JPanel();
		carSearchPanel.add(carBtnsPanel, "2, 5, 3, 1, fill, fill");
		carBtnsPanel.setLayout(new FormLayout(new ColumnSpec[] {
				FormFactory.RELATED_GAP_COLSPEC,
				ColumnSpec.decode("default:grow"),
				FormFactory.RELATED_GAP_COLSPEC,
				ColumnSpec.decode("default:grow"),
				FormFactory.RELATED_GAP_COLSPEC,
				ColumnSpec.decode("default:grow"),
				FormFactory.RELATED_GAP_COLSPEC,},
			new RowSpec[] {
				FormFactory.DEFAULT_ROWSPEC,}));
		
		btnCarClear = new JButton("Ryd");
		btnCarClear.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				clearSearchByCar();
			}
		});
		carBtnsPanel.add(btnCarClear, "2, 1");
		
		btnCarSearch = new JButton("S\u00F8g");
		btnCarSearch.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				searchByCar();
			}
		});
		carBtnsPanel.add(btnCarSearch, "6, 1");
		
		JPanel cusSearchPanel = new JPanel();
		cusSearchPanel.setBorder(new TitledBorder(null, "S\u00F8g via Kundeoplysninger", TitledBorder.LEADING, TitledBorder.TOP, null, null));
		sidePanel.add(cusSearchPanel, "1, 5, fill, fill");
		cusSearchPanel.setLayout(new FormLayout(new ColumnSpec[] {
				FormFactory.RELATED_GAP_COLSPEC,
				ColumnSpec.decode("default:grow"),
				FormFactory.RELATED_GAP_COLSPEC,
				ColumnSpec.decode("75px:grow"),
				FormFactory.RELATED_GAP_COLSPEC,},
			new RowSpec[] {
				FormFactory.DEFAULT_ROWSPEC,
				FormFactory.RELATED_GAP_ROWSPEC,
				FormFactory.DEFAULT_ROWSPEC,
				FormFactory.RELATED_GAP_ROWSPEC,
				FormFactory.DEFAULT_ROWSPEC,
				FormFactory.RELATED_GAP_ROWSPEC,
				FormFactory.DEFAULT_ROWSPEC,}));
		
		JLabel lblName = new JLabel("Navn");
		cusSearchPanel.add(lblName, "2, 1, right, default");
		
		txtCusName = new JTextFieldLimit(100);
		lblName.setLabelFor(txtCusName);
		cusSearchPanel.add(txtCusName, "4, 1, fill, default");
		txtCusName.setColumns(10);
		
		JLabel lblPhone = new JLabel("TelefonNr.");
		cusSearchPanel.add(lblPhone, "2, 3, right, default");
		
		txtPhone = new JTextFieldLimit(100);
		lblPhone.setLabelFor(txtPhone);
		cusSearchPanel.add(txtPhone, "4, 3, fill, default");
		txtPhone.setColumns(10);
		
		JLabel lblCVR = new JLabel("CVR nummer");
		cusSearchPanel.add(lblCVR, "2, 5, right, default");
		
		txtCVR = new JTextFieldLimit(20, true);
		lblCVR.setLabelFor(txtCVR);
		cusSearchPanel.add(txtCVR, "4, 5, fill, default");
		txtCVR.setColumns(10);
		
		JPanel cusBtnsPanel = new JPanel();
		cusSearchPanel.add(cusBtnsPanel, "2, 7, 3, 1, fill, fill");
		cusBtnsPanel.setLayout(new FormLayout(new ColumnSpec[] {
				FormFactory.RELATED_GAP_COLSPEC,
				ColumnSpec.decode("default:grow"),
				FormFactory.RELATED_GAP_COLSPEC,
				ColumnSpec.decode("default:grow"),
				FormFactory.RELATED_GAP_COLSPEC,
				ColumnSpec.decode("default:grow"),
				FormFactory.RELATED_GAP_COLSPEC,},
			new RowSpec[] {
				RowSpec.decode("default:grow"),}));
		
		btnCusClear = new JButton("Ryd");
		btnCusClear.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				clearSearchByCus();
			}
		});
		cusBtnsPanel.add(btnCusClear, "2, 1");
		
		btnCusSearch = new JButton("S\u00F8g");
		btnCusSearch.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				searchByCus();
			}
		});
		cusBtnsPanel.add(btnCusSearch, "6, 1");
		
		JPanel mainPanel = new JPanel();
		add(mainPanel, "3, 2, fill, fill");
		mainPanel.setLayout(new FormLayout(new ColumnSpec[] {
				ColumnSpec.decode("default:grow"),},
			new RowSpec[] {
				RowSpec.decode("default:grow"),}));
		
		JScrollPane scrollPane = new JScrollPane();
		mainPanel.add(scrollPane, "1, 1, fill, fill");
		
		model = new SaleOverviewTableModel();
		
		table = new JTable(model);
		table.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				tableMouseListener(e);
			}
		});
		styleTable();
		scrollPane.setViewportView(table);
		
		makeFieldListeners();
	}

	private void styleTable() {
		table.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		table.getColumnModel().getColumn(0).setPreferredWidth(40);
		table.getColumnModel().getColumn(1).setPreferredWidth(175);
		table.getColumnModel().getColumn(2).setPreferredWidth(175);
		table.getColumnModel().getColumn(3).setPreferredWidth(40);
		table.getColumnModel().getColumn(4).setPreferredWidth(40);
		table.getColumnModel().getColumn(5).setPreferredWidth(60);
		DefaultTableCellRenderer rightRenderer = new DefaultTableCellRenderer();
		rightRenderer.setHorizontalAlignment(SwingConstants.RIGHT);
		table.getColumnModel().getColumn(5).setCellRenderer(rightRenderer);
		table.setAutoCreateRowSorter(true);
		table.getColumnModel().getColumn(4).setCellRenderer(new PaidTableCellRenderer());
		table.getRowSorter().toggleSortOrder(0);
		table.getRowSorter().toggleSortOrder(0);
	}

	private void tableMouseListener(MouseEvent e) {
		int rowNumber = table.rowAtPoint(e.getPoint());
		int modelRowNum = table.convertRowIndexToModel(rowNumber);
        table.setRowSelectionInterval(rowNumber, rowNumber);
        if (SwingUtilities.isLeftMouseButton(e) && e.getClickCount() == 2) {
        	System.out.println("Generer pdf for faktura nr.: " + model.getSaleAt(modelRowNum).getId());
        	openPDFViewer(model.getSaleAt(modelRowNum));
        }
	}

	private void openPDFViewer(Sale sale) {
		try {
			new PDFViewerDialog(this, sale);
		} catch (BuildingPDFException e) {
			Utilities.showError(this, e.getMessage());
			e.printStackTrace();
		}
	}

	private void clearSearchByCus() {
		txtCusName.setText("");
		txtPhone.setText("");
		txtCVR.setText("");
	}

	private void clearSearchByCar() {
		txtRegNr.setText("");
		txtVIN.setText("");
	}

	private void makeFieldListeners() {
		makeSaleListener();
		makeCarListener();
		makeCusListener();
	}
	
	private void makeSaleListener() {
		chbAll.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				saleSearchSetEnable();
			}
		});
		txtSaleID.getDocument().addDocumentListener(new DocumentListener() {	
			@Override
			public void removeUpdate(DocumentEvent e) {
				saleSearchSetEnable();
			}
			
			@Override
			public void insertUpdate(DocumentEvent e) {
				saleSearchSetEnable();
			}
			
			@Override
			public void changedUpdate(DocumentEvent e) {
				saleSearchSetEnable();
			}
		});

	}

	private void saleSearchSetEnable() {
		if (!txtSaleID.isEmpty()) {
			chbAll.setEnabled(false);
		} else if (chbAll.isSelected()) {
			txtSaleID.setEnabled(false);
		} else {
			txtSaleID.setEnabled(true);
			chbAll.setEnabled(true);
		}
		parent.setDefaultButton(btnSaleSearch);
	}

	private void makeCusListener() {
		ArrayList<JTextField> cusFields = new ArrayList<JTextField>();
		cusFields.add(txtCusName);
		cusFields.add(txtPhone);
		cusFields.add(txtCVR);
		Utilities.addDocumentListener(cusFields, parent, btnCusSearch);
	}

	private void makeCarListener() {
		ArrayList<JTextField> carFields = new ArrayList<JTextField>();
		carFields.add(txtRegNr);
		carFields.add(txtVIN);
		Utilities.addDocumentListener(carFields, parent, btnCarSearch);
	}

	private void clearSearchBySale() {
		txtSaleID.setText("");
		chbAll.setSelected(false);
		saleSearchSetEnable();
	}

	private void searchBySale() {
		try {
			SaleCtr sCtr = new SaleCtr();
			ArrayList<Sale> sList = new ArrayList<Sale>();
			if (!txtSaleID.isEmpty()) {
				Sale s = sCtr.getSaleByID(txtSaleID.getValue());
				if (s != null) {
					sList.add(s);
				}
			} else if (chbAll.isSelected()) {
				sList = sCtr.getAllSales();
			} 
			updateModel(sList);
		}catch (ObjectNotExistException e) {
			Utilities.showError(this, e.getMessage());
			//e.printStackTrace();
		}	
	}
	

	private void searchByCar() {
		try {
			ArrayList<Sale> sList = null;
			SaleCtr sCtr = new SaleCtr();
			if(!txtRegNr.isEmpty()) {
				sList = sCtr.getSaleByCarRegNr(txtRegNr.getText().trim());
			} else if (!txtVIN.isEmpty()) {
				sList = sCtr.getSaleByCarVIN(txtVIN.getText().trim());
			}
			updateModel(sList);
		} catch (ObjectNotExistException e) {
			Utilities.showError(this, e.getMessage());
		}
	}
	
	private void searchByCus() {
		try {
			ArrayList<Sale> sList = null;
			SaleCtr sCtr = new SaleCtr();
			if(!txtCusName.isEmpty()) {
				sList = sCtr.getSaleByCusName(txtCusName.getText());
			} else if (!txtPhone.isEmpty()) {
				sList = sCtr.getSaleByCusPhone(txtPhone.getText());
			} else if (!txtCVR.isEmpty()) {
				sList = sCtr.getSaleByCusCVR(txtCVR.getText());
			}
			updateModel(sList);
		} catch (ObjectNotExistException e) {
			Utilities.showError(this, e.getMessage());
		}
	}
	
	private void updateModel(ArrayList<Sale> sList) {
		model.refresh(sList);
	}

	@Override
	public void setFocus() {
		txtSaleID.requestFocusInWindow();
		parent.setDefaultButton(btnSaleSearch);
	}

}
