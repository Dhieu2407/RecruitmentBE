package com.recruitmentbe.service.serviceImpl;

import java.sql.Date;
import java.util.ArrayList;
import java.util.List;

import org.json.JSONArray;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.recruitmentbe.model.Candidate;
import com.recruitmentbe.model.Certificate;
import com.recruitmentbe.model.Company;
import com.recruitmentbe.model.CongTySaveUngVien;
import com.recruitmentbe.model.Job;
import com.recruitmentbe.model.Major;
import com.recruitmentbe.model.Skill;
import com.recruitmentbe.model.UngTuyen;
import com.recruitmentbe.model.UngVienChungChi;
import com.recruitmentbe.model.UngVienKiNang;
import com.recruitmentbe.model.UngVienSaveCongTy;
import com.recruitmentbe.repository.CandidateRepository;
import com.recruitmentbe.repository.CertificateRepository;
import com.recruitmentbe.repository.CompanyRepository;
import com.recruitmentbe.repository.JobRepository;
import com.recruitmentbe.repository.MajorRepository;
import com.recruitmentbe.repository.SkillRepository;
import com.recruitmentbe.repository.UngTuyenRepository;
import com.recruitmentbe.service.CandidateService;

@Transactional
@Service
public class CandidateServiceImpl implements CandidateService {

	@Autowired
	private CandidateRepository candidateRepo;

	@Autowired
	private MajorRepository majorRepo;

	@Autowired
	private SkillRepository skillRepo;
	
	@Autowired
	private JobRepository jobRepo;

	@Autowired
    private UngTuyenRepository ungTuyenRepository;

	@Autowired
    private CompanyRepository companyRepo;

	@Autowired
    private CertificateRepository certificateRepo;

	@Override
	public List<Candidate> getAllCandidate() {
		return candidateRepo.findAll();
	}

	@Override
	public Candidate findByUngVienId(Long id) {
		return candidateRepo.findByUngVienId(id);
	}

