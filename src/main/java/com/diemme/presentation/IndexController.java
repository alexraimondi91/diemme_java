package com.diemme.presentation;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.diemme.business.BusinessException;
import com.diemme.business.IndexService;
import com.diemme.domain.NewsShowcase;


@Controller
public class IndexController {
	
	@Autowired 
	private IndexService service;
	
	@GetMapping("/")
	public String listNewsShowcases (Model model) throws BusinessException {
		List<NewsShowcase> showcases = service.findAllNewsShowcases();
		System.out.println(showcases);
		model.addAttribute("showcases", showcases);
		return "index.html";
		
	}
	

}
