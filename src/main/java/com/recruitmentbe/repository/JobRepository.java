package com.recruitmentbe.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.recruitmentbe.model.Company;
import com.recruitmentbe.model.Job;
import com.recruitmentbe.model.Major;

public interface JobRepository extends JpaRepository<Job,Integer> {
    public List<Job> findAll();
    public List<Job> findByTrangThai(int trangThai);
    public Job findByJobId(long id);
    public List<Job> findByTenJobContainingAndTrangThai(String tenUngVien,int tt);
    public List<Job> findByDiaChiContainingAndTrangThai(String diachi,int tt);
    public List<Job> findByChiTietContainingAndTrangThai(String moTa,int tt);
    public Long removeByJobId(long idJob);
    public List<Job> findByCongTyAndTrangThai(Company company, int tt);
    public List<Job> findByCongTy(Company company);
    public List<Job> findByNganh(Major major);
}
