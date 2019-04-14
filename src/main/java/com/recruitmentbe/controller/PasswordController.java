package com.recruitmentbe.controller;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.recruitmentbe.model.Candidate;
import com.recruitmentbe.service.PasswordService;

@RestController
@RequestMapping("/api")
public class PasswordController {


    @Autowired
    private PasswordService passwordService;

    //Th&ecirc;m Staff v&agrave;o b&#x1ea3;ng Account

    @PostMapping("/addstaff")
    public String addStaff(@RequestBody Candidate candidate) {
        passwordService.addAcount(candidate);
        return "Ok";
    }

    @RequestMapping(value = "/getaccount", method = RequestMethod.GET)
    public Optional<Candidate> getaccount(@RequestParam(value = "id") long id) {

        return passwordService.getCandidateById(id);
    }

    // Trả về danh sách các Candidate ở trong bảng Candidate
    @RequestMapping(value="/getallcandidate", method = RequestMethod.GET)
    public List<Candidate> getallCandidate(){
        List<Candidate> allCandidates = passwordService.getAll();
        List<Candidate> rs = new ArrayList<Candidate>();

        for(Candidate c : allCandidates){
            rs.add(c.convert());
        }
        return rs;
    }

    @RequestMapping(value = "/getString",method = RequestMethod.GET)
    public List<Candidate> getString()
    {

        List<Candidate> list = new ArrayList<>();
        Candidate candidate = new Candidate();
        candidate.setEmail("abc");
        Candidate candidate1 = new Candidate();
        candidate.setEmail("abcfffff");

        list.add(candidate);
        list.add(candidate1);
        return list;
    }

}
