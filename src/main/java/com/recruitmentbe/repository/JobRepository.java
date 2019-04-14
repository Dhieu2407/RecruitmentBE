package com.recruitmentbe.repository;

import com.recruitmentbe.model.Job;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface JobRepository extends JpaRepository<Job,Integer> {
    public List<Job> findAll();
}
