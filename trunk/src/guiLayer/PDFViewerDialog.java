package guiLayer;

import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.MalformedURLException;
import java.net.URL;

import javax.swing.JButton;
import javax.swing.JComponent;
import javax.swing.JDialog;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JToolBar;

import modelLayer.Customer;
import modelLayer.Sale;

import org.icepdf.ri.common.MyAnnotationCallback;
import org.icepdf.ri.common.SwingController;
import org.icepdf.ri.common.SwingViewBuilder;

import com.sun.jndi.toolkit.url.Uri;

import ctrLayer.InvoicePDFGenerator;
import dbLayer.DBSale;
import dbLayer.interfaceLayer.IFDBSale;
import exceptions.BuildingPDFException;
import exceptions.EmailException;
import guiLayer.extensions.Utilities;

/**
 * Dialog for showing a PDF Document
 *
 * @author Group 3, dmaa0214, UCN
 *
 */
public class PDFViewerDialog extends JDialog {

	private static final long serialVersionUID = 1L;
	private SwingController controller;
	private Sale sale;
	private ByteArrayOutputStream baos;
	private InvoicePDFGenerator pdfGenerator;

	/**
	 * Constructor for PDFViewerDialog objects.
	 *
	 */
	public PDFViewerDialog(JComponent parent, Sale sale) throws BuildingPDFException {
		if (sale != null) {
			setTitle("Faktura #" + sale.getId());
			buildDialog(true);
			
			loadSale(sale);
		} else {
			throw new BuildingPDFException("Intet Salg valgt");
		}
		setLocationRelativeTo(parent);
		setVisible(true);
	}
	
	public PDFViewerDialog(JDialog parent, String uri) throws BuildingPDFException {
		URL url;
		try {
			url = new URL(uri);
		} catch (MalformedURLException e) {
			throw new BuildingPDFException("Ingen PDF valgt");
		}
		
		buildDialog(false);
		
		loadPDF(url);

		setLocationRelativeTo(parent);
		setVisible(true);
	}
	
	public PDFViewerDialog(JComponent parent, int saleID) throws BuildingPDFException {
		IFDBSale dbSale = new DBSale();
		Sale sale = dbSale.getSale(saleID);
		
		if (sale != null) {
			setTitle("Faktura #" + sale.getId());
			buildDialog(true);
			
			loadSale(sale);
		} else {
			throw new BuildingPDFException("Intet Salg valgt");
		}
		setLocationRelativeTo(parent);
		setVisible(true);
	}

	private void loadSale(Sale sale) throws BuildingPDFException {
		this.sale = sale;
		pdfGenerator = new InvoicePDFGenerator(sale);
	        
		baos = pdfGenerator.createPDF();
		if (baos.size() > 1) {
			byte[] tempBytes = baos.toByteArray();
		      
		    controller.openDocument(tempBytes, 0, tempBytes.length, "", null);
		} else {
			throw new BuildingPDFException("Salget kan ikke vises pga. en systemfejl");
		}
	    
	}
	
	private void loadPDF(URL url) throws BuildingPDFException {
		ByteArrayOutputStream baos = new ByteArrayOutputStream();
		InputStream is = null;
		try {
			is = url.openStream();
			byte[] byteChunk = new byte[4096]; // Or whatever size you want to
												// read in at a time.
			int n;

			while ((n = is.read(byteChunk)) > 0) {
				baos.write(byteChunk, 0, n);
			}
		} catch (IOException e) {
			throw new BuildingPDFException(
					"Det var ikke muligt at vise den ønskede PDF");
		} finally {
			if (is != null) {
				try {
					is.close();
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
		}

		if (baos.size() > 1) {
			byte[] tempBytes = baos.toByteArray();

			controller.openDocument(tempBytes, 0, tempBytes.length, "", null);
		} else {
			throw new BuildingPDFException(
					"Det var ikke muligt at vise den ønskede PDF");
		}
	}

	private void buildDialog(boolean send) {
		setModalityType(DEFAULT_MODALITY_TYPE);
		setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
		setMinimumSize(new Dimension(800, 600));
		
		controller = new SwingController();

		
		SwingViewBuilder factory = createFactory(controller, send);
		
		JPanel viewerComponentPanel = factory.buildViewerPanel();
		
		// add interactive mouse link annotation support via callback
		controller.getDocumentViewController().setAnnotationCallback(
                new MyAnnotationCallback(controller.getDocumentViewController()));
		
		getContentPane().add(viewerComponentPanel);
		
	}

	private SwingViewBuilder createFactory(SwingController controller2, boolean send) {
		SwingViewBuilder factory = new SwingViewBuilder(controller) {
						
			@Override
			public JToolBar buildRotateToolBar() {
				return null;
			}
									
			@Override
			public JToolBar buildCompleteToolBar(boolean embeddableComponent) {
				JToolBar tb = super.buildCompleteToolBar(embeddableComponent);			
				if(send){
					tb.add(emailButton(), 1);
				}
				return tb;
			}
		
		};
		return factory;
	}
	
	private JButton emailButton() {
		JButton b = new JButton("Send Email");
		b.setToolTipText("Send faktura til kundens mail");
		b.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				sendInvoice();
			}
		});
		return b;
	}

	private void sendInvoice() {
		
		try {
			Customer cus = sale.getCustomer();
			if (cus.getEmail() != null) {
				int c = Utilities.showConfirm(this, "Vil du sende en email med fakturaen til " + cus.getName() + " (" + cus.getEmail() + ")?"
						+ "\nDet kan tage et par sekunder", "Bekræft");
				if (c == JOptionPane.YES_OPTION) {			
					try {
						pdfGenerator.sendEmailWithInvoice(baos);
						Utilities.showInformation(this, "En email med fakturaen er sendt til " + cus.getName(), "Email sendt");
					} catch (EmailException e) {
						Utilities.showError(this, e.getMessage(), "Email ikke sendt");
					}		
				}	
			} else {
				Utilities.showError(this, "Emailen kan ikke sendes, da kunden ikke har en email tilknyttet", "Email ikke sendt");
			}
		} catch (NullPointerException e) {
			System.out.println(e.getMessage());
			Utilities.showError(this, "Emailen kan ikke sendes, da der ikke er en kunde tilknyttet", "Email ikke sendt");
		}
	}

}
