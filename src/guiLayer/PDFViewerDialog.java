package guiLayer;

import java.awt.Dimension;

import guiLayer.exceptions.BuildingPDFException;

import javax.swing.JComponent;
import javax.swing.JDialog;
import javax.swing.JPanel;

import org.icepdf.ri.common.MyAnnotationCallback;
import org.icepdf.ri.common.SwingController;
import org.icepdf.ri.common.SwingViewBuilder;

import testLayer.pdf.CopyOfInvoicePDFGenerator;
import modelLayer.Sale;

import java.io.ByteArrayOutputStream;

/**
 * Dialog for showing a PDF Document
 *
 * @author Group 3, dmaa0214, UCN
 *
 */
public class PDFViewerDialog extends JDialog {

	private static final long serialVersionUID = 1L;
	private SwingController controller;

	/**
	 * Constructor for PDFViewerDialog objects.
	 *
	 */
	public PDFViewerDialog(JComponent parent, Sale sale) throws BuildingPDFException {
		if (sale != null) {
			setTitle("Faktura #" + sale.getId());
			buildDialog();
			
			loadSale(sale);
		} else {
			throw new BuildingPDFException("Intet Salg valgt");
		}
		setLocationRelativeTo(parent);
		setVisible(true);
	}

	private void loadSale(Sale sale) throws BuildingPDFException {
		CopyOfInvoicePDFGenerator generateInvoice = new CopyOfInvoicePDFGenerator(); //TODO �ndre til den rigtige
	        
		ByteArrayOutputStream baos = generateInvoice.createPDF(sale);
		if (baos.size() > 1) {
			byte[] tempBytes = baos.toByteArray();
		      
		    controller.openDocument(tempBytes, 0, tempBytes.length, "", null);
		} else {
			throw new BuildingPDFException("Salget kan ikke vises pga. en systemfejl");
		}
	    
	}

	private void buildDialog() {
		setModalityType(DEFAULT_MODALITY_TYPE);
		setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
		setMinimumSize(new Dimension(800, 600));
		
		controller = new SwingController();

		SwingViewBuilder factory = new SwingViewBuilder(controller);

		JPanel viewerComponentPanel = factory.buildViewerPanel();
		
		// add interactive mouse link annotation support via callback
		controller.getDocumentViewController().setAnnotationCallback(
                new MyAnnotationCallback(controller.getDocumentViewController()));
		
		getContentPane().add(viewerComponentPanel);
		
	}

}