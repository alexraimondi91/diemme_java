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
import com.diemme.domain.ProductShowcase;

@Controller
public class ProductController {

	@Autowired 
	private ProductService service;
	
	@GetMapping("/prodotti")
	public String listProductShowcase (Model model) throws BusinessException{
		List<ProductShowcase> products = service.findAllProductShowcases();
		model.addAttribute("prods", products);
		return "/frontoffice/prodotti/prodotti.html";
		
	}
	
	@GetMapping("/prodotti/image/{id}")
	@ResponseBody
	public byte[] getImage (@PathVariable Long id) throws BusinessException{
		
		Optional<ProductShowcase> product = service.findProductShowcase(id);
		byte[] imageProduct = product.get().getContentImg();
		return imageProduct;
	}
}
