package com.recruitmentbe.service.serviceImpl;

import com.recruitmentbe.model.Company;
import com.recruitmentbe.model.Job;
import com.recruitmentbe.model.Major;
import com.recruitmentbe.repository.CompanyRepository;
import com.recruitmentbe.repository.JobRepository;
import com.recruitmentbe.repository.MajorRepository;
import com.recruitmentbe.service.JobService;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
@Service
public class JobServiceImpl implements JobService {

    @Autowired
    JobRepository jobRepository;

    @Autowired
    MajorRepository majorRepository;

    @Autowired
    private CompanyRepository companyRepository;
    @Override
    public List<Job> getAllJob() {
        return jobRepository.findAll();
    }

    @Override
    public List<Job> findJob(String body) {
        JSONObject obj = new JSONObject(body);
        String tenJob;
        String diaChi;
        String tenNgannh;
        String tenCongty;

        List<Job> resultListJobs = new ArrayList<>();
        List<Job> searchByCondition = new ArrayList<>();
        List<Job> notContained = new ArrayList<>();


        try {

            tenJob = obj.getString("tenJob");
            resultListJobs = jobRepository.findByTenJobContaining(tenJob);

        }catch (Exception ex){
        }
        try{
            tenCongty = obj.getString("tenCongty");
            List<Company> listCompany = companyRepository.findByTenCongTyContaining(tenCongty);

            if(listCompany.size() > 0) {
                List<Job> allJobs = jobRepository.findAll();
                searchByCondition = new ArrayList<Job>();
                for(Job job : allJobs) {
                    if(job.getCongTy().getTenCongTy().equals(listCompany.get(0).getTenCongTy())) {
                        searchByCondition.add(job);
                    }
                }
                List<Job> listJobTemp = new ArrayList<>();
                for(Job jobSearch : searchByCondition){
                    for(Job jobResult : resultListJobs){
                        if(jobSearch.getJobId()==jobResult.getJobId()){
                            listJobTemp.add(jobSearch);
                        }
                    }
                }
                searchByCondition.clear();
                resultListJobs.clear();
                resultListJobs = listJobTemp;
            }

        }catch (Exception ex){
        }

        try{
            diaChi = obj.getString("diaChi");
            searchByCondition = jobRepository.findByDiaChiContaining(diaChi);

            List<Job> listJobTemp = new ArrayList<>();
            for(Job jobSearch : searchByCondition){
                for(Job jobResult : resultListJobs){
                    if(jobSearch.getJobId()==jobResult.getJobId()){
                        listJobTemp.add(jobSearch);
                    }
                }
            }
            searchByCondition.clear();
            resultListJobs.clear();
            resultListJobs = listJobTemp;

        }catch (Exception ex){

        }

        try{
            tenNgannh = obj.getString("tenNgannh");
            List<Major> listMajor = majorRepository.findByTenNganhContaining(tenNgannh);

            if(listMajor.size() > 0) {
                List<Job> allJobs = new ArrayList<>(resultListJobs);
                searchByCondition = new ArrayList<Job>();
                for(Job job : allJobs) {
                    if(job.getNganh().getTenNganh().equals(listMajor.get(0).getTenNganh())) {
                        searchByCondition.add(job);
                    }
                }
                List<Job> listJobTemp = new ArrayList<>();
                for(Job jobSearch : searchByCondition){
                    for(Job jobResult : resultListJobs){
                        if(jobSearch.getJobId()==jobResult.getJobId()){
                            listJobTemp.add(jobSearch);
                        }
                    }
                }
                searchByCondition.clear();
                resultListJobs.clear();
                resultListJobs = listJobTemp;
            }


        }catch (Exception ex){

        }




        return resultListJobs;
    }

   /* {
        "tenJob": "WEB",
       "tenCongty": "VNG",
       "diaChi"  : "",
       "tenNgannh":""

    }*/
}
