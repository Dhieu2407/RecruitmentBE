package com.recruitmentbe.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.recruitmentbe.model.Skill;

@Repository
public interface SkillRepository extends JpaRepository<Skill, Integer>{
	public List<Skill> findAll();
	public List<Skill> findById();
	public List<Skill> findByTenKiNang(String tenKiNang);
	
}
