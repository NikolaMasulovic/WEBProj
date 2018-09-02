package ProjWEB.PROJWEB.Service;

import java.net.PasswordAuthentication;
import java.util.Properties;

import javax.activation.DataHandler;
import javax.activation.DataSource;
import javax.activation.FileDataSource;
import javax.mail.Authenticator;
import javax.mail.BodyPart;
import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.Multipart;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeBodyPart;
import javax.mail.internet.MimeMessage;
import javax.mail.internet.MimeMultipart;

public class MailService {
	
	  private static String protocol = "smtp";

	    private String username = "nikolamas73@gmail.com";
	    private String password = "";

	    private Session session;
	    private Message message;
	    private Multipart multipart;

	    public MailService()
	    {
	        this.multipart = new MimeMultipart();
	    }
	    
	    public void sendWithAttachment(String to,String title) throws MessagingException {

			try {

		        SmtpAuthenticator authentication = new SmtpAuthenticator();
		        javax.mail.Message msg = new MimeMessage(Session
		                            .getDefaultInstance(this.getMailServerProperties(), authentication));
		        msg.setFrom(new InternetAddress("nikolamas73@gmail.com"));
		        msg.setRecipients(Message.RecipientType.TO,
					InternetAddress.parse("nikola.masulovic@netcast.rs"));
		        msg.setSubject("Testing Subject");
		        msg.setText("Dear Mail Crawler,"
					+ "\n\n No spam to my email, please!");
				Transport.send(msg);

				System.out.println("MAIL SENT::Done");

			} catch (MessagingException e) {
				throw new RuntimeException(e);
			}
		}

	    public void addAttachment(String filePath) throws MessagingException
	    {
	        BodyPart messageBodyPart = getFileBodyPart(filePath);
	        multipart.addBodyPart(messageBodyPart);

	        message.setContent(multipart);
	    }

	    private BodyPart getFileBodyPart(String filePath) throws MessagingException
	    {
	        BodyPart messageBodyPart = new MimeBodyPart();
	        DataSource dataSource = new FileDataSource(filePath);
	        messageBodyPart.setDataHandler(new DataHandler(dataSource));
	        messageBodyPart.setFileName(filePath);

	        return messageBodyPart;
	    }

	    private Properties getMailServerProperties()
	    {
	        Properties properties = System.getProperties();
	        properties.put("mail.smtp.starttls.enable", "true");
	        properties.put("mail.smtp.host", protocol + ".gmail.com");
	        properties.put("mail.user", this.username);
	        properties.put("mail.password", this.password);
	        properties.put("mail.smtp.port", "587");
	        properties.put("mail.smtp.auth", "true");

	        return properties;
	    }
	
	
}
