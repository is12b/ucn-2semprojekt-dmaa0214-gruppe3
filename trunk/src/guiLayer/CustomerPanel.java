package guiLayer;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import com.jgoodies.forms.layout.FormLayout;
import com.jgoodies.forms.layout.ColumnSpec;
import com.jgoodies.forms.layout.RowSpec;
import java.awt.BorderLayout;
import com.jgoodies.forms.factories.FormFactory;
import javax.swing.border.TitledBorder;
import javax.swing.UIManager;
import java.awt.Color;

/**
 * Class for CustomerPanel
 *
 * @author Group 3, dmaa0214, UCN
 *
 */
public class CustomerPanel extends JPanel {

	private JFrame frmFinnsAutoservice;
	private MainGUI parent;

	/**
	 * Create the application.
	 */
	public CustomerPanel(MainGUI parent) {
		this.parent = parent;
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frmFinnsAutoservice = new JFrame();
		frmFinnsAutoservice.setTitle("Finn's Auto-Service");
		frmFinnsAutoservice.setBounds(100, 100, 715, 468);
		frmFinnsAutoservice.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frmFinnsAutoservice.getContentPane().setLayout(new FormLayout(new ColumnSpec[] {
				FormFactory.RELATED_GAP_COLSPEC,
				ColumnSpec.decode("default:grow"),
				FormFactory.RELATED_GAP_COLSPEC,
				ColumnSpec.decode("default:grow"),},
			new RowSpec[] {
				FormFactory.RELATED_GAP_ROWSPEC,
				RowSpec.decode("default:grow"),}));
		
		JPanel panel = new JPanel();
		panel.setBorder(new TitledBorder(null, "S\u00F8g Kunde", TitledBorder.LEADING, TitledBorder.TOP, null, null));
		frmFinnsAutoservice.getContentPane().add(panel, "2, 2, fill, fill");
	}

}
