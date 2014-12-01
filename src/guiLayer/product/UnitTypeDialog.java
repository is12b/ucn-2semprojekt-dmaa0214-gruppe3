package guiLayer.product;

import guiLayer.extensions.Utilities;

import javax.swing.JDialog;
import javax.swing.JPanel;

import java.awt.CardLayout;
import java.awt.Component;
import java.awt.Dimension;
import java.awt.GridBagLayout;
import java.awt.GridBagConstraints;

import com.jgoodies.forms.layout.FormLayout;
import com.jgoodies.forms.layout.ColumnSpec;
import com.jgoodies.forms.layout.RowSpec;
import com.jgoodies.forms.factories.FormFactory;

import ctrLayer.UnitTypeCtr;
import ctrLayer.exceptionLayer.ObjectNotExistException;
import dbLayer.exceptions.DBException;

import javax.swing.border.TitledBorder;
import javax.swing.border.EmptyBorder;
import javax.swing.UIManager;

import java.awt.Color;
import java.util.ArrayList;

import javax.swing.DefaultListModel;
import javax.swing.JButton;
import javax.swing.JOptionPane;
import javax.swing.JTextField;
import javax.swing.JCheckBox;
import javax.swing.JLabel;
import javax.swing.JScrollPane;
import javax.swing.JList;
import javax.swing.ListSelectionModel;

import modelLayer.UnitType;

import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;
import javax.swing.event.ListSelectionListener;
import javax.swing.event.ListSelectionEvent;

import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

/**
 * Class for UnitTypeDialog
 *
 * @author Group 3, dmaa0214, UCN
 *
 */
public class UnitTypeDialog extends JDialog {

	private static final long serialVersionUID = 1L;
	private JTextField txtShortDesc;
	private JTextField txtDesc;
	private JList<UnitType> list;
	private DefaultListModel<UnitType> model;
	private JButton btnEditSelected;
	private JButton btnDelete;
	private TitledBorder createBorder;
	private UnitType unitType;
	private JCheckBox cheBoxDecAllowed;
	private JButton btnCreate;
	private JButton btnClear;
	private JButton btnCancel;
	private JButton btnEdit;
	private JPanel cardPanel;
	private boolean anyChanges = false;
	
	public UnitTypeDialog(Component parent) {
		
		buildDialog();

		setLocationRelativeTo(parent);
		setVisible(true);
	}

