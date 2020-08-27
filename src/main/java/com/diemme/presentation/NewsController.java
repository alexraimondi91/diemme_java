package com.diemme.presentation;

import java.io.IOException;
import java.time.ZonedDateTime;
import java.util.List;
import java.util.Optional;

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
import com.diemme.business.NewsService;
import com.diemme.business.UserService;
import com.diemme.component.PageModel;
import com.diemme.domain.NewsShowcase;
import com.diemme.domain.User;

@Controller
public class NewsController {
	
	@Autowired 
	private NewsService serviceNews;
	@Autowired
	private UserService serviceUser;
	@Autowired
	private PageModel pageModel;
	
private com.diemme.util.CompressionUtils CompressionUtils;

	
	@GetMapping("/news")
	public String listNewsShowcase (Model model) throws BusinessException{
		List<NewsShowcase> news = serviceNews.findAllNewsShowcases();
		model.addAttribute("news_showcase", news);
		return "/frontoffice/news/news.html";
		
	}
	
	@SuppressWarnings("static-access")
	@GetMapping("/newsGestione")
	public String manageNewsShocases(Model model) throws BusinessException {
		pageModel.setSIZE(5);
		pageModel.initPageAndSize();

		Page<NewsShowcase> news = serviceNews.getAllNewsPageable(pageModel.getPAGE(),
				pageModel.getSIZE());

		model.addAttribute("news_showcase", news);
		return "/backoffice/newsDashboard/manage.html";

	}
	
	@GetMapping("/news/image/{id}")
	@ResponseBody
	public byte[] getImage (@PathVariable Long id) throws BusinessException{
		
		Optional<NewsShowcase> product = serviceNews.findNewsShowcase(id);
		byte[] imageProduct = product.get().getContentImg();
		return imageProduct;
	}
	
	@GetMapping("/newsCrea")
	public String createNewsShocases(Model model) throws BusinessException {
		NewsShowcase newsShowcase = new NewsShowcase();
		model.addAttribute("news_showcase", newsShowcase);
		return "/backoffice/newsDashboard/create.html";
	}

	@SuppressWarnings("static-access")
	@PostMapping("/newsCrea")
	public ModelAndView create(@Valid @ModelAttribute("news_showcase") NewsShowcase news,
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
		modelAndView.setViewName("/backoffice/newsDashboard/create.html");
		news.setContentImg(bytes);
		news.setCompressImg(byteCompress);
		news.setUser(userAuth);
		try {
			serviceNews.saveNews(news);
		} catch (BusinessException e) {
			e.printStackTrace();

		}

		return modelAndView;
	}

	@PostMapping("/newsDelete/{id}")
	public String deleteNewsShocases(@PathVariable(value = "id") Long id) throws BusinessException {
		try {
			serviceNews.deleteNews(id);
		} catch (BusinessException e) {
			e.printStackTrace();

		}
		return "redirect:/newsGestione";

	}

	@GetMapping("/newsUpdate")
	public String updateNewsShocases(Long id, Model model) throws BusinessException {
		NewsShowcase newsShowcase = new NewsShowcase();
		try {
			newsShowcase = serviceNews.getNews(id);
		} catch (BusinessException e) {
			e.printStackTrace();

		}
		model.addAttribute("news_showcase_update", newsShowcase);
		return "/backoffice/newsDashboard/update.html";
	}

	@SuppressWarnings({ "static-access"})
	@PostMapping("/newsUpdate/{id}")
	public String update(@PathVariable("id") Long id,
			@Valid @ModelAttribute("news_showcase_update") NewsShowcase newsShowcase, Errors errors,
			@RequestParam("contentImg") MultipartFile contentImg, Authentication auth) throws BusinessException {
		byte[] bytes = new byte[(int) contentImg.getSize()];
		byte[] byteCompress = new byte[0];
		NewsShowcase newsOld = new NewsShowcase();
		NewsShowcase newsSave = new NewsShowcase();
		User userAuth = new User();
		
		try {
			newsOld = serviceNews.getNews(id);
			
		} catch (BusinessException e) {
			e.printStackTrace();

		}
		
		ZonedDateTime dateCreation = newsOld.getInsertDate();

		
		if (contentImg.isEmpty()) {

			newsShowcase.setContentImg(newsOld.getContentImg());
			newsShowcase.setCompressImg(newsOld.getCompressImg());
			newsShowcase.setModifyDate(ZonedDateTime.now());

		} else {

			try {
				byteCompress = CompressionUtils.compress(bytes);
				bytes = contentImg.getBytes();
			} catch (IOException e) {
				e.printStackTrace();
			}
			newsShowcase.setContentImg(bytes);
			newsShowcase.setCompressImg(byteCompress);
		}
		String username = auth.getName();

		try {
			userAuth = serviceUser.findUserByUserName(username);
		} catch (BusinessException e) {
			e.printStackTrace();

		}
		newsShowcase.setUser(userAuth);
		try {
			newsSave = serviceNews.saveNews(newsShowcase);
			newsSave.setInsertDate(dateCreation);
			serviceNews.saveNews(newsSave);
		} catch (BusinessException e) {
			e.printStackTrace();

		}

		return "redirect:/newsGestione";
	}

}
