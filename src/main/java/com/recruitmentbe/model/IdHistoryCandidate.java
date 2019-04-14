package com.recruitmentbe.model;

import java.io.Serializable;
import java.sql.Timestamp;
import java.util.Objects;

import javax.persistence.Embeddable;

@Embeddable
public class IdHistoryCandidate implements Serializable {
	private long ungVien;

	private long hanhDong;
	
	private Timestamp thoiGian;

	public long getUngVien() {
		return ungVien;
	}


	public void setUngVien(long ungVien) {
		this.ungVien = ungVien;
	}


	public long getHanhDong() {
		return hanhDong;
	}


	public void setHanhDong(long hanhDong) {
		this.hanhDong = hanhDong;
	}



	public Timestamp getThoiGian() {
		return thoiGian;
	}


	public void setThoiGian(Timestamp thoiGian) {
		this.thoiGian = thoiGian;
	}
	
	@Override
	public boolean equals(Object o) {
        if (this == o) return true;
 
        if (o == null || getClass() != o.getClass())
            return false;
 
    	IdHistoryCandidate that = (IdHistoryCandidate) o;
        
        return ungVien ==  that.getUngVien() && hanhDong ==  that.getHanhDong() && that.getThoiGian().equals(thoiGian);
    }
	
	@Override
    public int hashCode() {
        return Objects.hash(ungVien, hanhDong);
    }

}
