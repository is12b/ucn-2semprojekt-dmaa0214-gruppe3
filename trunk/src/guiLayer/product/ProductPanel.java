package guiLayer.product;

import guiLayer.MainGUI;
import guiLayer.extensions.DocumentListenerChange;
import guiLayer.extensions.JTextFieldLimit;
import guiLayer.extensions.TabbedPanel;
import guiLayer.extensions.Utilities;
import guiLayer.product.models.ProductTableModel;

import java.awt.Dimension;

import javax.swing.JPanel;

import com.jgoodies.forms.layout.FormLayout;
import com.jgoodies.forms.layout.ColumnSpec;
import com.jgoodies.forms.layout.RowSpec;
import com.jgoodies.forms.factories.FormFactory;

import ctrLayer.ProductCtr;
import ctrLayer.exceptionLayer.ObjectNotExistException;
import ctrLayer.interfaceLayer.IFProductCtr;
import dbLayer.exceptions.DBException;

import javax.swing.JMenuItem;
import javax.swing.JOptionPane;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.SwingConstants;
import javax.swing.SwingUtilities;
import javax.swing.border.TitledBorder;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.JButton;

import java.awt.FlowLayout;
import java.util.ArrayList;

import javax.swing.JLabel;

import modelLayer.Product;

import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

import javax.swing.JPopupMenu;
import javax.swing.ListSelectionModel;

/**
 * Class for ProductGUI
 *
 * @author Group 3, dmaa0214, UCN
 *
 */
public class ProductPanel extends TabbedPanel {

	private static final long serialVersionUID = 1L;
	private JTextFieldLimit txtID;
	private JTextFieldLimit txtItemNumber;
	private JTextFieldLimit txtName;
	private MainGUI parent;
	private ProductTableModel model;
	private JButton btnSearch;
	private JButton btnClear;
	private JPopupMenu popupMenu;
	private JTable table;
	private ArrayList<JTextField> fields;
	
	public ProductPanel(MainGUI parent) {
		this.parent = parent;
		buildPanel();
	}

