package handlers;

import javax.mail.MessagingException;
import javax.mail.internet.MimeMessage;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Component;
import org.springframework.mail.javamail.MimeMessageHelper;


/*
 * The handler for sending emails from the server. Currently, has only one
 * function. All methods are called statically, since there is no need for state
 * memory. 
 * 
 * The spring.mail property details are defined in the application.
 * properties file in src/main/resources.
 */
@Component
public class EmailHandler {
	
	/*
	 * sendMail() sends an email with a given content to a given address
	 * 
	 * Input: 
	 * - JavaMailSender sender: requires an instance to be given to this method
	 * - String email: the email address to send the email to
	 * - String content: the content of the message. No formatting is done, but
	 * 		the content is expected to be in html format.
	 * 
	 * Process:
	 * - Uses sender to craft a MimeMessage, message, which is then used to  create 
	 * 		a MimeMessageHelper, helper, for easier email formatting. 
	 * - helper sets the To and Text to email and content, respectively
	 * - The subject is always "Mail From Workout Generator"
	 * 		If necessary, overloaded method will handle a need for alternate
	 * 		subjects.
	 * - The message is sent!
	 * 
	 * Output:
	 * - An email sent from this server 			
	 */
	public void sendMail(JavaMailSender sender, String email, String content) {
		System.out.println("sendEmail called!");
		MimeMessage message = sender.createMimeMessage();
		System.out.println("message created!");
		MimeMessageHelper helper = new MimeMessageHelper(message);
		
		try {
			helper.setTo(email);
			helper.setText(content, true);
			helper.setSubject("Mail From Workout Generator");
			System.out.println("helper attributes set!");
		} catch (Exception e) {
			e.printStackTrace();
			System.out.println("ERROR IN sendMail!!");
		}
		System.out.println("sending message!");
		System.out.println(sender);
		sender.send(message);
		System.out.println("message sent!");
	}
	

}
