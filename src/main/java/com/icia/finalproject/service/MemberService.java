package com.icia.finalproject.service;

import com.icia.finalproject.dto.MemberDTO;
import com.icia.finalproject.entity.MemberEntity;
import com.icia.finalproject.entity.MemberFileEntity;
import com.icia.finalproject.repository.MemberFileRepository;
import com.icia.finalproject.repository.MemberRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class MemberService {

    private final MemberRepository memberRepository;
    private final MemberFileRepository memberFileRepository;

    public void save(MemberDTO memberDTO) throws IOException {
        if (memberDTO.getMemberFile().get(0).isEmpty()) {
            // 첨부파일 없음
            MemberEntity memberEntity = MemberEntity.save(memberDTO);
            memberRepository.save(memberEntity);
        } else {
            // 첨부파일 있음
            MemberEntity memberEntity = MemberEntity.toMemberEntityWithFile(memberDTO);
            // 게시글 저장처리 후 저장한 엔티티 가져옴
            MemberEntity savedEntity = memberRepository.save(memberEntity);
            // 파일 이름 처리, 파일 로컬에 저장 등
            // DTO에 담긴 파일리스트 꺼내기
            for (MultipartFile memberFile : memberDTO.getMemberFile()) {
                // 업로드한 파일 이름
                String originalFilename = memberFile.getOriginalFilename();
                // 저장용 파일 이름
                String storedFileName = System.currentTimeMillis() + "_" + originalFilename;
                // 저장경로+파일이름 준비
                String savePath = "\\C:\\Date\\spring_boot_img\\" + storedFileName;
                // 파일 폴더에 저장
                memberFile.transferTo(new File(savePath));
                // 파일 정보 board_file_table에 저장
                // 파일 정보 저장을 위한 BoardFileEntity 생성
                MemberFileEntity memberFileEntity = MemberFileEntity.saveMemberFile(savedEntity, originalFilename, storedFileName);
                memberFileRepository.save(memberFileEntity);
            }
        }
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

    public MemberDTO login(MemberDTO memberDTO) {
        Optional<MemberEntity> optionalMemberEntity = memberRepository.findByMemberEmailAndMemberPassword(memberDTO.getMemberEmail(), memberDTO.getMemberPassword());
        if (optionalMemberEntity.isPresent()) {
            return MemberDTO.toMemberDTO(optionalMemberEntity.get());
        } else {
            return null;
        }
    }


    public MemberDTO findByMemberEmail(String memberEmail) {
        Optional<MemberEntity> memberRepositoryByEmail = memberRepository.findByMemberEmail(memberEmail);
        if (memberRepositoryByEmail.isPresent()) {
            MemberEntity memberEntity = memberRepositoryByEmail.get();
            return MemberDTO.toMemberDTO(memberEntity);
        } else {
            return null;
        }
    }

    public void delete(Long id) {
        memberRepository.deleteById(id);
    }

    public MemberDTO findById(Long id) {
        Optional<MemberEntity> memberRepositoryById = memberRepository.findById(id);
        if (memberRepositoryById.isPresent()) {
            MemberEntity memberEntity = memberRepositoryById.get();
            return MemberDTO.toMemberDTO(memberEntity);
        } else {
            return null;
        }
    }

    public void update(MemberDTO memberDTO) {
        System.out.println("memberDTO = " + memberDTO);
        MemberEntity memberEntity = MemberEntity.update(memberDTO);
        memberRepository.save(memberEntity);
    }

    public List<MemberDTO> findAll() {
        List<MemberEntity> memberEntityList = memberRepository.findAll();
        List<MemberDTO> memberDTOList = new ArrayList<>();
        for (MemberEntity memberEntity : memberEntityList) {
            memberDTOList.add(MemberDTO.toMemberDTO(memberEntity));
        }
        return memberDTOList;
    }
}
