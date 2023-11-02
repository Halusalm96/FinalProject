package com.icia.finalproject.service;

import com.icia.finalproject.dto.MemberDTO;
import com.icia.finalproject.entity.FollowEntity;
import com.icia.finalproject.entity.MemberEntity;
import com.icia.finalproject.repository.FollowRepository;
import com.icia.finalproject.repository.MemberRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.NoSuchElementException;

@Service
@RequiredArgsConstructor
public class FollowService {

    private final FollowRepository followRepository;

    private final MemberRepository memberRepository;

    @Transactional
    public void save(Long toUserId, MemberDTO memberDTO) {
        MemberEntity memberEntity = memberRepository.findById(memberDTO.getId()).orElseThrow(() -> new NoSuchElementException());
        FollowEntity followEntity = FollowEntity.toSave(memberEntity, toUserId);
        followRepository.save(followEntity);
    }

    public void delete(Long toUserId, Long id) {
        followRepository.deleteByToUserIdAndMemberEntityId(toUserId, id);
    }
}
