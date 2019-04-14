package com.recruitmentbe.model;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;

@Entity
@Table(name="chungChiNgoaiNgu")
public class Certificate {
	@Id
	@Column(name= "chungChiId")
	private String chungChiId;
	
	@Column(name="tenChungChi")
	private String tenChungChi;
	
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "ngoaiNguId")
	private Language ngoaiNgu;
	
	@OneToMany(mappedBy = "chungChi", cascade = CascadeType.ALL, orphanRemoval = true)
	private List<UngVienChungChi> ungVien = new ArrayList<>();
	
	public String getChungChiId() {
		return chungChiId;
	}

	public void setChungChiId(String chungChiId) {
		this.chungChiId = chungChiId;
	}

	public String getTenChungChi() {
		return tenChungChi;
	}

	public void setTenChungChi(String tenChungChi) {
		this.tenChungChi = tenChungChi;
	}

	public Language getNgoaiNgu() {
		return ngoaiNgu;
	}

	public void setNgoaiNgu(Language ngoaiNgu) {
		this.ngoaiNgu = ngoaiNgu;
	}
	
	
}
