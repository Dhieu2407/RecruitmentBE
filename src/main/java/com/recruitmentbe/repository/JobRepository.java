package com.recruitmentbe.repository;

import com.recruitmentbe.model.Job;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface JobRepository extends JpaRepository<Job,Integer> {
    public List<Job> findAll();
    public Job findByJobId(long id);
    public List<Job> findByTenJobContaining(String tenUngVien);
    public List<Job> findByDiaChiContaining(String diachi);
    public List<Job> findByChiTietContaining(String moTa);
    public Long removeByJobId(long idJob);


}
