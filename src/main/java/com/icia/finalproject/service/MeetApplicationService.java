package com.icia.finalproject.service;

import com.icia.finalproject.dto.MeetApplicationDTO;
import com.icia.finalproject.dto.MeetDTO;
import com.icia.finalproject.entity.MeetApplicationEntity;
import com.icia.finalproject.entity.MeetEntity;
import com.icia.finalproject.entity.MemberEntity;
import com.icia.finalproject.repository.MeetApplicationRepository;
import com.icia.finalproject.repository.MeetRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.NoSuchElementException;

@Service
@RequiredArgsConstructor
public class MeetApplicationService {
    private final MeetApplicationRepository meetApplicationRepository;
    private final MeetRepository meetRepository;

    @Transactional
    public void save(MeetApplicationDTO meetApplicationDTO) throws IOException {
        MeetEntity meetEntity = meetRepository.findById(meetApplicationDTO.getMeetIdDate()).orElseThrow(() -> new NoSuchElementException());
        MeetApplicationEntity meetApplicationEntity = MeetApplicationEntity.toSave(meetEntity, meetApplicationDTO);
        meetApplicationRepository.save(meetApplicationEntity);
    }

    public List<MeetApplicationDTO> findByMemberId(Long memberId) {
        List<MeetApplicationEntity> meetApplicationEntityList = meetApplicationRepository.findByMemberId(memberId);
        List<MeetApplicationDTO> meetApplicationDTOList = new ArrayList<>();
        for (MeetApplicationEntity meetApplicationEntity : meetApplicationEntityList) {
            meetApplicationDTOList.add(MeetApplicationDTO.toMeetApplicationList(meetApplicationEntity));
        }
        return meetApplicationDTOList;
    }
}
