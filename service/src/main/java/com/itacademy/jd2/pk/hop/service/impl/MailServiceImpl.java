package com.itacademy.jd2.pk.hop.service.impl;

import java.util.Properties;

import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.itacademy.jd2.pk.hop.service.IMailService;
import com.itacademy.jd2.pk.hop.service.jndi.SmtpProperties;

@Service
public class MailServiceImpl implements IMailService {
	@Autowired
	private SmtpProperties smtpPropertiesHolder;
	private static final Logger LOGGER = LoggerFactory.getLogger(MailServiceImpl.class);

	public void sendEmail(String email) {

		Properties props = new Properties();
		props.put("mail.smtp.host", smtpPropertiesHolder.getHost());
		props.put("mail.smtp.socketFactory.port", smtpPropertiesHolder.getPort());
		props.put("mail.smtp.socketFactory.class", "javax.net.ssl.SSLSocketFactory");
		props.put("mail.smtp.auth", "true");
		props.put("mail.smtp.port", smtpPropertiesHolder.getPort());

		Session session = Session.getDefaultInstance(props, new javax.mail.Authenticator() {
			protected PasswordAuthentication getPasswordAuthentication() {
				return new PasswordAuthentication(smtpPropertiesHolder.getUser(), smtpPropertiesHolder.getPassword());
			}
		});

		try {

			Message message = new MimeMessage(session);
			message.setFrom(new InternetAddress(smtpPropertiesHolder.getFrom()));
			message.setRecipients(Message.RecipientType.TO, InternetAddress.parse("kuper55@list.ru"));
			// message.setRecipients(Message.RecipientType.TO,
			// InternetAddress.parse(email));

			message.setSubject("Testing  Subject");
			message.setText("Dear Friend," + "\n\n u reg on Training Orienteering service!");

			Transport.send(message);

			LOGGER.info("send mail-ok");

		} catch (MessagingException e) {
			throw new RuntimeException(e);
		}
	}
}