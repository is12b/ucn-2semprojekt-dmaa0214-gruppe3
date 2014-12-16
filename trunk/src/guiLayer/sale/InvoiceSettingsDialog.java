package guiLayer.sale;

import exceptions.DBException;
import exceptions.ObjectNotExistException;
import guiLayer.extensions.JTextFieldLimit;
import guiLayer.extensions.Utilities;

import java.awt.BorderLayout;
import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.SwingConstants;
import javax.swing.border.EmptyBorder;

import modelLayer.Setting;

import com.jgoodies.forms.factories.FormFactory;
import com.jgoodies.forms.layout.ColumnSpec;
import com.jgoodies.forms.layout.FormLayout;
import com.jgoodies.forms.layout.RowSpec;

import ctrLayer.SettingCtr;
import ctrLayer.interfaceLayer.IFSettingCtr;

/**
 * Class for InvoiceSettingsDialog
 *
 * @author Group 3, dmaa0214, UCN
 *
 */
public class InvoiceSettingsDialog extends JDialog {
	private static final long serialVersionUID = 1L;
	private final JPanel contentPanel = new JPanel();
	private JTextField txtAddress;
	private JTextField txtPost;
	private JTextField txtCity;
	private JTextField txtWebsite;
	private JTextField txtName;
	private JTextField txtEmail;
	private JTextField txtPhone;
	private JTextField txtFax;
	private JTextField txtCVR;
	private JTextField txtReg;
	private JTextField txtAcc;
	
	/**
	 * Create the dialog.
	 */
	public InvoiceSettingsDialog() {
		buildDialog();
		
		IFSettingCtr sCtr = new SettingCtr();
		txtAddress.setText(sCtr.getSettingByKey("INVOICE_ADDRESS").getValue());
		txtPost.setText(sCtr.getSettingByKey("INVOICE_POST").getValue());
		txtCity.setText(sCtr.getSettingByKey("INVOICE_CITY").getValue());
		txtWebsite.setText(sCtr.getSettingByKey("INVOICE_WEBSITE").getValue());
		txtName.setText(sCtr.getSettingByKey("INVOICE_NAME").getValue());
		txtEmail.setText(sCtr.getSettingByKey("INVOICE_EMAIL").getValue());
		txtPhone.setText(sCtr.getSettingByKey("INVOICE_PHONE").getValue());
		txtFax.setText(sCtr.getSettingByKey("INVOICE_FAX").getValue());
		txtCVR.setText(sCtr.getSettingByKey("INVOICE_CVR").getValue());
		txtReg.setText(sCtr.getSettingByKey("INVOICE_REG").getValue());
		txtAcc.setText(sCtr.getSettingByKey("INVOICE_ACC").getValue());
	}
	
