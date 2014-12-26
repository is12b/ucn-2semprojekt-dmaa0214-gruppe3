package ctrLayer;

import java.io.UnsupportedEncodingException;
import java.security.Security;
import java.util.Date;
import java.util.Properties;

import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.NoSuchProviderException;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.AddressException;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;

import com.sun.mail.smtp.SMTPTransport;

import ctrLayer.interfaceLayer.IFSettingCtr;

/**
 * Class for EmailCtr
 *
 * @author Group 3, dmaa0214, UCN
 *
 */
public class EmailCtr {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		new EmailCtr();
	}

	private IFSettingCtr settingCtr;
	private static boolean debug = true; //enable mail debugging
	
	/**
	 * Constructor for EmailCtr objects.
	 *
	 */
	public EmailCtr() {
		// TODO Auto-generated constructor stub
		send();
	}

	public void send() {
		
		settingCtr = new SettingCtr();
		
		Transport t = null;
		
		try {
			boolean useSSL = isSSL();
		
			Properties props = makeProperties(useSSL);
		
			Session session = Session.getInstance(props);
			session.setDebug(debug);
			
			String email = settingCtr.getSettingByKey("EMAIL_SMTP_EMAIL").getValue();
			String pass = settingCtr.getSettingByKey("EMAIL_SMTP_PASS").getValue();
			
			MimeMessage msg = createMessage(session, email);
			
			t = createTransport(session, useSSL);
			t.connect(email, pass);
			t.sendMessage(msg, msg.getAllRecipients()); 
			
		} catch (NullPointerException e) { 
			//Maybe because key is missing in DB
			//TODO send fejl videre
			System.out.println("EmailCtr - send - NullPointerException");
			e.printStackTrace();
		} catch (MessagingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (UnsupportedEncodingException e) { //pga. custom Afsender
			e.printStackTrace();
		} finally {
			closeTransport(t);
		}
		settingCtr = null;
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

	private MimeMessage createMessage(Session session, String from_email) throws UnsupportedEncodingException, MessagingException {
		MimeMessage msg = new MimeMessage(session);
		
		String from = settingCtr.getSettingByKey("EMAIL_SMTP_FROM").getValue();
				
		msg.setFrom(new InternetAddress(from_email, from));
		msg.setRecipient(Message.RecipientType.TO, new InternetAddress(from_email, "Kundenavn")); //TODO ændre til kunde email
		//msg.s
		msg.setSubject("Overskrift");
		msg.setText("Tekst? + " + new Date().toString(), "utf-8");
		return msg;
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
		} else {
			props.setProperty("mail.smtp.host", host);
			props.setProperty("mail.smtp.port", port);
			props.setProperty("mail.smtp.starttls.enable","true");
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
