package com.recruitmentbe.model;

import java.sql.Timestamp;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.IdClass;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Entity
@Table(name = "historyungvien")
@IdClass(IdHistoryCandidate.class)
public class HistoryCandidate {
	@Id
    @ManyToOne
    @JoinColumn(name = "ungvienId")
    private Candidate ungVien;

    @Id
    @ManyToOne
    @JoinColumn(name = "actionType")
    private ActionType hanhDong;

    @Column(name = "thoiGian")
    private Timestamp thoiGian;
    
    @Column(name = "actionDetail")
    private String chiTiet;
}
