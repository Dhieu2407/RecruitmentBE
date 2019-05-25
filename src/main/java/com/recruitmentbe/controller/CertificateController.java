package com.recruitmentbe.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.recruitmentbe.model.Certificate;
import com.recruitmentbe.service.CertificateService;

@RestController
@RequestMapping("/api/certificate")
public class CertificateController {
	@Autowired
	CertificateService certificateServiceImpl;
	
	@GetMapping(value = "/getAllCertificates")
	public List<Certificate> getAllCandidates() {
		List<Certificate> allCertificates = certificateServiceImpl.getAllCertificate();
		return allCertificates;
	}
}
