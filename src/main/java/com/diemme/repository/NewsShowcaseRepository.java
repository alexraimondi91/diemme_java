package com.diemme.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import com.diemme.model.NewsShowcase;


@Repository("NewsShowcaseRepository")
public interface NewsShowcaseRepository extends JpaRepository<NewsShowcase, Long>{

}
