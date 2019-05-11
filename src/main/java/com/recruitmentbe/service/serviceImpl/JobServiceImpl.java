package com.recruitmentbe.service.serviceImpl;

import com.recruitmentbe.model.*;
import com.recruitmentbe.repository.CompanyRepository;
import com.recruitmentbe.repository.JobRepository;
import com.recruitmentbe.repository.MajorRepository;
import com.recruitmentbe.service.JobService;
import org.json.JSONObject;
import org.omg.PortableInterceptor.INACTIVE;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.ui.Model;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Optional;

@Transactional
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
        String tenNganh;
        String tenCongTy;

        List<Job> resultListJobs = new ArrayList<>();
        List<Job> searchByCondition = new ArrayList<>();
        List<Job> notContained = new ArrayList<>();

        // tenJob bao tim kim theo tenJob,tenCongTy, va Mota
        if(!obj.getString("tenJob").equals("")){
            try {
                tenJob = obj.getString("tenJob");
                resultListJobs = jobRepository.findByTenJobContaining(tenJob);

            }catch (Exception ex){
            }
            // tim kiem theo ten cong
            try{
                tenCongTy = obj.getString("tenJob");
                List<Company> listCompany = companyRepository.findByTenCongTyContaining(tenCongTy);

                if(listCompany.size() > 0) {
                    List<Job> allJobs = jobRepository.findAll();
                    searchByCondition = new ArrayList<Job>();
                    for(Job job : allJobs) {
                        if(job.getCongTy().getTenCongTy().equals(listCompany.get(0).getTenCongTy())) {
                            searchByCondition.add(job);
                        }
                    }
                    List<Job> listJobTemp = new ArrayList<>(resultListJobs);
                    if(resultListJobs.size()>0){
                        for(Job jobSearch : searchByCondition){
                            for(Job jobResult : resultListJobs){
                                if(jobSearch.getJobId()!=jobResult.getJobId()){
                                    listJobTemp.add(jobSearch);
                                    break;
                                }
                            }
                        }
                        searchByCondition.clear();
                        resultListJobs.clear();
                        resultListJobs = listJobTemp;
                    }else {
                        resultListJobs = searchByCondition;
                    }


                }

            }catch (Exception ex){
            }
            //tim kiem theo mo ta
            try{
                String moTa = obj.getString("tenJob");
                searchByCondition= jobRepository.findByChiTietContaining(moTa);


                List<Job> listJobTemp = new ArrayList<>(resultListJobs);
                if(resultListJobs.size()>0){
                    for(Job jobSearch : searchByCondition){
                        for(Job jobResult : resultListJobs){
                            if(jobSearch.getJobId()!=jobResult.getJobId()){
                                listJobTemp.add(jobSearch);
                                break;
                            }
                        }
                    }
                    searchByCondition.clear();
                    resultListJobs.clear();
                    resultListJobs = listJobTemp;
                }else {
                    resultListJobs = searchByCondition;
                }




            }catch (Exception ex){
            }
        }else {
            resultListJobs = jobRepository.findAll();
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
        tenNganh = obj.getString("tenNganh");
        if(tenNganh.equals("") || tenNganh.equals("0")){
            tenNganh = "";
        }else {
            try{
                long idMajor = Long.parseLong(tenNganh);
                Major major = majorRepository.findByNganhId(idMajor);
                if(major != null) {
                    List<Job> allJobs = new ArrayList<>(resultListJobs);
                    searchByCondition = new ArrayList<Job>();
                    for(Job job : allJobs) {
                        if(job.getNganh().getTenNganh().equals(major.getTenNganh())) {
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
        }


        return resultListJobs;
    }


    @Override
    public Job findJobById(String body) {
        JSONObject obj = new JSONObject(body);
        String id = obj.getString("jobId");
        long idJob = Long.parseLong(id);
        return jobRepository.findByJobId(idJob);
    }

    @Override
    public List<Job> findJobRelate(String body) {
        JSONObject obj = new JSONObject(body);
        String id = obj.getString("nganhId");
        long idNganh = Long.parseLong(id);
        Major major = majorRepository.findByNganhId(idNganh);
        if (major != null) {
            List<Job> allJobs = jobRepository.findAll();
            List<Job> listJobRelate1 = new ArrayList<>();
            List<Job> listJobRelate2 = new ArrayList<>();
            for (Job job : allJobs) {
                if (job.getNganh().getTenNganh().equals(major.getTenNganh())) {
                    listJobRelate1.add(job);
                }
            }
            if (listJobRelate1.size() > 3) {
                for (int i = 0; i < 3; i++) {
                    listJobRelate2.add(listJobRelate1.get(i));
                }
                return listJobRelate2;
            } else {
                return listJobRelate1;
            }
        }else {
            return null;
        }
    }

    @Override
    public List<Job> findByTrangThai(String body) {
        JSONObject obj = new JSONObject(body);
        String trangThaiString = obj.getString("trangThai");
        int trangThai = Integer.parseInt(trangThaiString);
        return jobRepository.findByTrangThai(trangThai);
    }

    @Override
    public List<Job> findJobByIdCompany(String body) {
        JSONObject obj = new JSONObject(body);
        long idCompanyLong = obj.getLong("id");
        Company company = companyRepository.findByCongtyId(idCompanyLong);
        List<Job> listJobOfCompany = new ArrayList<>();
        if(company!=null){
            List<Job> allJob = jobRepository.findAll();
            for (Job job : allJob){
                if(job.getCongTy().getCongtyId()==company.getCongtyId()){
                    listJobOfCompany.add(job);
                }
            }
        }
        return listJobOfCompany;
    }

    @Override
    public Long deleteJob(String body) {
        JSONObject obj = new JSONObject(body);
        long idJob = obj.getLong("jobId");
        Job deletedJob = jobRepository.findByJobId(idJob);
        return jobRepository.removeByJobId(idJob);
    }

    @Override
    public Job updateJob(String body) {
        JSONObject obj = new JSONObject(body);


        long idJob = obj.getLong("jobId");
        Job updateJob = jobRepository.findByJobId(idJob);

        long luongToiThieu = obj.getLong("luongToiThieu");
        long luongToiDa = obj.getLong("luongToiDa");
        updateJob.setLuongToiThieu(luongToiThieu);
        updateJob.setLuongToiDa(luongToiDa);

        String tenJob = obj.getString("tenJob");
        updateJob.setTenJob(tenJob);

        String chucVu = obj.getString("chucVu1");
        updateJob.setChucVu1(chucVu);

        String diaDiem = obj.getString("diaChi");
        updateJob.setDiaChi(diaDiem);

        String moTaJob = obj.getString("chiTiet");
        updateJob.setChiTiet(moTaJob);

        String yeuCauUngVien = obj.getString("yeuCauUngVien");
        updateJob.setYeuCauCongViec(yeuCauUngVien);

        String quyenLoi = obj.getString("quyenLoi");
        updateJob.setQuyenLoi(quyenLoi);

        String yeuCauHoSo = obj.getString("yeuCauHoSo");
        updateJob.setYeuCauHoSo(yeuCauHoSo);

        int trangThai = Integer.parseInt(obj.getString("trangThai"));
        updateJob.setTrangThai(trangThai);

        int soNamKinhNghiem = obj.getInt("knToiThieu");
        updateJob.setKnToiThieu(soNamKinhNghiem);


        int soLuong = obj.getInt("soLuong");
        updateJob.setSoLuong(soLuong);


        Date hanCuoi = new Date();
        try {
            hanCuoi=new SimpleDateFormat("yyyy-MM-dd").parse(obj.getString("hanCuoi"));
        } catch (ParseException e) {
            e.printStackTrace();
        }

        java.sql.Date sqlHanCuoi = new java.sql.Date(hanCuoi.getTime());
        updateJob.setHanCuoi((java.sql.Date) sqlHanCuoi);
        return jobRepository.save(updateJob);
    }


    @Override
    public Job addJobStringBody(String body) {

        JSONObject obj = new JSONObject(body);
        Job addJob = new Job();
        long idCompany = obj.getLong("id");
        Company company = companyRepository.findByCongtyId(idCompany);
        addJob.setCongTy(company);
        long idMajor = Long.parseLong(obj.getString("tenNganh"));
        Major major = majorRepository.findByNganhId(idMajor);
        addJob.setNganh(major);
        long luongToiThieu = obj.getLong("luongToiThieu");
        long luongToiDa = obj.getLong("luongToiDa");
        addJob.setLuongToiThieu(luongToiThieu);
        addJob.setLuongToiDa(luongToiDa);

        String tenJob = obj.getString("tenJob");
        addJob.setTenJob(tenJob);

        String chucVu = obj.getString("chucVu1");
        addJob.setChucVu1(chucVu);

        String diaDiem = obj.getString("diaChi");
        addJob.setDiaChi(diaDiem);

        String moTaJob = obj.getString("chiTiet");
        addJob.setChiTiet(moTaJob);

        String yeuCauUngVien = obj.getString("yeuCauUngVien");
        addJob.setYeuCauCongViec(yeuCauUngVien);

        String quyenLoi = obj.getString("quyenLoi");
        addJob.setQuyenLoi(quyenLoi);

        String yeuCauHoSo = obj.getString("yeuCauHoSo");
        addJob.setYeuCauHoSo(yeuCauHoSo);

        int soNamKinhNghiem = obj.getInt("knToiThieu");
        addJob.setKnToiThieu(soNamKinhNghiem);

        int yeuCauGioiTinh = obj.getInt("gioiTinh");
        addJob.setGioiTinh(yeuCauGioiTinh);

        int soLuong = obj.getInt("soLuong");
        addJob.setSoLuong(soLuong);


        int tgLamViec = obj.getInt("tgLamViec");
        addJob.setTgLamViec(tgLamViec);

        addJob.setTrangThai(0);

        Date hanCuoi = new Date();
        try {
            hanCuoi=new SimpleDateFormat("yyyy-MM-dd").parse(obj.getString("hanCuoi"));
        } catch (ParseException e) {
            e.printStackTrace();
        }

        java.sql.Date sqlHanCuoi = new java.sql.Date(hanCuoi.getTime());
        addJob.setHanCuoi((java.sql.Date) sqlHanCuoi);
//
        Date ngayDang = new Date();
        java.sql.Date sqlNgayDang = new java.sql.Date(ngayDang.getTime());
        addJob.setNgayDang((java.sql.Date) sqlNgayDang);

        List<Job> allJob = jobRepository.findAll();
        if(allJob.size()==0){
            addJob.setJobId(1);
        }else {
            addJob.setJobId(allJob.get(allJob.size()-1).getJobId()+1);
        }

        try {
            jobRepository.save(addJob);
        }catch (Exception e){
            e.printStackTrace();
        }

        return addJob;
    }



   /* {
        "tenJob": "WEB",
       "tenCongTy": "VNG",
       "diaChi"  : "",
       "tenNganh":""

        {
        {
    "chucVu1": "4",
    "description": "4",
    "duedate": "2019-04-10",
    "location": "4",
    "major": "IT & Engineering",
    "quyenLoi": "4",
    "requireCadiate": "4",
    "requireYear": "4",
    "salaryMax": "4",
    "salaryMin": "4",
    "soLuong": "4",
    "tgLamViec": "1",
    "titleJob": "4",
    "yeuCauGioiTinh": "2",
    "yeuCauHoSo": "4"}
        }





    }*/
}
