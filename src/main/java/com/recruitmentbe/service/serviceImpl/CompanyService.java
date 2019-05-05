package com.recruitmentbe.service.serviceImpl;

import java.util.List;

import com.recruitmentbe.model.Candidate;
import com.recruitmentbe.model.Company;

public interface CompanyService {
	public List<Company> getAllCompany();
	public byte[] registerCompany(String body);
	public Company updateProfileCompany(String body);
	public Company findByCongTyId(Long id);
	public List<Candidate> getCandidateByCompany(String id);
}
