package com.diemme.business;

import org.springframework.data.domain.Page;

import com.diemme.domain.mysql.FileLayout;
import com.diemme.domain.mysql.Layout;
import com.diemme.domain.mysql.NewsShowcase;

public interface FileLayoutService {
	
	FileLayout getFileLayout (Long id);
	
	FileLayout saveFileLayout(FileLayout fileLayout) throws BusinessException;
	
	Page<FileLayout> getAllFileslayout(Integer page, Integer size, Long id) throws BusinessException;

}
