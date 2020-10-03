package com.diemme.presentation;

import java.io.IOException;
import java.time.LocalDateTime;
import java.time.ZonedDateTime;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;

import com.diemme.business.BusinessException;
import com.diemme.business.ChatUserService;
import com.diemme.business.UserService;
import com.diemme.component.PageModel;
import com.diemme.domain.mongo.Chat;
import com.diemme.domain.mongo.ChatType;
import com.diemme.domain.mongo.Message;
import com.diemme.domain.mysql.ChatUser;
import com.diemme.domain.mysql.Role;
import com.diemme.domain.mysql.User;
import com.diemme.wrapperForm.FormWrapperChat;
import com.diemme.wrapperForm.FormWrapperLayout;

@Controller
public class ChatController {

	@Autowired
	private ChatUserService chatUserService;

	@Autowired
	private UserService serviceUser;
	@Autowired
	private PageModel pageModel;

	@SuppressWarnings("static-access")
	@GetMapping("/chatGestione")
	public String manageMyLayouts(Model model, Authentication auth) throws BusinessException {

		User userAuth = new User();
		String username = auth.getName();
		try {
			userAuth = serviceUser.findUserByUserName(username);

		} catch (BusinessException e) {
			e.printStackTrace();

		}

		pageModel.initPageAndSize();
		pageModel.setSIZE(5);
		Page<ChatUser> chatsUser = chatUserService.getAllUserChat(pageModel.getPAGE(), pageModel.getSIZE(),
				userAuth.getId());
		model.addAttribute("chatsUser", chatsUser);
		pageModel.resetPAGE();
		return "/backoffice/chatDashboard/manage.html";

	}

	@GetMapping("/chat/{id}")
	@ResponseBody
	public Chat getChat(@PathVariable("id") String id) {
		Chat chat = new Chat();
		try {
			chat = chatUserService.getChat(id);
		} catch (BusinessException e) {
			e.printStackTrace();
		}
		return chat;
	}

	@PostMapping("/chatDelete/{id}/{idChatMongo}")
	public String deletelayout(@PathVariable(value = "id") Long id,
			@PathVariable(value = "idChatMongo") String idChatMongo) throws BusinessException {
		try {
			chatUserService.deleteChat(id, idChatMongo);
		} catch (BusinessException e) {
			e.printStackTrace();

		}
		return "redirect:/chatGestione";

	}

	@GetMapping("/chatVisione")
	public String getChatView(String id, Model model, Authentication auth) {
		User userAuth = new User();
		String username = auth.getName();
		try {
			userAuth = serviceUser.findUserByUserName(username);

		} catch (BusinessException e) {
			e.printStackTrace();

		}

		Long idUser = userAuth.getId();
		model.addAttribute("auth", idUser);
		model.addAttribute("idChat", id);
		return "/backoffice/chatDashboard/chat.html";

	}

	@GetMapping("/chatFile/{idChatMongo}/{index}")
	@ResponseBody
	public byte[] getImageChat(@PathVariable("idChatMongo") String idChatMongo, @PathVariable("index") int index) {
		Chat chat = new Chat();
		Message message = new Message();
		try {
			chat = chatUserService.getChat(idChatMongo);

		} catch (BusinessException e) {
			e.printStackTrace();

		}

		if (!chat.getMessages().isEmpty() && chat.getMessages() != null) {
			Message[] messages = chat.getMessages().toArray(new Message[chat.getMessages().size()]);

			for (int i = 0; i < messages.length; i++) {

				if (i == index) {
					message = messages[i];
					byte[] contentImg = message.getFile();
					return contentImg;
				}
			}
		}

		return null;

	}

	@GetMapping("/chatCrea")
	public String createNewsShocases(Model model, Authentication auth) throws BusinessException {
		FormWrapperChat formWrapperChat = new FormWrapperChat();
		User userAuth = new User();
		String typeChat = new String();
		Set<User> userRole = new HashSet<User>();
		String userName = auth.getName();

		try {
			userAuth = serviceUser.findUserByUserName(userName);

		} catch (BusinessException e) {
			e.printStackTrace();

		}
		for (Role role : userAuth.getRoles()) {

			if (role.getRole().equals("CLIENT")) {

				userRole = serviceUser.getUsersByRole("DESIGNER");
				typeChat = "un Designer!";

			} else if (role.getRole().equals("DESIGNER")) {

				userRole = serviceUser.getUsersByRole("PRODUCTOR");
				typeChat = "un Produttore!";

			}

		}
		model.addAttribute("typeChat", typeChat);
		model.addAttribute("userRole", userRole);
		model.addAttribute("formWrapperChat", formWrapperChat);

		return "/backoffice/chatDashboard/create.html";
	}

