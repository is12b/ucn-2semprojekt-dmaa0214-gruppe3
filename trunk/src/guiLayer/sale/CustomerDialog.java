package guiLayer.sale;

import guiLayer.sale.models.CustomerTreeModel;

import java.awt.BorderLayout;
import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTree;
import javax.swing.border.EmptyBorder;
import javax.swing.tree.TreeSelectionModel;

import modelLayer.Car;
import modelLayer.Customer;

/**
 * Class for CustomerDialog
 *
 * @author Group 3, dmaa0214, UCN
 *
 */
class CustomerDialog extends JDialog {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private final JPanel contentPanel = new JPanel();
	private CustomerTreeModel model;
	private JTree tree;
	private SalePanel parent;
	
	/**
	 * Create the dialog.
	 * @wbp.parser.constructor
	 */
	
	CustomerDialog(ArrayList<Customer> customers, SalePanel parent){
		this.parent = parent;
		buildDialog(customers);
	}	
	
	private void buildDialog(ArrayList<Customer> customers) {
		setTitle("Tilføj Kunde / Bil");
		setBounds(100, 100, 450, 300);
		getContentPane().setLayout(new BorderLayout());
		contentPanel.setBorder(new EmptyBorder(5, 5, 5, 5));
		getContentPane().add(contentPanel, BorderLayout.CENTER);
		contentPanel.setLayout(new BorderLayout(0, 0));
		{
			JScrollPane scrollPane = new JScrollPane();
			contentPanel.add(scrollPane, BorderLayout.CENTER);
			{
				tree = new JTree();
				tree.getSelectionModel().setSelectionMode(TreeSelectionModel.SINGLE_TREE_SELECTION);
				tree.setLargeModel(true);
				scrollPane.setViewportView(tree);
			}
		}
		{
			JPanel buttonPane = new JPanel();
			buttonPane.setLayout(new FlowLayout(FlowLayout.RIGHT));
			getContentPane().add(buttonPane, BorderLayout.SOUTH);
			{
				JButton okButton = new JButton("OK");
				okButton.addActionListener(new ActionListener() {
					public void actionPerformed(ActionEvent arg0) {
						setSelected();
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
						CustomerDialog.this.dispose();
					}
				});
				cancelButton.setActionCommand("Cancel");
				buttonPane.add(cancelButton);
			}
		}
		
		model = new CustomerTreeModel(customers);
		tree.setModel(model);
	}

	/**
	 * 
	 */
	private void setSelected() {
		Object o = tree.getLastSelectedPathComponent();
		
		if(o == null){
			return;
		}else{
			if(o instanceof Customer){
				parent.setCustomer((Customer) o);
				parent.setCar(null);
			}else if(o instanceof Car){
				parent.setCar((Car) o);
			}else{
				return;
			}
		}
		
		this.dispose();
	}

}
