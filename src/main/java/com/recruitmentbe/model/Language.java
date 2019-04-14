package com.recruitmentbe.model;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.ManyToMany;
import javax.persistence.OneToMany;
import javax.persistence.Table;

@Entity
@Table(name = "ngoaiNgu")

public class Language {
	@Id
	@Column(name="ngoaiNguId")
	private long ngoaiNguId;
	
	@Column(name="tenNngoaiNgu")
	private long tenNngoaiNgu;
	
	@OneToMany(mappedBy = "ngoaiNgu", cascade = CascadeType.ALL, orphanRemoval = true)
	private List<Certificate> cacChungChi = new ArrayList<>();

	@ManyToMany(fetch = FetchType.LAZY, cascade = { CascadeType.PERSIST, CascadeType.MERGE }, mappedBy = "ngoaiNgu")
	private List<Job> tinTuyenDung = new ArrayList<>();
	
	public long getNgoaiNguId() {
		return ngoaiNguId;
	}

	public void setNgoaiNguId(long ngoaiNguId) {
		this.ngoaiNguId = ngoaiNguId;
	}

	public long getTenNngoaiNgu() {
		return tenNngoaiNgu;
	}

	public void setTenNngoaiNgu(long tenNngoaiNgu) {
		this.tenNngoaiNgu = tenNngoaiNgu;
	}
	
	

}