	private void buildDialog(){
		setTitle("Faktura Indstillinger");
		setBounds(100, 100, 450, 300);
		getContentPane().setLayout(new BorderLayout());
		contentPanel.setBorder(new EmptyBorder(5, 5, 5, 5));
		getContentPane().add(contentPanel, BorderLayout.CENTER);
		contentPanel.setLayout(new FormLayout(new ColumnSpec[] {
				FormFactory.DEFAULT_COLSPEC,
				FormFactory.LABEL_COMPONENT_GAP_COLSPEC,
				ColumnSpec.decode("default:grow"),},
			new RowSpec[] {
				RowSpec.decode("default:grow"),}));
		{
			JPanel panel = new JPanel();
			contentPanel.add(panel, "1, 1, fill, fill");
			panel.setLayout(new FormLayout(new ColumnSpec[] {
					ColumnSpec.decode("left:default:grow"),
					FormFactory.LABEL_COMPONENT_GAP_COLSPEC,
					ColumnSpec.decode("default:grow"),},
				new RowSpec[] {
					FormFactory.DEFAULT_ROWSPEC,
					FormFactory.NARROW_LINE_GAP_ROWSPEC,
					FormFactory.PREF_ROWSPEC,
					FormFactory.NARROW_LINE_GAP_ROWSPEC,
					FormFactory.DEFAULT_ROWSPEC,
					FormFactory.NARROW_LINE_GAP_ROWSPEC,
					FormFactory.DEFAULT_ROWSPEC,
					FormFactory.NARROW_LINE_GAP_ROWSPEC,
					FormFactory.DEFAULT_ROWSPEC,
					FormFactory.NARROW_LINE_GAP_ROWSPEC,
					FormFactory.DEFAULT_ROWSPEC,
					FormFactory.NARROW_LINE_GAP_ROWSPEC,
					FormFactory.DEFAULT_ROWSPEC,
					FormFactory.NARROW_LINE_GAP_ROWSPEC,
					FormFactory.DEFAULT_ROWSPEC,
					FormFactory.NARROW_LINE_GAP_ROWSPEC,
					FormFactory.DEFAULT_ROWSPEC,
					FormFactory.NARROW_LINE_GAP_ROWSPEC,
					FormFactory.DEFAULT_ROWSPEC,}));
			{
				JLabel lblNewLabel_1 = new JLabel("Firma:");
				panel.add(lblNewLabel_1, "1, 1, 3, 1, center, default");
			}
			{
				JPanel panel_1 = new JPanel();
				panel.add(panel_1, "1, 3, 3, 1, fill, fill");
				panel_1.setLayout(new FormLayout(new ColumnSpec[] {
						FormFactory.DEFAULT_COLSPEC,
						FormFactory.RELATED_GAP_COLSPEC,
						ColumnSpec.decode("default:grow"),},
					new RowSpec[] {
						FormFactory.DEFAULT_ROWSPEC,}));
				{
					JLabel lblCvr = new JLabel("CVR:");
					panel_1.add(lblCvr, "1, 1, right, default");
				}
				{
					txtCVR = new JTextFieldLimit(10, true);
					panel_1.add(txtCVR, "3, 1, fill, default");
					txtCVR.setColumns(10);
				}
			}
			{
				JPanel panel_1 = new JPanel();
				panel.add(panel_1, "1, 5, 3, 1, fill, fill");
				panel_1.setLayout(new FormLayout(new ColumnSpec[] {
						FormFactory.DEFAULT_COLSPEC,
						FormFactory.RELATED_GAP_COLSPEC,
						ColumnSpec.decode("default:grow"),},
					new RowSpec[] {
						FormFactory.DEFAULT_ROWSPEC,}));
				{
					JLabel lblNewLabel = new JLabel("Navn:");
					panel_1.add(lblNewLabel, "1, 1");
				}
				{
					txtName = new JTextField();
					panel_1.add(txtName, "3, 1, fill, default");
					txtName.setColumns(10);
				}
			}
			{
				JPanel panel_1 = new JPanel();
				panel.add(panel_1, "1, 7, 3, 1, fill, fill");
				panel_1.setLayout(new FormLayout(new ColumnSpec[] {
						FormFactory.DEFAULT_COLSPEC,
						FormFactory.RELATED_GAP_COLSPEC,
						ColumnSpec.decode("default:grow"),},
					new RowSpec[] {
						FormFactory.DEFAULT_ROWSPEC,}));
				{
					JLabel lblNewLabel_2 = new JLabel("Vej:");
					panel_1.add(lblNewLabel_2, "1, 1");
					lblNewLabel_2.setHorizontalAlignment(SwingConstants.LEFT);
				}
				{
					txtAddress = new JTextField();
					panel_1.add(txtAddress, "3, 1");
					txtAddress.setColumns(10);
				}
			}
			{
				JPanel panel_1 = new JPanel();
				panel.add(panel_1, "1, 9, 3, 1, fill, fill");
				panel_1.setLayout(new FormLayout(new ColumnSpec[] {
						FormFactory.DEFAULT_COLSPEC,
						FormFactory.DEFAULT_COLSPEC,
						FormFactory.LABEL_COMPONENT_GAP_COLSPEC,
						FormFactory.DEFAULT_COLSPEC,
						FormFactory.DEFAULT_COLSPEC,},
					new RowSpec[] {
						FormFactory.DEFAULT_ROWSPEC,}));
				{
					JLabel lblNewLabel_4 = new JLabel("By:");
					panel_1.add(lblNewLabel_4, "1, 1, left, default");
				}
				{
					txtCity = new JTextField();
					panel_1.add(txtCity, "2, 1, fill, default");
					txtCity.setColumns(10);
				}
				{
					JLabel lblNewLabel_3 = new JLabel("Post:");
					panel_1.add(lblNewLabel_3, "4, 1, left, default");
				}
				{
					txtPost = new JTextFieldLimit(4, true);
					panel_1.add(txtPost, "5, 1, fill, default");
					txtPost.setColumns(10);
				}
			}
			{
				JPanel panel_1 = new JPanel();
				panel.add(panel_1, "1, 11, 3, 1, fill, fill");
				panel_1.setLayout(new FormLayout(new ColumnSpec[] {
						FormFactory.DEFAULT_COLSPEC,
						FormFactory.RELATED_GAP_COLSPEC,
						ColumnSpec.decode("default:grow"),},
					new RowSpec[] {
						FormFactory.DEFAULT_ROWSPEC,}));
				{
					JLabel lblTelefon = new JLabel("Telefon:");
					panel_1.add(lblTelefon, "1, 1, right, default");
				}
				{
					txtPhone = new JTextField();
					panel_1.add(txtPhone, "3, 1, fill, default");
					txtPhone.setColumns(10);
				}
			}
			{
				JPanel panel_1 = new JPanel();
				panel.add(panel_1, "1, 13, 3, 1, fill, fill");
				panel_1.setLayout(new FormLayout(new ColumnSpec[] {
						FormFactory.DEFAULT_COLSPEC,
						FormFactory.RELATED_GAP_COLSPEC,
						ColumnSpec.decode("default:grow"),},
					new RowSpec[] {
						FormFactory.DEFAULT_ROWSPEC,}));
				{
					JLabel lblFax = new JLabel("Fax.");
					panel_1.add(lblFax, "1, 1, right, default");
				}
				{
					txtFax = new JTextField();
					panel_1.add(txtFax, "3, 1, fill, default");
					txtFax.setColumns(10);
				}
			}
			{
				JLabel lblNewLabel_6 = new JLabel("Web:");
				panel.add(lblNewLabel_6, "1, 15, 3, 1, center, default");
			}
			{
				JPanel panel_1 = new JPanel();
				panel.add(panel_1, "1, 17, 3, 1, fill, fill");
				panel_1.setLayout(new FormLayout(new ColumnSpec[] {
						FormFactory.DEFAULT_COLSPEC,
						FormFactory.RELATED_GAP_COLSPEC,
						ColumnSpec.decode("default:grow"),},
					new RowSpec[] {
						FormFactory.DEFAULT_ROWSPEC,}));
				{
					JLabel lblNewLabel_5 = new JLabel("Hjemmeside:");
					panel_1.add(lblNewLabel_5, "1, 1");
				}
				{
					txtWebsite = new JTextField();
					panel_1.add(txtWebsite, "3, 1");
					txtWebsite.setColumns(10);
				}
			}
			{
				JPanel panel_1 = new JPanel();
				panel.add(panel_1, "1, 19, 3, 1, fill, fill");
				panel_1.setLayout(new FormLayout(new ColumnSpec[] {
						FormFactory.DEFAULT_COLSPEC,
						FormFactory.RELATED_GAP_COLSPEC,
						ColumnSpec.decode("default:grow"),},
					new RowSpec[] {
						FormFactory.DEFAULT_ROWSPEC,}));
				{
					JLabel lblNewLabel_7 = new JLabel("E-mail:");
					panel_1.add(lblNewLabel_7, "1, 1, left, default");
				}
				{
					txtEmail = new JTextField();
					panel_1.add(txtEmail, "3, 1, fill, default");
					txtEmail.setColumns(10);
				}
			}
		}
		{
			JPanel panel = new JPanel();
			contentPanel.add(panel, "3, 1, fill, fill");
			panel.setLayout(new FormLayout(new ColumnSpec[] {
					ColumnSpec.decode("default:grow"),
					FormFactory.RELATED_GAP_COLSPEC,
					ColumnSpec.decode("default:grow"),},
				new RowSpec[] {
					FormFactory.DEFAULT_ROWSPEC,
					FormFactory.NARROW_LINE_GAP_ROWSPEC,
					FormFactory.DEFAULT_ROWSPEC,
					FormFactory.NARROW_LINE_GAP_ROWSPEC,
					FormFactory.DEFAULT_ROWSPEC,
					FormFactory.RELATED_GAP_ROWSPEC,
					RowSpec.decode("default:grow"),}));
			{
				JLabel lblBankOplysninger = new JLabel("Bank oplysninger:");
				panel.add(lblBankOplysninger, "1, 1, 3, 1, center, default");
			}
			{
				JPanel panel_1 = new JPanel();
				panel.add(panel_1, "1, 3, 3, 1, fill, fill");
				panel_1.setLayout(new FormLayout(new ColumnSpec[] {
						FormFactory.DEFAULT_COLSPEC,
						FormFactory.RELATED_GAP_COLSPEC,
						ColumnSpec.decode("default:grow"),},
					new RowSpec[] {
						FormFactory.DEFAULT_ROWSPEC,}));
				{
					JLabel lblNewLabel_8 = new JLabel("Regnr:");
					panel_1.add(lblNewLabel_8, "1, 1, right, default");
				}
				{
					txtReg = new JTextFieldLimit(100, true);
					panel_1.add(txtReg, "3, 1, fill, default");
					txtReg.setColumns(10);
				}
			}
			{
				JPanel panel_1 = new JPanel();
				panel.add(panel_1, "1, 5, 3, 1, fill, fill");
				panel_1.setLayout(new FormLayout(new ColumnSpec[] {
						FormFactory.DEFAULT_COLSPEC,
						FormFactory.RELATED_GAP_COLSPEC,
						ColumnSpec.decode("default:grow"),},
					new RowSpec[] {
						FormFactory.DEFAULT_ROWSPEC,}));
				{
					JLabel lblNewLabel_9 = new JLabel("Kontonr:");
					panel_1.add(lblNewLabel_9, "1, 1, right, default");
				}
				{
					txtAcc = new JTextFieldLimit(100, true);
					panel_1.add(txtAcc, "3, 1, fill, default");
					txtAcc.setColumns(10);
				}
			}
		}
		{
			JPanel buttonPane = new JPanel();
			buttonPane.setLayout(new FlowLayout(FlowLayout.RIGHT));
			getContentPane().add(buttonPane, BorderLayout.SOUTH);
			{
				JButton okButton = new JButton("Gem");
				okButton.addActionListener(new ActionListener() {
					public void actionPerformed(ActionEvent e) {
						saveSettings();
					}
				});
				okButton.setActionCommand("OK");
				buttonPane.add(okButton);
				getRootPane().setDefaultButton(okButton);
			}
			{
				JButton cancelButton = new JButton("Annuller");
				cancelButton.addActionListener(new ActionListener() {
					public void actionPerformed(ActionEvent e) {
						InvoiceSettingsDialog.this.dispose();
					}
				});
				cancelButton.setActionCommand("Cancel");
				buttonPane.add(cancelButton);
			}
		}
	}

