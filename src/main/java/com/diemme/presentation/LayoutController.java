package com.diemme.presentation;

import java.io.IOException;
import java.time.ZonedDateTime;
import java.util.ArrayList;
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
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;

import com.diemme.business.BusinessException;
import com.diemme.business.LayoutService;
import com.diemme.business.NewsService;
import com.diemme.business.UserService;
import com.diemme.component.PageModel;
import com.diemme.domain.mysql.FileLayout;
import com.diemme.domain.mysql.FormWrapperLayout;
import com.diemme.domain.mysql.Layout;
import com.diemme.domain.mysql.NewsShowcase;
import com.diemme.domain.mysql.Role;
import com.diemme.domain.mysql.User;
import com.diemme.repository.mysql.FileLayoutRepository;

@Controller
public class LayoutController {

	@Autowired
	private LayoutService serviceLayout;
	@Autowired
	private UserService serviceUser;
	@Autowired
	private FileLayoutRepository fileLayoutRepository;
	@Autowired
	private PageModel pageModel;

	@SuppressWarnings("static-access")
	@GetMapping("/layoutGestione")
	public String manageMyLayouts(Model model, Authentication auth) throws BusinessException {
		pageModel.setSIZE(5);
		pageModel.initPageAndSize();
		Page<Layout> layouts = serviceLayout.getAllLayoutPageable(pageModel.getPAGE(), pageModel.getSIZE());
		model.addAttribute("layouts", layouts);

		return "/backoffice/layoutDashboard/manage.html";

	}

	@GetMapping("/layoutCrea")
	public String createNewsShocases(Model model) throws BusinessException {
		FormWrapperLayout layoutWrapper = new FormWrapperLayout();
		Set<User> userClients = serviceUser.getUsersByRole("CLIENT");
		model.addAttribute("userClients", userClients);
		model.addAttribute("layoutWrapper", layoutWrapper);

		return "/backoffice/layoutDashboard/create.html";
	}

	@PostMapping("/layoutCrea")
	public ModelAndView create(@Valid @ModelAttribute("layoutWrapper") FormWrapperLayout layoutWrapper, Errors errors,
			@RequestParam(value = "contentImg") List<MultipartFile> contentImg, Authentication auth)
			throws BusinessException {

		int i = 1;
		User userAuth = new User();
		User userClient = new User();
		ModelAndView modelAndView = new ModelAndView();
		Set<FileLayout> file = new HashSet<FileLayout>();
		Layout layoutSave = new Layout();
		List<byte[]> Listbytes = new ArrayList<byte[]>();
		Set<User> usersLayout = new HashSet<User>();

		String username = auth.getName();
		try {
			userAuth = serviceUser.findUserByUserName(username);

		} catch (BusinessException e) {
			e.printStackTrace();

		}
		
		for(User user : layoutWrapper.getUserClient()) {
			usersLayout.add(user);
		}
		
		usersLayout.add(userAuth);		
		modelAndView.addObject("userClient", userClient);
		modelAndView.addObject("successMessage", "l'oggetto è stato creato!");
		modelAndView.setViewName("/backoffice/layoutDashboard/create.html");
		layoutSave.setName(layoutWrapper.getLayout().getName());
		layoutSave.setDescription(layoutWrapper.getLayout().getDescription());
		layoutSave.setCompleted(false);
		layoutSave.setUsers(usersLayout);
		layoutSave.setStatus(new String("in progress"));		
		System.out.println("\n users" + usersLayout);

		try {
			layoutSave = serviceLayout.saveLayout(layoutSave);
		} catch (BusinessException e) {
			e.printStackTrace();

		}

		try {
			for (MultipartFile bytes : contentImg) {
				Listbytes.add(bytes.getBytes());
			}
		} catch (IOException e) {
			e.printStackTrace();
		}

		for (byte[] fileContent : Listbytes) {
			
			FileLayout fileLayoutSave = new FileLayout();
			fileLayoutSave.setContentImg(fileContent);
			fileLayoutSave.setLayout(layoutSave);
			fileLayoutSave.setName((String)"layout: "+ layoutSave.getName() + ", file N° " + i);
			fileLayoutRepository.save(fileLayoutSave);
			i ++;


		}

		layoutSave.setFileLayouts(file);
		

		return modelAndView;
	}

	@PostMapping("/layoutDelete/{id}")
	public String deletelayout(@PathVariable(value = "id") Long id) throws BusinessException {
		try {
			serviceLayout.deleteLayout(id);
		} catch (BusinessException e) {
			e.printStackTrace();

		}
		return "redirect:/layoutGestione";

	}

	@GetMapping("/layoutUpdate")
	public String updateNewsShocases(Long id, Model model) throws BusinessException {
		Layout layout = new Layout();
		try {
			layout = serviceLayout.getLayout(id);
		} catch (BusinessException e) {
			e.printStackTrace();

		}
		model.addAttribute("layout", layout);
		return "/backoffice/layoutDashboard/update.html";
	}

	@PostMapping("/layoutUpdate/{id}")
	public String update(@PathVariable("id") Long id, @Valid @ModelAttribute("layout") Layout layout, Errors errors,
			@RequestParam("contentImg") MultipartFile[] contentImg) throws BusinessException {

		List<FileLayout> file = new ArrayList<FileLayout>();

		byte[] bytes = new byte[(int) contentImg.getSize()];
		Set<User> usersLayout = new HashSet<User>();

		try {

			bytes = contentImg.getBytes();

		} catch (IOException e) {
			e.printStackTrace();
		}

		Layout layoutOld = new Layout();
		Layout layoutSave = new Layout();

		try {
			layoutOld = serviceLayout.getLayout(id);
			usersLayout = serviceLayout.getAllUsersLayout(layoutOld.getId());

		} catch (BusinessException e) {
			e.printStackTrace();

		}
		layoutSave.setCompleted(layout.getCompleted());
		layoutSave.setDescription(layout.getDescription());
		layoutSave.setName(layout.getName());
		layoutSave.setUsers(usersLayout);
		layoutSave.setModifyDate(ZonedDateTime.now());

		ZonedDateTime dateCreation = layoutOld.getInsertDate();

		if (contentImg.isEmpty()) {

			layoutSave.setContentImg(layoutOld.getContentImg());

		} else {

			layoutSave.setContentImg(Listbytes);
		}

		try {
			layoutSave = serviceLayout.saveLayout(layout);
			layoutSave.setInsertDate(dateCreation);
			serviceLayout.saveLayout(layoutSave);
		} catch (BusinessException e) {
			e.printStackTrace();

		}

		return "redirect:/layoutGestione";
	}

}
