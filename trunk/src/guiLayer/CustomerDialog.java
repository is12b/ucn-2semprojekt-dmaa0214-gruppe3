package guiLayer;

import guiLayer.models.CustomerTreeModel;

import java.awt.BorderLayout;
import java.awt.FlowLayout;
import java.util.ArrayList;
import java.util.List;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.tree.DefaultMutableTreeNode;
import javax.swing.BoxLayout;
import javax.swing.JScrollPane;
import javax.swing.JTree;

import modelLayer.Car;
import modelLayer.Customer;

import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

/**
 * Class for CustomerDialog
 *
 * @author Group 3, dmaa0214, UCN
 *
 */
public class CustomerDialog extends JDialog {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private final JPanel contentPanel = new JPanel();
	private CustomerTreeModel model;
	private JTree tree;
	private OrderPanel parent;
	
	/**
	 * Create the dialog.
	 * @wbp.parser.constructor
	 */
	
	public CustomerDialog(ArrayList<Customer> customers, OrderPanel parent){
		this.parent = parent;
		buildDialog(customers);
	}
	
	public CustomerDialog(List<Car> cars, OrderPanel parent){
		this.parent = parent;
		
		ArrayList<Customer> cust = new ArrayList<Customer>();
		for(Car c : cars){
			cust.add(c.getOwner());
		}
		
		buildDialog(cust);
	}
	
	
	public void buildDialog(ArrayList<Customer> customers) {
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
	protected void setSelected() {
		Object o = tree.getLastSelectedPathComponent();
		
		if(o == null){
			//TODO Error here
			return;
		}else{
			if(o instanceof Customer){
				parent.setCustomer((Customer) o);
			}else if(o instanceof Car){
				parent.setCar((Car) o);
			}else{
				//TODO Error here
				return;
			}
		}
		
		this.dispose();
	}

}
