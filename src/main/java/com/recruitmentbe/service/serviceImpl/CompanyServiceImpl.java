package com.recruitmentbe.service.serviceImpl;

import java.io.UnsupportedEncodingException;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.recruitmentbe.model.Candidate;
import com.recruitmentbe.model.Company;
import com.recruitmentbe.model.CongTySaveUngVien;
import com.recruitmentbe.model.Job;
import com.recruitmentbe.model.Major;
import com.recruitmentbe.model.UngTuyen;
import com.recruitmentbe.repository.CandidateRepository;
import com.recruitmentbe.repository.CompanyRepository;
import com.recruitmentbe.repository.JobRepository;
import com.recruitmentbe.repository.MajorRepository;

@Service
public class CompanyServiceImpl implements CompanyService {

	@Autowired
	CompanyRepository companyRepo;

	@Autowired
	JobRepository jobRepo;

	@Autowired
	CandidateRepository candidateRepo;

	@Autowired
    MajorRepository majorRepository;

	@Override
	public List<Company> getAllCompany() {
		return companyRepo.findAll();
	}

	@Override
	public byte[] registerCompany(String body) {
		JSONObject obj = new JSONObject(body);
		String username;
		try {
			username = obj.getString("username");
		} catch (Exception e1) {
			username = "";
		}
		String email;
		try {
			email = obj.getString("email");
		} catch (Exception e1) {
			email = "";
		}
		String password;
		try {
			password = obj.getString("password");
		} catch (Exception e1) {
			password = "";
		}

		Company newCompany = new Company();
		newCompany.setEmail(email);
		newCompany.setTenCongTy(username);
		newCompany.setPassword(password);
		List<Company> allCompanys = companyRepo.findAll();
		if (allCompanys.size() == 0) {
			newCompany.setCongtyId(0);
		} else {
			newCompany.setCongtyId(allCompanys.get(allCompanys.size() - 1).getCongtyId() + 1);
		}
		try {
			companyRepo.save(newCompany);
			return new JSONObject(newCompany).toString().getBytes("UTF-8");
		} catch (UnsupportedEncodingException e) {
			return "".getBytes();
		} catch (Exception e) {
			return "".getBytes();
		}
	}

	@Override
	public Company updateProfileCompany(String body) {
		JSONObject requestObj = new JSONObject(body);
		Long companyId = requestObj.getLong("id");
        Long idNganh = requestObj.getLong("idNganh");
		Company updatedCompany;
		updatedCompany = companyRepo.findByCongtyId(companyId);
        Major major = majorRepository.findByNganhId(idNganh);
       // updatedCompany.setNganh(major);
		if (updatedCompany == null) {
			updatedCompany = new Company();
			updatedCompany.setCongtyId(companyId);
            updatedCompany.setNganh(major);
		}
		try {
			updatedCompany.setEmail(requestObj.getString("email"));
		} catch (Exception e) {
		}
		try {
			updatedCompany.setDiaChi(requestObj.getString("diaChi"));
		} catch (Exception e) {
		}
		try {
			updatedCompany.setSdt(requestObj.getString("sdt"));
		} catch (Exception e) {
		}
		try {
			updatedCompany.setTenCongTy(requestObj.getString("tenCongTy"));
		} catch (Exception e) {
		}

		try {
			updatedCompany.setMoTa(requestObj.getString("moTa"));
		} catch (Exception e) {
		}

		try {
			updatedCompany.setPhucLoi(requestObj.getString("phucLoi"));
		} catch (Exception e) {
		}

		try {
			updatedCompany.setQuyMo(requestObj.getInt("quyMo"));
		} catch (Exception e) {
		}
		
		try {
			updatedCompany.setImgUrl(requestObj.getString("imgUrl"));
		} catch (Exception e) {
		}
		try {
			companyRepo.save(updatedCompany);
			return updatedCompany;
		} catch (Exception e) {
			return null;
		}
	}

	@Override
	public Company findByCongTyId(Long id) {
		return companyRepo.findByCongtyId(id);
	}

