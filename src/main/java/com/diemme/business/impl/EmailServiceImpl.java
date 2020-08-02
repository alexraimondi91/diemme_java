package com.diemme.business.impl;

import java.util.Properties;

import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;

import org.springframework.stereotype.Service;

import com.diemme.business.EmailService;

@Service
public class EmailServiceImpl implements EmailService {

	@Override
	public void sendContact(String from, String object, String body, String sender) {

		try {

			Message message = new MimeMessage(connect());
			message.setFrom(new InternetAddress(from));
			message.setRecipients(Message.RecipientType.TO, InternetAddress.parse("alexraimondi91@gmail.com"));
			message.setSubject("Gentile staff Diemme: " + object);
			message.setText(body + "\n\n Cordiali saluti,\n " + sender + "\n\n Mail from: " + from);

			Transport.send(message);

			System.out.println("Done");

		} catch (MessagingException e) {
			throw new RuntimeException(e);
		}
	}
	
	@Override
	public void sendUserActive(String from, String sender) {

		try {

			Message message = new MimeMessage(connect());
			message.setFrom(new InternetAddress(from));
			message.setRecipients(Message.RecipientType.TO, InternetAddress.parse("alexraimondi91@gmail.com"));
			message.setSubject("Gentile staff Diemme: hai un nuovo cliente da approvare!");
			message.setText("Il cliente: " + sender + " ha richiesto l'accesso alla piattaforma Diemme" 
							+ "\n\n Cordiali saluti,\n " + "\n\n Mail user: " + from);

			Transport.send(message);

			System.out.println("Done");

		} catch (MessagingException e) {
			throw new RuntimeException(e);
		}
	}

	private Session connect() {

		final String username = "ff7a3c6cdf3464";
		final String password = "0088c1ba3e0a62";

		Properties props = new Properties();
		props.put("mail.smtp.auth", "true");
		props.put("mail.smtp.starttls.enable", "true");// it’s optional in Mailtrap
		props.put("mail.smtp.host", "smtp.mailtrap.io");
		props.put("mail.smtp.port", "2525");// use one of the options in the SMTP settings tab in your Mailtrap Inbox

		Session session = Session.getInstance(props, new javax.mail.Authenticator() {
			protected PasswordAuthentication getPasswordAuthentication() {
				return new PasswordAuthentication(username, password);
			}
		});

		return session;

	}

}
