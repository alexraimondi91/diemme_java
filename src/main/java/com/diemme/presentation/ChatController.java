package com.diemme.presentation;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.diemme.business.BusinessException;
import com.diemme.business.ChatUserService;
import com.diemme.business.UserService;
import com.diemme.component.PageModel;
import com.diemme.domain.mongo.Chat;
import com.diemme.domain.mongo.Message;
import com.diemme.domain.mysql.ChatUser;
import com.diemme.domain.mysql.User;

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

	@DeleteMapping("/chatDelete/{id}/{idChatMongo}")
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

}
