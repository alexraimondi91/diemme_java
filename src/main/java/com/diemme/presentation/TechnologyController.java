package com.diemme.presentation;

import java.io.File;
import java.io.IOException;
import java.util.List;
import java.util.Optional;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;

import com.diemme.business.BusinessException;
import com.diemme.business.TechnologyService;
import com.diemme.domain.TechnologyShowcase;
import com.diemme.domain.User;

@Controller
public class TechnologyController {

	@Autowired
	private TechnologyService service;

	@GetMapping("/tecnologie")
	public String listTechnologyShocases(Model model) throws BusinessException {
		List<TechnologyShowcase> technologies = service.getAllTecnology();
		model.addAttribute("techno", technologies);
		return "/frontoffice/tecnologie/tecnologie.html";

	}

	@GetMapping("/tecnologie/image/{id}")
	@ResponseBody
	public byte[] getImage(@PathVariable Long id) throws BusinessException {
		Optional<TechnologyShowcase> product = service.getTecnology(id);
		byte[] imageProduct = product.get().getContentImg();
		return imageProduct;
	}
	
	@GetMapping("/tecnologieCrea")
	public String createTechnologyShocases(Model model) throws BusinessException {
		TechnologyShowcase technologyShowcase = new TechnologyShowcase();
		model.addAttribute("tecnology_showcase", technologyShowcase);
		return "/backoffice/technologyDashboard/create.html";
	}
	
	@PostMapping("/tecnologieCrea")
	public ModelAndView  create(@Valid @ModelAttribute("tecnology_showcase")  TechnologyShowcase technologies, Errors errors, @RequestParam("contentImg") MultipartFile contentImg) throws BusinessException{
        ModelAndView modelAndView = new ModelAndView();
		byte[] bytes = new byte[(int) contentImg.getSize()];
		try {
			bytes = contentImg.getBytes();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		modelAndView.addObject("successMessage", "l'oggetto è stato creato!");
        modelAndView.setViewName("/backoffice/technologyDashboard/create.html");


		technologies.setContentImg(bytes);
		System.out.println("\n\n\n technologies con file: "+ technologies);
		service.saveTechnology(technologies);
		return modelAndView;
	}
	
}
