package com.recruitmentbe.controller;

import java.io.UnsupportedEncodingException;
import java.util.List;

import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.recruitmentbe.model.Candidate;
import com.recruitmentbe.model.Job;
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

    @GetMapping(value = "/getCandidate/{id}")
    public Candidate getCandidate(@PathVariable("id") String idString) {
	    Long id = Long.parseLong(idString);
        Candidate candidate = candidateServiceImpl.findByUngVienId(id);
        if(candidate == null) {
            ResponseEntity.notFound().build();
        }
        return candidate;
    }

	@PostMapping(value = "/searchCandidates")
	public List<Candidate> searchCandidates(@RequestBody String body) {
		return candidateServiceImpl.findCandidateByConditions(body);
	}
	
	@PostMapping(value = "/updateProfileCandidates")
	public Candidate updateProfileCandidates(@RequestBody String body) {
		return candidateServiceImpl.updateProfileCandidates(body);
	}
	
	@PostMapping(value = "/bookmarkJob")
	public byte[] candidateBookmarkJob(@RequestBody String body) {
		return candidateServiceImpl.candidateBookmarkJob(body);
	}
	
	@GetMapping(value = "/getBookmarkedJobs/{candidateId}")
	public List<Job> getBookmarkedJobs (@PathVariable("candidateId") String idString){
		Candidate currentCandidate = candidateServiceImpl.findByUngVienId(Long.parseLong(idString));
		return currentCandidate.getTinTuyenDung();
	}

}
