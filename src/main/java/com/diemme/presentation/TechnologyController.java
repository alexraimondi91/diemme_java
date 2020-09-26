package com.diemme.presentation;

import java.io.IOException;
import java.time.ZonedDateTime;
import java.util.HashSet;
import java.util.List;
import java.util.Optional;
import java.util.Set;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.DeleteMapping;
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
import com.diemme.business.UserService;
import com.diemme.component.PageModel;
import com.diemme.domain.mysql.TechnologyShowcase;
import com.diemme.domain.mysql.User;

@Controller
public class TechnologyController {

	@Autowired
	private TechnologyService serviceTecnology;
	@Autowired
	private UserService serviceUser;
	@Autowired
	private PageModel pageModel;

	private com.diemme.util.CompressionUtils CompressionUtils;

	@GetMapping("/tecnologie")
	public String listTechnologyShocases(Model model) throws BusinessException {
		List<TechnologyShowcase> technologies = serviceTecnology.getAllTecnology();
		model.addAttribute("techno", technologies);
		return "/frontoffice/tecnologie/tecnologie.html";

	}

	@SuppressWarnings("static-access")
	@GetMapping("/tecnologieGestione")
	public String manageTechnologyShocases(Model model) throws BusinessException {
		pageModel.setSIZE(5);
		pageModel.initPageAndSize();

		Page<TechnologyShowcase> technologies = serviceTecnology.getAllTecnologyPageable(pageModel.getPAGE(),
				pageModel.getSIZE());
		pageModel.resetPAGE();
		model.addAttribute("techno", technologies);
		return "/backoffice/technologyDashboard/manage.html";

	}

	@GetMapping("/tecnologie/image/{id}")
	@ResponseBody
	public byte[] getImage(@PathVariable Long id) throws BusinessException {
		TechnologyShowcase product = serviceTecnology.getTecnology(id);
		byte[] imageProduct = product.getContentImg();
		return imageProduct;
	}

	@GetMapping("/tecnologieCrea")
	public String createTechnologyShocases(Model model) throws BusinessException {
		TechnologyShowcase technologyShowcase = new TechnologyShowcase();
		model.addAttribute("tecnology_showcase", technologyShowcase);
		return "/backoffice/technologyDashboard/create.html";
	}

	@SuppressWarnings("static-access")
	@PostMapping("/tecnologieCrea")
	public ModelAndView create(@Valid @ModelAttribute("tecnology_showcase") TechnologyShowcase technologies,
			Errors errors, @RequestParam("contentImg") MultipartFile contentImg, Authentication auth)
			throws BusinessException {
		User userAuth = new User();
		ModelAndView modelAndView = new ModelAndView();
		byte[] bytes = new byte[(int) contentImg.getSize()];
		byte[] byteCompress = new byte[0];

		try {
			byteCompress = CompressionUtils.compress(bytes);
			bytes = contentImg.getBytes();
		} catch (IOException e) {
			e.printStackTrace();
		}
		String username = auth.getName();
		try {
			userAuth = serviceUser.findUserByUserName(username);
		} catch (BusinessException e) {
			e.printStackTrace();

		}
		modelAndView.addObject("successMessage", "l'oggetto è stato creato!");
		modelAndView.setViewName("/backoffice/technologyDashboard/create.html");
		technologies.setContentImg(bytes);
		technologies.setCompressImg(byteCompress);
		technologies.setUser(userAuth);
		try {
			serviceTecnology.saveTechnology(technologies);
		} catch (BusinessException e) {
			e.printStackTrace();

		}

		return modelAndView;
	}

	@PostMapping("/tecnologieDelete/{id}")
	public String createTechnologyShocases(@PathVariable(value = "id") Long id) throws BusinessException {
		try {
			serviceTecnology.deleteTechnology(id);
		} catch (BusinessException e) {
			e.printStackTrace();

		}
		return "redirect:/tecnologieGestione";

	}

	@GetMapping("/tecnologieUpdate")
	public String updateTechnologyShocases(Long id, Model model) throws BusinessException {
		TechnologyShowcase technologyShowcase = new TechnologyShowcase();
		try {
			technologyShowcase = serviceTecnology.getTecnology(id);
		} catch (BusinessException e) {
			e.printStackTrace();

		}
		model.addAttribute("tecnology_showcase_update", technologyShowcase);
		return "/backoffice/technologyDashboard/update.html";
	}

	@SuppressWarnings("static-access")
	@PostMapping("/tecnologieUpdate/{id}")
	public String update(@PathVariable("id") Long id,
			@Valid @ModelAttribute("tecnology_showcase_update") TechnologyShowcase technologies, Errors errors,
			@RequestParam("contentImg") MultipartFile contentImg, Authentication auth) throws BusinessException {
		byte[] bytes = new byte[(int) contentImg.getSize()];
		byte[] byteCompress = new byte[0];
		TechnologyShowcase technologyOld = new TechnologyShowcase();
		TechnologyShowcase technologySave = new TechnologyShowcase();
		User userAuth = new User();


		try {
			technologyOld = serviceTecnology.getTecnology(id);
			
		} catch (BusinessException e) {
			e.printStackTrace();

		}

		ZonedDateTime dateCreation = technologyOld.getInsertDate();


		if (contentImg.isEmpty()) {
			technologies.setContentImg(technologyOld.getContentImg());
			technologies.setCompressImg(technologyOld.getCompressImg());
			technologies.setModifyDate(ZonedDateTime.now());

		} else {
			try {
				byteCompress = CompressionUtils.compress(bytes);
				bytes = contentImg.getBytes();
			} catch (IOException e) {
				e.printStackTrace();
			}
			technologies.setContentImg(bytes);
			technologies.setCompressImg(byteCompress);
		}
		String username = auth.getName();

		try {
			userAuth = serviceUser.findUserByUserName(username);
		} catch (BusinessException e) {
			e.printStackTrace();

		}
		technologies.setUser(userAuth);
		try {
			technologySave = serviceTecnology.saveTechnology(technologies);
			technologySave.setInsertDate(dateCreation);
			serviceTecnology.saveTechnology(technologySave);
		} catch (BusinessException e) {
			e.printStackTrace();

		}
		
		

		return "redirect:/tecnologieGestione";
	}

}
