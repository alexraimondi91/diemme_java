package com.diemme.presentation;

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
import com.diemme.business.EmailService;
import com.diemme.domain.Contact;

@Controller
public class ContactController {
	
	@Autowired
	EmailService service;
	
	
	@GetMapping("/contatti")
	public String listNewsShowcases (Model model) throws BusinessException {
		
		 model.addAttribute("contact", new Contact());
		return "/frontoffice/contatti/contatti.html";
		
	}
	
	@PostMapping("/sendEmail")
	public String sendEmail(@Valid  @ModelAttribute("contact") Contact contact, BindingResult result, RedirectAttributes redirectAttributes) {
		String from = contact.getEmail();
		String object = contact.getObject();
		String body = contact.getText();
		String nameSender = contact.getName();		
		redirectAttributes.addFlashAttribute("message", "Qualcosa è andato storto...Riprova!");
	    redirectAttributes.addFlashAttribute("alertClass", "alert-danger");
	    
	    if (result.hasErrors()) {
	        return "redirect:/contatti";
	    }
	    
	    service.sendContact(from, object, body, nameSender);
	    redirectAttributes.addFlashAttribute("message", "Il messaggio è stato inviato correttamente!");
	    redirectAttributes.addFlashAttribute("alertClass", "alert-success");			
			
        return "redirect:/contatti";
			
       
    }
	

}
