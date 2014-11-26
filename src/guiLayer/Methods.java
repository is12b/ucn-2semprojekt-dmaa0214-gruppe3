package guiLayer;

import guiLayer.exceptions.SubmitException;

import java.awt.Component;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.text.NumberFormat;

import javax.swing.JComponent;
import javax.swing.JDialog;
import javax.swing.JOptionPane;
import javax.swing.JTextField;
import javax.swing.KeyStroke;

/**
 * A Class that holds Methods used by many another classes
 *
 * @author Group 3, dmaa0214, UCN
 */
public abstract class Methods {

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
	 * Show error dialog as a {@link JOptionPane} object.
	 *
	 * @param parent The parent of this error 
	 * @param text The error text to show
	 * @param title The text shows as title of the dialog
	 */
	public static void showError(Component parent, String text, String title) {
		JOptionPane.showMessageDialog(parent, text, "Fejl", JOptionPane.ERROR_MESSAGE);
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
	public static String shortenString(String str, int length) {
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

}
