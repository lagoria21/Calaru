package calaru.mail;

import java.io.File;
import java.util.Properties;

import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.AddressException;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Service;

@Service
public class MailServiceImpl implements MailService {	
	
	private JavaMailSender mailSender;
	
	@Value("${spring.mail.host}")
	private String host;
	
	@Value("${spring.mail.port}")
	private String port;
	
	@Value("${spring.mail.user}")
	private String user;
	
	@Value("${spring.mail.password}")
	private String pass;
	
	
	
	public void enviar() throws AddressException, MessagingException{
		
		Properties props = new Properties();
	    props.setProperty("mail.smtp.host", host);
	    props.setProperty("mail.smtp.starttls.enable", "true");
	    props.setProperty("mail.smtp.port", port);
	    props.setProperty("mail.smtp.user", user);
	    props.setProperty("mail.smtp.auth", "true");
		
	  // Preparamos la sesion
        Session session = Session.getDefaultInstance(props);

        // Construimos el mensaje
        MimeMessage message = new MimeMessage(session);
        message.setFrom(new InternetAddress(user));
        message.addRecipient(Message.RecipientType.TO, new InternetAddress(user));
        message.setSubject("Se esta quedando sin stock por favor revisar el inventario");
        message.setText("Ejemplo de enviar email");

        // Lo enviamos.
        Transport t = session.getTransport("smtp");
        t.connect(user, pass);
        t.sendMessage(message, message.getAllRecipients());
        // Cierre.
        t.close();
	}
	
	
	@Override
	public void sendMail(String from, String to, String subject, String body){
        SimpleMailMessage message = new SimpleMailMessage();                
        message.setTo(to);
        message.setSubject(subject);
        message.setText(body);        
        message.setFrom(from);
        mailSender.send(message);
	}
	

	@Override
	public void sendMail(String from, String to, String subject, String body, File attachment, boolean isHtml,
			String nombre)  {
				
		MimeMessage mimeMessage = mailSender.createMimeMessage();	
    	MimeMessageHelper helper;       	
		
    	try {
			helper = new MimeMessageHelper(mimeMessage, true, "utf-8");			
			helper.setText(body, isHtml);
	    	helper.setTo(to);
	    	helper.setSubject(subject);
	    	helper.setFrom(from);
	    	if(attachment!= null)
	    	{
	    	// FileSystemResource file = new FileSystemResource(pathDirectorio.getPath()+attachment);
	    	// helper.addAttachment(nombrePdf, file);
	    	}
	 	    	
	    	mailSender.send(mimeMessage);
			} 
				catch (MessagingException e) {
				e.printStackTrace();
			}
		
	}
	
	

	
	
	
}
