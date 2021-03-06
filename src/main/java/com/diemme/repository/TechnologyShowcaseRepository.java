package com.diemme.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.diemme.domain.TechnologyShowcase;


@Repository("TecnologyShowcaseRepository")
public interface TechnologyShowcaseRepository extends JpaRepository<TechnologyShowcase, Long> {

}
