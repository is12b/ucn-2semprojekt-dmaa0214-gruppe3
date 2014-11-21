package guiLayer;

import java.awt.Dimension;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.UIManager;

import java.awt.Window.Type;

import javax.swing.JMenuBar;
import javax.swing.JMenu;
import javax.swing.JMenuItem;

import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

import javax.swing.JTabbedPane;

import java.awt.BorderLayout;

import javax.swing.JPanel;
import javax.swing.BoxLayout;

/**
 * Class for MainGUI
 *
 * @author Group 3, dmaa0214, UCN
 *
 */
public class MainGUI extends JFrame {

	private static final long serialVersionUID = 1L;
	private JTabbedPane tabbedPane;
	
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
		setTitle("Finn's Autoservice");
		setMinimumSize(new Dimension(800, 515));
		
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
		contentPane.add(tabbedPane);
		
		JPanel tabCus = new JPanel();
		tabbedPane.addTab("Kunde", null, tabCus, null);
		
		JPanel tabSale = new JPanel();
		tabbedPane.addTab("Faktura", null, tabSale, null);
		
		JPanel tabCar = new JPanel();
		tabbedPane.addTab("Biler", null, tabCar, null);
		
		JPanel tabProd = new JPanel();
		tabbedPane.addTab("Produkter", null, tabProd, null);
		
	}

	protected void openUnitTypeGUI() {
		UnitTypeDialog utDialog = new UnitTypeDialog(this);
		
	}

}
