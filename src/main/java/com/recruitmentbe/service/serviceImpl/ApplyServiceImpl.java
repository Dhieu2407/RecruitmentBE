package com.recruitmentbe.service.serviceImpl;

import com.recruitmentbe.model.Candidate;
import com.recruitmentbe.model.Company;
import com.recruitmentbe.model.Job;
import com.recruitmentbe.model.UngTuyen;
import com.recruitmentbe.repository.CandidateRepository;
import com.recruitmentbe.repository.CompanyRepository;
import com.recruitmentbe.repository.JobRepository;
import com.recruitmentbe.repository.UngTuyenRepository;
import com.recruitmentbe.service.ApplyService;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
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

    @Autowired
    CompanyRepository companyRepository;

    @Override
    public Integer getNumberNotification(String body) {
        int numberOfNotification = 0;
        JSONObject obj = new JSONObject(body);
        long idCompany = obj.getLong("id");
        Company company = companyRepository.findByCongtyId(idCompany);
        List<Job> listJob = jobRepository.findByCongTy(company);
        List<UngTuyen> listUngTuyen = new ArrayList<>();
        for(Job j : listJob){
            List<UngTuyen> list = ungTuyenRepository.findByJob(j);
            if(list.size()!=0 && list !=null){
                for(UngTuyen ut : list){
                    listUngTuyen.add(ut);
                    if(ut.getTrangThaiXem()==0){
                        numberOfNotification++;
                    }
                }
            }
        }

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
        UngTuyen ungTuyen = ungTuyenRepository.findByJobAndUngVien(job,candidate);
//        for(UngTuyen ut : listByCandidate){
//            for(UngTuyen ut1 : listByJob){
//                if(ut.getJob().getJobId()==ut1.getJob().getJobId() && ut.getUngVien().getUngVienId()==ut1.getUngVien().getUngVienId()){
//                    ungTuyen = ut;
//                    break;
//                }
//            }
//        }
        ungTuyen.setTrangThaiXem(1);
        return ungTuyenRepository.save(ungTuyen);
    }

    @Override
    public UngTuyen approveCandidate(String body) {
        JSONObject obj = new JSONObject(body);
        Long idJob = obj.getLong("jobId");
        Long idCandidate = obj.getLong("candidateId");
        int status = obj.getInt("trangThai");// 1 la chap nhan, 2 la tu choi
        Job job = jobRepository.findByJobId(idJob);
        Candidate candidate = candidateRepository.findByUngVienId(idCandidate);
        UngTuyen ungTuyen = ungTuyenRepository.findByJobAndUngVien(job,candidate);
        ungTuyen.setTrangThai(status);
        return ungTuyenRepository.save(ungTuyen);
    }

    @Override
    public UngTuyen findApply(String body) {
        JSONObject obj = new JSONObject(body);
        Long idJob = obj.getLong("jobId");
        Long idCandidate = obj.getLong("candidateId");
        Job job = jobRepository.findByJobId(idJob);
        Candidate candidate = candidateRepository.findByUngVienId(idCandidate);
        UngTuyen ungTuyen = ungTuyenRepository.findByJobAndUngVien(job,candidate);
        return ungTuyen;
    }
}
