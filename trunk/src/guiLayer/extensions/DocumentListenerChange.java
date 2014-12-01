package guiLayer.extensions;

import java.util.ArrayList;

import javax.swing.JTextField;
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;

/**
 * Class for DocumentListenerChange
 *
 * @author Group 3, dmaa0214, UCN
 *
 */
public class DocumentListenerChange implements DocumentListener {
	private ArrayList<JTextField> fields;
	private JTextField root;

	/**
	 * Constructor for DocumentListenerChange objects.
	 *
	 */
	public DocumentListenerChange(ArrayList<JTextField> fields, JTextField root) {
		this.fields = fields;
		this.root = root;
	}

	@Override
	public void changedUpdate(DocumentEvent arg0) {
		updateFields(root);
	}

	@Override
	public void insertUpdate(DocumentEvent arg0) {
		updateFields(root);
	}

	@Override
	public void removeUpdate(DocumentEvent arg0) {
		updateFields(root);
	}
	
	private void updateFields(JTextField c) {
		ArrayList<JTextField> comps = new ArrayList<JTextField>();
		comps.addAll(fields);
		boolean empty = c.getText().isEmpty();		
		if (!empty) {
			comps.remove(c);
			for (JTextField component : comps) {
				component.setEnabled(false);
			}
		} else {
			for (JTextField component : comps) {
				component.setEnabled(true);
			}
		}
	}

}
