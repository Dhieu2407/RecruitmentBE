package com.recruitmentbe.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.recruitmentbe.model.Certificate;

@Repository
public interface CertificateRepository extends JpaRepository<Certificate, Integer> {
	public List<Certificate> findAll();
	public List<Certificate> findByTenChungChi(String tenChungChi);

}
