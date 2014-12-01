package guiLayer.order;

import guiLayer.order.extensions.ProductCellRender;

import java.awt.BorderLayout;
import java.awt.FlowLayout;
import java.util.ArrayList;

import javax.swing.DefaultListModel;
import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JPanel;
import javax.swing.SwingUtilities;
import javax.swing.border.EmptyBorder;
import javax.swing.JScrollPane;
import javax.swing.JList;

import modelLayer.Customer;
import modelLayer.Product;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

/**
 * Class for ProductDialog
 *
 * @author Group 3, dmaa0214, UCN
 *
 */
public class ProductDialog extends JDialog {

	private final JPanel contentPanel = new JPanel();
	private JList<Product> list;
	private boolean closeMe = true;
	private OrderPanel parent;
	/**
	 * Create the dialog.
	 */
	public ProductDialog(ArrayList<Product> products, OrderPanel parent) {
		this.parent = parent;
		setTitle("S\u00F8g Produkt");
		setBounds(100, 100, 296, 456);
		getContentPane().setLayout(new BorderLayout());
		contentPanel.setBorder(new EmptyBorder(5, 5, 5, 5));
		getContentPane().add(contentPanel, BorderLayout.CENTER);
		contentPanel.setLayout(new BorderLayout(0, 0));
		{
			JScrollPane scrollPane = new JScrollPane();
			contentPanel.add(scrollPane, BorderLayout.CENTER);
			{
				list = new JList<Product>();
				ProductCellRender productRender = new ProductCellRender();
				list.setCellRenderer(productRender);
				scrollPane.setViewportView(list);
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
						createPartSaleDialog(list.getSelectedValue());
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
						ProductDialog.this.dispose();
					}
				});
				cancelButton.setActionCommand("Cancel");
				buttonPane.add(cancelButton);
			}
		}
		
		redraw(products);
	}
	
	
	/**
	 * @param selectedValue
	 */
	protected void createPartSaleDialog(Product product) {
		if(product != null){
			PartSaleDialog pDialog = new PartSaleDialog(product, this, parent);
			pDialog.setModalityType(ModalityType.APPLICATION_MODAL);
			pDialog.setVisible(true);
			
			if(closeMe){
				this.dispose();
			}
		}else{
			//TODO du skal v�lge noget...
		}
	}


	protected void redraw(final ArrayList<Product> products) {
		SwingUtilities.invokeLater(new Runnable() {
			@Override
			public void run() {
				DefaultListModel<Product> model = new DefaultListModel<Product>();
				if (products.size() > 0) {
					for (Product p : products) {
						model.addElement(p);
					}
				}
				list.setModel(model);
			}
		});
	}
	
	public void setCloseMe(boolean close){
		closeMe = close;
	}

}
