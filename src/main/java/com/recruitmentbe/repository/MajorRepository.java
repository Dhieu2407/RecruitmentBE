package com.recruitmentbe.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.recruitmentbe.model.Major;

@Repository
public interface MajorRepository extends JpaRepository<Major, Integer>{
	public List<Major> findAll();
	public List<Major> findByTenNganhContaining(String name);
	public Major findByNganhId(long id);
}
