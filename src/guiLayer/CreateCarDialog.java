package guiLayer;

import java.awt.Dimension;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;

import modelLayer.Customer;

import com.jgoodies.forms.factories.FormFactory;
import com.jgoodies.forms.layout.ColumnSpec;
import com.jgoodies.forms.layout.FormLayout;
import com.jgoodies.forms.layout.RowSpec;

/**
 * Class for CreateCarDialog
 *
 * @author Group 3, dmaa0214, UCN
 *
 */
public class CreateCarDialog extends JDialog {
	
	private static final long serialVersionUID = 1L;
	private Customer customer;
	private JTextField txtReg;
	private JTextField txtVin;
	private JTextField txtManufact;
	private JTextField txtModel;
	private JTextField txtYear;
	private JTextField txtMileage;

	public CreateCarDialog(Customer cust) {
		setTitle(customer.getName() + " - Opret Ny Bil");
		this.customer = cust;
		
		
		
		
		
		Dimension minSize = new Dimension(500,300);
		this.setMinimumSize(minSize);
		getContentPane().setLayout(new FormLayout(new ColumnSpec[] {
				FormFactory.RELATED_GAP_COLSPEC,
				ColumnSpec.decode("default:grow"),
				FormFactory.RELATED_GAP_COLSPEC,
				ColumnSpec.decode("default:grow"),},
			new RowSpec[] {
				FormFactory.RELATED_GAP_ROWSPEC,
				RowSpec.decode("default:grow"),}));
		
		JPanel panel_1 = new JPanel();
		getContentPane().add(panel_1, "2, 2, fill, fill");
		panel_1.setLayout(new FormLayout(new ColumnSpec[] {
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
				FormFactory.DEFAULT_ROWSPEC,
				FormFactory.RELATED_GAP_ROWSPEC,
				FormFactory.DEFAULT_ROWSPEC,
				FormFactory.RELATED_GAP_ROWSPEC,
				FormFactory.DEFAULT_ROWSPEC,
				FormFactory.RELATED_GAP_ROWSPEC,
				FormFactory.DEFAULT_ROWSPEC,
				FormFactory.RELATED_GAP_ROWSPEC,
				RowSpec.decode("default:grow"),}));
		
		JLabel lblReg = new JLabel("Reg-nr.");
		panel_1.add(lblReg, "2, 2, left, default");
		
		txtReg = new JTextField();
		panel_1.add(txtReg, "4, 2, fill, default");
		txtReg.setColumns(10);
		
		JLabel lblVin = new JLabel("Stel-nr");
		panel_1.add(lblVin, "2, 4, left, default");
		
		txtVin = new JTextField();
		txtVin.setText("");
		panel_1.add(txtVin, "4, 4, fill, default");
		txtVin.setColumns(10);
		
		JLabel lblManufact = new JLabel("Fabrikant");
		panel_1.add(lblManufact, "2, 6, left, default");
		
		txtManufact = new JTextField();
		txtManufact.setText("");
		panel_1.add(txtManufact, "4, 6, fill, default");
		txtManufact.setColumns(10);
		
		JLabel lblModel = new JLabel("Model");
		panel_1.add(lblModel, "2, 8, left, default");
		
		txtModel = new JTextField();
		txtModel.setText("");
		panel_1.add(txtModel, "4, 8, fill, default");
		txtModel.setColumns(10);
		
		JLabel lblYear = new JLabel("\u00C5r");
		panel_1.add(lblYear, "2, 10, left, default");
		
		txtYear = new JTextField();
		txtYear.setText("");
		panel_1.add(txtYear, "4, 10, fill, default");
		txtYear.setColumns(10);
		
		JLabel lblMileage = new JLabel("Kilometerstand");
		panel_1.add(lblMileage, "2, 12, left, default");
		
		txtMileage = new JTextField();
		txtMileage.setText("");
		panel_1.add(txtMileage, "4, 12, fill, default");
		txtMileage.setColumns(10);
		
		JPanel panel_2 = new JPanel();
		panel_1.add(panel_2, "1, 18, 4, 1, fill, fill");
		
		JButton btnCreate = new JButton("Opret");
		panel_2.add(btnCreate);
		
		JButton btnAnnuller = new JButton("Annuller");
		panel_2.add(btnAnnuller);
		
		JPanel panel = new JPanel();
		getContentPane().add(panel, "4, 2, fill, fill");
		
		JButton btnGetCarInfo = new JButton("Hent biloplysninger");
		panel.add(btnGetCarInfo);
		this.setVisible(true);
	}

}
