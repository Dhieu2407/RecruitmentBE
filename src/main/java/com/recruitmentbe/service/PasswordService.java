package com.recruitmentbe.service;

import java.util.List;
import java.util.Optional;

import com.recruitmentbe.model.Candidate;

public interface PasswordService {
    public boolean confirmPass(Candidate candidate, String pass);
    public void addAcount(Candidate candidate);
    public List<Candidate> getCandidateByEmail(String email);
    public Optional<Candidate> getCandidateById(long id);
    public List<Candidate> getAll();
}
