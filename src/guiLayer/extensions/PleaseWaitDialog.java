package guiLayer.extensions;

import java.awt.Color;
import java.awt.Component;
import java.awt.Dimension;
import java.net.URL;

import javax.swing.ImageIcon;
import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.SwingConstants;
import javax.swing.border.LineBorder;

import com.jgoodies.forms.layout.ColumnSpec;
import com.jgoodies.forms.layout.FormLayout;
import com.jgoodies.forms.layout.RowSpec;

/**
 * Class for PleaseWaitDialog
 *
 * @author Group 3, dmaa0214, UCN
 *
 */
public class PleaseWaitDialog extends JDialog {

	private static final long serialVersionUID = 1L;
	
	/**
	 * Constructor for PleaseWaitDialog objects.
	 *
	 * @param owner
	 */
	public PleaseWaitDialog(Component parent) {
		super();
		setModal(true);
		setUndecorated(true);
		setDefaultCloseOperation(DO_NOTHING_ON_CLOSE);
		setModalityType(ModalityType.APPLICATION_MODAL);
		setMinimumSize(new Dimension(150,50));
				
		JPanel panelBorder = new JPanel();
		panelBorder.setBorder(new LineBorder(new Color(0, 0, 0)));
		getContentPane().add(panelBorder);
		panelBorder.setLayout(new FormLayout(new ColumnSpec[] {
				ColumnSpec.decode("default:grow"),},
			new RowSpec[] {
				RowSpec.decode("default:grow"),}));
		JPanel p = new JPanel();
				
		URL imageURL = getClass().getClassLoader().getResource("loading.gif");
		ImageIcon icon = new ImageIcon(imageURL);
		
		JLabel lblIcon = new JLabel(icon);
		icon.setImageObserver(lblIcon);
		p.add(lblIcon);
		lblIcon.setHorizontalAlignment(SwingConstants.CENTER);
		
		JLabel lblText = new JLabel("Vent venligst..");
		p.add(lblText);
		lblText.setHorizontalAlignment(SwingConstants.CENTER);
		
		panelBorder.add(p, "1, 1, center, center");
		pack();
		setLocationRelativeTo(parent);
	}
}