	private void buildPanel() {
		setMinimumSize(new Dimension(200,200));
		setLayout(new FormLayout(new ColumnSpec[] {
				ColumnSpec.decode("max(200px;default)"),
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
		
		txtID = new JTextFieldLimit(11, true);
		lblID.setLabelFor(txtID);
		searchPanel.add(txtID, "4, 2, fill, default");
		txtID.setColumns(10);
		
		JLabel lblItemNumber = new JLabel("Varenummer:");
		lblItemNumber.setLabelFor(lblItemNumber);
		searchPanel.add(lblItemNumber, "2, 4, right, default");
		
		txtItemNumber = new JTextFieldLimit(50,false);
		searchPanel.add(txtItemNumber, "4, 4, fill, default");
		txtItemNumber.setColumns(10);
		
		JLabel lblName = new JLabel("Navn:");
		searchPanel.add(lblName, "2, 6, right, default");
		
		txtName = new JTextFieldLimit(200, false);
		lblName.setLabelFor(txtName);
		searchPanel.add(txtName, "4, 6, fill, default");
		txtName.setColumns(10);
		
		JPanel buttonPanel = new JPanel();
		searchPanel.add(buttonPanel, "2, 8, 3, 1, fill, fill");
		buttonPanel.setLayout(new FormLayout(new ColumnSpec[] {
				FormFactory.RELATED_GAP_COLSPEC,
				ColumnSpec.decode("default:grow"),
				FormFactory.RELATED_GAP_COLSPEC,
				FormFactory.GROWING_BUTTON_COLSPEC,
				FormFactory.RELATED_GAP_COLSPEC,
				FormFactory.DEFAULT_COLSPEC,},
			new RowSpec[] {
				FormFactory.RELATED_GAP_ROWSPEC,
				FormFactory.DEFAULT_ROWSPEC,}));
		
		btnClear = new JButton("Ryd");
		btnClear.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				clear();
			}
		});
		buttonPanel.add(btnClear, "2, 2");
		
		btnSearch = new JButton("S\u00F8g");
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
		btnOpret.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				openCreateDialog();
			}
		});
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
		
		table = new JTable(model);
		table.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		table.getColumnModel().getColumn(0).setPreferredWidth(40);
		table.getColumnModel().getColumn(1).setPreferredWidth(70);
		table.getColumnModel().getColumn(2).setPreferredWidth(90);
		table.getColumnModel().getColumn(3).setPreferredWidth(125);
		table.getColumnModel().getColumn(4).setPreferredWidth(175);
		table.getColumnModel().getColumn(5).setPreferredWidth(30);
		table.getColumnModel().getColumn(6).setPreferredWidth(60);
		table.setAutoCreateRowSorter(true);
		DefaultTableCellRenderer rightRenderer = new DefaultTableCellRenderer();
		rightRenderer.setHorizontalAlignment(SwingConstants.RIGHT);
		table.getColumnModel().getColumn(6).setCellRenderer(rightRenderer);
		table.getRowSorter().toggleSortOrder(0);
		scrollPane.setViewportView(table);
		
		table.addMouseListener(new MouseAdapter() {
		    @Override
		    public void mouseClicked(MouseEvent e) {
		        tableMouseListener(e);
		    }
		});
		
		popupMenu = new JPopupMenu();
		JMenuItem mntmEdit = new JMenuItem("Ændre");
		mntmEdit.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				editProduct(table.getSelectedRow());
			}
		});
		JMenuItem mntmDelete = new JMenuItem("Slet");
		mntmDelete.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				deleteProduct(table.getSelectedRow());
			}
		});
		popupMenu.add(mntmEdit);
		popupMenu.add(mntmDelete);
		
		fields = new ArrayList<JTextField>();
		fields.add(txtID);
		fields.add(txtItemNumber);
		fields.add(txtName);
		
		addDocumentListener(fields);
	}

	private void addDocumentListener(ArrayList<JTextField> fields) {
		for(JTextField f : fields){
			f.getDocument().addDocumentListener(new DocumentListenerChange(fields, f));
		}
	}

	private void deleteProduct(int selectedRow) {
		if (selectedRow != -1) {
			int selectedModelRow = table.convertRowIndexToModel(selectedRow);
			Product p = model.getProductAt(selectedModelRow);
			int c = Utilities.showWarning(this, "Er du sikker på du vil slette VareID: " + p.getId() + "?");
			if(c == JOptionPane.YES_OPTION) {
				IFProductCtr pCtr = new ProductCtr();
				try {
					pCtr.deleteProduct(p);
					model.removeProduct(p);
				} catch (ObjectNotExistException e) {
					model.removeProduct(p);
					//e.printStackTrace();
				} catch (DBException e) {
					Utilities.showError(this, e.getMessage());
					//e.printStackTrace();
				}
			}
			
		}
	}

	private void editProduct(int selectedRow) {
		if (selectedRow != -1) {
			int selectedModelRow = table.convertRowIndexToModel(selectedRow);
			Product product = model.getProductAt(selectedModelRow);
			if (product != null) {
				CreateProductDialog dialog = new CreateProductDialog(this, product);
				Product tempProd = dialog.getProduct();
				if (tempProd == null) {
					model.removeProduct(product);
				} else {
					model.fireTableDataChanged();
				}
				dialog.dispose();
			}
		}
	}

	private void tableMouseListener(MouseEvent e) {
		int rowNumber = table.rowAtPoint(e.getPoint());
        table.setRowSelectionInterval(rowNumber, rowNumber);
        if (SwingUtilities.isRightMouseButton(e)) {
        	popupMenu.show(table, e.getX(), e.getY());
        }
        else if (SwingUtilities.isLeftMouseButton(e) && e.getClickCount() == 2) {
        	System.out.println("venstreklik"); //TODO DO SOMETHING?
        	editProduct(rowNumber);
        }
	}

	private void openCreateDialog() {
		CreateProductDialog dialog = new CreateProductDialog(this);
		Product p = dialog.getProduct();
		if(p != null) {
			model.addProduct(p);
		}
		dialog.dispose();
	}

	private void clear() {
		txtID.setText("");
		txtItemNumber.setText("");
		txtName.setText("");
	}

	private void search() {
		IFProductCtr pCtr = new ProductCtr();
		ArrayList<Product> pList = new ArrayList<Product>();
		if (!txtID.getText().trim().isEmpty()) {
			try {
				Product p = pCtr.getProductByID(txtID.getValue());
				if (p != null) {
					pList.add(p);
				}
			} catch (NumberFormatException | ObjectNotExistException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		} else if (!txtItemNumber.getText().trim().isEmpty()) {
			try {
				pList = pCtr.searchProductsByItemNumber(txtItemNumber.getText().trim());
			} catch (ObjectNotExistException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		} else if (!txtName.getText().trim().isEmpty()) {
			try {
				pList = pCtr.searchProductsByName(txtName.getText().trim());
			} catch (ObjectNotExistException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		updateModel(pList);
	}

	private void updateModel(ArrayList<Product> pList) {
		model.refresh(pList);
		model.fireTableDataChanged();
	}

	@Override
	public void setFocus() {
		txtID.requestFocusInWindow();
		parent.setDefaultButton(btnSearch);
	}

}
