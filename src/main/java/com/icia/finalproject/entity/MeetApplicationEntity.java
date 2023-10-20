package com.icia.finalproject.entity;

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
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "member_id", referencedColumnName = "id")
    private MemberEntity memberEntity;
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "meet_id", referencedColumnName = "id")
    private MeetEntity meetEntity;
}
