package com.recruitmentbe.controller;

import com.recruitmentbe.model.Candidate;
import com.recruitmentbe.model.Job;
import com.recruitmentbe.repository.CandidateRepository;
import com.recruitmentbe.repository.JobRepository;
import com.recruitmentbe.service.CandidateService;
import com.recruitmentbe.service.JobService;
import org.json.JSONArray;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api")
public class JobController {

    @Autowired
    CandidateService candidateRepository;

    @Autowired
    JobService jobService;

    @GetMapping(value = "/getAllCandidates1")
    public List<Candidate> getAllCandidates() {
        System.out.println("all");
        List<Candidate> allCandidates = candidateRepository.getAllCandidate();
        return allCandidates;
    }

    @GetMapping(value = "/getJob")
    public List<Job> getAllJobs() {
        System.out.println("all");
        List<Job> allJOb = jobService.getAllJob();
        return allJOb;
    }


}