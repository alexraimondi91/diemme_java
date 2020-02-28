package com.diemme.repository.JpaRepository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.diemme.model.Layout;


@Repository("LayoutRepository")
public interface LayoutRepository  extends JpaRepository<Layout, Long>{

}
