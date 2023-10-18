package com.icia.finalproject.repository;

import com.icia.finalproject.entity.MemberEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface MemberRepository  extends JpaRepository<MemberEntity,Long> {
    Optional<MemberEntity> findByMemberEmail(String memberEmail);

    Optional<MemberEntity> findByMemberNickName(String memberNickName);

    Optional<MemberEntity> findByMemberMobile(String memberMobile);
}
