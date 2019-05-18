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

    @GetMapping(value = "/getNumBerNotify")
    public Integer getNumberNotifi() {
        return applyService.getNumberNotification() ;
    }

    @PostMapping(value = "/chuyenTrangThaiXem")
    public UngTuyen chuyenTTXem(@RequestBody String body){
        return applyService.chuyenTrangThaiXem(body);
    }
}
