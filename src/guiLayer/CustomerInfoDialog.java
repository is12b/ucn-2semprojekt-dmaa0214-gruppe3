package guiLayer;

import guiLayer.extensions.CarListModel;
import guiLayer.extensions.JTextFieldLimit;
import guiLayer.extensions.Utilities;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.border.TitledBorder;

import modelLayer.Car;
import modelLayer.Customer;

import com.jgoodies.forms.factories.FormFactory;
import com.jgoodies.forms.layout.ColumnSpec;
import com.jgoodies.forms.layout.FormLayout;
import com.jgoodies.forms.layout.RowSpec;

import ctrLayer.CustomerCtr;
import ctrLayer.interfaceLayer.IFCustomerCtr;

/**
 * Class for CustomerInfoDialog
 *
 * @author Group 3, dmaa0214, UCN
 *
 */
public class CustomerInfoDialog extends JDialog {

	private static final long serialVersionUID = 1L;
	private final JPanel contentPanel = new JPanel();
	private Customer customer;
	private JTextFieldLimit txtName;
	private JTextFieldLimit txtPost;
	private JTextFieldLimit txtCity;
	private JTextFieldLimit txtPhone;
	private JTextFieldLimit txtAddress;
	private JTextFieldLimit txtCvr;
	private JList<Car> carList;
	private CarListModel carModel;

	public CustomerInfoDialog(Customer customer) {
		buildDialog(customer);
	}

