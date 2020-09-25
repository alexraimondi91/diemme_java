package com.diemme.business.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.diemme.ResourceNotFoundException;
import com.diemme.business.BusinessException;
import com.diemme.business.FileLayoutService;
import com.diemme.domain.mysql.FileLayout;
import com.diemme.repository.mysql.FileLayoutRepository;

@Service
public class FileLayoutServiceImpl implements FileLayoutService{
	
	@Autowired
	FileLayoutRepository fileLayoutRepository;
	

	@Override
	public FileLayout getFileLayout(Long id) {
		return fileLayoutRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException("FileLayout", "id", id));
	}

	@Override
	public FileLayout saveFileLayout(FileLayout fileLayout) throws BusinessException {
		return fileLayoutRepository.save(fileLayout);
	}

}
