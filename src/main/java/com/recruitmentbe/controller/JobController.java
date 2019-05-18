package com.recruitmentbe.controller;

import com.recruitmentbe.model.Candidate;
import com.recruitmentbe.model.Job;
import com.recruitmentbe.model.Major;
import com.recruitmentbe.repository.CandidateRepository;
import com.recruitmentbe.repository.JobRepository;
import com.recruitmentbe.service.CandidateService;
import com.recruitmentbe.service.JobService;
import com.recruitmentbe.service.MajorService;
import org.json.JSONArray;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/job")
public class JobController {

    @Autowired
    CandidateService candidateRepository;

    @Autowired
    JobService jobService;

    @Autowired
    MajorService majorService;

    @GetMapping(value = "/getJob")
    public List<Job> getAllJobs() {
        System.out.println("all");
        List<Job> allJob = jobService.getAllJob();
        return allJob;
    }

    @PostMapping(value = "/searchJobs")
    public List<Job> searchJobs(@RequestBody String body) {
        return jobService.findJob(body);
    }

    @PostMapping(value = "/getJobById")
    public Job findJobByIdJob(@RequestBody String body){
        return jobService.findJobById(body);
    }

    @PostMapping(value = "/getListJobRelate")
    public List<Job> listJobRelate(@RequestBody String body){
        return jobService.findJobRelate(body);
    }

    @PostMapping(value = "/addJob")
    public Job addJob(@RequestBody String body){
        Job jobResult = jobService.addJobStringBody(body);
        if(jobResult!=null){
            return jobResult;
        }else {
            return null;
        }

    }

    @PostMapping(value = "/findByTrangThai")
    public List<Job> findByTrangThai(@RequestBody String body){
        return jobService.findByTrangThai(body);
    }

    @PostMapping(value = "/getListJobOfCompany")
    public List<Job> getListJobOfCompany(@RequestBody String body){
        return jobService.findJobByIdCompany(body);
    }


    @PostMapping(value = "/deleteJobById")
    public Long deleteJobById(@RequestBody String body){

        return jobService.deleteJob(body);

    }

    @PostMapping(value = "/updateJob")
    public Job updateJob(@RequestBody String body){
        return jobService.updateJob(body);
    }



}
