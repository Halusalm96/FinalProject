package com.icia.finalproject.dto;

import com.icia.finalproject.entity.BoardFileEntity;
import com.icia.finalproject.entity.MeetEntity;
import com.icia.finalproject.entity.MeetFileEntity;
import com.icia.finalproject.util.UtilClass;
import lombok.*;
import org.springframework.web.multipart.MultipartFile;

import java.util.ArrayList;
import java.util.List;

@Setter
@Getter
@Builder
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class MeetDTO {
    private Long id;
    private String meetWriter;
    private String meetTitle;
    private String meetContents;
    private String meetKind;
    private int meetNumber;
    private int meetFileAttached;
    private int meetHits;
    private String meetMap;
    private List<MultipartFile> meetFile;
    private String createdAt;
    private List<String> originalFileName = new ArrayList<>();
    private List<String> storedFileName = new ArrayList<>();

    public static MeetDTO toMeetList(MeetEntity meetEntity) {
        MeetDTO meetDTO = new MeetDTO();
        meetDTO.setId(meetEntity.getId());
        meetDTO.setMeetWriter(meetEntity.getMeetWriter());
        meetDTO.setMeetTitle(meetEntity.getMeetTitle());
        meetDTO.setMeetContents(meetEntity.getMeetContents());
        meetDTO.setMeetKind(meetEntity.getMeetKind());
        meetDTO.setMeetNumber(meetEntity.getMeetNumber());
        meetDTO.setMeetHits(meetEntity.getMeetHits());
        meetDTO.setMeetMap(meetEntity.getMeetMap());
        meetDTO.setCreatedAt((UtilClass.dateTimeFormat(meetEntity.getCreatedAt())));
        if (meetEntity.getMeetFileAttached()==1){
            for(MeetFileEntity meetFileEntity : meetEntity.getMeetFileEntityList()) {
                meetDTO.getOriginalFileName().add(meetFileEntity.getOriginalFileName());
                meetDTO.getStoredFileName().add(meetFileEntity.getStoredFileName());
            }
            meetDTO.setMeetFileAttached(1);
        }else{
            meetDTO.setMeetFileAttached(0);
        }
        return meetDTO;
    }
}
