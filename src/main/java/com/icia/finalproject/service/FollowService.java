package com.icia.finalproject.service;

import com.icia.finalproject.dto.MemberDTO;
import com.icia.finalproject.entity.FollowEntity;
import com.icia.finalproject.entity.MemberEntity;
import com.icia.finalproject.repository.FollowRopository;
import com.icia.finalproject.repository.MemberRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.NoSuchElementException;

@Service
@RequiredArgsConstructor
public class FollowService {

    private final FollowRopository followRopository;

    private final MemberRepository memberRepository;

    @Transactional
    public void save(Long toUserId, MemberDTO memberDTO) {
        MemberEntity memberEntity = memberRepository.findById(memberDTO.getId()).orElseThrow(() -> new NoSuchElementException());
        FollowEntity followEntity = FollowEntity.toSave(memberEntity,toUserId);
        followRopository.save(followEntity);
    }

    public void delete(Long toUserId, Long id) {
        followRopository.deleteByToUserIdAndMemberId(toUserId,id);
    }
}
