package com.recruitmentbe.repository;

import com.recruitmentbe.model.Job;
import com.recruitmentbe.model.UngTuyen;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface UngTuyenRepository extends JpaRepository<UngTuyen, Integer> {
    List<UngTuyen> findByJob(Job job);
}
