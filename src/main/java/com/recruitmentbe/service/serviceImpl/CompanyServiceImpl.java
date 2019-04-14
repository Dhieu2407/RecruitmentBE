package com.recruitmentbe.service.serviceImpl;

import java.io.UnsupportedEncodingException;
import java.util.List;

import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.recruitmentbe.model.Candidate;
import com.recruitmentbe.model.Company;
import com.recruitmentbe.repository.CompanyRepository;

@Service
public class CompanyServiceImpl implements CompanyService{

	@Autowired
	CompanyRepository companyRepo;
	
	@Override
	public List<Company> getAllCompany() {
		return companyRepo.findAll();
	}

	@Override
	public byte[] registerCompany(String body) {
		JSONObject obj = new JSONObject(body);
		String username;
		try {
			username = obj.getString("username");
		} catch (Exception e1) {
			username = "";
		}
		String email;
		try {
			email = obj.getString("email");
		} catch (Exception e1) {
			email = "";
		}
		String password;
		try {
			password = obj.getString("password");
		} catch (Exception e1) {
			password = "";
		}
		
		Company newCompany = new Company();
		newCompany.setEmail(email);
		newCompany.setTenCongTy(username);
		newCompany.setPassword(password);
		List<Company> allCandidates= companyRepo.findAll();
		if(allCandidates.size() == 0) {
			newCompany.setCongtyId(0);
		}else {
			newCompany.setCongtyId(allCandidates.get(allCandidates.size() - 1).getCongtyId() + 1);
		}
		try {
			companyRepo.save(newCompany);
			return new JSONObject(newCompany).toString().getBytes("UTF-8");
		} catch (UnsupportedEncodingException e) {
			return "".getBytes();
		} catch (Exception e) {
			return "".getBytes();
		}
	}
	

}
