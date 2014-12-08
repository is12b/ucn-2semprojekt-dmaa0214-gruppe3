package guiLayer;

import guiLayer.extensions.Utilities;

import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.border.TitledBorder;

import modelLayer.Car;

import com.jgoodies.forms.factories.FormFactory;
import com.jgoodies.forms.layout.ColumnSpec;
import com.jgoodies.forms.layout.FormLayout;
import com.jgoodies.forms.layout.RowSpec;

/**
 * Class for CarInfoDialog
 *
 * @author Group 3, dmaa0214, UCN
 *
 */
class CarInfoDialog extends JDialog {

	private static final long serialVersionUID = 1L;
	private Car car;
	private JTextField txtReg;
	private JTextField txtVin;
	private JTextField txtManufact;
	private JTextField txtModel;
	private JTextField txtFirstReg;
	private JTextField txtInsurance;
	private JTextField txtStatus;

	/**
	 * Constructor for CarInfoDialog objects.
	 *
	 * @param car
	 */
	CarInfoDialog(Car car) {
		this.car = car;
		buildDialog();
	}	

	private void buildDialog() {
		this.setModalityType(DEFAULT_MODALITY_TYPE);
		
		getContentPane().setLayout(new FormLayout(new ColumnSpec[] {
				FormFactory.RELATED_GAP_COLSPEC,
				ColumnSpec.decode("default:grow"),
				FormFactory.RELATED_GAP_COLSPEC,
				ColumnSpec.decode("default:grow"),},
				new RowSpec[] {
				FormFactory.RELATED_GAP_ROWSPEC,
				RowSpec.decode("default:grow"),}));

		
		JPanel panel_2 = new JPanel();
		panel_2.setBorder(new TitledBorder(null, "Biloplysninger", TitledBorder.LEADING, TitledBorder.TOP, null, null));
		getContentPane().add(panel_2, "2, 2, fill, fill");
		panel_2.setLayout(new FormLayout(new ColumnSpec[] {
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
				RowSpec.decode("default:grow"),}));

		setTitle(car.getRegNr() + " - Biloplysninger");
		
		JLabel lblRegNr = new JLabel("Reg-nr.");
		panel_2.add(lblRegNr, "2, 2, right, default");

		txtReg = new JTextField();
		panel_2.add(txtReg, "4, 2, fill, default");
		txtReg.setColumns(10);

		JLabel lblVin = new JLabel("Stel-nr.");
		panel_2.add(lblVin, "2, 4, right, default");

		txtVin = new JTextField();
		txtVin.setText("");
		panel_2.add(txtVin, "4, 4, fill, top");
		txtVin.setColumns(10);

		JLabel lblManufact = new JLabel("Fabrikant");
		panel_2.add(lblManufact, "2, 6, right, default");

		txtManufact = new JTextField();
		txtManufact.setText("");
		panel_2.add(txtManufact, "4, 6, fill, default");
		txtManufact.setColumns(10);

		JLabel lblModel = new JLabel("Model");
		panel_2.add(lblModel, "2, 8, right, default");

		txtModel = new JTextField();
		txtModel.setText("");
		panel_2.add(txtModel, "4, 8, fill, default");
		txtModel.setColumns(10);

		JLabel lblFistReg = new JLabel("1. reg. dato");
		panel_2.add(lblFistReg, "2, 10, right, default");

		txtFirstReg = new JTextField();
		txtFirstReg.setText("");
		panel_2.add(txtFirstReg, "4, 10, fill, default");
		txtFirstReg.setColumns(10);

		JLabel lblInsurance = new JLabel("Forsikring");
		panel_2.add(lblInsurance, "2, 12, right, default");

		txtInsurance = new JTextField();
		txtInsurance.setText("");
		panel_2.add(txtInsurance, "4, 12, fill, default");
		txtInsurance.setColumns(10);

		JLabel lblStatus = new JLabel("Status");
		panel_2.add(lblStatus, "2, 14, right, default");

		JButton btnSave = new JButton("Gem");
		btnSave.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
			}
		});

		txtStatus = new JTextField();
		txtStatus.setText("");
		panel_2.add(txtStatus, "4, 14, fill, default");
		txtStatus.setColumns(10);
		panel_2.add(btnSave, "2, 16");

		JPanel panel_3 = new JPanel();
		panel_2.add(panel_3, "4, 16, fill, fill");

		JPanel panel = new JPanel();
		panel.setBorder(new TitledBorder(null, "Synsinformation", TitledBorder.LEADING, TitledBorder.TOP, null, null));
		getContentPane().add(panel, "4, 2, fill, fill");
		panel.setLayout(new FormLayout(new ColumnSpec[] {
				FormFactory.RELATED_GAP_COLSPEC,
				ColumnSpec.decode("default:grow"),},
				new RowSpec[] {
				FormFactory.RELATED_GAP_ROWSPEC,
				FormFactory.DEFAULT_ROWSPEC,
				FormFactory.RELATED_GAP_ROWSPEC,
				RowSpec.decode("default:grow"),}));

		JPanel panel_1 = new JPanel();
		panel.add(panel_1, "2, 4, fill, fill");
		
		Utilities.addEscapeListener(this);
		Dimension minSize = new Dimension(500,350);
		this.setMinimumSize(minSize);
		this.setVisible(true);
	} //End Build
}