	@PostMapping("/chatCrea")
	@ResponseBody
	public ModelAndView createChat(Authentication auth,
			@Valid @ModelAttribute("formWrapperChat") FormWrapperChat formWrapperChat, Errors errors,
			@RequestParam(value = "contentImg") MultipartFile contentImg) throws BusinessException {

		Chat chatSave = new Chat();
		ChatUser chatUser1 = new ChatUser();
		ChatUser chatUser2 = new ChatUser();
		User userAuth = new User();
		Message message = new Message();
		String nameuser = new String();
		Set<Message> messageList = new HashSet<Message>();
		ModelAndView modelAndView = new ModelAndView();
		byte[] bytes = new byte[(int) contentImg.getSize()];
		String username = auth.getName();

		try {
			userAuth = serviceUser.findUserByUserName(username);

		} catch (BusinessException e) {
			e.printStackTrace();

		}

		nameuser = userAuth.getName() + " " + userAuth.getSurname();

		try {
			bytes = contentImg.getBytes();
		} catch (IOException e) {
			e.printStackTrace();
		}

		for (Role role : userAuth.getRoles()) {
			if (role.getRole().equals("CLIENT")) {
				chatUser1.setUser(userAuth);
				chatUser1.setNameProject(formWrapperChat.getNameProject());
				chatUser2.setUser(formWrapperChat.getUser());
				chatUser2.setNameProject(formWrapperChat.getNameProject());
				chatSave.setChatType(ChatType.CLIENT_DESIGNER);
			} else if (role.getRole().equals("DESIGNER")) {
				chatUser1.setUser(userAuth);
				chatUser1.setNameProject(formWrapperChat.getNameProject());
				chatUser2.setUser(formWrapperChat.getUser());
				chatUser2.setNameProject(formWrapperChat.getNameProject());
				chatSave.setChatType(ChatType.DESIGNER_PRODUCTOR);

			}
		}

		modelAndView.addObject("successMessage", "l'oggetto è stato creato!");
		modelAndView.setViewName("/backoffice/chatDashboard/create.html");
		message.setMessage(formWrapperChat.getMessage());
		message.setFile(bytes);
		message.setIdUser(userAuth.getId());
		message.setName(nameuser);
		message.setDate(LocalDateTime.now());
		messageList.add(message);
		chatSave.setMessages(messageList);

		try {
			chatUserService.saveNewChat(chatUser1, chatSave, chatUser2);
		} catch (BusinessException e) {
			e.printStackTrace();

		}

		return modelAndView;

	}

	@SuppressWarnings("null")
	@PostMapping("/chatUpdate/{id}")
	@ResponseBody
	public String updateChat(Authentication auth, String message,
			@RequestParam(value = "contentImg", required = false) MultipartFile contentImg,
			@PathVariable("id") String id) {

		Chat chatUpdate = new Chat();
		User userAuth = new User();
		Message messageSave = new Message();
		Set<Message> messageList = new HashSet<Message>();
		String username = auth.getName();
		String nameuser = new String();

		try {
			userAuth = serviceUser.findUserByUserName(username);

		} catch (BusinessException e) {
			e.printStackTrace();

		}

		nameuser = userAuth.getName() + " " + userAuth.getSurname();

		try {
			chatUpdate = chatUserService.getChat(id);

		} catch (BusinessException e) {
			e.printStackTrace();

		}

		messageSave.setDate(LocalDateTime.now());
		messageSave.setIdChat(chatUpdate.getId());
		messageSave.setIdUser(userAuth.getId());
		messageSave.setName(nameuser);
		messageSave.setMessage(message);
		

		if (contentImg != null) {

			if (!contentImg.isEmpty()) {

				byte[] bytes = new byte[(int) contentImg.getSize()];
				messageSave.setFile(bytes);
			}

		}


		for (

		Message messaggi : chatUpdate.getMessages()) {
			
			messaggi.setIdChat(id);
			messageList.add(messaggi);

		}
		


		messageList.add(messageSave);
		
		System.out.println("\n\n\n messageList " + messageList);

		chatUpdate.setChatType(chatUpdate.getChatType());
		chatUpdate.setId(id);
		chatUpdate.setMessages(messageList);
		
		




		try {
			chatUpdate = chatUserService.saveUpdateChat(chatUpdate);

		} catch (BusinessException e) {
			e.printStackTrace();

		}

		return "redirect:/chatVisione";

	}

}
