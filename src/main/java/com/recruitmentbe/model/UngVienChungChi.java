package com.recruitmentbe.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.IdClass;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Entity
@Table(name = "ungvien_ngoaingu")
@IdClass(IdUngVienChungChi.class)
public class UngVienChungChi {
	@Id
    @ManyToOne
    @JoinColumn(name = "ungvienId")
    private Candidate ungVien;

    @Id
    @ManyToOne
    @JoinColumn(name = "chungChiId")
    private Certificate chungChi;

    @Column(name = "diemSo")
    private int diemSo;

}
