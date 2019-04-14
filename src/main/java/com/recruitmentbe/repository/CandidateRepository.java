package com.recruitmentbe.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.recruitmentbe.model.Candidate;

@Repository
public interface CandidateRepository extends JpaRepository<Candidate, Integer> {
	public List<Candidate> findAll();
	public List<Candidate> findByEmailContaining(String email);
	public List<Candidate> findByTenUngVienContaining(String email);

}
