package com.recruitmentbe.model;

import java.io.Serializable;
import java.util.Objects;

import javax.persistence.Embeddable;

@Embeddable
public class IdUngTuyen implements Serializable{
	private long ungVien;

	private long job;

	public long getUngVien() {
		return ungVien;
	}

	public void setUngVien(long ungVienId) {
		this.ungVien = ungVienId;
	}
	
	public long getJob() {
		return job;
	}

	public void setJob(long jobId) {
		this.job = jobId;
	}

	@Override
	public boolean equals(Object o) {
        if (this == o) return true;
 
        if (o == null || getClass() != o.getClass())
            return false;
 
        IdUngTuyen that = (IdUngTuyen) o;
        
        return ungVien ==  that.getUngVien() && job ==  that.getJob();
    }
	
	@Override
    public int hashCode() {
        return Objects.hash(ungVien, job);
    }
	

}
