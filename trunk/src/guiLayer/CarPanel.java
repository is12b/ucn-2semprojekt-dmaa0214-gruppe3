package guiLayer;

import javax.swing.JPanel;

import guiLayer.extensions.TabbedPanel;

/**
 * Class for CarPanel
 *
 * @author Group 3, dmaa0214, UCN
 *
 */
class CarPanel extends JPanel implements TabbedPanel  {

	private static final long serialVersionUID = 1L;
	private MainGUI parent;

	/**
	 * Constructor for CarPanel objects.
	 * @param parent 
	 *
	 */
	public CarPanel(MainGUI parent) {
		// TODO Auto-generated constructor stub
		this.parent = parent;
	}

	@Override
	public void setFocus() {
		// TODO Auto-generated method stub

	}

}
