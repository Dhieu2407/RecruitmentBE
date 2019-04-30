package com.recruitmentbe.service.serviceImpl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.recruitmentbe.model.Skill;
import com.recruitmentbe.repository.SkillRepository;
import com.recruitmentbe.service.SkillService;

@Service
public class SkillServiceImpl implements SkillService {
	@Autowired
	SkillRepository skillRepo;
	public List<Skill> getAllSkills(){
		return skillRepo.findAll(); 
	}

}
