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
import com.diemme.business.ProductService;
import com.diemme.business.UserService;
import com.diemme.component.PageModel;
import com.diemme.domain.ProductShowcase;
import com.diemme.domain.User;

@Controller
public class ProductController {

	@Autowired 
	private ProductService serviceProduct;
	@Autowired
	private UserService serviceUser;
	@Autowired
	private PageModel pageModel;
	
	private com.diemme.util.CompressionUtils CompressionUtils;

	
	@GetMapping("/prodotti")
	public String listProductShowcase (Model model) throws BusinessException{
		List<ProductShowcase> products = serviceProduct.findAllProductShowcases();
		model.addAttribute("prods", products);
		return "/frontoffice/prodotti/prodotti.html";
		
	}
	
	@SuppressWarnings("static-access")
	@GetMapping("/prodottiGestione")
	public String manageProductShocases(Model model) throws BusinessException {
		pageModel.setSIZE(5);
		pageModel.initPageAndSize();

		Page<ProductShowcase> products = serviceProduct.getAllProductPageable(pageModel.getPAGE(),
				pageModel.getSIZE());

		model.addAttribute("prod", products);
		return "/backoffice/productDashboard/manage.html";

	}
	
	@GetMapping("/prodotti/image/{id}")
	@ResponseBody
	public byte[] getImage (@PathVariable Long id) throws BusinessException{
		
		Optional<ProductShowcase> product = serviceProduct.findProductShowcase(id);
		byte[] imageProduct = product.get().getContentImg();
		return imageProduct;
	}
	
	@GetMapping("/prodottiCrea")
	public String createProductShocases(Model model) throws BusinessException {
		ProductShowcase productShowcase = new ProductShowcase();
		model.addAttribute("product_showcase", productShowcase);
		return "/backoffice/productDashboard/create.html";
	}

	@SuppressWarnings("static-access")
	@PostMapping("/prodottiCrea")
	public ModelAndView create(@Valid @ModelAttribute("product_showcase") ProductShowcase products,
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
		modelAndView.setViewName("/backoffice/productDashboard/create.html");
		products.setContentImg(bytes);
		products.setCompressImg(byteCompress);
		products.setUser(userAuth);
		try {
			serviceProduct.saveProduct(products);
		} catch (BusinessException e) {
			e.printStackTrace();

		}

		return modelAndView;
	}

	@PostMapping("/productDelete/{id}")
	public String createProductShocases(@PathVariable(value = "id") Long id) throws BusinessException {
		try {
			serviceProduct.deleteProduct(id);
		} catch (BusinessException e) {
			e.printStackTrace();

		}
		return "redirect:/prodottiGestione";

	}

	@GetMapping("/prodottiUpdate")
	public String updateProductShocases(Long id, Model model) throws BusinessException {
		ProductShowcase productShowcase = new ProductShowcase();
		try {
			productShowcase = serviceProduct.getProduct(id);
		} catch (BusinessException e) {
			e.printStackTrace();

		}
		model.addAttribute("product_showcase_update", productShowcase);
		return "/backoffice/productDashboard/update.html";
	}

	@SuppressWarnings({ "static-access"})
	@PostMapping("/prodottiUpdate/{id}")
	public String update(@PathVariable("id") Long id,
			@Valid @ModelAttribute("product_showcase_update") ProductShowcase productShowcase, Errors errors,
			@RequestParam("contentImg") MultipartFile contentImg, Authentication auth) throws BusinessException {
		byte[] bytes = new byte[(int) contentImg.getSize()];
		byte[] byteCompress = new byte[0];
		ProductShowcase productOld = new ProductShowcase();
		ProductShowcase productSave = new ProductShowcase();
		User userAuth = new User();
		
		try {
			productOld = serviceProduct.getProduct(id);
			
		} catch (BusinessException e) {
			e.printStackTrace();

		}
		
		ZonedDateTime dateCreation = productOld.getInsertDate();

		
		if (contentImg.isEmpty()) {

			productShowcase.setContentImg(productOld.getContentImg());
			productShowcase.setCompressImg(productOld.getCompressImg());
			productShowcase.setModifyDate(ZonedDateTime.now());

		} else {

			try {
				byteCompress = CompressionUtils.compress(bytes);
				bytes = contentImg.getBytes();
			} catch (IOException e) {
				e.printStackTrace();
			}
			productShowcase.setContentImg(bytes);
			productShowcase.setCompressImg(byteCompress);
		}
		String username = auth.getName();

		try {
			userAuth = serviceUser.findUserByUserName(username);
		} catch (BusinessException e) {
			e.printStackTrace();

		}
		productShowcase.setUser(userAuth);
		try {
			productSave = serviceProduct.saveProduct(productShowcase);
			productSave.setInsertDate(dateCreation);
			serviceProduct.saveProduct(productSave);
		} catch (BusinessException e) {
			e.printStackTrace();

		}

		return "redirect:/prodottiGestione";
	}
}
