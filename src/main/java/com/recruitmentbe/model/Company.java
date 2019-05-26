package com.recruitmentbe.model;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.*;

import org.hibernate.annotations.Fetch;
import org.hibernate.annotations.FetchMode;

import com.fasterxml.jackson.annotation.JsonIdentityInfo;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.ObjectIdGenerators;

@Entity
@Table(name = "congty")
//@JsonIdentityInfo(generator = ObjectIdGenerators.PropertyGenerator.class, property = "congtyId")
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
	
	@Column(name = "password")
	private String password;

	@Column(name = "moTa")
	private String moTa;
	
	@Column(name = "phucLoi")
	private String phucLoi;
	
	@Column(name = "quyMo")
	private Integer quyMo;

	@Column(name = "imgUrl")
	private String imgUrl;
	
	@OneToMany(mappedBy = "congTy", cascade = CascadeType.ALL, orphanRemoval = true)
	private List<Job> tinTuyenDung = new ArrayList<>();
	
	@OneToMany(mappedBy = "congTy", cascade = CascadeType.ALL, orphanRemoval = true)
	private List<HistoryCompany> lichSuHanhDong = new ArrayList<>();
	
//	@ManyToMany(fetch = FetchType.LAZY, cascade = { CascadeType.PERSIST, CascadeType.MERGE })
//	@JoinTable(name = "congTySaveUngVien", joinColumns = { @JoinColumn(name = "congTyId") }, inverseJoinColumns = {
//			@JoinColumn(name = "ungVienId") })
//	private List<Candidate> ungVien = new ArrayList<>();
	
	@OneToMany(mappedBy = "congTy", cascade = CascadeType.ALL, orphanRemoval = true, fetch = FetchType.EAGER)
	@JsonIgnoreProperties("congTy")
	@Fetch(value = FetchMode.SUBSELECT) 
	private List<CongTySaveUngVien> ungVienSaved = new ArrayList<>();
	
	@OneToMany(mappedBy = "congTy", cascade = CascadeType.ALL, orphanRemoval = true, fetch = FetchType.LAZY)
	@JsonIgnoreProperties("congTy")
	private List<UngVienSaveCongTy> ungVien = new ArrayList<>();
	
	// nghia add
	@ManyToOne(fetch = FetchType.EAGER)
	@JoinColumn(name = "nganhId")
	private Major nganh;

	//phucnh
    @ManyToMany(fetch = FetchType.EAGER, cascade = { CascadeType.PERSIST, CascadeType.MERGE })
    @JoinTable(name = "congtysaveungvien", joinColumns = { @JoinColumn(name = "congTyID") }, inverseJoinColumns = {
        @JoinColumn(name = "ungVienId") })
    private List<Candidate> ungVienSave = new ArrayList<>();

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

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public List<Job> getTinTuyenDung() {
		return tinTuyenDung;
	}

	public void setTinTuyenDung(List<Job> tinTuyenDung) {
		this.tinTuyenDung = tinTuyenDung;
	}

	public List<HistoryCompany> getLichSuHanhDong() {
		return lichSuHanhDong;
	}

	public void setLichSuHanhDong(List<HistoryCompany> lichSuHanhDong) {
		this.lichSuHanhDong = lichSuHanhDong;
	}
	
	public List<CongTySaveUngVien> getUngVienSaved() {
		return ungVienSaved;
	}

	public void setUngVienSaved(List<CongTySaveUngVien> ungVienSaved) {
		this.ungVienSaved = ungVienSaved;
	}

	public String getMoTa() {
		return moTa;
	}

	public void setMoTa(String moTa) {
		this.moTa = moTa;
	}

	public String getPhucLoi() {
		return phucLoi;
	}

	public void setPhucLoi(String phucLoi) {
		this.phucLoi = phucLoi;
	}

	public Integer getQuyMo() {
		return quyMo;
	}

	public void setQuyMo(Integer quyMo) {
		this.quyMo = quyMo;
	}

	public String getImgUrl() {
		return imgUrl;
	}

	public void setImgUrl(String imgUrl) {
		this.imgUrl = imgUrl;
	}

	public List<UngVienSaveCongTy> getUngVien() {
		return ungVien;
	}

	public void setUngVien(List<UngVienSaveCongTy> ungVien) {
		this.ungVien = ungVien;
	}

	public Major getNganh() {
		return nganh;
	}

	public void setNganh(Major nganh) {
		this.nganh = nganh;
	}

    public List<Candidate> getUngVienSave() {
        return ungVienSave;
    }

    public void setUngVienSave(List<Candidate> ungVienSave) {
        this.ungVienSave = ungVienSave;
    }
}
