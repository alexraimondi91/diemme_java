package com.diemme.repository.JpaRepository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import com.diemme.model.ProductShowcase;

@Repository("ProducsShowcaseRepository")
public interface ProducsShowcaseRepository extends JpaRepository<ProductShowcase, Long>{

}
