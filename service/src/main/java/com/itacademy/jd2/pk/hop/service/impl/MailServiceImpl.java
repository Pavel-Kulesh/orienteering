package com.itacademy.jd2.pk.hop.service.impl;

import java.util.Properties;

import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;

import org.springframework.stereotype.Service;

import com.itacademy.jd2.pk.hop.service.IMailSevice;

@Service
public class MailServiceImpl implements IMailSevice{
	
	
	
	public void sendEmail(String email) {
		Properties props = new Properties();
		props.put("mail.smtp.host", "smtp.gmail.com");
		props.put("mail.smtp.socketFactory.port", "465");
		props.put("mail.smtp.socketFactory.class", "javax.net.ssl.SSLSocketFactory");
		props.put("mail.smtp.auth", "true");
		props.put("mail.smtp.port", "465");

		Session session = Session.getDefaultInstance(props, new javax.mail.Authenticator() {
			protected PasswordAuthentication getPasswordAuthentication() {
				return new PasswordAuthentication("it.academy.test.mail@gmail.com", "10Hobbit16");
			}
		});

		try {

			Message message = new MimeMessage(session);
			message.setFrom(new InternetAddress("it.academy.test.mail@gmail.com"));
			message.setRecipients(Message.RecipientType.TO, InternetAddress.parse("kuper55@list.ru"));
			// message.setRecipients(Message.RecipientType.TO,
			// InternetAddress.parse(email));

			message.setSubject("Testing  Subject");
			message.setText("Dear Friend," + "\n\n u reg on Training Orienteering service!");

			Transport.send(message);

			System.out.println("Done");

		} catch (MessagingException e) {
			throw new RuntimeException(e);
		}
	}
}