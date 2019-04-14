package com.recruitmentbe.web.rest;

import java.io.UnsupportedEncodingException;
import java.util.List;

import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.recruitmentbe.model.Candidate;
import com.recruitmentbe.service.CandidateService;


@RestController
public class CandidateController {
	@Autowired
	private CandidateService candidateServiceImpl;

	@PostMapping(value = "/candidateRegister")
	public byte[] addNews(@RequestBody String body) throws Exception {
		System.out.println("add candidate");
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

	@GetMapping(value = "/datn/allCandidates")
	public List<Candidate> getAllNews() {
		System.out.println("all");
		List<Candidate> allCandidates = candidateServiceImpl.getAllCandidate();
//		try {
//			return (new JSONArray(allCandidates)).toString().getBytes("UTF-8");
//		} catch (Exception e) {
//			e.printStackTrace();
//			return "".getBytes();
//		}
		return allCandidates;
	}

	@GetMapping(value = "/all")
	public String test() {
		System.out.println("test");
		return "";
	}

}
