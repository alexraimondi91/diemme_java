package com.diemme.business;

import java.util.List;
import java.util.Optional;

import org.springframework.data.domain.Page;

import com.diemme.domain.mysql.NewsShowcase;

public interface NewsService {

	List<NewsShowcase> findAllNewsShowcases() throws BusinessException;

	Optional<NewsShowcase> findNewsShowcase(Long id) throws BusinessException;

	NewsShowcase getNews(Long id) throws BusinessException;

	NewsShowcase saveNews(NewsShowcase news) throws BusinessException;

	Page<NewsShowcase> getAllNewsPageable(Integer page, Integer size) throws BusinessException;

	void deleteNews(Long id) throws BusinessException;

}