	/**
	 * @param customer
	 */
	private void buildDialog(Customer customer) {
		Dimension minSize = new Dimension(500, 200);
		this.setMinimumSize(minSize);
		this.setModalityType(DEFAULT_MODALITY_TYPE);
		setTitle("Kundeinformation");
		setBounds(100, 100, 450, 300);
		getContentPane().setLayout(new BorderLayout());
		contentPanel.setBorder(new EmptyBorder(5, 5, 5, 5));
		getContentPane().add(contentPanel, BorderLayout.CENTER);
		contentPanel.setLayout(new FormLayout(new ColumnSpec[] {
				FormFactory.RELATED_GAP_COLSPEC,
				ColumnSpec.decode("default:grow"),
				FormFactory.RELATED_GAP_COLSPEC,
				ColumnSpec.decode("default:grow"),},
				new RowSpec[] {
				FormFactory.RELATED_GAP_ROWSPEC,
				RowSpec.decode("default:grow"),}));
		{
			JPanel panelCustInfo = new JPanel();
			panelCustInfo.setBorder(new TitledBorder(null, "Kundeinformation", TitledBorder.LEADING, TitledBorder.TOP, null, null));
			contentPanel.add(panelCustInfo, "2, 2, fill, fill");
			panelCustInfo.setLayout(new FormLayout(new ColumnSpec[] {
					FormFactory.RELATED_GAP_COLSPEC,
					ColumnSpec.decode("default:grow"),
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
					FormFactory.DEFAULT_ROWSPEC,}));
			{
				JLabel lblName = new JLabel("Navn:");
				panelCustInfo.add(lblName, "2, 2, left, default");
			}
			{
				txtName = new JTextFieldLimit(60, false);
				txtName.setText(customer.getName());
				panelCustInfo.add(txtName, "4, 2, fill, default");
				txtName.setColumns(10);
			}
			{
				JLabel lblPostCode = new JLabel("Postnummer:");
				panelCustInfo.add(lblPostCode, "2, 4, left, default");
			}
			{
				txtPost = new JTextFieldLimit(10, true);
				txtPost.setText("" + customer.getPostalCode());
				panelCustInfo.add(txtPost, "4, 4, fill, default");
				txtPost.setColumns(10);
			}
			{
				JLabel lblCity = new JLabel("By:");
				panelCustInfo.add(lblCity, "2, 6, left, default");
			}
			{
				txtCity = new JTextFieldLimit(50, false);
				txtCity.setText(customer.getCity());
				panelCustInfo.add(txtCity, "4, 6, fill, default");
				txtCity.setColumns(10);
			}
			{
				JLabel lblPhone = new JLabel("Tlf:");
				panelCustInfo.add(lblPhone, "2, 8, left, default");
			}
			{
				txtPhone = new JTextFieldLimit(18, false);
				txtPhone.setText(customer.getPhoneNumber());
				panelCustInfo.add(txtPhone, "4, 8, fill, default");
				txtPhone.setColumns(10);
			}
			{
				JLabel lblAddress = new JLabel("Adresse:");
				panelCustInfo.add(lblAddress, "2, 10, left, default");
			}
			{
				txtAddress = new JTextFieldLimit(60, false);
				txtAddress.setText(customer.getAddress());
				panelCustInfo.add(txtAddress, "4, 10, fill, default");
				txtAddress.setColumns(10);
			}
			{
				JLabel lblCvr = new JLabel("CVR-nr:");
				panelCustInfo.add(lblCvr, "2, 12, left, default");
			}
			{
				txtCvr = new JTextFieldLimit(20, true);
				txtCvr.setText("" + customer.getCvr());
				panelCustInfo.add(txtCvr, "4, 12, fill, default");
				txtCvr.setColumns(10);
			}
		}
		{
			JPanel panel = new JPanel();
			panel.setBorder(new TitledBorder(null, "Bilinformation", TitledBorder.LEADING, TitledBorder.TOP, null, null));
			contentPanel.add(panel, "4, 2, fill, fill");
			panel.setLayout(new FormLayout(new ColumnSpec[] {
					FormFactory.RELATED_GAP_COLSPEC,
					ColumnSpec.decode("default:grow"),},
					new RowSpec[] {
					FormFactory.RELATED_GAP_ROWSPEC,
					RowSpec.decode("default:grow"),
					FormFactory.RELATED_GAP_ROWSPEC,
					RowSpec.decode("default:grow"),}));
			{
				carList = new JList<Car>();
				carList.addMouseListener(new MouseAdapter() {
					public void mouseClicked(MouseEvent e) {
						mouseClickCar(customer, e);
					}
				});

				carModel = new CarListModel(customer.getCars());

				carList.setModel(carModel);
				panel.add(carList, "2, 2, fill, fill");
			}
			{
				JPanel panelDiv = new JPanel();
				panelDiv.setBorder(new TitledBorder(null, "Diverse", TitledBorder.LEADING, TitledBorder.TOP, null, null));
				panel.add(panelDiv, "2, 4, fill, fill");
				{
					JButton btnOpretNyBil = new JButton("Opret ny bil");
					btnOpretNyBil.addActionListener(new ActionListener() {
						public void actionPerformed(ActionEvent e) {
							new CreateCarDialog(customer);
						}
					});
					panelDiv.add(btnOpretNyBil);
				}
				{
					JButton btnSeTidligereFaktura = new JButton("Se tidligere faktura");
					panelDiv.add(btnSeTidligereFaktura);
				}
			}
		}
		{
			JPanel buttonPane = new JPanel();
			getContentPane().add(buttonPane, BorderLayout.SOUTH);
			buttonPane.setLayout(new FormLayout(new ColumnSpec[] {
					ColumnSpec.decode("left:pref:grow"),
					ColumnSpec.decode("left:pref:grow"),},
					new RowSpec[] {
					FormFactory.LINE_GAP_ROWSPEC,
					FormFactory.MIN_ROWSPEC,}));
			{
				JButton okButton = new JButton("OK");
				okButton.addActionListener(new ActionListener() {
					public void actionPerformed(ActionEvent e) {
						okay();
					}
				});
				buttonPane.add(okButton, "1, 2, center, center");
				getRootPane().setDefaultButton(okButton);
			}
			{
				JButton cancelButton = new JButton("Annuller");
				cancelButton.addActionListener(new ActionListener() {
					public void actionPerformed(ActionEvent e) {
						closeWindow();
					}
				});
				buttonPane.add(cancelButton, "2, 2, center, center");
			}

			Utilities.addEscapeListener(this);
			this.customer = customer;
			this.setVisible(true);

		}
	} //END BuildDialog

	private void okay() {
		updateCustomer();
		closeWindow();
	}

	/**
	 * 
	 */
	private void closeWindow() {
		this.setVisible(false);
		this.dispose();
	}

	/**
	 * @return 
	 * 
	 */
	private void updateCustomer() {
		IFCustomerCtr cCtr = new CustomerCtr();
		cCtr.updateCustomer(customer, txtName.getText(), txtPhone.getText(), txtAddress.getText(), txtCity.getText(), Integer.parseInt(txtPost.getText()), Integer.parseInt(txtCvr.getText()), false);
	}

	private void updateList() {
		carModel.refresh(customer.getCars());
	}

	/**
	 * @param customer
	 * @param e
	 */
	private void mouseClickCar(Customer customer, MouseEvent e) {
		if (e.getClickCount() == 2) {
			JList list = (JList)e.getSource();
			int index = list.getSelectedIndex();
			Car car = customer.getCars().get(index);
			new CarInfoDialog(car);
			updateList();
		}
	}
	
}
