package guiLayer.extensions;

import guiLayer.MainGUI;

import java.util.ArrayList;

import javax.swing.JButton;
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
	private JButton defaultBtn = null;
	private MainGUI frame;

	/**
	 * Constructor for DocumentListenerChange objects.
	 *
	 */
	public DocumentListenerChange(ArrayList<JTextField> fields, JTextField root) {
		this.fields = fields;
		this.root = root;
	}

	/**
	 * Constructor for DocumentListenerChange objects.
	 *
	 */
	public DocumentListenerChange(ArrayList<JTextField> fields, JTextField root, MainGUI frame,
			JButton defaultBtn) {
		this.fields = fields;
		this.root = root;
		this.frame = frame;
		this.defaultBtn  = defaultBtn;
	}

	@Override
	public void changedUpdate(DocumentEvent arg0) {
		updateFields();
	}

	@Override
	public void insertUpdate(DocumentEvent arg0) {
		updateFields();
	}

	@Override
	public void removeUpdate(DocumentEvent arg0) {
		updateFields();
	}
	
	private void updateFields() {
		if (defaultBtn != null && frame != null) {
			frame.setDefaultButton(defaultBtn);
		}
		ArrayList<JTextField> comps = new ArrayList<JTextField>();
		comps.addAll(fields);
		boolean empty = root.getText().isEmpty();		
		if (!empty) {
			comps.remove(root);
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
