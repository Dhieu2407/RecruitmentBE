package com.recruitmentbe.service.serviceImpl;

import java.util.List;
import java.util.Set;

import com.recruitmentbe.model.Candidate;
import com.recruitmentbe.model.Company;

public interface CompanyService {
	public List<Company> getAllCompany();
	public byte[] registerCompany(String body);
	public Company updateProfileCompany(String body);
	public Company findByCongTyId(Long id);
	public Set<Candidate> getCandidateByCompany(String id);
	public byte[] companySaveCandidate(String body);
}
