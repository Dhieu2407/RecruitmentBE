package com.recruitmentbe.service.serviceImpl;

import java.util.List;

import com.recruitmentbe.model.Company;

public interface CompanyService {
	public List<Company> getAllCompany();
	public byte[] registerCompany(String body);
}
