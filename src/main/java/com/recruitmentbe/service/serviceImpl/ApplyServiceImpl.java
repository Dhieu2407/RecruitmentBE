package com.recruitmentbe.service.serviceImpl;

import com.recruitmentbe.model.Candidate;
import com.recruitmentbe.model.Job;
import com.recruitmentbe.model.UngTuyen;
import com.recruitmentbe.repository.CandidateRepository;
import com.recruitmentbe.repository.JobRepository;
import com.recruitmentbe.repository.UngTuyenRepository;
import com.recruitmentbe.service.ApplyService;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Transactional
@Service
public class ApplyServiceImpl implements ApplyService {

    @Autowired
    UngTuyenRepository ungTuyenRepository;

    @Autowired
    JobRepository jobRepository;

    @Autowired
    CandidateRepository candidateRepository;

    @Override
    public Integer getNumberNotification() {
        int numberOfNotification = 0;
        List<UngTuyen>  list = ungTuyenRepository.findByTrangThaiXem(0);
        numberOfNotification = list.size();
        return numberOfNotification;
    }

    @Override
    public UngTuyen chuyenTrangThaiXem(String body) {
        JSONObject obj = new JSONObject(body);
        Long idJob = obj.getLong("jobId");
        Long idCandidate = obj.getLong("candidateId");
        Job job = jobRepository.findByJobId(idJob);
        Candidate candidate = candidateRepository.findByUngVienId(idCandidate);
        List<UngTuyen> listByCandidate = ungTuyenRepository.findByUngVien(candidate);
        List<UngTuyen> listByJob = ungTuyenRepository.findByJob(job);
        UngTuyen ungTuyen = new UngTuyen();
        for(UngTuyen ut : listByCandidate){
            for(UngTuyen ut1 : listByJob){
                if(ut.getJob().getJobId()==ut1.getJob().getJobId() && ut.getUngVien().getUngVienId()==ut1.getUngVien().getUngVienId()){
                    ungTuyen = ut;
                    break;
                }
            }
        }
        ungTuyen.setTrangThaiXem(1);
        return ungTuyenRepository.save(ungTuyen);
    }
}
