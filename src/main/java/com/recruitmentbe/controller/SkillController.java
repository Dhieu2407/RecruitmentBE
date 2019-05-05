package com.recruitmentbe.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.recruitmentbe.model.Skill;
import com.recruitmentbe.service.SkillService;

@RestController
@RequestMapping("/api/skill")
public class SkillController {
	@Autowired
	SkillService skillServiceImpl;
	
	@GetMapping(value = "/getAllSkills")
	public List<Skill> getAllSkills() {
		List<Skill> allSkills = skillServiceImpl.getAllSkills();
		return allSkills;
	}
}
