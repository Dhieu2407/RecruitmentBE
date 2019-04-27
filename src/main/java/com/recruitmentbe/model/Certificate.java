package com.recruitmentbe.model;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;

@Entity
@Table(name="chungChiNgoaiNgu")
public class Certificate {
	@Id
	@Column(name= "chungChiId")
	private long chungChiId;
	
	@Column(name="tenChungChi")
	private String tenChungChi;
	
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "ngoaiNguId")
	private Language ngoaiNgu;
	
	@OneToMany(mappedBy = "chungChi", cascade = CascadeType.ALL, orphanRemoval = true)
	private List<UngVienChungChi> ungVien = new ArrayList<>();
	
	public String getTenChungChi() {
		return tenChungChi;
	}

	public long getChungChiId() {
		return chungChiId;
	}

	public void setChungChiId(long chungChiId) {
		this.chungChiId = chungChiId;
	}

	public List<UngVienChungChi> getUngVien() {
		return ungVien; 
	}

	public void setUngVien(List<UngVienChungChi> ungVien) {
		this.ungVien = ungVien;
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
