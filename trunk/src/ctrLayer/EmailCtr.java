package ctrLayer;

import java.io.ByteArrayOutputStream;
import java.io.UnsupportedEncodingException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Properties;

import javax.activation.DataHandler;
import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.NoSuchProviderException;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeBodyPart;
import javax.mail.internet.MimeMessage;
import javax.mail.internet.MimeMultipart;
import javax.mail.util.ByteArrayDataSource;
import modelLayer.Customer;
import modelLayer.Sale;

import ctrLayer.interfaceLayer.IFSettingCtr;
import exceptions.BuildingPDFException;
import exceptions.EmailException;

/**
 * Class for EmailCtr
 *
 * @author Group 3, dmaa0214, UCN
 *
 */
public class EmailCtr {

	private IFSettingCtr settingCtr;
	private static boolean debug = true; //enable mail debugging
	
	/*
	public static void main(String[] args) {
		new EmailCtr();
	}
	  
	private void testMethod() {
		IFSaleCtr sCtr = new SaleCtr();
		
		try {
			Sale s = sCtr.getSaleByID(19);
			sendInvoice(s);
		} catch (ObjectNotExistException e) {
			e.printStackTrace();
		} catch (EmailException e) {
			e.printStackTrace();
		}
	}*/
	
	/**
	 * Constructor for EmailCtr objects.
	 *
	 */
	public EmailCtr() {
		//testMethod();
	}
	
	public void sendInvoice(Sale sale) throws EmailException {	
		
		checkCustomer(sale);
		
		try {
			InvoicePDFGenerator pdfGenerator = new InvoicePDFGenerator(sale);
			ByteArrayOutputStream baos = pdfGenerator.createPDF();
			sendInvoice(sale, baos);
		} catch (BuildingPDFException e) {
			throw new EmailException("Faktura PDF'en kunne ikke generes");
		}
	}

	public void sendInvoice(Sale sale, ByteArrayOutputStream baos) throws EmailException {
		
		checkCustomer(sale);
		
		settingCtr = new SettingCtr();
		
		Transport t = null;
		
		try {
			boolean useSSL = isSSL();
		
			Properties props = makeProperties(useSSL);
		
			Session session = Session.getInstance(props);
			session.setDebug(debug);
			
			String email = settingCtr.getSettingByKey("EMAIL_SMTP_EMAIL").getValue();
			String pass = settingCtr.getSettingByKey("EMAIL_SMTP_PASS").getValue();
			String fromText = settingCtr.getSettingByKey("EMAIL_SMTP_FROM").getValue();
			
			Customer cus = sale.getCustomer();
			
			String cusName = cus.getName();
			String cusEmail = cus.getEmail();
						
			MimeMessage msg = new MimeMessage(session);
			
			msg.setFrom(new InternetAddress(email, fromText));
			msg.setRecipient(Message.RecipientType.TO, new InternetAddress(cusEmail, cusName));
			
			msg.setSubject("Faktura #" + sale.getId());
			
			//email text
			MimeBodyPart msgBodyText = new MimeBodyPart();
			String text = generateEmailText(sale, fromText);
			msgBodyText.setText(text, "utf-8");

			//pdf attachment
			MimeBodyPart attachment = new MimeBodyPart();
			ByteArrayDataSource bads = new ByteArrayDataSource(baos.toByteArray(), "application/pdf");
			attachment.setDataHandler(new DataHandler(bads));
			attachment.setFileName("Faktura "+ sale.getId() + ".pdf");
			
			MimeMultipart multipart = new MimeMultipart();
			multipart.addBodyPart(msgBodyText);
			multipart.addBodyPart(attachment); 
			
			msg.setContent(multipart);
			//msg.setText(text, "utf-8");
			
			t = createTransport(session, useSSL);
			t.connect(email, pass);
			t.sendMessage(msg, msg.getAllRecipients()); 
			
		} catch (NullPointerException e) { 
			//Maybe because key is missing in DB
			System.out.println("EmailCtr - send - NullPointerException");
			e.printStackTrace();
			throw new EmailException("Der er sket en fejl, Emailen kan derfor ikke sendes");
		} catch (MessagingException e) {
			System.out.println("EmailCtr - send - MessagingException");
			e.printStackTrace();
			throw new EmailException("Der er sket en fejl, Emailen kan derfor ikke sendes");
		} catch (UnsupportedEncodingException e) { //pga. custom Afsender
			e.printStackTrace();
		} finally {
			closeTransport(t);
		}
		settingCtr = null;
	}

	private String generateEmailText(Sale sale, String fromText) {
		String ret = "Hej " + sale.getCustomer().getName() + ""
				+ "\n\n"
				+ "Vedlagt er faktura"
				+ "\n\n";
		if (sale.isPaid()) {
			ret += "Den er betalt";
		} else {
			Date deadlineDate = sale.getPaymentDeadline();
			ret += "Den er ikke betalt og skal betales senest d. "
					+ new SimpleDateFormat("dd-MM-yyyy").format(deadlineDate)
					+ "\n"
					+ "Efter denne dato tilskrives renter";
		}
		ret += "\n\n"
				+ "Venlig hilsen"
				+ "\n"
				+ fromText;		
		
		return ret;
	}

	private void checkCustomer(Sale sale) throws EmailException {
		if (sale != null) {
			Customer cus = sale.getCustomer();
			if (cus == null) {
				throw new EmailException("Der er ingen kunde tilknyttet salget");
			} else {
				if (cus.getEmail() == null || cus.getEmail().isEmpty()) {
					throw new EmailException("Den tilknyttede kunde har ingen e-mail adresse angivet");
				}
			}
		} else {
			throw new EmailException("Der er intet salg valgt?");
		}
	}

	private void closeTransport(Transport t) {
		if (t != null) {
	        try {
	            t.close();
	        } catch (Exception e) { 
	        	//ignored
	        }
	    }
	}

	private Transport createTransport(Session session, boolean useSSL) throws NoSuchProviderException {
		Transport t;
		if (useSSL) {
			t = session.getTransport("smtps");
		} else {
			t = session.getTransport("smtp");
		}
		return t;
	}

	private Properties makeProperties(boolean useSSL) {				
		Properties props = System.getProperties();
		String host = settingCtr.getSettingByKey("EMAIL_SMTP_HOST").getValue();
		String port = settingCtr.getSettingByKey("EMAIL_SMTP_PORT").getValue();
				
		if (useSSL) {
			props.setProperty("mail.smtps.host", host);
			props.setProperty("mail.smtps.port", port);
			props.setProperty("mail.smtps.auth", "true");
		} else {
			props.setProperty("mail.smtp.host", host);
			props.setProperty("mail.smtp.port", port);
			props.setProperty("mail.smtp.starttls.enable","true");
			props.setProperty("mail.smtp.auth", "true");
		}
		return props;
	}

	private boolean isSSL() {
		boolean useSSL = true;
		if (settingCtr.getSettingByKey("EMAIL_SMTP_SSL").getValue().equals("0")) {
			useSSL = false;
		}
		return useSSL;
	}

}
