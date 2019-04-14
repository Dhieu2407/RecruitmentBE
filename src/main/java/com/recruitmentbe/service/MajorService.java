package com.recruitmentbe.service;

import java.util.List;

import com.recruitmentbe.model.Candidate;
import com.recruitmentbe.model.Major;

public interface MajorService {
	public List<Major> getAllMajor();
	public List<Major> findMajorByName(String name);
	
}
