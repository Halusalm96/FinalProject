package com.icia.finalproject.dto;

import com.icia.finalproject.entity.MeetApplicationEntity;
import lombok.*;

@Setter
@Getter
@Builder
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class MeetApplicationDTO {
    private Long id;
    private String applicationWriter;
    private Long meetIdDate;
    private Long memberIdDate;
    private String meetName;

    public static MeetApplicationDTO toMeetApplicationList(MeetApplicationEntity meetApplicationEntity) {
    MeetApplicationDTO meetApplicationDTO = new MeetApplicationDTO();
    meetApplicationDTO.setId(meetApplicationEntity.getId());
    meetApplicationDTO.setApplicationWriter(meetApplicationEntity.getApplicationWriter());
    meetApplicationDTO.setMeetIdDate(meetApplicationEntity.getMeetIdDate());
    meetApplicationDTO.setMemberIdDate(meetApplicationEntity.getMemberIdDate());
    meetApplicationDTO.setMeetName(meetApplicationEntity.getMeetName());
    return meetApplicationDTO;
    }
}
