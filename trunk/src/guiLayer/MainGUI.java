package guiLayer;

import guiLayer.extensions.TabbedPanel;

import java.awt.Dimension;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.UIManager;

import javax.swing.JButton;
import javax.swing.JMenuBar;
import javax.swing.JMenu;
import javax.swing.JMenuItem;

import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

import javax.swing.JTabbedPane;

import javax.swing.JPanel;
import javax.swing.BoxLayout;
import javax.swing.event.ChangeListener;
import javax.swing.event.ChangeEvent;

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
	private CarPanel tabCar;
	private OrderPanel tabSale;
	private ProductPanel tabProd;
	
	
	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			@Override
			public void run() {
				try {
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
		
		tabSale = new OrderPanel(this);
		tabbedPane.addTab("Faktura", null, tabSale, null);
		
		tabCar = new CarPanel(this);
		tabbedPane.addTab("Biler", null, tabCar, null);
		
		tabProd = new ProductPanel(this);
		tabbedPane.addTab("Produkter", null, tabProd, null);
		
		pack();
		setFocus();
	}

	protected void openUnitTypeGUI() {
		UnitTypeDialog utDialog = new UnitTypeDialog(this);
		
	}
	
	public void setDefaultButton(JButton button){
		getRootPane().setDefaultButton(button);
	}
	
	private void setFocus() {
		TabbedPanel selectedTab = (TabbedPanel) tabbedPane.getSelectedComponent();
		selectedTab.setFocus();
		/*
		final Boolean customerTab = tabbedPane.getSelectedIndex() == 0;
		final Boolean orderTab = tabbedPane.getSelectedIndex() == 1;
		final Boolean prodTab = tabbedPane.getSelectedIndex() == 2;
		final Boolean saleTab = tabbedPane.getSelectedIndex() == 3;
				
		//TODO Muligvis forkerte navne på variablerne
				
		if(customerTab) {
			tabCus.setFocus();
		} else if(orderTab) {
			
		} else if (prodTab) {
			
		} else if (saleTab) {
			
		}
		*/
		
	}

}
