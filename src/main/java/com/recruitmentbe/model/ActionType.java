package com.recruitmentbe.model;

import java.util.ArrayList;
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
@Table(name="actionType")
public class ActionType {
	@Id
	@Column(name="actionTypeId")
	private long actionTypeId;
	
	@Column(name="actionTypeName")
	private String tenHanhDong;
	
	@OneToMany(mappedBy = "hanhDong", cascade = CascadeType.ALL, orphanRemoval = true)
	private List<HistoryCandidate> ungVien = new ArrayList<>();

	@OneToMany(mappedBy = "congTy", cascade = CascadeType.ALL, orphanRemoval = true)
	private List<HistoryCompany> congTy = new ArrayList<>();
}
