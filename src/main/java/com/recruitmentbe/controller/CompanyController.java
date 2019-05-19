package com.recruitmentbe.controller;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.recruitmentbe.model.Candidate;
import com.recruitmentbe.model.Company;
import com.recruitmentbe.model.CongTySaveUngVien;
import com.recruitmentbe.model.Job;
import com.recruitmentbe.service.serviceImpl.CompanyService;

@RestController
@RequestMapping("/api/company")
public class CompanyController {
	@Autowired
	CompanyService companyServiceImpl;
	
	@PostMapping(value = "/companyRegister")
	public byte[] addCompany(@RequestBody String body) throws Exception {
		return companyServiceImpl.registerCompany(body);
	}
	
	@GetMapping(value = "/getAllCompanies")
	public List<Company> getAllCompanies() {
		List<Company> allCompanies = companyServiceImpl.getAllCompany();
		return allCompanies;
	}
	
	@PostMapping(value = "/updateProfileCompany")
	public Company updateProfileCompany(@RequestBody String body) {
		return companyServiceImpl.updateProfileCompany(body);
	}
	
	@GetMapping(value = "/getCompany/{id}")
    public Company getCompany(@PathVariable("id") String idString) {
	    Long id = Long.parseLong(idString);
        Company Company = companyServiceImpl.findByCongTyId(id);
        if(Company == null) {
            ResponseEntity.notFound().build();
        }
        return Company;
    }
	

	@GetMapping(value = "/getCandidateByCompany/{id}")
    public Set<Candidate> getCandidateByCompany(@PathVariable("id") String idString) {
		return companyServiceImpl.getCandidateByCompany(idString);
    }
	
	@PostMapping(value = "/saveCandidate")
	public byte[] saveCandidate(@RequestBody String body) {
		return companyServiceImpl.companySaveCandidate(body);
	}
	@GetMapping(value = "/getSavedCandidate/{companyId}")
	public List<Candidate> getSavedCandidate (@PathVariable("companyId") String idString){
		Company currentCompany = companyServiceImpl.findByCongTyId(Long.parseLong(idString));
//		Candidate currentCandidate = candidateServiceImpl.findByUngVienId(Long.parseLong(idString));
		List<Candidate> returnResult = new ArrayList<Candidate>();
		List<CongTySaveUngVien> listCtsuv = currentCompany.getUngVienSaved();
		for(CongTySaveUngVien ctsuv : listCtsuv) {
			returnResult.add(ctsuv.getUngVien());
		}
		return returnResult;
	}
}
