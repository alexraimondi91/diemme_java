package com.diemme.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.diemme.domain.ProductShowcase;

@Repository("ProducsShowcaseRepository")
public interface ProducsShowcaseRepository extends JpaRepository<ProductShowcase, Long>{

}
