package com.recruitmentbe.model;

import java.io.Serializable;
import java.sql.Timestamp;
import java.util.Objects;

public class IdHistoryCompany implements Serializable {
	private long congTy;

	private long hanhDong;
	
	private Timestamp thoiGian;

	public long getCongTy() {
		return congTy;
	}


	public void setCongTy(long congTy) {
		this.congTy = congTy;
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
 
    	IdHistoryCompany that = (IdHistoryCompany) o;
        
        return congTy ==  that.getCongTy() && hanhDong ==  that.getHanhDong() && that.getThoiGian().equals(thoiGian);
    }
	
	@Override
    public int hashCode() {
        return Objects.hash(congTy, hanhDong);
    }

}
