package exceptions;

import javax.swing.JComboBox;
import javax.swing.JComponent;
import javax.swing.JOptionPane;
import javax.swing.JTextField;

/**
 * Thrown to indicate a error in a submitted form.
 */
public class SubmitException extends Exception {

	private static final long serialVersionUID = 1L;
	private JComponent parent = null;
	
	/**
	 * Constructs an SubmitException with the specified detail message and set focus to JComboBox if it's not null.
	 * @param arg0 the detail message.
	 * @param cmb the comboBox to get focus.
	 */
	public SubmitException(String arg0, JComboBox<?> cmb) {
		super(arg0);
		if(cmb != null) {
			parent = cmb;
			cmb.requestFocusInWindow();
		}
	}
	
	/**
	 * Constructs an SubmitException with the specified detail message and set focus to JTextField if it's not null.
	 * @param arg0 the detail message.
	 * @param field the field to get focus.
	 */
	public SubmitException(String arg0, JTextField field) {
		super(arg0);
		if(field != null) {
			parent = field;
			field.requestFocusInWindow();
		}
	}
	
	/**
	 * Constructor for SubmitException objects.
	 *
	 * @param string
	 */
	public SubmitException(String arg0) {
		super(arg0);
	}

	/**
	 * Show an error popup with the error text.
	 */
	public void showError(){
		JOptionPane.showMessageDialog(parent, getMessage(), "Fejl", JOptionPane.ERROR_MESSAGE);
	}

}
