package guiLayer.models;

import java.util.ArrayList;

import javax.swing.event.TreeModelListener;
import javax.swing.tree.TreeModel;
import javax.swing.tree.TreePath;

import modelLayer.Car;
import modelLayer.Customer;

/**
 * Class for CustomerTreeModel
 *
 * @author Group 3, dmaa0214, UCN
 *
 */
public class CustomerTreeModel implements TreeModel {
	private ArrayList<Customer> customers;
	private final String ROOT = "Customers";

	/**
	 * Constructor for CustomerTreeModel objects.
	 *
	 */
	public CustomerTreeModel(ArrayList<Customer> customers) {
		this.customers = customers;
	}

	@Override
	public void addTreeModelListener(TreeModelListener arg0) {}

	@Override
	public Object getChild(Object parent, int index) {
		Object o = null;
		
		if(parent instanceof String){
			if(customers.size() > index){
				o = customers.get(index);
			}
		}else if(parent instanceof Customer){
			ArrayList<Car> cars = ((Customer)o).getCars();
			if(cars.size() > index){
				o = cars.get(index);
			}
		}
		
		return o;
	}

	@Override
	public int getChildCount(Object parent) {
		int count = 0;
		
		if(parent instanceof String){
			count = customers.size();
		}else if(parent instanceof Customer){
			count = ((Customer) parent).getCars().size();
		}
		
		return count;
	}

	@Override
	public int getIndexOfChild(Object parent, Object child) {
		int index = -1;
		
		if(parent instanceof String){
			index = customers.indexOf(child);
		}else if(parent instanceof Customer){
			index = ((Customer) parent).getCars().indexOf(child);
		}
		
		return index;
	}

	@Override
	public Object getRoot() {
		return ROOT;
	}

	@Override
	public boolean isLeaf(Object node) {
		boolean leafNode = true;
		
		if(node instanceof String){
			leafNode = false;
		}else if(node instanceof Customer){
			Customer c = (Customer)node;
			if(c.getCars() != null){
				if(c.getCars().size() != 0){
					leafNode = false;
				}
			}
		}
		
		return leafNode;
	}

	@Override
	public void removeTreeModelListener(TreeModelListener l) {}

	@Override
	public void valueForPathChanged(TreePath path, Object newValue) {}

}
