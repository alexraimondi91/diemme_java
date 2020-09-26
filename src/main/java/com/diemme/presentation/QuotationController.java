package com.diemme.presentation;

import java.time.ZonedDateTime;
import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.security.core.Authentication;
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
import com.diemme.business.QuotationService;
import com.diemme.business.UserService;
import com.diemme.component.PageModel;
import com.diemme.domain.mysql.ContactShowcase;
import com.diemme.domain.mysql.QuotationShowcase;
import com.diemme.domain.mysql.User;

@Controller
public class QuotationController {

	@Autowired
	private QuotationService serviceQuotation;
	@Autowired
	private UserService serviceUser;
	@Autowired
	private PageModel pageModel;

	@GetMapping("/preventivi")
	public String listQuotationShowcase(Model model) throws BusinessException {
		List<QuotationShowcase> quotation = serviceQuotation.findAllQuotationShowcases();
		model.addAttribute("quotation", quotation);
		return "/frontoffice/preventivi/preventivi";

	}

	@SuppressWarnings("static-access")
	@GetMapping("/preventiviGestione")
	public String manageQuotationShocases(Model model) throws BusinessException {
		pageModel.setSIZE(5);
		pageModel.initPageAndSize();
		Page<QuotationShowcase> quotations = serviceQuotation.getAllQuotationPageable(pageModel.getPAGE(),
				pageModel.getSIZE());
		pageModel.resetPAGE();
		model.addAttribute("quot", quotations);
		return "/backoffice/quotationDashboard/manage.html";

	}

	@GetMapping("/preventiviCrea")
	public String createquotattionShocases(Model model) throws BusinessException {
		QuotationShowcase quotationShowcase = new QuotationShowcase();
		model.addAttribute("quotation_showcase", quotationShowcase);
		return "/backoffice/quotationDashboard/create.html";
	}

	@PostMapping("/preventiviCrea")
	public ModelAndView create(@Valid @ModelAttribute("quotation_showcase") QuotationShowcase quotation, Errors errors,
			Authentication auth) throws BusinessException {
		User userAuth = new User();
		ModelAndView modelAndView = new ModelAndView();
		String username = auth.getName();
		try {
			userAuth = serviceUser.findUserByUserName(username);
		} catch (BusinessException e) {
			e.printStackTrace();

		}

		quotation.setUser(userAuth);

		try {
			serviceQuotation.saveQuotation(quotation);
		} catch (BusinessException e) {
			e.printStackTrace();

		}
		modelAndView.addObject("successMessage", "l'oggetto è stato creato!");
		modelAndView.setViewName("/backoffice/quotationDashboard/create.html");

		return modelAndView;
	}

	@GetMapping("/preventiviUpdate")
	public String updateQuotationShocases(Long id, Model model) throws BusinessException {
		QuotationShowcase quotationShowcase = new QuotationShowcase();

		try {
			quotationShowcase = serviceQuotation.getQuotation(id);

		} catch (BusinessException e) {
			e.printStackTrace();

		}
		model.addAttribute("quotation_showcase_update", quotationShowcase);

		return "/backoffice/quotationDashboard/update.html";
	}

	@PostMapping("/preventiviUpdate/{id}")
	public String update(@PathVariable("id") Long id,
			@Valid @ModelAttribute("quotation_showcase_update") QuotationShowcase quotationShowcase, Errors errors,
			Authentication auth) throws BusinessException {
		User userAuth = new User();
		String username = auth.getName();
		QuotationShowcase quotationOld = new QuotationShowcase();

		
		try {
			quotationOld = serviceQuotation.getQuotation(id);
		} catch (BusinessException e) {
			e.printStackTrace();

		}
		ZonedDateTime dateCreation = quotationOld.getInsertDate();

		try {
			userAuth = serviceUser.findUserByUserName(username);
		} catch (BusinessException e) {
			e.printStackTrace();

		}
		quotationShowcase.setUser(userAuth);
		quotationShowcase.setInsertDate(dateCreation);
		quotationShowcase.setModifyDate(ZonedDateTime.now());

		try {
			serviceQuotation.saveQuotation(quotationShowcase);
		} catch (BusinessException e) {
			e.printStackTrace();

		}

		return "redirect:/preventiviGestione";
	}

	@PostMapping("/preventiviDelete/{id}")
	public String deleteQuotationShocases(@PathVariable(value = "id") Long id) throws BusinessException {
		try {
			serviceQuotation.deleteQuotation(id);
		} catch (BusinessException e) {
			e.printStackTrace();

		}
		return "redirect:/preventiviGestione";

	}
}
