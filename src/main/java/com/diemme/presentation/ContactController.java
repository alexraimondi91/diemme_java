package com.diemme.presentation;

import java.util.List;
import java.util.Optional;

import javax.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.diemme.business.BusinessException;
import com.diemme.business.ContactService;
import com.diemme.business.EmailService;
import com.diemme.domain.Contact;
import com.diemme.domain.ContactShowcase;

@Controller
public class ContactController {
	
	@Autowired
	EmailService emailService;
	@Autowired
	ContactService contactService;
	
	
	@GetMapping("/contatti")
	public String listContactsShowcases (Model model) throws BusinessException {	
		Optional<ContactShowcase> ultimateContact = contactService.findUltimateContac();
		model.addAttribute("contact", new Contact());
		model.addAttribute("contacts", ultimateContact);
		
		System.out.println("\n \n \n" +  ultimateContact);
		return "/frontoffice/contatti/contatti.html";
		
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
	    
	    emailService.sendContact(from, object, body, nameSender);
	    redirectAttributes.addFlashAttribute("message", "Il messaggio è stato inviato correttamente!");
	    redirectAttributes.addFlashAttribute("alertClass", "alert-success");			
			
        return "redirect:/contatti";
			
       
    }
	

}
