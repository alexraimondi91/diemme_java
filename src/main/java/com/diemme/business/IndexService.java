package com.diemme.business;

import com.diemme.domain.NewsShowcase;
import java.util.List;
import java.util.Optional;


public interface IndexService {
	
	List<NewsShowcase> findAllNewsShowcases() throws BusinessException;
	
	Optional<NewsShowcase> findNewsShowcase(Long id) throws BusinessException;
	


}
