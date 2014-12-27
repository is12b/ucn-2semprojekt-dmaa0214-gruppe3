package guiLayer.customer;

import exceptions.ObjectNotExistException;
import guiLayer.extensions.Utilities;

import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.DefaultListModel;
import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.border.TitledBorder;

import modelLayer.Car;
import modelLayer.Inspection;

import com.jgoodies.forms.factories.FormFactory;
import com.jgoodies.forms.layout.ColumnSpec;
import com.jgoodies.forms.layout.FormLayout;
import com.jgoodies.forms.layout.RowSpec;

import ctrLayer.CarCtr;

import javax.swing.BoxLayout;

import java.awt.BorderLayout;
import java.util.ArrayList;

import javax.swing.JScrollPane;
import javax.swing.JList;

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
	private JList<String> list;
	private DefaultListModel<String> model;

	/**
	 * Constructor for CarInfoDialog objects.
	 *
	 * @param car
	 */
	CarInfoDialog(Car car) {
		this.car = car;
		if(car.getExtra() == null){
			try {
				CarCtr cCtr = new CarCtr();
				cCtr.getCarExtra(car);
			} catch (ObjectNotExistException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		buildDialog();
	}	

	private void buildDialog() {
		this.setModalityType(DEFAULT_MODALITY_TYPE);
		
		getContentPane().setLayout(new FormLayout(new ColumnSpec[] {
				FormFactory.RELATED_GAP_COLSPEC,
				ColumnSpec.decode("max(195dlu;default)"),
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
				FormFactory.DEFAULT_ROWSPEC,
				FormFactory.RELATED_GAP_ROWSPEC,
				RowSpec.decode("default:grow"),}));

		setTitle(car.getRegNr() + " - Biloplysninger");
		
		JLabel lblRegNr = new JLabel("Reg-nr.");
		panel_2.add(lblRegNr, "2, 2, right, default");

		txtReg = new JTextField();
		txtReg.setText(car.getRegNr());
		panel_2.add(txtReg, "4, 2, fill, default");
		txtReg.setColumns(10);

		JLabel lblVin = new JLabel("Stel-nr.");
		panel_2.add(lblVin, "2, 4, right, default");

		txtVin = new JTextField();
		txtVin.setText(car.getVin());
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
		txtModel.setText(car.getModel());
		panel_2.add(txtModel, "4, 8, fill, default");
		txtModel.setColumns(10);

		JLabel lblFistReg = new JLabel("1. reg. dato");
		panel_2.add(lblFistReg, "2, 10, right, default");
		

		txtFirstReg = new JTextField();
		if(car.getExtra() != null){
			txtFirstReg.setText(car.getExtra().getFirstRegDate());
		}
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

		txtStatus = new JTextField();
		if(car.getExtra() != null){
			txtStatus.setText(car.getExtra().getStatus());
		}
		panel_2.add(txtStatus, "4, 14, fill, default");
		txtStatus.setColumns(10);

		JPanel panel_3 = new JPanel();
		panel_2.add(panel_3, "2, 16, 3, 1, fill, fill");
		panel_3.setLayout(new FormLayout(new ColumnSpec[] {
				ColumnSpec.decode("default:grow"),
				FormFactory.RELATED_GAP_COLSPEC,
				FormFactory.DEFAULT_COLSPEC,
				FormFactory.RELATED_GAP_COLSPEC,
				ColumnSpec.decode("default:grow"),},
			new RowSpec[] {
				FormFactory.DEFAULT_ROWSPEC,}));
		
		JButton btnSave = new JButton("Gem");
		panel_3.add(btnSave, "1, 1, fill, default");
		
		JButton btnUpdate = new JButton("Opdater");
		btnUpdate.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				updateExtra();
			}
		});
		panel_3.add(btnUpdate, "5, 1, fill, default");

		JPanel panel = new JPanel();
		panel.setBorder(new TitledBorder(null, "Synsinformation", TitledBorder.LEADING, TitledBorder.TOP, null, null));
		getContentPane().add(panel, "4, 2, fill, fill");
		panel.setLayout(new FormLayout(new ColumnSpec[] {
				ColumnSpec.decode("default:grow"),},
			new RowSpec[] {
				RowSpec.decode("default:grow"),}));

		JPanel panel_1 = new JPanel();
		panel.add(panel_1, "1, 1, fill, fill");
		panel_1.setLayout(new BorderLayout(0, 0));
		
		JScrollPane scrollPane = new JScrollPane();
		panel_1.add(scrollPane, BorderLayout.CENTER);
		
		list = new JList<String>();
		model = new DefaultListModel<String>();
		list.setModel(model);
		if(car.getInspections() != null || car.getInspections().size() > 0){
			populateList(car.getInspections());
		}
		scrollPane.setViewportView(list);
		
		Utilities.addEscapeListener(this);
		Dimension minSize = new Dimension(500,350);
		this.setMinimumSize(minSize);
		this.setVisible(true);
	}
	
	
	/**
	 * 
	 */
	protected void updateExtra() {
		// TODO Auto-generated method stub
		
	}

	private void populateList(ArrayList<Inspection> inspecs){
		for(Inspection i : inspecs){
			System.out.println(i.getDate());
			model.addElement(i.toString());
		}
	}
}

