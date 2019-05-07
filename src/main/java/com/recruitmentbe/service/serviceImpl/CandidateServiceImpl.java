package com.recruitmentbe.service.serviceImpl;

import java.sql.Date;
import java.util.ArrayList;
import java.util.List;

import com.recruitmentbe.repository.*;
import org.json.JSONArray;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.recruitmentbe.model.Candidate;
import com.recruitmentbe.model.Job;
import com.recruitmentbe.model.Major;
import com.recruitmentbe.model.Skill;
import com.recruitmentbe.model.UngTuyen;
import com.recruitmentbe.model.UngVienChungChi;
import com.recruitmentbe.model.UngVienKiNang;
import com.recruitmentbe.service.CandidateService;

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
//		updatedCandidate.getNganh().getUngvien().add(updatedCandidate);
//		System.out.println(new JSONObject(updatedCandidate).toString());
		System.out.println(updatedCandidate.getUngVienId());
//		System.out.println(updatedCandidate.getChungChi().get(0).getChungChi().getTenChungChi());
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
	    long idUngTuyen = obj.getLong("id");
        Job job = jobRepo.findByJobId(idUngTuyen);
	    List<UngTuyen> list = ungTuyenRepository.findByJob(job);
        return list;
    }

}
