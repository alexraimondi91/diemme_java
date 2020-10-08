package com.diemme.presentation;

import java.io.IOException;
import java.time.ZonedDateTime;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Optional;
import java.util.Set;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.CacheManager;
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
import com.diemme.business.FileLayoutService;
import com.diemme.business.LayoutService;
import com.diemme.business.NewsService;
import com.diemme.business.UserService;
import com.diemme.component.PageModel;
import com.diemme.domain.mysql.FileLayout;
import com.diemme.domain.mysql.Layout;
import com.diemme.domain.mysql.NewsShowcase;
import com.diemme.domain.mysql.Role;
import com.diemme.domain.mysql.StatusType;
import com.diemme.domain.mysql.User;
import com.diemme.repository.mysql.FileLayoutRepository;
import com.diemme.wrapperForm.FormWrapperLayout;

@Controller
public class LayoutController {

	@Autowired
	private LayoutService serviceLayout;
	@Autowired
	private UserService serviceUser;
	@Autowired
	private FileLayoutService fileLayoutService;
	@Autowired
	private PageModel pageModel;


	@SuppressWarnings("static-access")
	@GetMapping("/layoutGestione")
	public String manageMyLayouts(Model model ) throws BusinessException {
		
		pageModel.initPageAndSize();
		pageModel.setSIZE(5);
		Page<Layout> layouts = serviceLayout.getAllLayoutPageable(pageModel.getPAGE(), pageModel.getSIZE());
		model.addAttribute("layouts", layouts);
		pageModel.resetPAGE();
		return "/backoffice/layoutDashboard/manage.html";

	}
	
	@SuppressWarnings("static-access")
	@GetMapping("/layoutProduzioneGestione")
	public String manageLayoutsByStatus(Model model ) throws BusinessException {
		
		pageModel.initPageAndSize();
		pageModel.setSIZE(5);
		Page<Layout> layouts = serviceLayout.getLayoutsByStatus(StatusType.TRANSFERRED_TO_PRODUCTION, pageModel.getPAGE(), pageModel.getSIZE());
		model.addAttribute("layouts", layouts);
		pageModel.resetPAGE();
		return "/backoffice/factoryDashboard/manage.html";

	}

	@GetMapping("/layoutCrea")
	public String create(Model model) throws BusinessException {
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
		layoutSave.setStatus(StatusType.IN_PROGRESS);		

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
			fileLayoutSave.setName((String)"layout: "+ layoutSave.getName() + ", file N° " + i);
			
			try {
				fileLayoutService.saveFileLayout(fileLayoutSave);
			} catch (BusinessException e) {
				e.printStackTrace();

			}
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
	public String updateLayout(Long id, Model model) throws BusinessException {
		Layout layout = new Layout();
		try {
			layout = serviceLayout.getLayout(id);
		} catch (BusinessException e) {
			e.printStackTrace();

		}
		
		
		
		model.addAttribute("StatusType", StatusType.values());
		model.addAttribute("layout", layout);
		return "/backoffice/layoutDashboard/update.html";
	}

	@PostMapping("/layoutUpdate/{id}")
	public String updateLayout(@PathVariable("id") Long id, @Valid @ModelAttribute("layout") Layout layout, Errors errors,
			@RequestParam("contentImg") List<MultipartFile> contentImg, Authentication auth) throws BusinessException {

		int i = 1;
		Set<FileLayout> file = new HashSet<FileLayout>();
		List<byte[]> Listbytes = new ArrayList<byte[]>();
		User userAuth = new User();


		Layout layoutOld = new Layout();
		Layout layoutSave = new Layout();
		
		String username = auth.getName();
		try {
			userAuth = serviceUser.findUserByUserName(username);

		} catch (BusinessException e) {
			e.printStackTrace();

		}

		try {
			layoutOld = serviceLayout.getLayout(id);			

		} catch (BusinessException e) {
			e.printStackTrace();

		}
		layoutSave.setId(id);
		layoutSave.setCompleted(layout.getCompleted());
		layoutSave.setDescription(layout.getDescription());
		layoutSave.setName(layout.getName());
		layoutSave.setUsers(layoutOld.getUsers());
		layoutSave.setStatus(layout.getStatus());
		layoutSave.setModifyDate(ZonedDateTime.now());

		ZonedDateTime dateCreation = layoutOld.getInsertDate();

		if (contentImg.isEmpty()) {
			
			Set<FileLayout> oldFiles = layoutOld.getFileLayouts();
			layoutSave.setFileLayouts(oldFiles);

		} else {

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
				fileLayoutSave.setName((String)"layout: "+ layoutSave.getName() + ", file N° " + i);
				fileLayoutService.saveFileLayout(fileLayoutSave);
				i ++;


			}

			layoutSave.setFileLayouts(file);		}

		try {
			layoutSave = serviceLayout.saveLayout(layoutSave);
			layoutSave.setInsertDate(dateCreation);
			serviceLayout.saveLayout(layoutSave);
		} catch (BusinessException e) {
			e.printStackTrace();

		}

		return "redirect:/layoutGestione";
	}
	
	@GetMapping("/layoutUpdateProductor")
	public String updateProductorLayout(Long id, Model model) throws BusinessException {
		Layout layout = new Layout();
		try {
			layout = serviceLayout.getLayout(id);
		} catch (BusinessException e) {
			e.printStackTrace();

		}		
		
		model.addAttribute("StatusType", StatusType.values());
		model.addAttribute("layout", layout);
		return "/backoffice/factoryDashboard/update.html";
	}
	
	@PostMapping("/layoutUpdateProductor/{id}")
	public String updateProductorLayout(@PathVariable("id") Long id, @Valid @ModelAttribute("layout") Layout layout, Errors errors,
			 Authentication auth) throws BusinessException {

		User userAuth = new User();


		Layout layoutOld = new Layout();
		Layout layoutSave = new Layout();
		
		String username = auth.getName();
		try {
			userAuth = serviceUser.findUserByUserName(username);

		} catch (BusinessException e) {
			e.printStackTrace();

		}

		try {
			layoutOld = serviceLayout.getLayout(id);

		} catch (BusinessException e) {
			e.printStackTrace();

		}
		layoutSave.setId(layoutOld.getId());
		layoutSave.setCompleted(layoutOld.getCompleted());
		layoutSave.setDescription(layoutOld.getDescription());
		layoutSave.setName(layoutOld.getName());
		layoutSave.setUsers(layoutOld.getUsers());
		layoutSave.setStatus(layout.getStatus());
		layoutSave.setModifyDate(ZonedDateTime.now());
		ZonedDateTime dateCreation = layoutOld.getInsertDate();			
		Set<FileLayout> oldFiles = layoutOld.getFileLayouts();
		layoutSave.setFileLayouts(oldFiles);

		

		try {

			layoutSave = serviceLayout.saveLayout(layoutSave);
			layoutSave.setInsertDate(dateCreation);
			serviceLayout.saveLayout(layoutSave);
		} catch (BusinessException e) {
			e.printStackTrace();

		}

		return "redirect:/layoutGestione";
	}

}
