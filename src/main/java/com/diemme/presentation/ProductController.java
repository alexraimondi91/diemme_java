package com.diemme.presentation;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import com.diemme.business.BusinessException;
import com.diemme.business.ProductService;
import com.diemme.domain.ProductShowcase;

@Controller
public class ProductController {

	@Autowired 
	private ProductService service;
	
	@GetMapping("/prodotti")
	public String listProductShocase (Model model) throws BusinessException{
		List<ProductShowcase> products = service.findAllProductShowcases();
		model.addAttribute("prods", products);
		return "/frontoffice/prodotti/prodotti.html";
		
	}
}
