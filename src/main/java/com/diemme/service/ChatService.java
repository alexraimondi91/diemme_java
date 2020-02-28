package com.diemme.service;

import java.time.ZonedDateTime;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.diemme.ResourceNotFoundException;
import com.diemme.customType.MessageID;
import com.diemme.model.Chat;
import com.diemme.model.Message;
import com.diemme.repository.JpaRepository.ChatRepository;
import com.diemme.repository.JpaRepository.UserChatRepository;
import com.diemme.repository.JpaRepository.UserRepository;

@Service
public class ChatService {

	@Autowired
	private ChatRepository chatRepository;


	@Autowired
	private UserChatRepository userChatRepository;

	

	public List<Chat> getAll() {

		return chatRepository.findAll();
	}

	public Chat getChat(Long id) {

		return chatRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException("Chat", "id", id));
	}

	@Transactional(rollbackFor = Exception.class)
	public void addChat(Chat chat) {
		
			
		Set<Message> userChatList = chat.getMessage();
		chat.setMessage(new HashSet<>());
		Chat chatSaved = chatRepository.save(chat); 
		
		for(Message userchat : userChatList) {
			userchat.setId(new MessageID (chatSaved.getId(), userchat.getId().getUserId()));	
			userChatRepository.save(userchat);
		}
		
		
	}

	@Transactional(rollbackFor = Exception.class)
	public Chat updateChat(Chat chat, Long id) {

		if (chatRepository.existsById(id)) {
			
			chat.setId(id);

		
			return chatRepository.save(chat);

		}
		throw new ResourceNotFoundException("Chat", "id", chat.getId());
	}

	@Transactional(rollbackFor = Exception.class)
	public void deleteChat(Chat chat) {

		chatRepository.delete(chat);
	}

}
