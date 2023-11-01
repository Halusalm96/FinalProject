package com.icia.finalproject.service;

import com.icia.finalproject.dto.BoardDTO;
import com.icia.finalproject.dto.MeetDTO;
import com.icia.finalproject.entity.*;
import com.icia.finalproject.repository.MeetFileRepository;
import com.icia.finalproject.repository.MeetRepository;
import com.icia.finalproject.repository.MemberRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.NoSuchElementException;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class MeetService {
    private final MeetRepository meetRepository;
    private final MeetFileRepository meetFileRepository;
    private final MemberRepository memberRepository;
    @Transactional
    public List<MeetDTO> findAll() {
        List<MeetEntity> meetEntityList = meetRepository.findAll();
        List<MeetDTO> meetDTOList = new ArrayList<>();
        for (MeetEntity meetEntity : meetEntityList) {
            meetDTOList.add(MeetDTO.toMeetList(meetEntity));
        }
        return meetDTOList;
    }

    @Transactional
    public void save(MeetDTO meetDTO) throws IOException {
        if (meetDTO.getMeetFile().get(0).isEmpty()) {
            MeetEntity meetEntity = MeetEntity.toSave(meetDTO);
            meetRepository.save(meetEntity);
        } else {
            MeetEntity meetEntity = MeetEntity.toMeetEntityWithFIle(meetDTO);
            MeetEntity saveEntity = meetRepository.save(meetEntity);
            for (MultipartFile meetFIle : meetDTO.getMeetFile()) {
                String originalFilename = meetFIle.getOriginalFilename();
                String storedFileName = System.currentTimeMillis() + "_" + originalFilename;
                String savePath = "\\C:\\Date\\spring_boot_img\\" + storedFileName;
                meetFIle.transferTo(new File(savePath));
                MeetFileEntity meetFileEntity = MeetFileEntity.toSaveMeetFile(saveEntity, originalFilename, storedFileName);
                meetFileRepository.save(meetFileEntity);
            }
        }
    }

    @Transactional
    public void increaseHits(Long id) {
        meetRepository.increaseHits(id);
    }

    @Transactional
    public MeetDTO findById(Long id) {
        Optional<MeetEntity> meetEntityById = meetRepository.findById(id);
        if(meetEntityById.isPresent()) {
            MeetEntity meetEntity = meetEntityById.get();
            return MeetDTO.toMeetList(meetEntity);
        }else {
            return null;
        }
    }

    public void delete(Long id) {
        meetRepository.deleteById(id);
    }

    public void update(MeetDTO meetDTO) {
        MemberEntity memberEntity = memberRepository.findByMemberNickName(meetDTO.getMeetWriter()).orElseThrow(() -> new NoSuchElementException());
        MeetEntity meetEntity = MeetEntity.toUpdateEntity(memberEntity, meetDTO);
        meetRepository.save(meetEntity);
    }

}
