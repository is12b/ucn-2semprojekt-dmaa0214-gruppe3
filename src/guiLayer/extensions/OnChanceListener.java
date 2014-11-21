package guiLayer.extensions;

import java.awt.Component;
import java.util.ArrayList;

import javax.swing.JTextField;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;

/**
 * Class for OnChanceListener
 *
 * @author Group 3, dmaa0214, UCN
 *
 */
public class OnChanceListener implements ChangeListener {
	ArrayList<JTextField> fields;

	/**
	 * Constructor for OnChanceListener objects.
	 *
	 */
	public OnChanceListener(ArrayList<JTextField> fields) {
		this.fields = fields;
	}

	@Override
	public void stateChanged(ChangeEvent arg0) {
		updateFields(arg0.getSource());
	}
	
	private void updateFields(Object c) {
		ArrayList<Component> comps = new ArrayList<Component>();
		comps.addAll(fields);
		boolean empty = true;
		if (c instanceof JTextField) {
			empty = ((JTextField) c).getText().isEmpty();
		}
		if (!empty) {
			comps.remove(c);
			for (Component component : comps) {
				component.setEnabled(false);
			}
		} else {
			for (Component component : comps) {
				component.setEnabled(true);
			}
		}
	}

}
