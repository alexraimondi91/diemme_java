package com.diemme.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import com.diemme.model.QuotationShowcase;

@Repository("QuotationShowcaseRepository")
public interface QuotationShowcaseRepository extends JpaRepository<QuotationShowcase, Long> {

}