	@Override
	public Candidate registerCandidate(String username, String email, String password) {
		Candidate candidate = new Candidate();
		candidate.setEmail(email);
		candidate.setTenUngVien(username);
		candidate.setPassword(password);
		List<Candidate> allCandidates = candidateRepo.findAll();
		if (allCandidates.size() == 0) {
			candidate.setUngVienId(0L);
		} else {
			candidate.setUngVienId(allCandidates.get(allCandidates.size() - 1).getUngVienId() + 1);
		}
		try {
			candidateRepo.save(candidate);
			return candidate;
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}

	@Override
	public List<Candidate> findCandidateByConditions(String body) {
		JSONObject obj = new JSONObject(body);
		String username;
		List<Candidate> resultListCandidates = candidateRepo.findAll();
		List<Candidate> searchByCondition = new ArrayList<>();
		List<Candidate> notContained = new ArrayList<>();
		try {
			username = obj.getString("username");
			searchByCondition = candidateRepo.findByTenUngVienContaining(username);
//			notContained = new ArrayList<>(resultListCandidates);
//			notContained.removeAll(searchByCondition);
//			resultListCandidates.removeAll(notContained);
//			notContained.clear();
//			searchByCondition.clear();
		} catch (Exception e1) {

		}
		String email;
		try {
			email = obj.getString("email");
			searchByCondition.addAll(candidateRepo.findByEmailContaining(email));
			notContained = new ArrayList<>(resultListCandidates);
			notContained.removeAll(searchByCondition);
			resultListCandidates.removeAll(notContained);
			notContained.clear();
			searchByCondition.clear();
		} catch (Exception e1) {

		}
		String tenNganh;
		try {
			tenNganh = obj.getString("major");
			if (tenNganh.trim().length() != 0) {
				List<Major> majorSearchedByCondition = majorRepo.findByTenNganhContaining(tenNganh);
				if (majorSearchedByCondition.size() > 0) {
					List<Candidate> allCandidates = candidateRepo.findAll();
					searchByCondition = new ArrayList<Candidate>();
					for (Candidate c : allCandidates) {
						if (c.getNganh().getNganhId() == majorSearchedByCondition.get(0).getNganhId()) {
							searchByCondition.add(c);
						}
					}
					notContained = new ArrayList<>(resultListCandidates);
					notContained.removeAll(searchByCondition);
					resultListCandidates.removeAll(notContained);
					notContained.clear();
					searchByCondition.clear();
				}
			}
		} catch (Exception e1) {

		}
		List<Candidate> tempListCandidate = new ArrayList<>();
		
		try {
			int kiNangIdSearched = obj.getInt("kiNang");
			tempListCandidate = resultListCandidates;
			resultListCandidates = new ArrayList<Candidate>();
			for(Candidate c : tempListCandidate) {
				if(c.getKiNang() == null || c.getKiNang().size() == 0) {
					continue;
				}
				for(UngVienKiNang uvkn : c.getKiNang()) {
					if(uvkn.getKiNang().getKinangId() == kiNangIdSearched) {
						resultListCandidates.add(c);
						break;
					}
				}
			}
		}catch(Exception e) {
			
		}
		
		tempListCandidate = new ArrayList<>();
		tempListCandidate.addAll(resultListCandidates);
		resultListCandidates = new ArrayList<Candidate>();
		for(Candidate c : tempListCandidate) {
			Candidate rsCandidate = new Candidate();
			rsCandidate.setUngVienId(c.getUngVienId());
			rsCandidate.setSdt(c.getSdt());
			rsCandidate.setEmail(c.getEmail());
			rsCandidate.setTenUngVien(c.getTenUngVien());
			rsCandidate.setImgUrl(c.getImgUrl());
			rsCandidate.setLuongMongMuon(c.getLuongMongMuon());
			rsCandidate.setDiaChi(c.getDiaChi());
			rsCandidate.setNganh(c.getNganh());
			resultListCandidates.add(rsCandidate);
		}
		
		return resultListCandidates;
	}

	@Override
	public Candidate updateProfileCandidates(String body) {
		JSONObject requestObj = new JSONObject(body);
		long ungVienId = requestObj.getLong("id");
		Candidate updatedCandidate = null;
		updatedCandidate = candidateRepo.findByUngVienId(ungVienId);
		if (updatedCandidate == null) {
			updatedCandidate = new Candidate();
			updatedCandidate.setUngVienId(ungVienId);
		}

		try {
			updatedCandidate.setTenUngVien(requestObj.getString("name"));
		} catch (Exception e) {

		}
		try {
			updatedCandidate.setDiaDiem(requestObj.getString("diaDiemLamViec"));
		} catch (Exception e) {

		}
		try {
			updatedCandidate.setEmail(requestObj.getString("email"));
		} catch (Exception e) {

		}
		try {
			updatedCandidate.setMoTa(requestObj.getString("moTa"));
		} catch (Exception e) {

		}
		try {
			updatedCandidate.setNganh(majorRepo.findByTenNganhContaining(requestObj.getString("major")).get(0));
		} catch (Exception e) {

		}
		try {
			updatedCandidate.setDiaChi(requestObj.getString("address"));
		} catch (Exception e) {

		}
		try {
			updatedCandidate.setSdt(requestObj.getString("phone")); 
		} catch (Exception e) {

		}
		try {
			updatedCandidate.setImgUrl(requestObj.getString("imgUrl")); 
		} catch (Exception e) {

		}
		try {
			updatedCandidate.setMucTieuNgheNghiep(requestObj.getString("careerGoals"));
		} catch (Exception e) {

		}
		try {
			updatedCandidate.setLuongMongMuon(requestObj.getBigDecimal("wantedSalary").longValue());
		} catch (Exception e) {

		}
		try {
			updatedCandidate.setLichSuLamViec(requestObj.getJSONArray("workDescription").toString());
		} catch (Exception e) {

		}
		try {
			updatedCandidate.setTrinhDoDaiHoc(requestObj.getJSONArray("school").toString()); 
		} catch (Exception e) {

		}
		
		
		//skill
		JSONArray cacKiNang = requestObj.getJSONArray("skill");
		ArrayList<Integer> kinhNghiemKiNang = new ArrayList<Integer>();
		ArrayList<String> cacTenKiNang = new ArrayList<String>();
		for (int i = 0; i < cacKiNang.length(); ++i) {
			JSONObject obj = cacKiNang.getJSONObject(i);
			kinhNghiemKiNang.add(obj.getInt("expYear"));
			cacTenKiNang.add(obj.getString("skillName"));
		}
		ArrayList<Skill> listKiNang = new ArrayList<>();
		ArrayList<UngVienKiNang> listFkSkill = new ArrayList<>();
		for (String s : cacTenKiNang) {
			try {
				listKiNang.add(skillRepo.findByTenKiNang(s).get(0));
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
		for (int i = 0; i < listKiNang.size(); ++i) {
			Skill s = listKiNang.get(i);
			UngVienKiNang uk = new UngVienKiNang();
			uk.setKiNang(s);
			uk.setUngVien(updatedCandidate);
			uk.setSoNamKinhNghiem(kinhNghiemKiNang.get(i));
			listFkSkill.add(uk);
		}
		updatedCandidate.setKiNang(listFkSkill);
		for(UngVienChungChi uc : updatedCandidate.getChungChi()) {
			uc.setUngVien(updatedCandidate);
		}
		
		
		
		
		//foreign language
		JSONArray cacChungChi = requestObj.getJSONArray("certificate");
		ArrayList<String> diemSo = new ArrayList<String>();
		ArrayList<String> cacTenChungChi = new ArrayList<String>();
		for (int i = 0; i < cacChungChi.length(); ++i) {
			JSONObject obj = cacChungChi.getJSONObject(i);
			diemSo.add(obj.getString("score"));
			cacTenChungChi.add(obj.getString("certificateName"));
		}
		ArrayList<Certificate> listChungChi = new ArrayList<>();
		ArrayList<UngVienChungChi> listUvcc = new ArrayList<>();
		for (String s : cacTenChungChi) {
			try {
				listChungChi.add(certificateRepo.findByTenChungChi(s).get(0));
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
		for (int i = 0; i < listChungChi.size(); ++i) {
			Certificate c = listChungChi.get(i);
			UngVienChungChi uc = new UngVienChungChi();
			uc.setChungChi(c);
			uc.setUngVien(updatedCandidate);
			uc.setDiemSo(diemSo.get(i));
			listUvcc.add(uc);
		}
		updatedCandidate.setChungChi(listUvcc);
		for(UngVienChungChi uc : updatedCandidate.getChungChi()) {
			uc.setUngVien(updatedCandidate);
		}
		
		try {
			updatedCandidate.setModifyDate(new Date((new java.util.Date()).getTime()));
			candidateRepo.save(updatedCandidate); 
			return updatedCandidate;
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
	}

	@Override
	public byte[] candidateBookmarkJob(String body) {
		JSONObject obj = new JSONObject(body);
		Long candidateId = obj.getLong("candidateId");
		Long jobId = obj.getLong("jobId");
		Candidate candidate = candidateRepo.findByUngVienId(candidateId);
		Job job = jobRepo.findByJobId(jobId);
		if(candidate.getTinTuyenDung() == null || candidate.getTinTuyenDung().size() == 0) {
			List<Job> jobs = new ArrayList<>();
			candidate.setTinTuyenDung(jobs);
		}
		int duplicate = -1;
		for(int i = 0 ; i < candidate.getTinTuyenDung().size() ; ++i) {
			Job j = candidate.getTinTuyenDung().get(i);
			if(j.getJobId() == jobId) {
				duplicate = i;
				break;
			}
		}
		if(duplicate != -1) {
			candidate.getTinTuyenDung().remove(duplicate);
		}else {
			candidate.getTinTuyenDung().add(job);
		}
		try {
			candidateRepo.save(candidate);
			JSONObject returnObj = new JSONObject("{\"status\" : \"success\"}");
			return returnObj.toString().getBytes("UTF-8");
		}catch(Exception e) {
			e.printStackTrace();
			return "".getBytes();
		}
	}
	
	
	
	
	
	@Override
	public byte[] candidateApplyJob(String body) {
		JSONObject obj = new JSONObject(body);
		Long candidateId = obj.getLong("candidateId");
		Long jobId = obj.getLong("jobId");
		Candidate candidate = candidateRepo.findByUngVienId(candidateId);
		Job job = jobRepo.findByJobId(jobId);
		if(candidate.getTinTuyenDungUngTuyen() == null || candidate.getTinTuyenDungUngTuyen().size() == 0) {
			List<UngTuyen> tinTuyenDungUngTuyen = new ArrayList<>();
			candidate.setTinTuyenDungUngTuyen(tinTuyenDungUngTuyen);
		}
		int duplicate = -1;
		for(int i = 0 ; i < candidate.getTinTuyenDungUngTuyen().size() ; ++i) {
			UngTuyen j = candidate.getTinTuyenDungUngTuyen().get(i);
			if(j.getJob().getJobId() == jobId) {
				duplicate = i;
				break;
			}
		}
		if(duplicate != -1) {
			candidate.getTinTuyenDungUngTuyen().remove(duplicate);
//			int duplicateCandidate = -1;
//			for(int i = 0 ; i < job.getNguoiUngTuyen().size() ; ++i) {
//				UngTuyen ut = job.getNguoiUngTuyen().get(i);
//				if(ut.getUngVien().getUngVienId() == candidateId) {
//					duplicateCandidate = i;
//					break;
//				}
//			}
//			job.getNguoiUngTuyen().remove(duplicateCandidate);
		}else {
			UngTuyen ut = new UngTuyen();
			ut.setUngVien(candidate);
			ut.setJob(job);
			ut.setTrangThai(0);
			candidate.getTinTuyenDungUngTuyen().add(ut);
		}
		try {
			candidateRepo.save(candidate);
//			jobRepo.save(job);
			JSONObject returnObj = new JSONObject("{\"status\" : \"success\"}");
			return returnObj.toString().getBytes("UTF-8");
		}catch(Exception e) {
			e.printStackTrace();
			return "".getBytes();
		}
		
	}
	
	@Override
	public List<Job> getAppliedJobs (String idString){
		return null;
		
	}
	
	@Override
	public List<Candidate> getApplicants (String idString){
		return null;
		
	}

    @Override
    public List<UngTuyen> getListUngTuyen(String body) {
	    JSONObject obj = new JSONObject(body);
	    long idUngTuyen = obj.getLong("jobId");
        Job job = jobRepo.findByJobId(idUngTuyen);
	    List<UngTuyen> list = ungTuyenRepository.findByJob(job);
        return list;
    }

    @Override
    public List<UngTuyen> getListUngTuyenOfCompany(String body) {
	    JSONObject obj = new JSONObject(body);
	    long idCompany = obj.getLong("id");
        Company company = companyRepo.findByCongtyId(idCompany);
	    List<Job> listJob = jobRepo.findByCongTy(company);
	    List<UngTuyen> listUngTuyen = new ArrayList<>();
	    for(Job j : listJob){
	        List<UngTuyen> list = ungTuyenRepository.findByJob(j);
	        if(list.size()!=0 && list !=null){
	            for(UngTuyen ut : list){
	                listUngTuyen.add(ut);
                }
            }
        }
	    UngTuyen[] list = new UngTuyen[listUngTuyen.size()];
	    List<UngTuyen> listUt = new ArrayList<>();
	    for(int i=0;i<listUngTuyen.size();i++){
	        list[i] = listUngTuyen.get(i);
        }
	    for(int i=0;i<listUngTuyen.size()-1;i++){
	        for(int j=i+1;j<listUngTuyen.size();j++){
	            if(list[i].getTrangThai()>list[j].getTrangThai()){
	                UngTuyen temp = list[i];
	                list[i] = list[j];
	                list[j] = temp;
                }
            }
            listUt.add(list[i]);
        }
	    listUt.add(list[listUngTuyen.size()-1]);
        return listUt;
    }
    
    @Override
	public byte[] candidateSaveCompany(String body) {
		JSONObject obj = new JSONObject(body);
		Long candidateId = obj.getLong("candidateId");
		Long companyId = obj.getLong("companyId");
		Candidate candidate = candidateRepo.findByUngVienId(candidateId);
		Company company = companyRepo.findByCongtyId(companyId);
		if(candidate.getCongTySaved() == null || candidate.getCongTySaved().size() == 0) {
			List<UngVienSaveCongTy> companySaved = new ArrayList<UngVienSaveCongTy>();
			candidate.setCongTySaved(companySaved);
		}
		int duplicate = -1;
		for(int i = 0 ; i < candidate.getCongTySaved().size() ; ++i) {
			UngVienSaveCongTy congTy = candidate.getCongTySaved().get(i);
			if(congTy.getCongTy().getCongtyId() == companyId) {
				duplicate = i;
				break;
			}
		}
		if(duplicate != -1) {
			candidate.getCongTySaved().remove(duplicate);
		}else {
			UngVienSaveCongTy congTyWillBeSaved = new UngVienSaveCongTy();
			congTyWillBeSaved.setCongTy(company);
			congTyWillBeSaved.setUngVien(candidate);
			congTyWillBeSaved.setNgaySave(new java.sql.Date((new java.util.Date()).getTime()));
			candidate.getCongTySaved().add(congTyWillBeSaved);
		}
		try {
			candidateRepo.save(candidate);
			JSONObject returnObj = new JSONObject("{\"status\" : \"success\"}");
			return returnObj.toString().getBytes("UTF-8");
		}catch(Exception e) {
			e.printStackTrace();
			return "".getBytes();
		}
	}

    @Override
    public int getNumberNotifyTinder(String body) {
	    JSONObject obj = new JSONObject(body);
	    long idCompany = obj.getLong("id");
	    List<Candidate> listResultCandidate = new ArrayList<>();
	    Company company = companyRepo.findByCongtyId(idCompany);
	    List<CongTySaveUngVien> list =  company.getUngVienSaved();
	    for(CongTySaveUngVien ctsuv : list){
	        Candidate candidate = ctsuv.getUngVien();
	        List<UngVienSaveCongTy> listCandidateSaveComp = candidate.getCongTySaved();
	        for(UngVienSaveCongTy uvsct : listCandidateSaveComp){
	            if(uvsct.getCongTy().getCongtyId()==idCompany){
	                Candidate candidate1 = new Candidate();
	                candidate1.setUngVienId(candidate.getUngVienId());
	                candidate1.setTenUngVien(candidate.getTenUngVien());
                    candidate1.setImgUrl(candidate.getImgUrl());
                    candidate1.setNganh(candidate.getNganh());
                    candidate1.setKiNang(candidate.getKiNang());
                    candidate1.setDiaChi(candidate.getDiaChi());
                    listResultCandidate.add(candidate1);
                    break;
                }

            }
        }
        return listResultCandidate.size();
    }

    @Override
    public List<Candidate> getCandidateTinder(String body) {
        JSONObject obj = new JSONObject(body);
        long idCompany = obj.getLong("id");
        List<Candidate> listResultCandidate = new ArrayList<>();
        Company company = companyRepo.findByCongtyId(idCompany);
        List<CongTySaveUngVien> list =  company.getUngVienSaved();
        for(CongTySaveUngVien ctsuv : list){
            Candidate candidate = ctsuv.getUngVien();
            List<UngVienSaveCongTy> listCandidateSaveComp = candidate.getCongTySaved();
            for(UngVienSaveCongTy uvsct : listCandidateSaveComp){
                if(uvsct.getCongTy().getCongtyId()==idCompany){
                    Candidate candidate1 = new Candidate();
                    candidate1.setUngVienId(candidate.getUngVienId());
                    candidate1.setTenUngVien(candidate.getTenUngVien());
                    candidate1.setImgUrl(candidate.getImgUrl());
                    candidate1.setNganh(candidate.getNganh());
                    candidate1.setKiNang(candidate.getKiNang());
                    candidate1.setDiaChi(candidate.getDiaChi());
                    listResultCandidate.add(candidate1);
                    break;
                }

            }
        }
        return listResultCandidate;
    }

	@Override
	public Candidate deleteCandidate(String body) {
		JSONObject obj = new JSONObject(body);
        Long ungVienId = obj.getLong("id");
        Candidate deletedCandidate = candidateRepo.findByUngVienId(ungVienId);
        try {
        	candidateRepo.removeByUngVienId(ungVienId);
        	return deletedCandidate;
        }catch(Exception e) {
        	e.printStackTrace();
            return null;
        }
	}
    
    
}
