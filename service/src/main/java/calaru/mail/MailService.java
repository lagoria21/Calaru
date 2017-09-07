package calaru.mail;

import java.io.File;

import javax.mail.MessagingException;
import javax.mail.internet.AddressException;

public interface MailService {
	
	/**
	 * Envia un mail con texto plano y sin posibilidad de adjuntar archivos con un mailSender especificado por parametro.
	 * @param from
	 * @param to
	 * @param subject
	 * @param body
	*/
	
	public void sendMail(String from, String to, String subject, String body);
	
	/**
	 * Permite enviar un mail con texto plano o html y con archivos adjuntos con un mailSender especificado por parametro.
	 * @param from
	 * @param to
	 * @param subject
	 * @param body
	 * @param attachment
	 */
	
	public void sendMail(String from, String to, String subject, String body, File attachment, boolean isHtml, String nombre);
	
	public void enviar() throws AddressException, MessagingException;

}
