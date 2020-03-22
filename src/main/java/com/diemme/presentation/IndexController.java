package com.diemme.presentation;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Base64;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.ResponseBody;

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
		model.addAttribute("showcases", showcases);
		return "/frontoffice/home/home.html";
		
	}
	
	@GetMapping("/showcase/{id}")
	@ResponseBody
	public byte[] listNewsShowcases (@PathVariable Long id, Model model) throws BusinessException {
		Optional<NewsShowcase> showcase = service.findNewsShowcase(id);
		byte[] byteImage = showcase.get().getContentImg();
		return byteImage;
		
	}
}
