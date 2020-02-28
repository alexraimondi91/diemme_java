package com.diemme.repository.JpaRepository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import com.diemme.model.ContactShowcase;


@Repository("ContactShowcaseRepository")
public interface ContactShowcaseRepository extends JpaRepository<ContactShowcase, Long>{

}
