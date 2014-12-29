package guiLayer.customer;

import exceptions.BuildingPDFException;
import exceptions.ObjectNotExistException;
import guiLayer.PDFViewerDialog;
import guiLayer.extensions.Utilities;

import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

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

import guiLayer.extensions.InfoPanel;

/**
 * Class for CarInfoDialog
 *
 * @author Group 3, dmaa0214, UCN
 *
 */
class CarInfoDialog extends JDialog {

	private static final long serialVersionUID = 1L;
	private Car car;
	private JList<Inspection> list;
	private DefaultListModel<Inspection> model;
	private JTextField txtRegnr;
	private JTextField txtVin;
	private JTextField txtType;
	private JTextField txtLastChangeReg;
	private JTextField txtUse;
	private JTextField txtLastChangeVehicle;
	private JTextField txtStatus;
	private JTextField txtPosVin;
	private JTextField txtTotal;
	private JTextField txtTecTotal;
	private JTextField txtInspectionFreq;
	private JTextField txtCallInspectionDate;
	private JTextField txtModel;
	private JTextField txtBrand;
	private JTextField txtMileage;
	private JTextField txtYear;
	private JTextField txtFirstReg;

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
				RowSpec.decode("max(195dlu;default):grow"),}));

		
		JPanel panel_2 = new JPanel();
		panel_2.setBorder(new TitledBorder(null, "Biloplysninger", TitledBorder.LEADING, TitledBorder.TOP, null, null));
		getContentPane().add(panel_2, "2, 2, fill, fill");
		panel_2.setLayout(new FormLayout(new ColumnSpec[] {
				FormFactory.RELATED_GAP_COLSPEC,
				ColumnSpec.decode("default:grow"),
				FormFactory.RELATED_GAP_COLSPEC,
				ColumnSpec.decode("default:grow"),},
			new RowSpec[] {
				FormFactory.DEFAULT_ROWSPEC,
				FormFactory.RELATED_GAP_ROWSPEC,
				FormFactory.DEFAULT_ROWSPEC,
				FormFactory.RELATED_GAP_ROWSPEC,
				RowSpec.decode("default:grow"),
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
				FormFactory.DEFAULT_ROWSPEC,
				FormFactory.RELATED_GAP_ROWSPEC,
				FormFactory.DEFAULT_ROWSPEC,
				FormFactory.RELATED_GAP_ROWSPEC,
				RowSpec.decode("default:grow"),
				FormFactory.RELATED_GAP_ROWSPEC,
				FormFactory.DEFAULT_ROWSPEC,
				FormFactory.RELATED_GAP_ROWSPEC,
				FormFactory.DEFAULT_ROWSPEC,
				FormFactory.RELATED_GAP_ROWSPEC,
				RowSpec.decode("default:grow"),}));

		if(car.getRegNr() != null && !car.getRegNr().trim().isEmpty()){
			setTitle(car.getRegNr() + " - Biloplysninger");
		}else if(car.getVin() != null && !car.getVin().trim().isEmpty()){
			setTitle(car.getVin() + " - Biloplysninger");
		}
		
		JPanel panel_4 = new JPanel();
		panel_2.add(panel_4, "2, 1, 3, 1, fill, fill");
		panel_4.setLayout(new FormLayout(new ColumnSpec[] {
				FormFactory.DEFAULT_COLSPEC,
				FormFactory.RELATED_GAP_COLSPEC,
				ColumnSpec.decode("default:grow"),},
			new RowSpec[] {
				FormFactory.DEFAULT_ROWSPEC,}));
		
		JLabel lblRegnr = new JLabel("Regnr:");
		panel_4.add(lblRegnr, "1, 1, right, default");
		
		txtRegnr = new JTextField();
		panel_4.add(txtRegnr, "3, 1, fill, default");
		txtRegnr.setColumns(10);
		
		JPanel panel_5 = new JPanel();
		panel_2.add(panel_5, "2, 3, 3, 1, fill, fill");
		panel_5.setLayout(new FormLayout(new ColumnSpec[] {
				FormFactory.DEFAULT_COLSPEC,
				FormFactory.RELATED_GAP_COLSPEC,
				ColumnSpec.decode("default:grow"),},
			new RowSpec[] {
				FormFactory.DEFAULT_ROWSPEC,}));
		
		JLabel lblStelnr = new JLabel("Stelnr:");
		panel_5.add(lblStelnr, "1, 1, right, default");
		
		txtVin = new JTextField();
		panel_5.add(txtVin, "3, 1, fill, default");
		txtVin.setColumns(10);
		
		JPanel panel_20 = new JPanel();
		panel_2.add(panel_20, "2, 5, 3, 1, fill, fill");
		panel_20.setLayout(new FormLayout(new ColumnSpec[] {
				FormFactory.DEFAULT_COLSPEC,
				FormFactory.RELATED_GAP_COLSPEC,
				ColumnSpec.decode("default:grow"),},
			new RowSpec[] {
				FormFactory.DEFAULT_ROWSPEC,}));
		
		JLabel lblFrsteRegDato = new JLabel("F\u00F8rste reg. Dato:");
		panel_20.add(lblFrsteRegDato, "1, 1, right, default");
		
		txtFirstReg = new JTextField();
		txtFirstReg.setText("");
		panel_20.add(txtFirstReg, "3, 1, fill, default");
		txtFirstReg.setColumns(10);
		
		JPanel panel_6 = new JPanel();
		panel_2.add(panel_6, "2, 7, 3, 1, fill, fill");
		panel_6.setLayout(new FormLayout(new ColumnSpec[] {
				FormFactory.DEFAULT_COLSPEC,
				FormFactory.RELATED_GAP_COLSPEC,
				ColumnSpec.decode("default:grow"),},
			new RowSpec[] {
				FormFactory.DEFAULT_ROWSPEC,}));
		
		JLabel lblNewLabel = new JLabel("Art:");
		panel_6.add(lblNewLabel, "1, 1, right, default");
		
		txtType = new JTextField();
		panel_6.add(txtType, "3, 1, fill, default");
		txtType.setColumns(10);		
		JPanel panel_8 = new JPanel();
		panel_2.add(panel_8, "2, 9, 3, 1, fill, fill");
		panel_8.setLayout(new FormLayout(new ColumnSpec[] {
				FormFactory.DEFAULT_COLSPEC,
				FormFactory.RELATED_GAP_COLSPEC,
				ColumnSpec.decode("default:grow"),},
			new RowSpec[] {
				FormFactory.DEFAULT_ROWSPEC,}));
		
		JLabel lblNewLabel_1 = new JLabel("Seneste \u00E6ndring / Bil:");
		panel_8.add(lblNewLabel_1, "1, 1, right, default");
		
		txtLastChangeVehicle = new JTextField();
		panel_8.add(txtLastChangeVehicle, "3, 1, fill, default");
		txtLastChangeVehicle.setColumns(10);
		
		JPanel panel_9 = new JPanel();
		panel_2.add(panel_9, "2, 11, 3, 1, fill, fill");
		panel_9.setLayout(new FormLayout(new ColumnSpec[] {
				FormFactory.DEFAULT_COLSPEC,
				FormFactory.RELATED_GAP_COLSPEC,
				ColumnSpec.decode("default:grow"),},
			new RowSpec[] {
				FormFactory.DEFAULT_ROWSPEC,}));
		
		JLabel lblNewLabel_2 = new JLabel("Anvendelse:");
		panel_9.add(lblNewLabel_2, "1, 1, right, default");
		
		txtUse = new JTextField();
		panel_9.add(txtUse, "3, 1, fill, default");
		txtUse.setColumns(10);
		
		JPanel panel_7 = new JPanel();
		panel_2.add(panel_7, "2, 13, 3, 1, fill, fill");
		panel_7.setLayout(new FormLayout(new ColumnSpec[] {
				FormFactory.DEFAULT_COLSPEC,
				FormFactory.RELATED_GAP_COLSPEC,
				ColumnSpec.decode("default:grow"),},
			new RowSpec[] {
				FormFactory.DEFAULT_ROWSPEC,}));
		
		JLabel lblNewLabel_3 = new JLabel("Seneste \u00E6ndring / Registering:");
		panel_7.add(lblNewLabel_3, "1, 1, right, default");
		
		txtLastChangeReg = new JTextField();
		panel_7.add(txtLastChangeReg, "3, 1, fill, default");
		txtLastChangeReg.setColumns(10);
		
		JPanel panel_10 = new JPanel();
		panel_2.add(panel_10, "2, 15, 3, 1, fill, fill");
		panel_10.setLayout(new FormLayout(new ColumnSpec[] {
				FormFactory.DEFAULT_COLSPEC,
				FormFactory.RELATED_GAP_COLSPEC,
				ColumnSpec.decode("default:grow"),},
			new RowSpec[] {
				FormFactory.DEFAULT_ROWSPEC,}));
		
		JLabel lblNewLabel_4 = new JLabel("Status:");
		panel_10.add(lblNewLabel_4, "1, 1, right, default");
		
		txtStatus = new JTextField();
		panel_10.add(txtStatus, "3, 1, fill, default");
		txtStatus.setColumns(10);
		
		JPanel panel_11 = new JPanel();
		panel_2.add(panel_11, "2, 17, 3, 1, fill, fill");
		panel_11.setLayout(new FormLayout(new ColumnSpec[] {
				FormFactory.DEFAULT_COLSPEC,
				FormFactory.RELATED_GAP_COLSPEC,
				ColumnSpec.decode("default:grow"),},
			new RowSpec[] {
				FormFactory.DEFAULT_ROWSPEC,}));
		
		JLabel lblNewLabel_5 = new JLabel("Teknisk total v\u00E6gt:");
		panel_11.add(lblNewLabel_5, "1, 1, right, default");
		
		txtTecTotal = new JTextField();
		panel_11.add(txtTecTotal, "3, 1, fill, default");
		txtTecTotal.setColumns(10);
		
		JPanel panel_12 = new JPanel();
		panel_2.add(panel_12, "2, 19, 3, 1, fill, fill");
		panel_12.setLayout(new FormLayout(new ColumnSpec[] {
				FormFactory.DEFAULT_COLSPEC,
				FormFactory.RELATED_GAP_COLSPEC,
				ColumnSpec.decode("default:grow"),},
			new RowSpec[] {
				FormFactory.DEFAULT_ROWSPEC,}));
		
		JLabel lblNewLabel_6 = new JLabel("Total v\u00E6gt:");
		panel_12.add(lblNewLabel_6, "1, 1, right, default");
		
		txtTotal = new JTextField();
		panel_12.add(txtTotal, "3, 1, fill, default");
		txtTotal.setColumns(10);
		
		JPanel panel_13 = new JPanel();
		panel_2.add(panel_13, "2, 21, 3, 1, fill, fill");
		panel_13.setLayout(new FormLayout(new ColumnSpec[] {
				FormFactory.DEFAULT_COLSPEC,
				FormFactory.RELATED_GAP_COLSPEC,
				ColumnSpec.decode("default:grow"),},
			new RowSpec[] {
				FormFactory.DEFAULT_ROWSPEC,}));
		
		JLabel lblNewLabel_7 = new JLabel("Anbringelse af stelnummer:");
		panel_13.add(lblNewLabel_7, "1, 1, right, default");
		
		txtPosVin = new JTextField();
		panel_13.add(txtPosVin, "3, 1, fill, default");
		txtPosVin.setColumns(10);
		
		JPanel panel_18 = new JPanel();
		panel_2.add(panel_18, "2, 23, 3, 1, fill, fill");
		panel_18.setLayout(new FormLayout(new ColumnSpec[] {
				FormFactory.DEFAULT_COLSPEC,
				FormFactory.RELATED_GAP_COLSPEC,
				ColumnSpec.decode("default:grow"),},
			new RowSpec[] {
				FormFactory.DEFAULT_ROWSPEC,}));
		
		JLabel lblNewLabel_10 = new JLabel("Model:");
		panel_18.add(lblNewLabel_10, "1, 1, right, default");
		
		txtModel = new JTextField();
		panel_18.add(txtModel, "3, 1, fill, default");
		txtModel.setColumns(10);
		
		JPanel panel_17 = new JPanel();
		panel_2.add(panel_17, "2, 25, 3, 1, fill, fill");
		panel_17.setLayout(new FormLayout(new ColumnSpec[] {
				FormFactory.DEFAULT_COLSPEC,
				FormFactory.RELATED_GAP_COLSPEC,
				ColumnSpec.decode("default:grow"),},
			new RowSpec[] {
				FormFactory.DEFAULT_ROWSPEC,}));
		
		JLabel lblNewLabel_11 = new JLabel("Fabrikat:");
		panel_17.add(lblNewLabel_11, "1, 1, right, default");
		
		txtBrand = new JTextField();
		panel_17.add(txtBrand, "3, 1, fill, default");
		txtBrand.setColumns(10);
		
		JPanel panel_19 = new JPanel();
		panel_2.add(panel_19, "2, 27, 3, 1, fill, fill");
		panel_19.setLayout(new FormLayout(new ColumnSpec[] {
				FormFactory.DEFAULT_COLSPEC,
				FormFactory.RELATED_GAP_COLSPEC,
				ColumnSpec.decode("default:grow"),},
			new RowSpec[] {
				FormFactory.DEFAULT_ROWSPEC,}));
		
		JLabel lblrgang = new JLabel("\u00C5rgang:");
		panel_19.add(lblrgang, "1, 1, right, default");
		
		txtYear = new JTextField();
		panel_19.add(txtYear, "3, 1, fill, default");
		txtYear.setColumns(10);
		
		JPanel panel_16 = new JPanel();
		panel_2.add(panel_16, "2, 29, 3, 1, fill, fill");
		panel_16.setLayout(new FormLayout(new ColumnSpec[] {
				FormFactory.DEFAULT_COLSPEC,
				FormFactory.RELATED_GAP_COLSPEC,
				ColumnSpec.decode("default:grow"),},
			new RowSpec[] {
				FormFactory.DEFAULT_ROWSPEC,}));
		
		JLabel lblNewLabel_12 = new JLabel("Kilometer stand:");
		panel_16.add(lblNewLabel_12, "1, 1, right, default");
		
		txtMileage = new JTextField();
		panel_16.add(txtMileage, "3, 1, fill, default");
		txtMileage.setColumns(10);

		JPanel panel_3 = new JPanel();
		panel_2.add(panel_3, "2, 31, 3, 1, fill, fill");
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
				FormFactory.DEFAULT_ROWSPEC,
				FormFactory.RELATED_GAP_ROWSPEC,
				FormFactory.DEFAULT_ROWSPEC,
				FormFactory.UNRELATED_GAP_ROWSPEC,
				FormFactory.DEFAULT_ROWSPEC,
				RowSpec.decode("default:grow"),}));
		
		JPanel panel_14 = new JPanel();
		panel.add(panel_14, "1, 1, fill, fill");
		panel_14.setLayout(new FormLayout(new ColumnSpec[] {
				FormFactory.DEFAULT_COLSPEC,
				FormFactory.RELATED_GAP_COLSPEC,
				ColumnSpec.decode("default:grow"),},
			new RowSpec[] {
				FormFactory.DEFAULT_ROWSPEC,}));
		
		JLabel lblNewLabel_9 = new JLabel("Frekvens for periodisk syn:");
		panel_14.add(lblNewLabel_9, "1, 1, right, default");
		
		txtInspectionFreq = new JTextField();
		panel_14.add(txtInspectionFreq, "3, 1, fill, default");
		txtInspectionFreq.setColumns(10);
		
		JPanel panel_15 = new JPanel();
		panel.add(panel_15, "1, 3, fill, fill");
		panel_15.setLayout(new FormLayout(new ColumnSpec[] {
				FormFactory.DEFAULT_COLSPEC,
				FormFactory.RELATED_GAP_COLSPEC,
				ColumnSpec.decode("default:grow"),},
			new RowSpec[] {
				FormFactory.DEFAULT_ROWSPEC,}));
		
		JLabel lblNewLabel_8 = new JLabel("Beregnet dato for syn:");
		panel_15.add(lblNewLabel_8, "1, 1, right, default");
		
		txtCallInspectionDate = new JTextField();
		panel_15.add(txtCallInspectionDate, "3, 1, fill, default");
		txtCallInspectionDate.setColumns(10);
		
		JLabel lblTidligereSyn = new JLabel("Tidligere syn:");
		panel.add(lblTidligereSyn, "1, 5");

		JPanel panel_1 = new JPanel();
		panel.add(panel_1, "1, 6, fill, fill");
		panel_1.setLayout(new BorderLayout(0, 0));
		
		JScrollPane scrollPane = new JScrollPane();
		panel_1.add(scrollPane, BorderLayout.CENTER);
		
		list = new JList<Inspection>();
		
		list.addMouseListener(new MouseAdapter() {
		    public void mouseClicked(MouseEvent evt) {
		        JList list = (JList)evt.getSource();
		        if (evt.getClickCount() == 2) {
		            Inspection i = (Inspection) list.getModel().getElementAt(list.locationToIndex(evt.getPoint()));
		            try {
						new PDFViewerDialog(CarInfoDialog.this, i.getUrl());
					} catch (BuildingPDFException e) {
						Utilities.showError(CarInfoDialog.this, e.getMessage());
					}
		        }
		    }
		});
		model = new DefaultListModel<Inspection>();
		list.setModel(model);

		populate();
		scrollPane.setViewportView(list);
		
		Utilities.addEscapeListener(this);
		Dimension minSize = new Dimension(500,350);
		this.setMinimumSize(new Dimension(725, 500));
		this.setVisible(true);
	}
	
	
	/**
	 * 
	 */
	protected void updateExtra() {
		CarCtr cCtr = new CarCtr();
		try {
			cCtr.updateExtra(car);
			populate();
		} catch (ObjectNotExistException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	private void populate(){
		setText(txtRegnr, car.getRegNr());
		setText(txtVin, car.getVin());
		setText(txtYear, String.valueOf(car.getYear()));
		
		if(car.getExtra() != null){
			setText(txtType, car.getExtra().getType());
			setText(txtLastChangeReg, car.getExtra().getLatestChangeReg());
			setText(txtUse, car.getExtra().getUse());
			setText(txtLastChangeVehicle, car.getExtra().getLatestChangeVehicle());
			setText(txtStatus, car.getExtra().getStatus());
			setText(txtPosVin, car.getExtra().getPosOfChassisNumber());
			setText(txtTotal, car.getExtra().getTotalWeight());
			setText(txtTecTotal, car.getExtra().getTecTotalWeight());
			setText(txtInspectionFreq, car.getExtra().getInspectionFreq());
			setText(txtCallInspectionDate, car.getExtra().getCalInspectionDate());
			setText(txtModel, car.getModel());
			setText(txtBrand, car.getBrand());
			setText(txtMileage, String.valueOf(car.getMileage()));
			setText(txtFirstReg, car.getExtra().getFirstRegDate());
		}
		
		if(car.getInspections() != null && car.getInspections().size() > 0){
			populateList(car.getInspections());
		}
	}
	
	private void setText(JTextField field, String text){
		if(text != null && !text.trim().isEmpty()){
			field.setText(text);
		}
	}

	private void populateList(ArrayList<Inspection> inspecs){
		model.clear();
		
		for(Inspection i : inspecs){
			System.out.println(i.getDate());
			model.addElement(i);
		}
	}
}

