package com.diemme.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.diemme.domain.FileLayout;

@Repository("FileLayoutRepository")
public interface FileLayoutRepository extends JpaRepository <FileLayout, Long>{

}
