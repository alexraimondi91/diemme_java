package com.diemme.presentation;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.ResponseBody;

import com.diemme.business.BusinessException;
import com.diemme.business.ProductService;
import com.diemme.business.TechnologyService;
import com.diemme.domain.ProductShowcase;
import com.diemme.domain.TechnologyShowcase;

@Controller
public class TechnologyController {
	
	@Autowired 
	private TechnologyService service;
	
	@GetMapping("/tecnologie")
	public String listTechnologyShocases (Model model) throws BusinessException{
		List<TechnologyShowcase> technologies = service.getAllTecnology();
		model.addAttribute("techno", technologies);
		return "/frontoffice/tecnologie/tecnologie.html";
		
	}
	
	@GetMapping("/tecnologie/image/{id}")
	@ResponseBody
	public byte[] getImage (@PathVariable Long id) throws BusinessException{
		
		Optional<TechnologyShowcase> product = service.getTecnology(id);
		byte[] imageProduct = product.get().getContentImg();
		return imageProduct;
	}

}
