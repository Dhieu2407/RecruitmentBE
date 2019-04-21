package com.recruitmentbe.service;

import 	java.util.List;

import com.recruitmentbe.model.Candidate;

public interface CandidateService {
	public Candidate registerCandidate(String username, String email, String password);
	public List<Candidate> getAllCandidate();
    public Candidate findByUngVienId(Long id);
	public List<Candidate> findCandidateByConditions(String body);
	public Candidate updateProfileCandidates(String body);
}
