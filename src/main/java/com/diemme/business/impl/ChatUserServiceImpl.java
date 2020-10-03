package com.diemme.business.impl;

import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

import com.diemme.ResourceNotFoundException;
import com.diemme.business.BusinessException;
import com.diemme.business.ChatUserService;
import com.diemme.domain.mongo.Chat;
import com.diemme.domain.mysql.ChatUser;
import com.diemme.repository.mongo.ChatRepository;
import com.diemme.repository.mysql.ChatUserRepository;

@Service
public class ChatUserServiceImpl implements ChatUserService {

	@Autowired
	private ChatUserRepository chatUserRepository;

	@Autowired
	private ChatRepository chatRepository;

	@Override
	public Page<ChatUser> getAllUserChat(Integer page, Integer size, Long idUser) throws BusinessException {
		return chatUserRepository.findIdChatMongoDb(PageRequest.of(page, size), idUser);
	}

	@Override
	public Set<ChatUser> getAllChatUser(String idChatMongo) throws BusinessException {
		return chatUserRepository.findUserChatMongoDb(idChatMongo);
	}

	@Override
	public Chat getChat(String idChatMongo) {
		return chatRepository.findById(idChatMongo)
				.orElseThrow(() -> new ResourceNotFoundException("FileLayout", "id", idChatMongo));
	}

	@Override
	public void deleteChat(Long idChatUser, String idChatMongo) throws BusinessException {
		
		chatUserRepository.deleteUserChatMongoDb(idChatMongo);		
		chatRepository.deleteById(idChatMongo);
	}
	
	@Override
	public void saveChat(ChatUser chatUser, Chat chat, ChatUser chatUser2) throws BusinessException {
	    chat = chatRepository.insert(chat);		
		chatUser.setIdChatMongo(chat.getId());
		chatUserRepository.save(chatUser);
		chatUser2.setIdChatMongo(chat.getId());
		chatUserRepository.save(chatUser2);		
	}

	
}
