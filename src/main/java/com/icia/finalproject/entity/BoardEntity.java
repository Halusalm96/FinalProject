package com.icia.finalproject.entity;

import lombok.AccessLevel;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@Getter
@Setter(AccessLevel.PRIVATE)
@Table(name = "board_table")
public class BoardEntity extends BaseEntity{
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(nullable = false, length = 50)
    private String boardTitle;
    @Column(nullable = false, length = 50)
    private String boardKind;
    @Column(nullable = false, length = 50)
    private String boardContents;
    @Column(nullable = false, length = 50)
    private String boardWriter;
    @Column
    private int boardFileAttached;
    @Column
    private int boardHits;
    @Column
    private int boardPoint;
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "member_id", referencedColumnName = "id")
    private MemberEntity memberEntity;
    @OneToMany(mappedBy = "boardEntity", cascade = CascadeType.REMOVE, orphanRemoval = true, fetch = FetchType.LAZY)
    private List<BoardFileEntity> boardFileEntityList = new ArrayList<>();
}
