package com.icia.finalproject.entity;

import com.icia.finalproject.dto.BoardDTO;
import com.icia.finalproject.dto.MeetApplicationDTO;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;

@Entity
@Getter
@Setter(AccessLevel.PRIVATE)
@Table(name = "meet_application_table")
public class MeetApplicationEntity extends BaseEntity{
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column (nullable = false, length = 50)
    private String applicationWriter;
    @Column
    private Long meetIdDate;
    @Column(length = 50)
    private String meetName;
    @Column
    private Long memberIdDate;
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "meet_id", referencedColumnName = "id")
    private MeetEntity meetEntity;
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "member_id", referencedColumnName = "id")
    private MemberEntity memberEntity;

    public static MeetApplicationEntity toSave(MeetEntity meetEntity, MemberEntity memberEntity,MeetApplicationDTO meetApplicationDTO) {
    MeetApplicationEntity meetApplicationEntity = new MeetApplicationEntity();
    meetApplicationEntity.setMeetEntity(meetEntity);
    meetApplicationEntity.setApplicationWriter(meetApplicationDTO.getApplicationWriter());
    meetApplicationEntity.setMeetIdDate(meetApplicationDTO.getMeetIdDate());
    meetApplicationEntity.setMemberIdDate(meetApplicationDTO.getMemberIdDate());
    meetApplicationEntity.setMeetName(meetApplicationDTO.getMeetName());
    meetApplicationEntity.setMemberEntity(memberEntity);
    return meetApplicationEntity;
    }
}
