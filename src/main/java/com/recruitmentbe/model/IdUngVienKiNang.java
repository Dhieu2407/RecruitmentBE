package com.recruitmentbe.model;

import java.io.Serializable;
import java.util.Objects;

import javax.persistence.Embeddable;

@Embeddable
public class IdUngVienKiNang  implements Serializable{
	private long ungVien;

	private long kiNang;

	public long getUngVien() {
		return ungVien;
	}

	public void setUngVien(long ungVienId) {
		this.ungVien = ungVienId;
	}

	public long getKiNang() {
		return kiNang;
	}

	public void setKiNang(long kiNangId) {
		this.kiNang = kiNangId;
	}
	
	@Override
	public boolean equals(Object o) {
        if (this == o) return true;
 
        if (o == null || getClass() != o.getClass())
            return false;
 
    	IdUngVienKiNang that = (IdUngVienKiNang) o;
        
        return ungVien ==  that.getUngVien() && kiNang ==  that.getKiNang();
    }
	
	@Override
    public int hashCode() {
        return Objects.hash(ungVien, kiNang);
    }
}
