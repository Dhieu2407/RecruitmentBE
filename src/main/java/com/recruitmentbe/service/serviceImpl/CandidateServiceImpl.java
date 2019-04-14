package com.recruitmentbe.service.serviceImpl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.recruitmentbe.model.Candidate;
import com.recruitmentbe.repository.CandidateRepository;
import com.recruitmentbe.service.CandidateService;

@Service
public class CandidateServiceImpl implements CandidateService{

	@Autowired
	private CandidateRepository candidateRepo;
	
	@Override
	public List<Candidate> getAllCandidate() {
		return candidateRepo.findAll();
	}

	@Override
	public Candidate registerCandidate(String username, String email, String password) {
		Candidate candidate = new Candidate();
		candidate.setEmail(email);
		candidate.setTenUngVien(username);
		candidate.setPassword(password);
		List<Candidate> allCandidates= candidateRepo.findAll();
		candidate.setUngVienId(allCandidates.get(allCandidates.size() - 1).getUngVienId() + 1);
		try {
			candidateRepo.save(candidate);
			return candidate;
		}catch(Exception e) {
			e.printStackTrace();
		}
		return null;
	}

}
