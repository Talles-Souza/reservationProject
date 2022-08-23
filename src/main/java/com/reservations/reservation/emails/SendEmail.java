package com.reservations.reservation.emails;

import javax.mail.internet.MimeMessage;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.JavaMailSenderImpl;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Service;

import lombok.extern.slf4j.Slf4j;

@Service
@Slf4j
public class SendEmail {

	@Autowired
	private JavaMailSender sendMail;

	public SendEmail(JavaMailSender sendmail) {
		this.sendMail = sendmail;
	}

	public void send(String to, String title, String content) {
		System.out.println("Login realizado com sucesso");
		System.out.println(to);

		var message = new SimpleMailMessage();

		message.setTo(to);
		message.setSubject(title);
		message.setText(content);
		sendMail.send(message);
		System.out.println("Email enviado com sucesso");

	}

	public void sendHtmlMail(String to, String title, String content) throws Exception {
		MimeMessage mimeMessage = sendMail.createMimeMessage();
		MimeMessageHelper helper = new MimeMessageHelper(mimeMessage, true);
		try {
			helper.setTo(to);
			helper.setSubject(title);
			StringBuilder sBuilder = new StringBuilder();
			sBuilder.append("<html>\r\n");
			sBuilder.append("	<body>\r\n");
			sBuilder.append("		<div align=\"center\">\r\n");
			sBuilder.append("			E-MAIL NO FORMATO HTML\r\n");
			sBuilder.append("		</div>\r\n");
			sBuilder.append("		<br/>\r\n");
			sBuilder.append("		<center>\r\n");
			sBuilder.append("<div>" + content + "</div>");
			sBuilder.append("		</center>\r\n");
			sBuilder.append("	</body>\r\n");
			sBuilder.append("</html>");
			helper.setText(sBuilder.toString(), true);
			sendMail.send(mimeMessage);
			System.out.println("Email enviado com sucesso");

		} catch (Exception e) {
			throw new Exception("Erro ao enviar o email - " + e.getMessage());
		}
	}
}
