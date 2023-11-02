package com.icia.finalproject.service;

import com.icia.finalproject.dto.MeetApplicationDTO;
import com.icia.finalproject.entity.MeetApplicationEntity;
import com.icia.finalproject.entity.MeetEntity;
import com.icia.finalproject.entity.MemberEntity;
import com.icia.finalproject.repository.MeetApplicationRepository;
import com.icia.finalproject.repository.MeetRepository;
import com.icia.finalproject.repository.MemberRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.NoSuchElementException;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class MeetApplicationService {
    private final MeetApplicationRepository meetApplicationRepository;
    private final MeetRepository meetRepository;
    private final MemberRepository memberRepository;

    @Transactional
    public void save(MeetApplicationDTO meetApplicationDTO) throws IOException {
        MeetEntity meetEntity = meetRepository.findById(meetApplicationDTO.getMeetIdDate()).orElseThrow(() -> new NoSuchElementException());
        MemberEntity memberEntity = memberRepository.findById(meetApplicationDTO.getMemberIdDate()).orElseThrow(() -> new NoSuchElementException());
        MeetApplicationEntity meetApplicationEntity = MeetApplicationEntity.toSave(meetEntity,memberEntity, meetApplicationDTO);
        meetApplicationRepository.save(meetApplicationEntity);
    }

    @Transactional
    public List<MeetApplicationDTO> findByMemberId(Long memberId) {
        Optional<MemberEntity> memberEntity = memberRepository.findById(memberId);
        List<MeetApplicationEntity> meetApplicationEntityList = meetApplicationRepository.findByMemberEntity(memberEntity);
        List<MeetApplicationDTO> meetApplicationDTOList = new ArrayList<>();
        for (MeetApplicationEntity meetApplicationEntity : meetApplicationEntityList) {
            meetApplicationDTOList.add(MeetApplicationDTO.toMeetApplicationList(meetApplicationEntity));
        }
        return meetApplicationDTOList;
    }
}
