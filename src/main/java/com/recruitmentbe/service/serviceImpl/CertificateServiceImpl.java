package com.recruitmentbe.service.serviceImpl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.recruitmentbe.repository.CertificateRepository;
import com.recruitmentbe.service.CertificateService;

@Service
public class CertificateServiceImpl implements CertificateService{
	
	@Autowired
	CertificateRepository certificateRepo;
	
	@Override
	public List<com.recruitmentbe.model.Certificate> getAllCertificate() {
		return certificateRepo.findAll();
	}
	
}
