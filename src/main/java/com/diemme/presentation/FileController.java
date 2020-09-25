package com.diemme.presentation;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.ResponseBody;

import com.diemme.business.BusinessException;
import com.diemme.business.FileLayoutService;
import com.diemme.component.PageModel;
import com.diemme.domain.mysql.FileLayout;
import com.diemme.domain.mysql.NewsShowcase;

@Controller
public class FileController {
	
	@Autowired
	private FileLayoutService fileService;
	@Autowired
	private PageModel pageModel;
	
	@SuppressWarnings("static-access")
	@GetMapping("/layoutVisione")
	public String manageNewsShocases(Model model, Long id) throws BusinessException {
		pageModel.setSIZE(1);
		pageModel.initPageAndSize();

		Page<FileLayout> files = fileService.getAllFileslayout(pageModel.getPAGE(),
				pageModel.getSIZE(),id);

		model.addAttribute("files", files);
		return "/backoffice/layoutDashboard/manageFileLayout.html";

	}
	
	@GetMapping("/fileDelete")
	@ResponseBody
	public byte[] getImage (Long id) throws BusinessException{
		
		FileLayout file = fileService.getFileLayout(id);
		byte[] imageFile = file.getContentImg();
		return imageFile;
	}

}
