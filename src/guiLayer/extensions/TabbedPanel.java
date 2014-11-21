package guiLayer.extensions;

import javax.swing.JPanel;

/**
 * An abstract class that indicate a tab in the mainframe.
 *
 * @author Group 3, dmaa0214, UCN
 *
 */
public abstract class TabbedPanel extends JPanel {

	private static final long serialVersionUID = 1L;
	
	/**
	 * Defined that Component that {@link java.awt.Component#requestFocusInWindow()
     * requestFocusInWindow()}
	 */
	public abstract void setFocus(); 
}
