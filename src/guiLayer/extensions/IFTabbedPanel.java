package guiLayer.extensions;

/**
 * An interface, to ensure that all "Tabs" have a setFocus method implemented
 *
 * @author Group 3, dmaa0214, UCN
 *
 */
public interface IFTabbedPanel {
	
	/**
	 * Defined that Component that {@link java.awt.Component#requestFocusInWindow()
     * requestFocusInWindow()}
	 */
	public void setFocus(); 
}
