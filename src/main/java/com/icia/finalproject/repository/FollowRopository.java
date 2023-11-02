package com.icia.finalproject.repository;

import com.icia.finalproject.entity.FollowEntity;

public interface FollowRopository {
    void save(FollowEntity followEntity);

    void deleteByToUserIdAndMemberId(Long toUserId, Long id);
}
