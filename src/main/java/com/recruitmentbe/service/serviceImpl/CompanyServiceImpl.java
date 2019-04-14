package com.recruitmentbe.service.serviceImpl;

import java.io.UnsupportedEncodingException;
import java.util.ArrayList;
import java.util.List;

import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.recruitmentbe.model.Company;
import com.recruitmentbe.model.Company;
import com.recruitmentbe.model.Skill;
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
		List<Company> allCompanys= companyRepo.findAll();
		if(allCompanys.size() == 0) {
			newCompany.setCongtyId(0);
		}else {
			newCompany.setCongtyId(allCompanys.get(allCompanys.size() - 1).getCongtyId() + 1);
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
	
	@Override
	public Company updateProfileCompany(String body) {
		JSONObject requestObj = new JSONObject(body);
		String companyId = requestObj.getString("id");
		Company updatedCompany = companyRepo.findByCongtyId(Long.parseLong(companyId));
		updatedCompany.setEmail(requestObj.getString("email"));
		updatedCompany.setDiaChi(requestObj.getString("diaChi"));
		updatedCompany.setSdt(requestObj.getString("sdt"));
		try {
			companyRepo.save(updatedCompany);
			return updatedCompany;
		}catch(Exception e) {
			return null;
		}
	}
	

}
