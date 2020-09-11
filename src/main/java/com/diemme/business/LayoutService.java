package com.diemme.business;

import java.util.List;
import java.util.Optional;

import org.springframework.data.domain.Page;

import com.diemme.domain.Layout;

public interface LayoutService {
	
	List<Layout> findAllLayout() throws BusinessException;

	Optional<Layout> findNewsLayout(Long id) throws BusinessException;

	Layout getLayout(Long id) throws BusinessException;

	Layout saveLayout(Layout news) throws BusinessException;

	Page<Layout> getAllLayoutPageable(Integer page, Integer size) throws BusinessException;

	void deleteLayout(Long id) throws BusinessException;

}
