package com.icia.finalproject.service;

import com.icia.finalproject.dto.MeetDTO;
import com.icia.finalproject.entity.BoardEntity;
import com.icia.finalproject.entity.BoardFileEntity;
import com.icia.finalproject.entity.MeetEntity;
import com.icia.finalproject.entity.MeetFileEntity;
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
}
