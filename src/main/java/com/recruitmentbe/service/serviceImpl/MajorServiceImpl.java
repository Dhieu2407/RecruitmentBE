package com.recruitmentbe.service.serviceImpl;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.recruitmentbe.model.Major;
import com.recruitmentbe.repository.JobRepository;
import com.recruitmentbe.repository.MajorRepository;
import com.recruitmentbe.service.MajorService;

@Service
public class MajorServiceImpl implements MajorService {

	@Autowired
	MajorRepository majorRepo ;

	@Autowired
	JobRepository jobRepo ;
	
	@Override
	public List<Major> getAllMajor() {
		List<Major> mjs = majorRepo.findAll();
		List<Major> resultListMajors = new ArrayList<Major>();
		for(Major m : mjs) {
			Major rsMajor = new Major();
			rsMajor.setNganhId(m.getNganhId());
			rsMajor.setTenNganh(m.getTenNganh());
			rsMajor.setTinTuyenDung(jobRepo.findByNganh(m));
			resultListMajors.add(rsMajor);
		}
		return resultListMajors;
	}

	@Override
	public List<Major> findMajorByName(String name) {
		return majorRepo.findByTenNganhContaining(name);
	}

}
