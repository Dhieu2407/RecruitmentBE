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

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@Entity
@Table(name = "kinang")
public class Skill {
	@Id
	@Column(name="kinangId")
	private long kinangId;
	
	@Column(name = "ten")
	private String tenKiNang;
	
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "nganhId")
	private Major nganh;

	@ManyToMany(fetch = FetchType.LAZY, cascade = { CascadeType.PERSIST, CascadeType.MERGE }, mappedBy = "kiNang")
	private List<Job> tinTuyenDung = new ArrayList<>();

	@OneToMany(mappedBy = "kiNang", cascade = CascadeType.ALL, orphanRemoval = true, fetch = FetchType.LAZY)
	@JsonIgnoreProperties("kiNang")
	private List<UngVienKiNang> ungVien = new ArrayList<>();
	
	
	public long getKinangId() {
		return kinangId;
	}

	public void setKinangId(long kinangId) {
		this.kinangId = kinangId;
	}

	public String getTenKiNang() {
		return tenKiNang;
	}

	public void setTenKiNang(String tenKiNang) {
		this.tenKiNang = tenKiNang;
	}

	public Major getNganh() {
		return nganh;
	}

	public void setNganh(Major nganh) {
		this.nganh = nganh;
	}

	public List<Job> getTinTuyenDung() {
		return tinTuyenDung;
	}

	public void setTinTuyenDung(List<Job> tinTuyenDung) {
		this.tinTuyenDung = tinTuyenDung;
	}

	public List<UngVienKiNang> getUngVien() {
		return ungVien;
	}

	public void setUngVien(List<UngVienKiNang> ungVien) {
		this.ungVien = ungVien;
	}
	
	
}
