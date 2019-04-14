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
import com.google.gson.*;
import org.json.JSONObject;

@Entity
@Table(name = "ungvien")
public class Candidate {
	@Id
	@Column(name="ungVienId")
	private long ungVienId;
	
	@Column(name = "ten")
	private String tenUngVien;
	

	@Column(name = "password")
	private String password;

	@Column(name = "sdt")
	private String sdt;
	
	@Column(name = "email")
	private String email; //findByEmail
	
	@Column(name = "diachi")
	private String diaChi;
	
	@Column(name = "trinhDoDaiHoc")
	private String trinhDoDaiHoc;
	
	@OneToMany(mappedBy = "ungVien", cascade = CascadeType.ALL, orphanRemoval = true)
	private List<UngVienChungChi> chungChi = new ArrayList<>();
	
	@OneToMany(mappedBy = "ungVien", cascade = CascadeType.ALL, orphanRemoval = true)
	private List<HistoryCandidate> lichSuHanhDong = new ArrayList<>();
	
	@ManyToMany(fetch = FetchType.LAZY, cascade = { CascadeType.PERSIST, CascadeType.MERGE })
	@JoinTable(name = "ungvienSaveJob", joinColumns = { @JoinColumn(name = "ungVienId") }, inverseJoinColumns = {
			@JoinColumn(name = "jobId") })
	private List<Job> tinTuyenDung = new ArrayList<>();
	
	@ManyToMany(fetch = FetchType.LAZY, cascade = { CascadeType.PERSIST, CascadeType.MERGE }, mappedBy = "ungVien")
	private List<Company> congTy = new ArrayList<>();


	// check lai
	@ManyToMany(fetch = FetchType.LAZY, cascade = { CascadeType.PERSIST, CascadeType.MERGE })
	@JoinTable(name = "ungvien_kinang", joinColumns = { @JoinColumn(name = "ungVienId") }, inverseJoinColumns = {
			@JoinColumn(name = "kinangId") })
	private List<Skill> kiNang = new ArrayList<>();
	
	public long getUngVienId() {
		return ungVienId;
	}
	public void setUngVienId(long ungVienId) {
		this.ungVienId = ungVienId;
	}
	public String getTenUngVien() {
		return tenUngVien;
	}
	public void setTenUngVien(String tenUngVien) {
		this.tenUngVien = tenUngVien;
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
	public String getDiaChi() {
		return diaChi;
	}
	public void setDiaChi(String diaChi) {
		this.diaChi = diaChi;
	}
	public String getTrinhDoDaiHoc() {
		return trinhDoDaiHoc;
	}
	public void setTrinhDoDaiHoc(String trinhDoDaiHoc) {
		this.trinhDoDaiHoc = trinhDoDaiHoc;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	public List<UngVienChungChi> getChungChi() {
		return chungChi;
	}
	public void setChungChi(List<UngVienChungChi> chungChi) {
		this.chungChi = chungChi;
	}
	public List<HistoryCandidate> getLichSuHanhDong() {
		return lichSuHanhDong;
	}
	public void setLichSuHanhDong(List<HistoryCandidate> lichSuHanhDong) {
		this.lichSuHanhDong = lichSuHanhDong;
	}
	public List<Job> getTinTuyenDung() {
		return tinTuyenDung;
	}
	public void setTinTuyenDung(List<Job> tinTuyenDung) {
		this.tinTuyenDung = tinTuyenDung;
	}
	public List<Company> getCongTy() {
		return congTy;
	}
	public void setCongTy(List<Company> congTy) {
		this.congTy = congTy;
	}
	
	public String convertToJson(){
		Candidate c = new Candidate();
		c.setEmail(this.getEmail());
		c.setUngVienId(this.getUngVienId());
		c.setTenUngVien(this.getTenUngVien());
		c.setPassword(this.getPassword());
		c.setDiaChi(this.getDiaChi());
		return (new JSONObject(c)).toString();
	}

	public Candidate convert(){
		Candidate c = new Candidate();
		c.setEmail(this.getEmail());
		c.setUngVienId(this.getUngVienId());
		c.setTenUngVien(this.getTenUngVien());
		c.setPassword(this.getPassword());
		c.setDiaChi(this.getDiaChi());
		return c;
	}
	
	@Override
	public boolean equals(Object otherCandidate) {
		if (this == otherCandidate) return true;
		 
        if (otherCandidate == null || getClass() != otherCandidate.getClass())
            return false;
 
    	Candidate that = (Candidate) otherCandidate;
        
        return this.ungVienId ==  that.getUngVienId();
	}
}
