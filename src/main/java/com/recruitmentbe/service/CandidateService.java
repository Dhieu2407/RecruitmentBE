package com.recruitmentbe.service;

import 	java.util.List;

import com.recruitmentbe.model.Candidate;
import com.recruitmentbe.model.Job;
import com.recruitmentbe.model.UngTuyen;

public interface CandidateService {
	public Candidate registerCandidate(String username, String email, String password);
	public List<Candidate> getAllCandidate();
    public Candidate findByUngVienId(Long id);
	public List<Candidate> findCandidateByConditions(String body);
	public Candidate updateProfileCandidates(String body);
	public byte[] candidateBookmarkJob(String body);
	public byte[] candidateApplyJob(String body);
	public List<Job> getAppliedJobs (String idString);
	public List<Candidate> getApplicants (String idString);
	 public List<UngTuyen> getListUngTuyen(String body);
	 public List<UngTuyen> getListUngTuyenOfCompany(String body);
	public byte[] candidateSaveCompany(String body);
	public  int getNumberNotifyTinder(String body);
	public List<Candidate> getCandidateTinder(String body);
}
