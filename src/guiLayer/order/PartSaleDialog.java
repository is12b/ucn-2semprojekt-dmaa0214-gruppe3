package guiLayer.order;

import guiLayer.extensions.JDoubleField;
import guiLayer.extensions.JTextFieldLimit;

import java.awt.BorderLayout;
import java.awt.FlowLayout;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import modelLayer.Product;

import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

import com.jgoodies.forms.layout.FormLayout;
import com.jgoodies.forms.layout.ColumnSpec;
import com.jgoodies.forms.layout.RowSpec;
import com.jgoodies.forms.factories.FormFactory;

import javax.swing.JLabel;
import javax.swing.JTextField;

/**
 * Class for PartSaleDialog
 *
 * @author Group 3, dmaa0214, UCN
 *
 */
public class PartSaleDialog extends JDialog {

	private final JPanel contentPanel = new JPanel();
	private JTextField txtAmount;
	private ProductDialog parent;
	private OrderPanel order;
	private JTextField txtPrice;
	private boolean isOrderPanel = false;
	/**
	 * Create the dialog.
	 * @param parent 
	 */
	public PartSaleDialog(Product product, OrderPanel order){
		this.order = order;
		isOrderPanel = true;
		buildDialog(product);
	}
	
	public PartSaleDialog(Product product, ProductDialog parent, OrderPanel order) {
		this.parent = parent;
		this.order = order;
		buildDialog(product);
	}
	
	public void buildDialog(Product product){
		setTitle("Tilf�j Produkt");
		setBounds(100, 100, 270, 169);
		getContentPane().setLayout(new BorderLayout());
		contentPanel.setBorder(new EmptyBorder(5, 5, 5, 5));
		getContentPane().add(contentPanel, BorderLayout.CENTER);
		contentPanel.setLayout(new FormLayout(new ColumnSpec[] {
				ColumnSpec.decode("default:grow"),},
			new RowSpec[] {
				FormFactory.DEFAULT_ROWSPEC,
				RowSpec.decode("fill:max(33dlu;default)"),
				FormFactory.RELATED_GAP_ROWSPEC,
				RowSpec.decode("default:grow"),}));
		{
			JLabel lblItem = new JLabel(product.getName() + " / " + product.getItemNumber());
			contentPanel.add(lblItem, "1, 1, center, default");
		}
		{
			JPanel panel = new JPanel();
			contentPanel.add(panel, "1, 2, fill, fill");
			panel.setLayout(new FormLayout(new ColumnSpec[] {
					FormFactory.DEFAULT_COLSPEC,
					FormFactory.LABEL_COMPONENT_GAP_COLSPEC,
					ColumnSpec.decode("default:grow"),
					FormFactory.LABEL_COMPONENT_GAP_COLSPEC,
					FormFactory.DEFAULT_COLSPEC,},
				new RowSpec[] {
					FormFactory.RELATED_GAP_ROWSPEC,
					FormFactory.DEFAULT_ROWSPEC,
					FormFactory.RELATED_GAP_ROWSPEC,
					FormFactory.DEFAULT_ROWSPEC,}));
			{
				JLabel lblAntal = new JLabel("Antal:");
				panel.add(lblAntal, "1, 2, right, default");
			}
			{
				if(product.getUnitType().isDecimalAllowed()){
					txtAmount = new JDoubleField();
				}else{
					txtAmount = new JTextFieldLimit(5, true);
				}
				
				panel.add(txtAmount, "3, 2, fill, default");
				txtAmount.setColumns(10);
			}
			{
				JLabel lblUnittype = new JLabel(product.getUnitType().getShortDescription());
				panel.add(lblUnittype, "5, 2");
			}
			{
				JLabel lblPris = new JLabel("Pris:");
				panel.add(lblPris, "1, 4, right, default");
			}
			{
				txtPrice = new JDoubleField();
				txtPrice.setText(String.valueOf(product.getPrice()));
				panel.add(txtPrice, "3, 4, fill, default");
				txtPrice.setColumns(10);
			}
			{
				JLabel lblKr = new JLabel("kr.");
				panel.add(lblKr, "5, 4");
			}
		}
		{
			JPanel buttonPane = new JPanel();
			buttonPane.setLayout(new FlowLayout(FlowLayout.RIGHT));
			getContentPane().add(buttonPane, BorderLayout.SOUTH);
			{
				JButton okButton = new JButton("OK");
				okButton.addActionListener(new ActionListener() {
					public void actionPerformed(ActionEvent e) {
						if(!txtAmount.getText().isEmpty() && !txtPrice.getText().isEmpty()){
							order.addPartSale(product, Double.parseDouble(txtAmount.getText()), Double.parseDouble(txtPrice.getText()));
							
							PartSaleDialog.this.dispose();
						}
					}
				});
				okButton.setActionCommand("OK");
				buttonPane.add(okButton);
				getRootPane().setDefaultButton(okButton);
			}
			{
				JButton cancelButton = new JButton("Cancel");
				cancelButton.addActionListener(new ActionListener() {
					public void actionPerformed(ActionEvent e) {
						if(!isOrderPanel){
							parent.setCloseMe(false);
						}
						PartSaleDialog.this.dispose();
					}
				});
				cancelButton.setActionCommand("Cancel");
				buttonPane.add(cancelButton);
			}
		}
	}
	

}
