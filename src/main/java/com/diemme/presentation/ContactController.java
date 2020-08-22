package com.diemme.presentation;

import java.io.IOException;
import java.util.List;
import java.util.Optional;

import javax.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.diemme.business.BusinessException;
import com.diemme.business.ContactService;
import com.diemme.business.EmailService;
import com.diemme.business.UserService;
import com.diemme.component.PageModel;
import com.diemme.domain.Contact;
import com.diemme.domain.ContactShowcase;
import com.diemme.domain.ProductShowcase;
import com.diemme.domain.User;

@Controller
public class ContactController {
	
	@Autowired
	EmailService serviceEmail;
	@Autowired
	ContactService serviceContact;
	@Autowired
	private UserService serviceUser;
	@Autowired
	private PageModel pageModel;
	
	@GetMapping("/contatti")
	public String listContactsShowcases (Model model) throws BusinessException {	
		
		Optional<ContactShowcase> ultimateContact = serviceContact.findUltimateContac();
		model.addAttribute("contact", new Contact());
		model.addAttribute("contacts", ultimateContact);		
		return "/frontoffice/contatti/contatti.html";
		
	}
	
	@SuppressWarnings("static-access")
	@GetMapping("/contattiGestione")
	public String manageContactShocases(Model model) throws BusinessException {
		pageModel.setSIZE(5);
		pageModel.initPageAndSize();
		Optional<ContactShowcase> ultimateContact = serviceContact.findUltimateContac();
		Page<ContactShowcase> contacts = serviceContact.getAllContactPageable(pageModel.getPAGE(),
				pageModel.getSIZE());
		
		model.addAttribute("cont", contacts);
		return "/backoffice/contactDashboard/manage.html";

	}
	
	@GetMapping("/contattiCrea")
	public String createProductShocases(Model model) throws BusinessException {
		ContactShowcase contactShowcase = new ContactShowcase();
		model.addAttribute("contact_showcase", contactShowcase);
		return "/backoffice/contactDashboard/create.html";
	}
	
	@PostMapping("/contattiCrea")
	public ModelAndView create(@Valid @ModelAttribute("contact_showcase") ContactShowcase contact,
			Errors errors, Authentication auth)
			throws BusinessException {
		User userAuth = new User();
		ModelAndView modelAndView = new ModelAndView();
		String username = auth.getName();
		try {
			userAuth = serviceUser.findUserByUserName(username);
		} catch (BusinessException e) {
			e.printStackTrace();

		}
		modelAndView.addObject("successMessage", "l'oggetto è stato creato!");
		modelAndView.setViewName("/backoffice/contactDashboard/create.html");
		contact.setUser(userAuth);
		contact.setActive(false);
		try {
			serviceContact.saveContactShowcase(contact);
		} catch (BusinessException e) {
			e.printStackTrace();

		}

		return modelAndView;
	}

	
	@PostMapping("/sendEmail")
	public String sendEmail(@Valid  @ModelAttribute("contact") Contact contact, BindingResult result, RedirectAttributes redirectAttributes)throws BusinessException {
		String from = contact.getEmail();
		String object = contact.getObject();
		String body = contact.getText();
		String nameSender = contact.getName();		
		redirectAttributes.addFlashAttribute("message", "Qualcosa è andato storto...Riprova!");
	    redirectAttributes.addFlashAttribute("alertClass", "alert-danger");
	    
	    if (result.hasErrors()) {
	        return "redirect:/contatti";
	    }
	    
	    serviceEmail.sendContact(from, object, body, nameSender);
	    redirectAttributes.addFlashAttribute("message", "Il messaggio è stato inviato correttamente!");
	    redirectAttributes.addFlashAttribute("alertClass", "alert-success");			
			
        return "redirect:/contatti";
			
       
    }
	

}
