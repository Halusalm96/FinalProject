package com.icia.finalproject.service;

import com.icia.finalproject.dto.MemberDTO;
import com.icia.finalproject.entity.MemberEntity;
import com.icia.finalproject.repository.MemberRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class MemberService {

    private final MemberRepository memberRepository;

    public void save(MemberDTO memberDTO) {
        MemberEntity memberEntity = MemberEntity.save(memberDTO);
        memberRepository.save(memberEntity);
    }
}
