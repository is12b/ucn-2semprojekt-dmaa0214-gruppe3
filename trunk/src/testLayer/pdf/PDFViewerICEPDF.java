package testLayer.pdf;

import java.io.ByteArrayOutputStream;

import javax.swing.JFrame;
import javax.swing.JPanel;

import modelLayer.Sale;

import org.icepdf.ri.common.SwingController;
import org.icepdf.ri.common.SwingViewBuilder;

import dbLayer.DBSale;
import dbLayer.interfaceLayer.IFDBSale;
import exceptions.BuildingPDFException;


public class PDFViewerICEPDF {
	    public PDFViewerICEPDF() throws BuildingPDFException {
	        	        
	        // build a component controller
	        SwingController controller = new SwingController();
	        controller.setDocumentToolMode(2);

	        SwingViewBuilder factory = new SwingViewBuilder(controller);

	        
	        JPanel viewerComponentPanel = factory.buildViewerPanel();
	        

	        // add interactive mouse link annotation support via callback
	        controller.getDocumentViewController().setAnnotationCallback(
	                new org.icepdf.ri.common.MyAnnotationCallback(
	                        controller.getDocumentViewController()));

	        JFrame applicationFrame = new JFrame();
	        applicationFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	        applicationFrame.getContentPane().add(viewerComponentPanel);

	        // Now that the GUI is all in place, we can try openning a PDF
	        //controller.openDocument(filePath);
	        
	        IFDBSale dbSale = new DBSale();
	        Sale s = dbSale.getSale(7);
	        
	        InvoicePDFGenerator generateInvoice = new InvoicePDFGenerator(s);
	        	        
	        ByteArrayOutputStream baos = generateInvoice.createPDF();
	        byte[] tempBytes = baos.toByteArray();
	        
	        controller.openDocument(tempBytes, 0, tempBytes.length, "TEST, HVOR ER DETTE?", null);

	        // show the component
	        applicationFrame.pack();
	        applicationFrame.setVisible(true);
	    }
	
}
