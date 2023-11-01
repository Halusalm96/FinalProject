package com.icia.finalproject.repository;

import com.icia.finalproject.entity.MeetApplicationEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface MeetApplicationRepository extends JpaRepository<MeetApplicationEntity,Long> {
    List<MeetApplicationEntity> findByMemberId(Long memberId);
}
