package com.icia.finalproject.repository;

import com.icia.finalproject.entity.MeetApplicationEntity;
import com.icia.finalproject.entity.MemberEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface MeetApplicationRepository extends JpaRepository<MeetApplicationEntity,Long> {

    List<MeetApplicationEntity> findByMemberEntity(Optional<MemberEntity> memberEntity);
}
