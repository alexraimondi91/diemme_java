package com.diemme.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.diemme.domain.Layout;



@Repository("LayoutRepository")
public interface LayoutRepository  extends JpaRepository<Layout, Long>{

}
