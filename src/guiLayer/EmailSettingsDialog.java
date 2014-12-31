package guiLayer;

import exceptions.DBException;
import exceptions.ObjectNotExistException;
import guiLayer.extensions.JTextFieldLimit;
import guiLayer.extensions.Utilities;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import com.jgoodies.forms.layout.FormLayout;
import com.jgoodies.forms.layout.ColumnSpec;
import com.jgoodies.forms.layout.RowSpec;
import com.jgoodies.forms.factories.FormFactory;

import ctrLayer.SettingCtr;
import ctrLayer.interfaceLayer.IFSettingCtr;

import javax.swing.JTextField;
import javax.swing.JLabel;
import javax.swing.SwingConstants;
import javax.swing.JCheckBox;

import modelLayer.Setting;

/**
 * Class for EmailSettingsDialog
 *
 * @author Group 3, dmaa0214, UCN
 *
 */
public class EmailSettingsDialog extends JDialog {

	private static final long serialVersionUID = 1L;
	private final JPanel contentPanel = new JPanel();
	private JTextField txtHost;
	private JTextFieldLimit txtPort;
	private JTextField txtEmail;
	private JTextField txtPass;
	private JCheckBox chbSSL;
	private JTextField txtFromText;

	/**
	 * Create the dialog.
	 */
	public EmailSettingsDialog(MainGUI parent) {
		buildDialog();
		setLocationRelativeTo(parent);
		
		loadTexts();
	}

	private void loadTexts() {
		IFSettingCtr sCtr = new SettingCtr();
		try {
			txtHost.setText(sCtr.getSettingByKey("EMAIL_SMTP_HOST").getValue());
			txtPort.setText(sCtr.getSettingByKey("EMAIL_SMTP_PORT").getValue());
			boolean useSSL = true;
			if (sCtr.getSettingByKey("EMAIL_SMTP_SSL").getValue().equals("0")) {
				useSSL = false;
			}
			chbSSL.setSelected(useSSL);
			txtFromText.setText(sCtr.getSettingByKey("EMAIL_SMTP_FROM").getValue());
			txtEmail.setText(sCtr.getSettingByKey("EMAIL_SMTP_EMAIL").getValue());
			txtPass.setText(sCtr.getSettingByKey("EMAIL_SMTP_PASS").getValue());
			
			setVisible(true);
		} catch (NullPointerException e) {
			System.out.println("EmailSettingDialog exception: " + e.getMessage());
			Utilities.showError(this, "Indstillingerne kunne ikke hentes \nKontakt venligst den systemansvarlige");
			setVisible(false);
		}
	}

	private void buildDialog() {
		setTitle("E-mail indstillinger");
		setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
		setModalityType(ModalityType.APPLICATION_MODAL);
		//setBounds(100, 100, 317, 241);
		setMinimumSize(new Dimension(280, 270));
		getContentPane().setLayout(new BorderLayout());
		contentPanel.setBorder(new EmptyBorder(5, 5, 5, 5));
		getContentPane().add(contentPanel, BorderLayout.CENTER);
		contentPanel.setLayout(new FormLayout(new ColumnSpec[] {
				ColumnSpec.decode("default:grow"),},
				new RowSpec[] {
				RowSpec.decode("default:grow"),}));
		{
			JPanel panel = new JPanel();
			contentPanel.add(panel, "1, 1, fill, fill");
			panel.setLayout(new FormLayout(new ColumnSpec[] {
					ColumnSpec.decode("default:grow"),
					FormFactory.RELATED_GAP_COLSPEC,
					ColumnSpec.decode("max(50dlu;default):grow(2)"),},
				new RowSpec[] {
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
					FormFactory.DEFAULT_ROWSPEC,}));

			JLabel lblTitle = new JLabel("E-mail indstillinger");
			lblTitle.setHorizontalAlignment(SwingConstants.CENTER);
			panel.add(lblTitle, "1, 1, 3, 1");

			JLabel lblHost = new JLabel("SMTP Host:");
			panel.add(lblHost, "1, 3, right, default");

			txtHost = new JTextField();
			lblHost.setLabelFor(txtHost);
			panel.add(txtHost, "3, 3, fill, default");
			txtHost.setColumns(10);

			JLabel lblSmtpPort = new JLabel("SMTP Port:");
			panel.add(lblSmtpPort, "1, 5, right, default");

			txtPort = new JTextFieldLimit(6,true);
			lblSmtpPort.setLabelFor(txtPort);
			panel.add(txtPort, "3, 5, fill, top");
			txtPort.setColumns(10);

			JLabel lblBrugSsl = new JLabel("Brug SSL:");
			lblBrugSsl.setHorizontalAlignment(SwingConstants.RIGHT);
			panel.add(lblBrugSsl, "1, 7");

			chbSSL = new JCheckBox("");
			lblBrugSsl.setLabelFor(chbSSL);
			panel.add(chbSSL, "3, 7");
			
			JLabel lblFromText = new JLabel("Afsender navn:");
			panel.add(lblFromText, "1, 9, right, default");
			
			txtFromText = new JTextField();
			lblFromText.setLabelFor(txtFromText);
			panel.add(txtFromText, "3, 9, fill, default");
			txtFromText.setColumns(10);

			JLabel lblEmail = new JLabel("E-mail-adresse:");
			panel.add(lblEmail, "1, 11, right, default");

			txtEmail = new JTextField();
			lblEmail.setLabelFor(txtEmail);
			panel.add(txtEmail, "3, 11, fill, top");
			txtEmail.setColumns(10);

			JLabel lblPass = new JLabel("Adgangskode:");
			panel.add(lblPass, "1, 13, right, default");

			txtPass = new JTextField();
			lblPass.setLabelFor(txtPass);
			panel.add(txtPass, "3, 13, fill, default");
			txtPass.setColumns(10);
		}
		{
			JPanel buttonPane = new JPanel();
			buttonPane.setLayout(new FlowLayout(FlowLayout.RIGHT));
			getContentPane().add(buttonPane, BorderLayout.SOUTH);
			{
				JButton okButton = new JButton("Gem");
				okButton.addActionListener(new ActionListener() {
					public void actionPerformed(ActionEvent e) {
						save();
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
						dispose();
					}
				});
				cancelButton.setActionCommand("Cancel");
				buttonPane.add(cancelButton);
			}
		}
		pack();
	}

	private void save() {
		IFSettingCtr sCtr = new SettingCtr();
		
		try {
			sCtr.updateSetting(new Setting("EMAIL_SMTP_HOST", txtHost.getText()));
			sCtr.updateSetting(new Setting("EMAIL_SMTP_PORT", txtPort.getText()));
			
			String useSSL = "0";
			if (chbSSL.isSelected()) {
				useSSL = "1";
			}
			sCtr.updateSetting(new Setting("EMAIL_SMTP_SSL", useSSL));
			sCtr.updateSetting(new Setting("EMAIL_SMTP_FROM", txtFromText.getText()));
			sCtr.updateSetting(new Setting("EMAIL_SMTP_EMAIL", txtEmail.getText()));
			sCtr.updateSetting(new Setting("EMAIL_SMTP_PASS", txtPass.getText()));
			Utilities.showInformation(this, "Indstillingerne er gemt", "E-mail indstillinger er gemt");
			this.dispose();
		} catch (DBException | ObjectNotExistException e) {
			System.out.println("invoiceSettingDialog exception: " + e.getMessage());
			Utilities.showError(this, "Indstillingerne kunne ikke gemmes \nDette kan muligvis skyldes ulovlige tegn");
		}
	}

}
