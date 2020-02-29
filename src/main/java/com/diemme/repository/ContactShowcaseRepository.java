package com.diemme.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.diemme.domain.ContactShowcase;




@Repository("ContactShowcaseRepository")
public interface ContactShowcaseRepository extends JpaRepository<ContactShowcase, Long>{

}
