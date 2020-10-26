package com.diemme.business;

import java.util.List;

import org.springframework.data.domain.Page;

import com.diemme.domain.mysql.FileLayout;
import com.diemme.domain.mysql.Layout;
import com.diemme.domain.mysql.NewsShowcase;

public interface FileLayoutService {
	
	FileLayout getFileLayout (Long id) throws BusinessException;
	
	FileLayout saveFileLayout(FileLayout fileLayout) throws BusinessException;

	Page<FileLayout> getAllFileslayout(Integer page, Integer size, List<Long> id) throws BusinessException;

}