	@Override
	public Set<Candidate> getCandidateByCompany(String id) {
		List<Job> allJobs = jobRepo.findAll();
		List<Long> allJobsRelatedToCompany = new ArrayList<Long>();
		for (Job j : allJobs) {
			if (j.getCongTy().getCongtyId() == Long.parseLong(id)) {
				allJobsRelatedToCompany.add(j.getJobId());
			}
		}
		List<Candidate> allCandidates = candidateRepo.findAll();
		Set<Candidate> allCandidateRelatedToCompany = new HashSet<Candidate>();
		for (Candidate c : allCandidates) {
			for (UngTuyen ut : c.getTinTuyenDungUngTuyen()) {
				if (allJobsRelatedToCompany.contains(ut.getJob().getJobId())) {
					allCandidateRelatedToCompany.add(c);
					break;
				}
			}
		}
		return allCandidateRelatedToCompany;
	}
	
	@Override
	public byte[] companySaveCandidate(String body) {
		JSONObject obj = new JSONObject(body);
		Long candidateId = obj.getLong("candidateId");
		Long companyId = obj.getLong("companyId");
		Candidate candidate = candidateRepo.findByUngVienId(candidateId);
		Company company = companyRepo.findByCongtyId(companyId);
		if(company.getUngVienSaved() == null || company.getUngVienSaved().size() == 0) {
			List<CongTySaveUngVien> ungVienSaved = new ArrayList<CongTySaveUngVien>();
			company.setUngVienSaved(ungVienSaved);
		}
		int duplicate = -1;
		for(int i = 0 ; i < company.getUngVienSaved().size() ; ++i) {
			CongTySaveUngVien ungVien = company.getUngVienSaved().get(i);
			if(ungVien.getUngVien().getUngVienId() == candidateId) {
				duplicate = i;
				break;
			}
		}
		
		if(duplicate != -1) {
			company.getUngVienSaved().remove(duplicate);
		}else {
			CongTySaveUngVien candidateWillBeSaved = new CongTySaveUngVien();
			candidateWillBeSaved.setCongTy(company);
			candidateWillBeSaved.setUngVien(candidate);
			candidateWillBeSaved.setNgaySave(new java.sql.Date((new java.util.Date()).getTime()));
			company.getUngVienSaved().add(candidateWillBeSaved);
		}

		try {
			companyRepo.save(company);
			JSONObject returnObj = new JSONObject("{\"status\" : \"success\"}");
			return returnObj.toString().getBytes("UTF-8");
		}catch(Exception e) {
			e.printStackTrace();
			return "".getBytes();
		}
	}

    @Override
    public List<Company> searchCompany(String body) {
	    JSONObject obj = new JSONObject(body);
        String nameCompany = obj.getString("companyName");
        String strIdNganh = obj.getString("careerId");
        long idNganh=0;
        if(strIdNganh.length()!=0){
            idNganh = Long.parseLong(strIdNganh);
        }
        if((nameCompany==null || nameCompany.length()==0) && (strIdNganh==null || strIdNganh.length()==0)){
            List<Company> listResult = companyRepo.findAll();
            return listResult;
        }else{
            if((nameCompany==null || nameCompany.length()==0) && idNganh!=0){
                Major major = majorRepository.findByNganhId(idNganh);
                List<Company> listResult = companyRepo.findByNganh(major);
                return  listResult;
            }else if(nameCompany!=null && (strIdNganh==null || strIdNganh.length()==0)){
                List<Company> listResult = companyRepo.findByTenCongTyContaining(nameCompany);
                return listResult;
            }else {
                Major major = majorRepository.findByNganhId(idNganh);
                List<Company> listCompanyByMajor = companyRepo.findByNganh(major);

                List<Company> listCompanyByTen = companyRepo.findByTenCongTyContaining(nameCompany);

                List<Company> listResult = new ArrayList<>();
                for(Company cByTen: listCompanyByTen){
                    for(Company cByMajor: listCompanyByMajor){
                        if(cByMajor.getCongtyId()==cByTen.getCongtyId()){
                            listResult.add(cByMajor);
                            break;
                        }
                    }
                }
                return  listResult;
            }
        }

    }

    @Transactional
	@Override
	public Company deleteCompany(String body) {
		JSONObject obj = new JSONObject(body);
        Long congTyId = obj.getLong("id");
        Company deletedCompany = companyRepo.findByCongtyId(congTyId);
        try {
        	companyRepo.removeByCongtyId(congTyId);
        	return deletedCompany;
        }catch(Exception e) {
        	e.printStackTrace();
            return null;
        }
	}
    
    
}
