package com.icia.finalproject.service;

import com.icia.finalproject.dto.MeetApplicationDTO;
import com.icia.finalproject.entity.MeetApplicationEntity;
import com.icia.finalproject.entity.MeetEntity;
import com.icia.finalproject.entity.MemberEntity;
import com.icia.finalproject.repository.MeetApplicationRepository;
import com.icia.finalproject.repository.MeetRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.io.IOException;
import java.util.NoSuchElementException;

@Service
@RequiredArgsConstructor
public class MeetApplicationService {
    private final MeetApplicationRepository meetApplicationRepository;
    private final MeetRepository meetRepository;
    @Transactional
    public void save(MeetApplicationDTO meetApplicationDTO) throws IOException {
        MeetEntity meetEntity = meetRepository.findById(meetApplicationDTO.getMeetIdDate()).orElseThrow(() -> new NoSuchElementException());
        MeetApplicationEntity meetApplicationEntity = MeetApplicationEntity.toSave(meetEntity,meetApplicationDTO);
    meetApplicationRepository.save(meetApplicationEntity);
    }
}
