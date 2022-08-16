package com.reservations.reservation.emails;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;

import lombok.extern.slf4j.Slf4j;

@Service
@Slf4j
public class SendEmail {

	@Autowired
	private JavaMailSender sendMail;
	
	public SendEmail (JavaMailSender sendmail) {
		this.sendMail = sendmail;
	}
	
	public void send (String to, String title, String content) {
		System.out.println("Login realizado com sucesso");
		System.out.println(to);
		
		var message = new SimpleMailMessage() ;
		
		message.setTo(to);
		message.setSubject(title);
		message.setText(content);
		sendMail.send(message);
		System.out.println("Email enviado com sucesso");
		
	}
}
