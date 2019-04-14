package com.recruitmentbe.model;

import java.io.Serializable;

import javax.persistence.Embeddable;

@Embeddable
public class IdUngVienChungChi implements Serializable{
	private long ungVien;

	private long chungChi;

	public long getUngVien() {
		return ungVien;
	}

	public void setUngVien(long ungVienId) {
		this.ungVien = ungVienId;
	}

	public long getChungChi() {
		return chungChi;
	}

	public void setChungChi(long chungChiId) {
		this.chungChi = chungChiId;
	}
	
	@Override
	public boolean equals(Object o) {
        if (this == o) return true;
 
        if (o == null || getClass() != o.getClass())
            return false;
 
    	IdUngVienChungChi that = (IdUngVienChungChi) o;
        
        return ungVien ==  that.getUngVien() && chungChi ==  that.getChungChi();
    }
	
}
