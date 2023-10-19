package com.icia.finalproject.service;

import com.icia.finalproject.dto.MemberDTO;
import com.icia.finalproject.entity.MemberEntity;
import com.icia.finalproject.repository.MemberRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@RequiredArgsConstructor
public class MemberService {

    private final MemberRepository memberRepository;

    public void save(MemberDTO memberDTO) {
        MemberEntity memberEntity = MemberEntity.save(memberDTO);
        memberRepository.save(memberEntity);
    }

    public boolean checkEmail(String memberEmail) {
        Optional<MemberEntity> optionalMemberEntity = memberRepository.findByMemberEmail(memberEmail);
        if (optionalMemberEntity.isEmpty()) {
            return true;
        } else {
            return false;
        }
    }

    public boolean checkNickName(String memberNickName) {
        Optional<MemberEntity> optionalMemberEntity = memberRepository.findByMemberNickName(memberNickName);
        if (optionalMemberEntity.isEmpty()) {
            return true;
        } else {
            return false;
        }
    }

    public boolean checkMobile(String memberMobile) {
        Optional<MemberEntity> optionalMemberEntity = memberRepository.findByMemberMobile(memberMobile);
        if (optionalMemberEntity.isEmpty()) {
            return true;
        } else {
            return false;
        }
    }

    public boolean login(MemberDTO memberDTO) {
        Optional<MemberEntity> optionalMemberEntity = memberRepository.findByMemberEmailAndMemberPassword(memberDTO.getMemberEmail(), memberDTO.getMemberPassword());
        if (optionalMemberEntity.isPresent()) {
            return true;
        } else {
            return false;
        }
    }


    public MemberDTO findByMemberEmail(String memberEmail) {
        Optional<MemberEntity> memberRepositoryByEmail = memberRepository.findByMemberEmail(memberEmail);
        if (memberRepositoryByEmail.isPresent()) {
            MemberEntity memberEntity = memberRepositoryByEmail.get();
            return MemberDTO.detail(memberEntity);
        } else {
            return null;
        }
    }

    public void delete(Long id) {
        memberRepository.deleteById(id);
    }

    public MemberDTO findById(Long id) {
        Optional<MemberEntity> memberRepositoryById = memberRepository.findById(id);
        if(memberRepositoryById.isPresent()) {
            MemberEntity memberEntity = memberRepositoryById.get();
            return MemberDTO.detail(memberEntity);
        }else {
         return null;
        }
    }

    public void update(MemberDTO memberDTO) {
        System.out.println("memberDTO = " + memberDTO);
        MemberEntity memberEntity = MemberEntity.update(memberDTO);
        memberRepository.save(memberEntity);
    }
}
