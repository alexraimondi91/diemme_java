package com.diemme.presentation;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.Optional;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.ResponseBody;

import com.diemme.business.BusinessException;
import com.diemme.business.FileLayoutService;
import com.diemme.business.LayoutService;
import com.diemme.component.PageModel;
import com.diemme.domain.mysql.FileLayout;
import com.diemme.domain.mysql.Layout;
import com.diemme.domain.mysql.NewsShowcase;

@Controller
public class FileController {
	
	@Autowired
	private FileLayoutService fileService;
	
	@Autowired
	private LayoutService layoutService;
	@Autowired
	private PageModel pageModel;
	
	@SuppressWarnings("static-access")
	@GetMapping("/layoutVisione")
	public String manageNewsShocases(Model model, Long idLayout) throws BusinessException {
		pageModel.setSIZE(1);
		pageModel.initPageAndSize();
		
		List<Long> idFiles = new ArrayList<Long>();
		

		Layout Layout = layoutService.getLayout(idLayout);
		
		for(FileLayout file : Layout.getFileLayouts()) {
			idFiles.add(file.getId());
		}
		
		Page<FileLayout> files = fileService.getAllFileslayout(pageModel.getPAGE(),pageModel.getSIZE(), idFiles);
		
		model.addAttribute("idLayout", idLayout);
		model.addAttribute("files", files);
		pageModel.resetPAGE();
		return "/backoffice/layoutDashboard/manageFileLayout.html";

	}
	
	@GetMapping("/layout/image/{id}")
	@ResponseBody
	public byte[] getImage (@PathVariable Long id) throws BusinessException{
		FileLayout file = fileService.getFileLayout(id);
		byte[] imageFile = file.getContentImg();
		return imageFile;
	}

}
