package com.recruitmentbe.model;
import java.sql.Date;
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
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Entity
@Table(name="job")
public class Job {
	@Id
	@Column(name="jobId")
	private long jobId;
	
	@Column(name="tenJob")
	private String tenJob;
	
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "congTyId")
	private Company congTy;
	
	@Column(name="luongToiThieu")
	private long luongToiThieu;

	@Column(name="luongToiDa")
	private long luongToiDa;
	
	@Column(name="soNamKNToiThieu")
	private int knToiThieu;
	
	@Column(name="diaChi")
	private String diaChi;
	
	@Column(name="chiTiet")
	private String chiTiet;
	
	@Column(name="soLuongCanTuyen")
	private int soLuong;
	
	@Column(name="hanCuoi")
	private Date hanCuoi;
	
	@Column(name="ngayDang")
	private Date ngayDang;
	
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "nganhId")
	private Major nganh;

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "chucVuId")
	private Position chucVu;
	
	@ManyToMany(fetch = FetchType.LAZY, cascade = { CascadeType.PERSIST, CascadeType.MERGE })
	@JoinTable(name = "job_kinang", joinColumns = { @JoinColumn(name = "jobId") }, inverseJoinColumns = {
			@JoinColumn(name = "kinangId") })
	private List<Skill> kiNang = new ArrayList<>();
	
	@ManyToMany(fetch = FetchType.LAZY, cascade = { CascadeType.PERSIST, CascadeType.MERGE })
	@JoinTable(name = "yeuCauNgoaiNguJob", joinColumns = { @JoinColumn(name = "jobId") }, inverseJoinColumns = {
			@JoinColumn(name = "ngoaiNguId") })
	private List<Language> ngoaiNgu = new ArrayList<>();
	
	@ManyToMany(fetch = FetchType.LAZY, cascade = { CascadeType.PERSIST, CascadeType.MERGE }, mappedBy = "tinTuyenDung")
	private List<Candidate> tinTuyenDung = new ArrayList<>();

    public long getJobId() {
        return jobId;
    }

    public void setJobId(long jobId) {
        this.jobId = jobId;
    }

    public String getTenJob() {
        return tenJob;
    }

    public void setTenJob(String tenJob) {
        this.tenJob = tenJob;
    }

    public Company getCongTy() {
        return congTy;
    }

    public void setCongTy(Company congTy) {
        this.congTy = congTy;
    }

    public long getLuongToiThieu() {
        return luongToiThieu;
    }

    public void setLuongToiThieu(long luongToiThieu) {
        this.luongToiThieu = luongToiThieu;
    }

    public long getLuongToiDa() {
        return luongToiDa;
    }

    public void setLuongToiDa(long luongToiDa) {
        this.luongToiDa = luongToiDa;
    }

    public int getKnToiThieu() {
        return knToiThieu;
    }

    public void setKnToiThieu(int knToiThieu) {
        this.knToiThieu = knToiThieu;
    }

    public String getDiaChi() {
        return diaChi;
    }

    public void setDiaChi(String diaChi) {
        this.diaChi = diaChi;
    }

    public String getChiTiet() {
        return chiTiet;
    }

    public void setChiTiet(String chiTiet) {
        this.chiTiet = chiTiet;
    }

    public int getSoLuong() {
        return soLuong;
    }

    public void setSoLuong(int soLuong) {
        this.soLuong = soLuong;
    }

    public Date getHanCuoi() {
        return hanCuoi;
    }

    public void setHanCuoi(Date hanCuoi) {
        this.hanCuoi = hanCuoi;
    }

    public Date getNgayDang() {
        return ngayDang;
    }

    public void setNgayDang(Date ngayDang) {
        this.ngayDang = ngayDang;
    }

    public Major getNganh() {
        return nganh;
    }

    public void setNganh(Major nganh) {
        this.nganh = nganh;
    }

    public Position getChucVu() {
        return chucVu;
    }

    public void setChucVu(Position chucVu) {
        this.chucVu = chucVu;
    }

    public List<Skill> getKiNang() {
        return kiNang;
    }

    public void setKiNang(List<Skill> kiNang) {
        this.kiNang = kiNang;
    }

    public List<Language> getNgoaiNgu() {
        return ngoaiNgu;
    }

    public void setNgoaiNgu(List<Language> ngoaiNgu) {
        this.ngoaiNgu = ngoaiNgu;
    }

    public List<Candidate> getTinTuyenDung() {
        return tinTuyenDung;
    }

    public void setTinTuyenDung(List<Candidate> tinTuyenDung) {
        this.tinTuyenDung = tinTuyenDung;
    }
}
