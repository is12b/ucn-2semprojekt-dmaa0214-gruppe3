package guiLayer.extensions;

import exceptions.SubmitException;
import guiLayer.MainGUI;

import java.awt.Component;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.text.NumberFormat;
import java.util.ArrayList;

import javax.swing.JButton;
import javax.swing.JComponent;
import javax.swing.JDialog;
import javax.swing.JOptionPane;
import javax.swing.JTextField;
import javax.swing.KeyStroke;

/**
 * A Class that holds Utilities used by many another classes
 *
 * @author Group 3, dmaa0214, UCN
 */
public abstract class Utilities {

	/**
	 * Show warning dialog as a {@link JOptionPane} object.
	 *
	 * @param parent The parent of this warning 
	 * @param text The warning text to show
	 * @return The choice as a {@link JOptionPane} value
	 */
	public static int showWarning(Component parent, String text) {
		return JOptionPane.showConfirmDialog(parent, text, "Advarsel", JOptionPane.YES_NO_OPTION, JOptionPane.WARNING_MESSAGE);
	}
	
	/**
	 * Show confirm dialog as a {@link JOptionPane} object.
	 *
	 * @param parent The parent of this confirm dialog 
	 * @param text The confirm text to show
	 * @param title The text shows as title of the dialog
	 */
	public static int showConfirm(Component parent, String text, String title) {
		return JOptionPane.showConfirmDialog(parent, text, title, JOptionPane.YES_NO_OPTION, JOptionPane.QUESTION_MESSAGE);
	}
	
	/**
	 * Show error dialog as a {@link JOptionPane} object.
	 *
	 * @param parent The parent of this error 
	 * @param text The error text to show
	 * @param title The text shows as title of the dialog
	 */
	public static void showError(Component parent, String text, String title) {
		if(text.trim().isEmpty()) {
			text = "Der er sket en ukendt systemfejl";
		}
		JOptionPane.showMessageDialog(parent, text, "Fejl", JOptionPane.ERROR_MESSAGE);
	}
	
	/**
	 * Show information dialog as a {@link JOptionPane} object.
	 *
	 * @param parent The parent of this information 
	 * @param text The information text to show
	 * @param title The text shows as title of the dialog
	 */
	public static void showInformation(Component parent, String text, String title){
		JOptionPane.showMessageDialog(parent, text, title, JOptionPane.INFORMATION_MESSAGE);
	}
	
	/**
	 * Show error dialog as a {@link JOptionPane} object.
	 *
	 * @param parent The parent of this error 
	 * @param text The error text to show
	 */
	public static void showError(Component parent, String text) {
		showError(parent, text, "Fejl");
	}
	
	/**
	 * Adds an escape key Listener to a dialog.
	 * @param dialog The dialog to add the listener.
	 */
	public static void addEscapeListener(JDialog dialog) {
		ActionListener escListener = new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				dialog.setVisible(false);
				dialog.dispose();
			}
		};
		dialog.getRootPane().registerKeyboardAction(escListener,
				KeyStroke.getKeyStroke(KeyEvent.VK_ESCAPE, 0),
				JComponent.WHEN_IN_FOCUSED_WINDOW);
	}
	
	
	/**
	 * Method for making a number to money format
	 * 
	 * @param number the number to edit
	 * @return the money format for the number
	 */
	public static String getAsMoney(double number) {
		NumberFormat money = NumberFormat.getCurrencyInstance();
		return money.format(number);
	}

	/**
	 * Method to shorten a text
	 * 
	 * @param str the string to short
	 * @param i the length of the returned string
	 * @return the shorten string
	 */
	public static String shortenString(String str, int length) { // NO_UCD (unused code)
		String retStr = str;
		
		if(str.length() > length) {
			retStr = str.substring(0, length);
		}
		return retStr;
	}
	
	/**
	 * Method for checking a TextField is empty and return the text.
	 * 
	 * @param field the field to check
	 * @param objText a word that describes the field
	 * @throws SubmitException if field is empty
	 */
	public static String getTextFromReqField(JTextField field, String objText) throws SubmitException {
		String ret = field.getText().trim();
		if(ret.isEmpty()) {
			throw new SubmitException(objText + " må ikke være tom.", field);
		}
		return ret;
	}

	/**
	 * Method for adding a documentListener on some textfields, 
	 * that disable all other fields than the active one
	 * 
	 * @param fields the list of fields to add the listener to
	 */
	public static void addDocumentListener(ArrayList<JTextField> fields) {
		for(JTextField f : fields){
			f.getDocument().addDocumentListener(new DocumentListenerChange(fields, f));
		}
	}
	
	/**
	 * Method for adding a documentListener on some textfields, 
	 * that disable all other fields than the active one and set default button
	 * 
	 * @param fields the list of fields to add the listener to
	 * @param frame the parent frame
	 * @param defaultBtn the button to set as default
	 */
	public static void addDocumentListener(ArrayList<JTextField> fields, MainGUI frame, JButton defaultBtn) {
		for(JTextField f : fields){
			f.getDocument().addDocumentListener(new DocumentListenerChange(fields, f, frame, defaultBtn));
		}
	}

}
