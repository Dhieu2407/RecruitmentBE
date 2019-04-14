package com.recruitmentbe.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.recruitmentbe.model.Company;
import com.recruitmentbe.service.serviceImpl.CompanyService;

@RestController
@RequestMapping("/api")
public class CompanyController {
	@Autowired
	CompanyService companyServiceImpl;
	
	@PostMapping(value = "/companyRegister")
	public byte[] addCompany(@RequestBody String body) throws Exception {
		return companyServiceImpl.registerCompany(body);
	}
	
	@GetMapping(value = "/getAllCompanies")
	public List<Company> getAllCompanies() {
		List<Company> allCompanies = companyServiceImpl.getAllCompany();
		return allCompanies;
	}
}
