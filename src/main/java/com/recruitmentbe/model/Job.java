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
import javax.persistence.OneToMany;
import javax.persistence.Table;

import org.hibernate.annotations.Fetch;
import org.hibernate.annotations.FetchMode;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@Entity
@Table(name="job")
public class Job {
	@Id
	@Column(name="jobId")
	private long jobId;
	
	@Column(name="tenJob")
	private String tenJob;
	
	@ManyToOne(fetch = FetchType.EAGER)
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

	@Column(name="tgLamViec")
    private int tgLamViec;

	@Column(name="quyenLoi")
    private String quyenLoi;

	@Column(name="yeuCauCongViec")
    private String yeuCauCongViec;

	@Column(name="yeuCauHoSo")
    private String yeuCauHoSo;

	@Column(name = "gioiTinh")
    private int gioiTinh;

	@Column(name ="chucVuLamViec")
	private String chucVu1;

	@Column(name = "trangThai")
    private int trangThai;
	
	@ManyToOne(fetch = FetchType.EAGER)
	@JoinColumn(name = "nganhId")
	private Major nganh;

	@ManyToOne(fetch = FetchType.EAGER)
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
	

//	@OneToMany(mappedBy = "job", cascade = CascadeType.ALL, orphanRemoval = true, fetch = FetchType.EAGER)
//	@JsonIgnoreProperties("job")
//	@Fetch(value = FetchMode.SUBSELECT)
//	private List<UngTuyen> nguoiUngTuyen = new ArrayList<>();
	
	@OneToMany(mappedBy = "job", cascade = CascadeType.ALL, orphanRemoval = true)
	private List<UngTuyen> nguoiUngTuyen = new ArrayList<>();

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

    public String getQuyenLoi() {
        return quyenLoi;
    }

    public void setQuyenLoi(String quyenLoi) {
        this.quyenLoi = quyenLoi;
    }

    public String getYeuCauCongViec() {
        return yeuCauCongViec;
    }

    public void setYeuCauCongViec(String yeuCauCongViec) {
        this.yeuCauCongViec = yeuCauCongViec;
    }

    public String getYeuCauHoSo() {
        return yeuCauHoSo;
    }

    public void setYeuCauHoSo(String yeuCauHoSo) {
        this.yeuCauHoSo = yeuCauHoSo;
    }

    public int getTgLamViec() {
        return tgLamViec;
    }

    public void setTgLamViec(int tgLamViec) {
        this.tgLamViec = tgLamViec;
    }

    public int getGioiTinh() {
        return gioiTinh;
    }

    public void setGioiTinh(int gioiTinh) {
        this.gioiTinh = gioiTinh;
    }

    public String getChucVu1() {
        return chucVu1;
    }

    public void setChucVu1(String chucVu1) {
        this.chucVu1 = chucVu1;
    }

    public int getTrangThai() {
        return trangThai;
    }

    public void setTrangThai(int trangThai) {
        this.trangThai = trangThai;
    }

	public List<UngTuyen> getNguoiUngTuyen() {
		return nguoiUngTuyen;
	}

	public void setNguoiUngTuyen(List<UngTuyen> nguoiUngTuyen) {
		this.nguoiUngTuyen = nguoiUngTuyen;
	}
}
