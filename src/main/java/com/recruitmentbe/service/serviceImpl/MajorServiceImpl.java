package com.recruitmentbe.service.serviceImpl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;

import com.recruitmentbe.model.Major;
import com.recruitmentbe.repository.MajorRepository;
import com.recruitmentbe.service.MajorService;

public class MajorServiceImpl implements MajorService {

	@Autowired
	MajorRepository majorRepo ;
	
	@Override
	public List<Major> getAllMajor() {
		return majorRepo.findAll();
	}

	@Override
	public List<Major> findMajorByName(String name) {
		return majorRepo.findByTenNganhContaining(name);
	}

}
