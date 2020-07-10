package com.diemme.presentation;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.diemme.business.BusinessException;
import com.diemme.business.QuotationService;
import com.diemme.domain.QuotationShowcase;

@Controller
public class QuotationController {

	@Autowired
	private QuotationService service;

	@GetMapping("/preventivi")
	public String listQuotationShowcase(Model model) throws BusinessException {
		List<QuotationShowcase> quotation = service.findAllQuotationShowcases();
		
		System.out.println("quotation: " + new ModelAndView("frontoffice/preventivi/preventivi"));
		model.addAttribute("quotation", quotation);
		return "/frontoffice/preventivi/preventivi";

	}
}
