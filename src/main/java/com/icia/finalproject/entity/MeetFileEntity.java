package com.icia.finalproject.entity;

import lombok.AccessLevel;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;

@Entity
@Getter
@Setter(AccessLevel.PRIVATE)
@Table(name = "meet_file_table")
public class MeetFileEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(length = 100)
    private String originalFileName;
    @Column(length = 100)
    private String storedFileName;
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "meet_id", referencedColumnName = "id")
    private MeetEntity meetEntity;
}
