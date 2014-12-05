package testLayer.pdf;

import java.io.ByteArrayOutputStream;

import javax.swing.JFrame;
import javax.swing.JPanel;

import org.icepdf.ri.common.SwingController;
import org.icepdf.ri.common.SwingViewBuilder;


public class PDFViewerICEPDF {
	    public static void main(String[] args) {
	        // Get a file from the command line to open
	        String filePath = "C:\\tmpInvoice.pdf";
	        
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
	        
	        CopyOfInvoicePDFGenerator generateInvoice = new CopyOfInvoicePDFGenerator();
	        	        
	        ByteArrayOutputStream baos = generateInvoice.createPDF(generateInvoice.getSale());
	        byte[] tempBytes = baos.toByteArray();
	        
	        controller.openDocument(tempBytes, 0, tempBytes.length, "TEST, HVOR ER DETTE?", null);

	        // show the component
	        applicationFrame.pack();
	        applicationFrame.setVisible(true);
	    }
	
}
