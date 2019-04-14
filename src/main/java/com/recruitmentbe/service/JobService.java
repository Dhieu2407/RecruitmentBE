package com.recruitmentbe.service;


import com.recruitmentbe.model.Job;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface JobService{
    public List<Job> getAllJob();

}
