package com.recruitmentbe.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.IdClass;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Entity
@Table(name = "ungtuyen")
@IdClass(IdUngTuyen.class)
public class UngTuyen {
	@Id
    @ManyToOne
    @JoinColumn(name = "ungvienId")
    private Candidate ungVien;

    @Id
    @ManyToOne
    @JoinColumn(name = "jobId")
    private Job job;

    @Column(name = "trangThai")
    private long trangThai;

    @Column(name="lyDo")
    private String lyDo;

	public Candidate getUngVien() {
		return ungVien;
	}

	public void setUngVien(Candidate ungVien) {
		this.ungVien = ungVien;
	}

	public Job getJob() {
		return job;
	}

	public void setJob(Job job) {
		this.job = job;
	}

	public long getTrangThai() {
		return trangThai;
	}

	public void setTrangThai(long trangThai) {
		this.trangThai = trangThai;
	}

    public String getLyDo() {
        return lyDo;
    }

    public void setLyDo(String lyDo) {
        this.lyDo = lyDo;
    }
}