	/**
	 * 
	 */
	private void saveSettings() {
		IFSettingCtr sCtr = new SettingCtr();
		
		try {
			sCtr.updateSetting(new Setting("INVOICE_ADDRESS", txtAddress.getText()));
			sCtr.updateSetting(new Setting("INVOICE_POST", txtPost.getText()));
			sCtr.updateSetting(new Setting("INVOICE_CITY", txtCity.getText()));
			sCtr.updateSetting(new Setting("INVOICE_WEBSITE", txtWebsite.getText()));
			sCtr.updateSetting(new Setting("INVOICE_NAME", txtName.getText()));
			sCtr.updateSetting(new Setting("INVOICE_EMAIL", txtEmail.getText()));
			sCtr.updateSetting(new Setting("INVOICE_PHONE", txtPhone.getText()));
			sCtr.updateSetting(new Setting("INVOICE_FAX", txtFax.getText()));
			sCtr.updateSetting(new Setting("INVOICE_CVR", txtCVR.getText()));
			sCtr.updateSetting(new Setting("INVOICE_REG", txtReg.getText()));
			sCtr.updateSetting(new Setting("INVOICE_ACC", txtAcc.getText()));
		} catch (DBException | ObjectNotExistException e) {
			System.out.println("invoiceSettingDialog exception: " + e.getMessage());
			Utilities.showError(this, "Indtillingerne kunne ikke gemmes \nDette kan muligvis skyldes ulovlige tegn");
		}
		
		this.dispose();
	}
	
}
