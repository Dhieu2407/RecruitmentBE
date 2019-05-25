package com.recruitmentbe.controller;

import com.recruitmentbe.model.UngTuyen;
import com.recruitmentbe.service.serviceImpl.ApplyServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/apply")
public class ApplyController {

    @Autowired
    ApplyServiceImpl applyService;

    @PostMapping(value = "/getNumBerNotify")
    public Integer getNumberNotifi(@RequestBody String body) {
        return applyService.getNumberNotification(body) ;
    }

    @PostMapping(value = "/chuyenTrangThaiXem")
    public UngTuyen chuyenTTXem(@RequestBody String body){
        return applyService.chuyenTrangThaiXem(body);
    }

    @PostMapping(value = "/approveCandidate")
    public  UngTuyen approveCandidate(@RequestBody String body){
        return applyService.approveCandidate(body);
    }

    @PostMapping(value = "/findApply")
    public UngTuyen findApply(@RequestBody String body){
        return applyService.findApply(body);
    }
}
