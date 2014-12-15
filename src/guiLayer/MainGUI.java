package guiLayer;

import guiLayer.customer.CustomerPanel;
import guiLayer.extensions.IFTabbedPanel;
import guiLayer.product.ProductPanel;
import guiLayer.product.UnitTypeDialog;
import guiLayer.sale.InvoiceSettingsDialog;
import guiLayer.sale.SalePanel;
import guiLayer.sale.SaleOverviewPanel;

import java.awt.Dialog.ModalityType;
import java.awt.Dimension;
import java.awt.EventQueue;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Locale;

import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JPanel;
import javax.swing.JTabbedPane;
import javax.swing.UIManager;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;

/**
 * Class for MainGUI
 *
 * @author Group 3, dmaa0214, UCN
 *
 */
public class MainGUI extends JFrame {

	private static final long serialVersionUID = 1L;
	private JTabbedPane tabbedPane;
	private CustomerPanel tabCus;
	//private CarPanel tabCar;
	private SalePanel tabOrder;
	private ProductPanel tabProd;
	private SaleOverviewPanel tabSaleOverview;
	
	
	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			@Override
			public void run() {
				try {
					Locale.setDefault(new Locale("da", "DK"));
					UIManager.put("OptionPane.cancelButtonText", "Annuller");
				    UIManager.put("OptionPane.noButtonText", "Nej");
				    UIManager.put("OptionPane.okButtonText", "Ok");
				    UIManager.put("OptionPane.yesButtonText", "Ja");
					UIManager.setLookAndFeel("com.sun.java.swing.plaf.windows.WindowsLookAndFeel");
					MainGUI frame = new MainGUI();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the frame.
	 */
	public MainGUI() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setTitle("Finn's Auto-Service");
		setMinimumSize(new Dimension(1000, 600));
		
		JMenuBar menuBar = new JMenuBar();
		setJMenuBar(menuBar);
		
		JMenu mnFile = new JMenu("Filer");
		menuBar.add(mnFile);
		
		JMenu mnSettings = new JMenu("Indstillinger");
		menuBar.add(mnSettings);
		
		JMenuItem mntmUnitTypes = new JMenuItem("Enhedstyper");
		mntmUnitTypes.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				openUnitTypeGUI();
			}
		});
		mnSettings.add(mntmUnitTypes);
		
		JMenuItem mntmInvoiceSettings = new JMenuItem("Faktura");
		mntmInvoiceSettings.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				openInvoiceSettings();
			}
		});
		mnSettings.add(mntmInvoiceSettings);
		
		JMenu mnHelp = new JMenu("Hj\u00E6lp");
		menuBar.add(mnHelp);
		
		JPanel contentPane = new JPanel();
		setContentPane(contentPane);
		contentPane.setLayout(new BoxLayout(contentPane, BoxLayout.X_AXIS));
		
		tabbedPane = new JTabbedPane(JTabbedPane.TOP);
		tabbedPane.addChangeListener(new ChangeListener() {
			public void stateChanged(ChangeEvent arg0) {
				setFocus();
			}
		});
		contentPane.add(tabbedPane);
		
		tabCus = new CustomerPanel(this);
		tabbedPane.addTab("Kunde", null, tabCus, null);
		
		tabOrder = new SalePanel(this);
		tabbedPane.addTab("Faktura", null, tabOrder, null);
		
		//tabCar = new CarPanel(this);
		//tabbedPane.addTab("Biler", null, tabCar, null);
		
		tabProd = new ProductPanel(this);
		tabbedPane.addTab("Produkter", null, tabProd, null);
		
		tabSaleOverview = new SaleOverviewPanel(this);
		tabbedPane.addTab("Faktura Oversigt", null, tabSaleOverview, null);
		
		pack();
		setFocus();
	}

	/**
	 * 
	 */
	private void openInvoiceSettings() {
		InvoiceSettingsDialog iDialog = new InvoiceSettingsDialog();
		iDialog.setModalityType(ModalityType.APPLICATION_MODAL);
		iDialog.setVisible(true);
	}

	private void openUnitTypeGUI() {
		UnitTypeDialog utDialog = new UnitTypeDialog(this);
		utDialog.setVisible(false);
		utDialog.dispose();
	}
	
	public void setDefaultButton(JButton button){
		getRootPane().setDefaultButton(button);
	}
	
	public void recreateOrderPanel(){
		tabbedPane.remove(tabOrder);
		tabOrder = new SalePanel(this);
		tabbedPane.insertTab("Faktura", null, tabOrder, null, 1);
		tabbedPane.setSelectedIndex(1);
	}
	
	private void setFocus() {
		try {
			IFTabbedPanel selectedTab = (IFTabbedPanel) tabbedPane.getSelectedComponent();
			selectedTab.setFocus();
		} catch (Exception e) {
			
		}
	}

}
