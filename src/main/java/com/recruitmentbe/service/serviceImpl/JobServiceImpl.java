package com.recruitmentbe.service.serviceImpl;

import com.recruitmentbe.model.Job;
import com.recruitmentbe.repository.JobRepository;
import com.recruitmentbe.service.JobService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
@Service
public class JobServiceImpl implements JobService {

    @Autowired
    JobRepository jobRepository;
    @Override
    public List<Job> getAllJob() {
        return jobRepository.findAll();
    }
}
