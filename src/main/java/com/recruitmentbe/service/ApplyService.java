package com.recruitmentbe.service;
import com.recruitmentbe.model.UngTuyen;
import org.springframework.data.jpa.repository.JpaRepository;
public interface ApplyService {
    public Integer getNumberNotification(String body);
    public UngTuyen chuyenTrangThaiXem(String body);
    public UngTuyen approveCandidate(String body);
    public UngTuyen findApply(String body);
}
