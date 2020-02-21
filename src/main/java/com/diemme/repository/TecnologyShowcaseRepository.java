package com.diemme.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import com.diemme.model.TecnologyShowcase;

@Repository("TecnologyShowcaseRepository")
public interface TecnologyShowcaseRepository extends JpaRepository<TecnologyShowcase, Long> {

}
