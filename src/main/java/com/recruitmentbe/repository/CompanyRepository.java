package com.recruitmentbe.repository;

import java.util.List;

import com.recruitmentbe.model.Major;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.recruitmentbe.model.Company;

@Repository
public interface CompanyRepository extends JpaRepository<Company, Integer>{
	public List<Company> findAll();
	public Company findByCongtyId(long companyId);
    public List<Company> findByNganh(Major major);
    public List<Company> findByTenCongTyContaining(String tenCongTy);
	public Long removeByCongtyId(long congTyId);
}
