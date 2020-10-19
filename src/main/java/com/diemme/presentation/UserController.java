package com.diemme.presentation;

import java.time.ZonedDateTime;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.ModelAndView;

import com.diemme.business.BusinessException;
import com.diemme.business.EmailService;
import com.diemme.business.RoleService;
import com.diemme.business.UserService;
import com.diemme.component.PageModel;
import com.diemme.domain.mysql.Role;
import com.diemme.domain.mysql.User;

@Controller
public class UserController {

	@Autowired
	private UserService serviceUser;
	@Autowired
	private RoleService serviceRole;
	@Autowired
	private EmailService serviceEmail;
	@Autowired
	private PageModel pageModel;

	@GetMapping("/utenteCrea")
	public String createUser(Model model) throws BusinessException {
		User user = new User();
		List<Role> roles = serviceRole.getAllRoles();
		model.addAttribute("roles", roles);
		model.addAttribute("user", user);
		return "/backoffice/userManage/create.html";
	}

	@SuppressWarnings("static-access")
	@GetMapping("/utentiGestione")
	public String manageUsers(Model model) throws BusinessException {
		pageModel.setSIZE(5);
		pageModel.initPageAndSize();
		Page<User> users = serviceUser.getAllUserPageable(pageModel.getPAGE(), pageModel.getSIZE());
		model.addAttribute("users", users);
		pageModel.resetPAGE();
		return "/backoffice/userManage/manage.html";

	}

	@PostMapping("/utenteCrea")
	public ModelAndView create(@Valid @ModelAttribute("user") User user, BindingResult bindingResult)
			throws BusinessException {

		ModelAndView modelAndView = new ModelAndView();
		BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();

		User userExists = new User();
		try {
			userExists = serviceUser.findUserByUserName(user.getUserName());
		} catch (BusinessException e) {
			e.printStackTrace();
		}

		Set<Role> roles = new HashSet<Role>();
		roles.addAll(user.getRoles());
		user.setRoles(roles);
		user.setActive(true);
		user.setPassword(passwordEncoder.encode(user.getPassword()));
		user.setAddressShipment(user.getAddressShipment());
		user.setAddressShipment(user.getAddressShipment());
		user.setFiscalCode(user.getFiscalCode());
		user.setPIva(user.getPIva());
		user.setCompanyName(user.getCompanyName());

		if (userExists != null) {
			bindingResult.rejectValue("userName", "error.user",
					"Questo nome utente è già stato preso, riprova con un altro!");
		}
		if (bindingResult.hasErrors()) {
			modelAndView.setViewName("/backoffice/userManage/create.html");
		} else {
			try {
				serviceUser.saveUser(user);
			} catch (BusinessException e) {
				e.printStackTrace();
			}

			modelAndView.addObject("successMessage", "l'utente è stato creato!");
			modelAndView.setViewName("/backoffice/userManage/create.html");

		}

		return modelAndView;

	}

	@GetMapping("/utenteUpdate")
	public String updateUser(Long id, Model model) throws BusinessException {
		User userUpdate = new User();
		try {
			userUpdate = serviceUser.getUser(id);
		} catch (BusinessException e) {
			e.printStackTrace();

		}
		List<Role> roles = serviceRole.getAllRoles();
		model.addAttribute("roles", roles);
		model.addAttribute("userUpdate", userUpdate);
		return "/backoffice/userManage/update.html";
	}

	@PostMapping("/utenteUpdate/{id}")
	public ModelAndView updateUser(@PathVariable("id") Long id, @ModelAttribute("userUpdate") User userUpdate,
			BindingResult bindingResult) throws BusinessException {

		User userSave = new User();
		User userExists = new User();
		Boolean userActiveOld = true;
		ModelAndView modelAndView = new ModelAndView();
		try {
			userExists = serviceUser.getUser(id);
			userActiveOld = userExists.getActive();
	
			
		} catch (BusinessException e) {
			e.printStackTrace();

		}

		ZonedDateTime dateCreation = userExists.getInsertDate();		
		if(userUpdate.getRoles() != null) {
			Set<Role> roles = new HashSet<Role>();
			roles.addAll(userUpdate.getRoles());
			userUpdate.setRoles(roles);

		}	
		

		userUpdate.setActive(userUpdate.getActive());		
		userUpdate.setPassword(userExists.getPassword());
		userUpdate.setAddressShipment(userUpdate.getAddressShipment());
		userUpdate.setFiscalCode(userUpdate.getFiscalCode());
		userUpdate.setPIva(userUpdate.getPIva());
		userUpdate.setCompanyName(userUpdate.getCompanyName());		
		
		
		if (bindingResult.hasErrors()) {
			
			modelAndView.setViewName("/backoffice/userManage/update.html");
			
		} else {
			
			try {
				userSave = serviceUser.saveUser(userUpdate);
				userSave.setInsertDate(dateCreation);
				userSave.setModifyDate(ZonedDateTime.now());				
				serviceUser.saveUser(userSave);							

				if(userActiveOld == false && userUpdate.getActive() == true) {
					serviceEmail.sendUserActivated();
				}
				
				
			} catch (BusinessException e) {
				e.printStackTrace();
			}

			modelAndView.setViewName("redirect:/utentiGestione");

		}
		
		return modelAndView;
	}
	
	@PostMapping("/utentiDelete/{id}")
	public String deleteUser(@PathVariable(value = "id") Long id) throws BusinessException {
		try {
			serviceUser.deleteUser(id);
		} catch (BusinessException e) {
			e.printStackTrace();

		}
		return "redirect:/utentiGestione";

	}

}
