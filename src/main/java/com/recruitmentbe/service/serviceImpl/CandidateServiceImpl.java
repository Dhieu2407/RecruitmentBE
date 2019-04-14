package com.recruitmentbe.service.serviceImpl;

import java.util.ArrayList;
import java.util.List;

import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.recruitmentbe.model.Candidate;
import com.recruitmentbe.model.Major;
import com.recruitmentbe.model.Skill;
import com.recruitmentbe.repository.CandidateRepository;
import com.recruitmentbe.repository.MajorRepository;
import com.recruitmentbe.repository.SkillRepository;
import com.recruitmentbe.service.CandidateService;

@Service
public class CandidateServiceImpl implements CandidateService{

	@Autowired
	private CandidateRepository candidateRepo;

	@Autowired
	private MajorRepository majorRepo;

	@Autowired
	private SkillRepository skillRepo;
	
	@Override
	public List<Candidate> getAllCandidate() {
		return candidateRepo.findAll();
	}

	@Override
	public Candidate registerCandidate(String username, String email, String password) {
		Candidate candidate = new Candidate();
		candidate.setEmail(email);
		candidate.setTenUngVien(username);
		candidate.setPassword(password);
		List<Candidate> allCandidates= candidateRepo.findAll();
		if(allCandidates.size() == 0) {
			candidate.setUngVienId(0);
		}else {
			candidate.setUngVienId(allCandidates.get(allCandidates.size() - 1).getUngVienId() + 1);
		}
		try {
			candidateRepo.save(candidate);
			return candidate;
		}catch(Exception e) {
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
			notContained = new ArrayList<>(resultListCandidates);
			notContained.removeAll(searchByCondition);
			resultListCandidates.removeAll(notContained);
			notContained.clear();
			searchByCondition.clear();
		} catch (Exception e1) {
			
		}
		String email;
		try {
			email = obj.getString("email");
			searchByCondition = candidateRepo.findByEmailContaining(email);
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
			List<Major> majorSearchedByCondition = majorRepo.findByTenNganhContaining(tenNganh);
			if(majorSearchedByCondition.size() > 0) {
				List<Candidate> allCandidates = candidateRepo.findAll();
				searchByCondition = new ArrayList<Candidate>();
				for(Candidate c : allCandidates) {
					if(c.getNganh().getNganhId() == majorSearchedByCondition.get(0).getNganhId()) {
						searchByCondition.add(c);
					}
				}
				notContained = new ArrayList<>(resultListCandidates);
				notContained.removeAll(searchByCondition);
				resultListCandidates.removeAll(notContained);
				notContained.clear();
				searchByCondition.clear();
			}
		} catch (Exception e1) {
			
		}
		return resultListCandidates;
	}

	@Override
	public Candidate updateProfileCandidates(String body) {
		JSONObject requestObj = new JSONObject(body);
		String ungVienId = requestObj.getString("id");
		Candidate updatedCandidate = candidateRepo.findByUngVienId(Long.parseLong(ungVienId));
		updatedCandidate.setDiaDiem(requestObj.getString("diaDiemLamViec"));
		updatedCandidate.setEmail(requestObj.getString("email"));
		updatedCandidate.setMoTa(requestObj.getString("moTa"));
		updatedCandidate.setNganh(majorRepo.findByTenNganhContaining(requestObj.getString("nganh")).get(0));
		updatedCandidate.setDiaChi(requestObj.getString("diaChi"));
		updatedCandidate.setSdt(requestObj.getString("sdt"));
		updatedCandidate.setLuongMongMuon(requestObj.getBigDecimal("luongMongMuon").longValue());
		String[] cacKiNang = requestObj.getString("kiNang").split(",");
		ArrayList<Skill> listKiNang = new ArrayList<>();
		for(String s : cacKiNang) {
			try {
				listKiNang.add(skillRepo.findByTenKiNang(s).get(0));
			}catch(Exception e) {
				e.printStackTrace();
			}
		}
		updatedCandidate.setKiNang(listKiNang);
		try {
			candidateRepo.save(updatedCandidate);
			return updatedCandidate;
		}catch(Exception e) {
			return null;
		}
	}
	
	
}
