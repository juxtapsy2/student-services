package doancuoiky.web;

import java.util.*;
import javax.mail.*;
import javax.mail.internet.*;

public class SendMail {
	public void send(String to, String subject, String msg, final String user, final String pass) {
		Properties props = new Properties();
		/* Specifies the IP address of your default mail server.
		 * for ex: if you use gmail server then pass smtp.gmail.com to mail.smtp.host()
		 * Change it according to your mail server.
		 */
		props.put("mail.transport.protocol", "smtp");
		
		props.put("mail.smtp.host", "smtp.gmail.com");
		// Below mentioned smtp.mail.port is optional
		props.put("mail.smtp.port", "587");
		props.put("mail.smtp.auth", "true");
		props.put("mail.smtp.starttls.enable", "true");
		props.put("mail.smtp.ssl.trust", "smtp.gmail.com");
		
		Session session = Session.getInstance(props, new javax.mail.Authenticator() {
			@Override protected PasswordAuthentication getPasswordAuthentication() {
				return new PasswordAuthentication(user, pass);
			}
		});
		
		try {
			// Create an instance of MimeMessage, accepting MIME type and headers
			MimeMessage message = new MimeMessage(session);
			message.setFrom(new InternetAddress(user));
			message.addRecipient(Message.RecipientType.TO, new InternetAddress(to));
			message.setSubject(subject);
			message.setContent(msg, "text/html");
			// Deliver the message to the recipient
			Transport.send(message);
		}
		catch (MessagingException e) {
			e.printStackTrace();
		}
	};
}