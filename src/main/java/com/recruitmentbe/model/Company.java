package com.recruitmentbe.model;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.OneToMany;
import javax.persistence.Table;

@Entity
@Table(name = "congty")
public class Company {
	@Id
	@Column(name = "congtyId")
	private long congtyId;

	@Column(name = "ten")
	private String tenCongTy;

	@Column(name = "diachi")
	private String diaChi;

	@Column(name = "sdt")
	private String sdt;

	@Column(name = "email")
	private String email;
	
	@OneToMany(mappedBy = "congTy", cascade = CascadeType.ALL, orphanRemoval = true)
	private List<Job> tinTuyenDung = new ArrayList<>();
	
	@OneToMany(mappedBy = "congTy", cascade = CascadeType.ALL, orphanRemoval = true)
	private List<HistoryCompany> lichSuHanhDong = new ArrayList<>();
	
	@ManyToMany(fetch = FetchType.LAZY, cascade = { CascadeType.PERSIST, CascadeType.MERGE })
	@JoinTable(name = "congTySaveUngVien", joinColumns = { @JoinColumn(name = "congTyId") }, inverseJoinColumns = {
			@JoinColumn(name = "ungVienId") })
	private List<Candidate> ungVien = new ArrayList<>();

	public long getCongtyId() {
		return congtyId;
	}

	public void setCongtyId(long congtyId) {
		this.congtyId = congtyId;
	}

	public String getTenCongTy() {
		return tenCongTy;
	}

	public void setTenCongTy(String tenCongTy) {
		this.tenCongTy = tenCongTy;
	}

	public String getDiaChi() {
		return diaChi;
	}

	public void setDiaChi(String diaChi) {
		this.diaChi = diaChi;
	}

	public String getSdt() {
		return sdt;
	}

	public void setSdt(String sdt) {
		this.sdt = sdt;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}
	

}
