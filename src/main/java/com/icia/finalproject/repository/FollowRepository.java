package com.icia.finalproject.repository;

import com.icia.finalproject.entity.FollowEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

public interface FollowRepository  extends JpaRepository<FollowEntity,Long> {

    @Modifying
    @Query("DELETE FROM FollowEntity f WHERE f.toUserId = :toUserId AND f.memberEntity.id = :memberId")
    void deleteByToUserIdAndMemberEntityId(@Param("toUserId") Long toUserId, @Param("memberId") Long memberId);

//    void deleteByToUserIdAndMemberId(Long toUserId, Long memberId);
}
