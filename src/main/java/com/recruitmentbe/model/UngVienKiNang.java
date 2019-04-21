package com.recruitmentbe.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.IdClass;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Entity
@Table(name = "ungvien_kinang")
@IdClass(IdUngVienKiNang.class)
public class UngVienKiNang {
	@Id
    @ManyToOne
    @JoinColumn(name = "ungvienId")
    private Candidate ungVien;

    @Id
    @ManyToOne
    @JoinColumn(name = "kiNangId")
    private Skill kiNang;

    @Column(name = "soNamKinhNghiem")
    private int soNamKinhNghiem;

	public Candidate getUngVien() {
		return ungVien;
	}

	public void setUngVien(Candidate ungVien) {
		this.ungVien = ungVien;
	}

	public Skill getKiNang() {
		return kiNang;
	}

	public void setKiNang(Skill kiNang) {
		this.kiNang = kiNang;
	}

	public int getSoNamKinhNghiem() {
		return soNamKinhNghiem;
	}

	public void setSoNamKinhNghiem(int soNamKinhNghiem) {
		this.soNamKinhNghiem = soNamKinhNghiem;
	}
    
}
