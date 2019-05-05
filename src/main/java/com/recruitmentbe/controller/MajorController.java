package com.recruitmentbe.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.recruitmentbe.model.Major;
import com.recruitmentbe.service.MajorService;

@RestController
@RequestMapping("/api/major")
public class MajorController {
	
	@Autowired
	MajorService majorServiceImpl;
	@GetMapping(value = "/getAllMajors")
	public List<Major> getAllMajors() {
		List<Major> allMajors = majorServiceImpl.getAllMajor();
		return allMajors;
	}
}
