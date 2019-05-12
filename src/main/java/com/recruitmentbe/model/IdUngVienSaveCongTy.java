package com.recruitmentbe.model;

import java.io.Serializable;
import java.util.Objects;

import javax.persistence.Embeddable;

@Embeddable
public class IdUngVienSaveCongTy implements Serializable {
	private long ungVien;

	private long congTy;

	public long getUngVien() {
		return ungVien;
	}

	public void setUngVien(long ungVienId) {
		this.ungVien = ungVienId;
	}

	public long getCongTy() {
		return congTy;
	}

	public void setCongTy(long congTy) {
		this.congTy = congTy;
	}
	
	@Override
	public boolean equals(Object o) {
        if (this == o) return true;
 
        if (o == null || getClass() != o.getClass())
            return false;
 
        IdCongTySaveUngVien that = (IdCongTySaveUngVien) o;
        
        return ungVien ==  that.getUngVien() && congTy ==  that.getCongTy();
    }

	@Override
    public int hashCode() {
        return Objects.hash(ungVien, congTy);
    }
}
