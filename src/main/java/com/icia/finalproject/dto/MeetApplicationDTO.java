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

    public static MeetApplicationDTO toMeetApplicationList(MeetApplicationEntity meetApplicationEntity) {
    MeetApplicationDTO meetApplicationDTO = new MeetApplicationDTO();
    meetApplicationDTO.setId(meetApplicationEntity.getId());
    meetApplicationDTO.setApplicationWriter(meetApplicationEntity.getApplicationWriter());
    meetApplicationDTO.setMeetIdDate(meetApplicationEntity.getMeetIdDate());
    return meetApplicationDTO;
    }
}
