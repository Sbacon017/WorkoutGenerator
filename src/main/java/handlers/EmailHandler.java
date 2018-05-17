package handlers;

import javax.mail.MessagingException;
import javax.mail.internet.MimeMessage;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Component;
import org.springframework.mail.javamail.MimeMessageHelper;

@Component
public class EmailHandler{
	

	
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