	private void buildDialog() {
		setTitle("Enhedstyper");
		setModalityType(DEFAULT_MODALITY_TYPE);
		setMinimumSize(new Dimension(500, 200));
		setSize(new Dimension(550, 250));
		
		GridBagLayout gridBagLayout = new GridBagLayout();
		gridBagLayout.columnWidths = new int[]{500, 0};
		gridBagLayout.rowHeights = new int[]{260, 0};
		gridBagLayout.columnWeights = new double[]{1.0, Double.MIN_VALUE};
		gridBagLayout.rowWeights = new double[]{1.0, Double.MIN_VALUE};
		getContentPane().setLayout(gridBagLayout);
		
		JPanel panel = new JPanel();
		panel.setBorder(new EmptyBorder(2, 2, 2, 2));
		GridBagConstraints gbc_panel = new GridBagConstraints();
		gbc_panel.fill = GridBagConstraints.BOTH;
		gbc_panel.gridx = 0;
		gbc_panel.gridy = 0;
		getContentPane().add(panel, gbc_panel);
		panel.setLayout(new FormLayout(new ColumnSpec[] {
				ColumnSpec.decode("220px"),
				FormFactory.RELATED_GAP_COLSPEC,
				ColumnSpec.decode("default:grow"),},
			new RowSpec[] {
				RowSpec.decode("default:grow"),}));
		
		JPanel createPanel = new JPanel();
		createBorder = new TitledBorder(UIManager.getBorder("TitledBorder.border"), "Opret ny", TitledBorder.LEADING, TitledBorder.TOP, null, new Color(0, 0, 0));
		createPanel.setBorder(createBorder);
		panel.add(createPanel, "1, 1, fill, fill");
		createPanel.setLayout(new FormLayout(new ColumnSpec[] {
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
		
		JLabel lblShortDesc = new JLabel("Forkortelse:");
		createPanel.add(lblShortDesc, "2, 2, right, default");
				
		txtShortDesc = new JTextField();
				
		lblShortDesc.setLabelFor(txtShortDesc);
		createPanel.add(txtShortDesc, "4, 2, fill, default");
		txtShortDesc.setColumns(10);
		
		txtShortDesc.getDocument().addDocumentListener(onChangeListner());
		
		JLabel lblDesc = new JLabel("Beskrivelse:");
		createPanel.add(lblDesc, "2, 4, right, default");
		
		txtDesc = new JTextField();
		lblDesc.setLabelFor(txtDesc);
		createPanel.add(txtDesc, "4, 4, fill, default");
		txtDesc.setColumns(10);
		
		txtDesc.getDocument().addDocumentListener(onChangeListner());
		
		JLabel lblDecimalAllowed = new JLabel("Decimaler tilladt:");
		createPanel.add(lblDecimalAllowed, "2, 6");
		
		cheBoxDecAllowed = new JCheckBox("");
		cheBoxDecAllowed.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				checkForText();
			}
		});
		lblDecimalAllowed.setLabelFor(cheBoxDecAllowed);
		createPanel.add(cheBoxDecAllowed, "4, 6");
		
		
		cardPanel = new JPanel();
		
		createPanel.add(cardPanel, "2, 8, 3, 1, fill, fill");
		
		JPanel createCard = new JPanel();
		createCard.setLayout(new FormLayout(new ColumnSpec[] {
				FormFactory.DEFAULT_COLSPEC,
				ColumnSpec.decode("pref:grow"),
				FormFactory.DEFAULT_COLSPEC,},
			new RowSpec[] {
				FormFactory.RELATED_GAP_ROWSPEC,
				FormFactory.DEFAULT_ROWSPEC,}));
		
		btnClear = new JButton("Ryd");
		btnClear.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				clearForm();
			}
		});
		btnClear.setEnabled(false);
								
		createCard.add(btnClear, "1, 2");
		
		btnCreate = new JButton("Opret");
		btnCreate.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				create();
			}
		});
		btnCreate.setEnabled(false);
				
		createCard.add(btnCreate, "3, 2");
		
		JPanel editCard = new JPanel();
		editCard.setLayout(new FormLayout(new ColumnSpec[] {
				FormFactory.DEFAULT_COLSPEC,
				ColumnSpec.decode("pref:grow"),
				FormFactory.DEFAULT_COLSPEC,},
			new RowSpec[] {
				FormFactory.RELATED_GAP_ROWSPEC,
				FormFactory.DEFAULT_ROWSPEC,}));
		
		btnCancel = new JButton("Annuller");
		btnCancel.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				cancelEditing();
			}
		});
		
		editCard.add(btnCancel, "1, 2");
		
		btnEdit = new JButton("Gem");
		btnEdit.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				edit();
			}
		});
		
		btnEdit.setEnabled(false);
		
		editCard.add(btnEdit, "3, 2");
		cardPanel.setLayout(new CardLayout(0, 0));
		
		cardPanel.add(createCard, "creating");
		cardPanel.add(editCard, "editing");
		
		
		JPanel mainPanel = new JPanel();
		mainPanel.setBorder(new TitledBorder(UIManager.getBorder("TitledBorder.border"), "Eksisterende enhedstyper", TitledBorder.LEADING, TitledBorder.TOP, null, new Color(0, 0, 0)));
		panel.add(mainPanel, "3, 1, fill, fill");
		mainPanel.setLayout(new FormLayout(new ColumnSpec[] {
				FormFactory.RELATED_GAP_COLSPEC,
				ColumnSpec.decode("default:grow"),
				FormFactory.RELATED_GAP_COLSPEC,},
			new RowSpec[] {
				FormFactory.RELATED_GAP_ROWSPEC,
				RowSpec.decode("default:grow"),
				FormFactory.RELATED_GAP_ROWSPEC,
				FormFactory.DEFAULT_ROWSPEC,}));
		
		JScrollPane scrollPane = new JScrollPane();
		mainPanel.add(scrollPane, "2, 2, fill, fill");
		
		model = new DefaultListModel<UnitType>();
		list = new JList<UnitType>(model);
		list.addListSelectionListener(new ListSelectionListener() {
			public void valueChanged(ListSelectionEvent e) {
				setListButtonsEnable(!list.isSelectionEmpty());
			}
		});
		list.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		scrollPane.setViewportView(list);
		
		JPanel panel_2 = new JPanel();
		mainPanel.add(panel_2, "2, 4, fill, fill");
		panel_2.setLayout(new FormLayout(new ColumnSpec[] {
				FormFactory.DEFAULT_COLSPEC,
				ColumnSpec.decode("default:grow"),
				FormFactory.DEFAULT_COLSPEC,},
			new RowSpec[] {
				FormFactory.RELATED_GAP_ROWSPEC,
				FormFactory.DEFAULT_ROWSPEC,}));
		
		btnEditSelected = new JButton("\u00C6ndre");
		btnEditSelected.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				editSelected();
			}
		});
		btnEditSelected.setEnabled(false);
		panel_2.add(btnEditSelected, "1, 2");
		
		btnDelete = new JButton("Slet");
		btnDelete.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				delete();
			}
		});
		btnDelete.setEnabled(false);
		panel_2.add(btnDelete, "3, 2");

		updateModel();
	}

	private void delete() {
		UnitType ut = list.getSelectedValue();
		if (ut != null) {
			int choice = Utilities.showWarning(this, "Er du sikker på du vil slette enhedstypen: " + ut.getShortDescription() + "?");
			if (choice == JOptionPane.YES_OPTION) {
				try {
					new UnitTypeCtr().deleteUnitType(ut);
					unitType = null;
					updateModel();
					reDraw();
					anyChanges = true;
				} catch (DBException e) {
					Utilities.showError(this, e.getMessage(), "Fejl");
					//e.printStackTrace();
				} catch (ObjectNotExistException e) {
					Utilities.showError(this, e.getMessage(), "Fejl");
					unitType = null;
					updateModel();
					reDraw();
					anyChanges = true;
				}
			}
		}
	}

	private void create() {
		if (isSomeFieldsEmpty()) {
			Utilities.showError(this, "Forkortelse eller beskrivelse må ikke være tom");
		} else {
			try {
				new UnitTypeCtr().createUnitType(txtDesc.getText().trim(), txtShortDesc.getText().trim(), cheBoxDecAllowed.isSelected());
				clearForm();
				updateModel();
				anyChanges = true;
			} catch (DBException e) {
				Utilities.showError(this, e.getMessage(), "Fejl i oprettelse");
				//e.printStackTrace();
			}
		}
	}

	protected void edit() {
		if (isSomethingChanged()) {
			//System.out.println("før: " + unitType);
			try {
				new UnitTypeCtr().updateUnitType(unitType, txtDesc.getText().trim(), txtShortDesc.getText().trim(), cheBoxDecAllowed.isSelected());
				unitType = null;
				updateModel();
				reDraw();
			} catch (DBException e) {
				Utilities.showError(this, e.getMessage());
			} catch (ObjectNotExistException e) {
				Utilities.showError(this, e.getMessage());
				unitType = null;
				updateModel();
				reDraw();
			}
			//System.out.println("efter: " + unitType);
			anyChanges = true;
		}
	}
	
	private void checkForText() {
		if (!isEditing()) {
			setCreateButtonsEnable();
		} else {
			btnEdit.setEnabled(isSomethingChanged());
		}
	}

	private DocumentListener onChangeListner() {
		DocumentListener docListner = new DocumentListener() {
		    @Override
			public void insertUpdate(DocumentEvent e) {
		    	checkForText();
			}
			@Override
			public void removeUpdate(DocumentEvent e) {
				checkForText();
			}
			@Override
			public void changedUpdate(DocumentEvent e) {
				checkForText();
			}
		};
		return docListner;
	}

	private void cancelEditing() {
		boolean stopEditing = true;
		if (isSomethingChanged()) {
			int choice = Utilities.showWarning(this, "Er du sikker på du vil annullere ændringerne?");
			if (choice != JOptionPane.YES_OPTION) {
				stopEditing = false;
			}
		} 
		if (stopEditing) {
			list.clearSelection();
			unitType = null;
			reDraw();
		}
	}
	
	private boolean isSomeFieldsEmpty() {
		return (txtShortDesc.getText().trim().isEmpty()
				|| txtDesc.getText().trim().isEmpty());
	}
		
	private boolean isAllFieldsEmpty() {
		return (txtShortDesc.getText().trim().isEmpty()
				&& txtDesc.getText().trim().isEmpty());
	}
	
	private boolean isSomethingChanged() {
		return (isEditing() && !txtShortDesc.getText().equals(unitType.getShortDescription())
				|| !txtDesc.getText().equals(unitType.getDescription())
				|| !cheBoxDecAllowed.isSelected() == unitType.isDecimalAllowed());
	}
	
	private boolean isEditing() {
		return (unitType != null);
	}
	
	private void clearForm() {
		txtShortDesc.setText("");
		txtDesc.setText("");
		cheBoxDecAllowed.setSelected(false);
		setCreateButtonsEnable();
		txtShortDesc.requestFocus();
	}
	

	private  void editSelected() {
		unitType = list.getSelectedValue();
		if (unitType != null) {
			reDraw();
			txtShortDesc.setText(unitType.getShortDescription());
			txtDesc.setText(unitType.getDescription());
			cheBoxDecAllowed.setSelected(unitType.isDecimalAllowed());
			btnEdit.setEnabled(false);
		}
	}
	
	private void reDraw() {
		CardLayout cl = (CardLayout)(cardPanel.getLayout());
		if (isEditing()) {
			createBorder.setTitle("Ændre " + unitType.getDescription());
			cl.show(cardPanel, "editing");
		} else {
			createBorder.setTitle("Opret ny");
			cl.show(cardPanel, "creating");
		}
		clearForm();
		repaint();
	}
	
	private void setCreateButtonsEnable() {
		btnClear.setEnabled(!isAllFieldsEmpty());
		btnCreate.setEnabled(!isSomeFieldsEmpty());
	}

	private void setListButtonsEnable(boolean enable) {
		btnEditSelected.setEnabled(enable);
		btnDelete.setEnabled(enable);
	}

	private void updateModel() {
		ArrayList<UnitType> utList = new UnitTypeCtr().getUnitTypes();
		model.removeAllElements();
		for (UnitType ut : utList) {
			model.addElement(ut);
		}
	}

	/**
	 * Get Method for getting a boolean indicate, if the user
	 * has created or edited something with unitTypes.
	 * @return true if something is changed
	 */
	public boolean isAnyThingChanged() {
		return anyChanges;
	}

}
