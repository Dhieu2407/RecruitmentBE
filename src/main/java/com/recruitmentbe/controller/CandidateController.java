package com.recruitmentbe.controller;

import java.io.UnsupportedEncodingException;
import java.util.ArrayList;
import java.util.List;

import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.recruitmentbe.model.Candidate;
import com.recruitmentbe.service.CandidateService;


@RestController
@RequestMapping("/api")
public class CandidateController {
	@Autowired
	private CandidateService candidateServiceImpl;

	@PostMapping(value = "/candidateRegister")
	public byte[] addCandidate(@RequestBody String body) throws Exception {
		JSONObject obj = new JSONObject(body);
		String username;
		try {
			username = obj.getString("username");
		} catch (Exception e1) {
			username = "";
		}
		String email;
		try {
			email = obj.getString("email");
		} catch (Exception e1) {
			email = "";
		}
		String password;
		try {
			password = obj.getString("password");
		} catch (Exception e1) {
			password = "";
		}
		Candidate newCandidate = candidateServiceImpl.registerCandidate(username, email, password);
		try {
			return new JSONObject(newCandidate).toString().getBytes("UTF-8");
		} catch (UnsupportedEncodingException e) {
			return "".getBytes();
		} catch (Exception e) {
			return "".getBytes();
		}
	}

	@GetMapping(value = "/getAllCandidates")
	public List<Candidate> getAllCandidates() {
		System.out.println("all");
		List<Candidate> allCandidates = candidateServiceImpl.getAllCandidate();
		return allCandidates;
	}

	@GetMapping(value = "/searchCandidates")
	public String searchCandidates(@RequestBody String body) {
		JSONObject obj = new JSONObject(body);
		String username;
		List<Candidate> resultListCandidates = candidateServiceImpl.getAllCandidate();
		List<Candidate> searchByCondition = new ArrayList<>();
		List<Candidate> notContained = new ArrayList<>();
//		try {
//			username = obj.getString("username");
//			searchByCondition = candidateServiceImpl.findByTenUngVien();
//			notContained = new ArrayList<>(resultListCandidates);
//			notContained.removeAll(searchByCondition);
//			resultListCandidates.removeAll(notContained);
//			notContained.clear();
//			searchByCondition.clear();
//		} catch (Exception e1) {
//			username = "";
//		}
//		String email;
//		try {
//			email = obj.getString("email");
//			searchByCondition = candidateServiceImpl.findByEmail();
//			notContained = new ArrayList<>(resultListCandidates);
//			notContained.removeAll(searchByCondition);
//			resultListCandidates.removeAll(notContained);
//			notContained.clear();
//			searchByCondition.clear();
//		} catch (Exception e1) {
//			email = "";
//		}
//		String tenNganh;
//		try {
//			tenNganh = obj.getString("major");
//			searchByCondition = candidateServiceImpl.findByEmail();
//			notContained = new ArrayList<>(resultListCandidates);
//			notContained.removeAll(searchByCondition);
//			resultListCandidates.removeAll(notContained);
//			notContained.clear();
//			searchByCondition.clear();
//		} catch (Exception e1) {
//			password = "";
//		}
//		
		return "";
	}

}
