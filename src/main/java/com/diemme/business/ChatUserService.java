package com.diemme.business;

import java.util.Set;

import org.springframework.data.domain.Page;

import com.diemme.domain.mongo.Chat;
import com.diemme.domain.mysql.ChatUser;

public interface ChatUserService {

	public Page<ChatUser> getAllUserChat(Integer page, Integer size, Long idUser) throws BusinessException;

	public Set<ChatUser> getAllChatUser(String idChatMongo) throws BusinessException;

	public Chat getChat(String idChatMongo) throws BusinessException;

	public void deleteChat(Long idChatUser, String idChatMongo) throws BusinessException;

	public void saveChat(ChatUser chatUser, Chat chat, ChatUser chatUser2) throws BusinessException;


}
