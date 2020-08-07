package com.diemme.presentation;

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
import org.springframework.web.bind.annotation.ResponseBody;
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
		ModelAndView modelAndView = new ModelAndView();
		TechnologyShowcase technologyShowcase = new TechnologyShowcase();
        modelAndView.addObject("tecnology_showcase", technologyShowcase);
		return "/backoffice/technologyDashboard/create.html";
	}
	
	@PostMapping("/tecnologieCrea")
	public String  create(@Valid @ModelAttribute("createTechnology")  TechnologyShowcase technologies, Errors errors) throws BusinessException{
		return "/backoffice/technologyDashboard/create.html";
	}
	
}
