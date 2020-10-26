package com.diemme.business;

import java.util.List;
import java.util.Optional;

import org.springframework.data.domain.Page;
import org.springframework.web.multipart.MultipartFile;

import com.diemme.domain.mysql.TechnologyShowcase;
import com.diemme.domain.mysql.User;

public interface TechnologyService {
	
	public List<TechnologyShowcase> getAllTecnology () throws BusinessException;
	
	public TechnologyShowcase getTecnology (Long id) throws BusinessException;
	
	TechnologyShowcase saveTechnology (TechnologyShowcase technology) throws BusinessException;

	Page<TechnologyShowcase> getAllTecnologyPageable(Integer page, Integer size) throws BusinessException;

	void deleteTechnology(Long id) throws BusinessException;

	void createTechnology(TechnologyShowcase technology, MultipartFile contentImg, User userAuth)
			throws BusinessException;

	void updateTechnology(Long id, TechnologyShowcase technology, MultipartFile contentImg, User userAuth)
			throws BusinessException;

}
