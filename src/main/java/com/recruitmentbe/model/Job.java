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
	
}
