package com.diemme.business;

import com.diemme.domain.NewsShowcase;
import java.util.List;


public interface IndexService {
	
	List<NewsShowcase> findAllNewsShowcases() throws BusinessException;

}
