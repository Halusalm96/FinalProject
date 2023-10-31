package com.icia.finalproject.entity;

import com.icia.finalproject.dto.MeetDTO;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@Getter
@Setter(AccessLevel.PRIVATE)
@Table(name = "meet_table")
public class MeetEntity extends BaseEntity{
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column (nullable = false, length = 50)
    private String meetWriter;
    @Column (nullable = false, length = 50)
    private String meetTitle;
    @Column (nullable = false, length = 500)
    private String meetContents;
    @Column(nullable = false, length = 50)
    private String meetKind;
    @Column (nullable = false, length = 20)
    private int meetNumber;
    @Column
    private int meetFileAttached;
    @Column
    private int meetHits;
    @Column
    private int meetPoint;
    @Column (nullable = false, length = 50)
    private String meetMap;
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "member_id", referencedColumnName = "id")
    private MemberEntity memberEntity;
    @OneToMany(mappedBy = "meetEntity", cascade = CascadeType.REMOVE, orphanRemoval = true, fetch = FetchType.LAZY)
    private List<MeetFileEntity> meetFileEntityList = new ArrayList<>();

    public static MeetEntity toSave(MeetDTO meetDTO) {
        MeetEntity meetEntity = new MeetEntity();
        meetEntity.setMeetWriter(meetDTO.getMeetWriter());
        meetEntity.setMeetTitle(meetDTO.getMeetTitle());
        meetEntity.setMeetContents(meetDTO.getMeetContents());
        meetEntity.setMeetHits(meetDTO.getMeetHits());
        meetEntity.setMeetKind(meetDTO.getMeetKind());
        meetEntity.setMeetMap(meetDTO.getMeetMap());
        meetEntity.setMeetNumber(meetDTO.getMeetNumber());
        meetEntity.setMeetPoint(meetDTO.getMeetPoint());
        meetEntity.setMeetFileAttached(0);
        return meetEntity;
    }

    public static MeetEntity toMeetEntityWithFIle(MeetDTO meetDTO) {
        MeetEntity meetEntity = new MeetEntity();
        meetEntity.setMeetWriter(meetDTO.getMeetWriter());
        meetEntity.setMeetTitle(meetDTO.getMeetTitle());
        meetEntity.setMeetContents(meetDTO.getMeetContents());
        meetEntity.setMeetHits(meetDTO.getMeetHits());
        meetEntity.setMeetKind(meetDTO.getMeetKind());
        meetEntity.setMeetMap(meetDTO.getMeetMap());
        meetEntity.setMeetNumber(meetDTO.getMeetNumber());
        meetEntity.setMeetPoint(meetDTO.getMeetPoint());
        meetEntity.setMeetFileAttached(1);
        return meetEntity;
    }
}
