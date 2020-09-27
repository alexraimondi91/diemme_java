package com.diemme.business;

import java.util.Set;

import org.springframework.data.domain.Page;

import com.diemme.domain.mongo.Chat;
import com.diemme.domain.mysql.ChatUser;

public interface ChatUserService {
	
	public Page<ChatUser> getAllUserChat (Integer page, Integer size, Long idUser);
	
	public Set<ChatUser> getAllChatUser (String idChatMongo);

	public Chat getChat(String idChatMongo);


}
