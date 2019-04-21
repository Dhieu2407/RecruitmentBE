package com.recruitmentbe.model;

import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;

@Entity
@Table(name="chucVu")
public class Position {
	@Id
	@Column(name="chucVuId")
	private long chucVuId;
	
	@Column(name="tenChucVu")
	private String tenChucVu;
	
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "nganhId")
	private Major nganh;
	
	@OneToMany(mappedBy = "chucVu", cascade = CascadeType.ALL, orphanRemoval = true)
	private List<Job> tinTuyenDung;

    public long getChucVuId() {
        return chucVuId;
    }

    public void setChucVuId(long chucVuId) {
        this.chucVuId = chucVuId;
    }

    public String getTenChucVu() {
        return tenChucVu;
    }

    public void setTenChucVu(String tenChucVu) {
        this.tenChucVu = tenChucVu;
    }

    public Major getNganh() {
        return nganh;
    }

    public void setNganh(Major nganh) {
        this.nganh = nganh;
    }

    public List<Job> getTinTuyenDung() {
        return tinTuyenDung;
    }

    public void setTinTuyenDung(List<Job> tinTuyenDung) {
        this.tinTuyenDung = tinTuyenDung;
    }
}
