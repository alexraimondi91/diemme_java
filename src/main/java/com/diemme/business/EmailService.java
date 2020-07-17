package com.diemme.business;


public interface EmailService {
	
	public void sendContact(String from, String subject, String body, String sender) throws RuntimeException;	
	
	public void sendUserActive(String from, String sender) throws RuntimeException;		


}
