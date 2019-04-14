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
import javax.persistence.Table;

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

	@ManyToMany(fetch = FetchType.LAZY, cascade = { CascadeType.PERSIST, CascadeType.MERGE }, mappedBy = "kiNang")
	private List<Candidate> ungVien = new ArrayList<>();
	
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
	
}
