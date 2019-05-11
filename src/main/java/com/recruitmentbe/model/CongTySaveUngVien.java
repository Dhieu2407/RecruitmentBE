package com.recruitmentbe.model;

import java.sql.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.IdClass;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Entity
@Table(name = "congtysaveungvien")
@IdClass(IdCongTySaveUngVien.class)
public class CongTySaveUngVien {
	@Id
    @ManyToOne
    @JoinColumn(name = "ungvienId")
    private Candidate ungVien;

    @Id
    @ManyToOne
    @JoinColumn(name = "congTyId")
    private Company congTy;
    
    @Column(name = "ngaySave")
    private Date ngaySave;

	public Candidate getUngVien() {
		return ungVien;
	}

	public void setUngVien(Candidate ungVien) {
		this.ungVien = ungVien;
	}

	public Company getCongTy() {
		return congTy;
	}

	public void setCongTy(Company congTy) {
		this.congTy = congTy;
	}

	public Date getNgaySave() {
		return ngaySave;
	}

	public void setNgaySave(Date ngaySave) {
		this.ngaySave = ngaySave;
	}
}
