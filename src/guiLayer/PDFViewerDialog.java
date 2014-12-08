package guiLayer;

import guiLayer.exceptions.BuildingPDFException;

import java.awt.Dimension;
import java.io.ByteArrayOutputStream;

import javax.swing.JComponent;
import javax.swing.JDialog;
import javax.swing.JPanel;

import modelLayer.Sale;

import org.icepdf.ri.common.MyAnnotationCallback;
import org.icepdf.ri.common.SwingController;
import org.icepdf.ri.common.SwingViewBuilder;

import ctrLayer.InvoicePDFGenerator;
import dbLayer.DBSale;
import dbLayer.interfaceLayer.IFDBSale;

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
	
	public PDFViewerDialog(JComponent parent, int saleID) throws BuildingPDFException {
		IFDBSale dbSale = new DBSale();
		Sale sale = dbSale.getSale(saleID);
		
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
		InvoicePDFGenerator generateInvoice = new InvoicePDFGenerator(sale);
	        
		ByteArrayOutputStream baos = generateInvoice.createPDF();
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
