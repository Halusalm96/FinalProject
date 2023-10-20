package com.icia.finalproject.entity;

import com.icia.finalproject.dto.BoardDTO;
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

    public static BoardEntity toSave(BoardDTO boardDTO) {
        BoardEntity boardEntity = new BoardEntity();
        boardEntity.setBoardTitle(boardDTO.getBoardTitle());
        boardEntity.setBoardKind(boardDTO.getBoardKind());
        boardEntity.setBoardContents(boardDTO.getBoardContents());
        boardEntity.setBoardWriter(boardDTO.getBoardWriter());
        boardEntity.setBoardHits(boardDTO.getBoardHits());
        boardEntity.setBoardPoint(boardDTO.getBoardPoint());
        boardEntity.setBoardFileAttached(0);
        return boardEntity;
    }

    public static BoardEntity toBoardEntityWithFIle(BoardDTO boardDTO) {
        BoardEntity boardEntity = new BoardEntity();
        boardEntity.setBoardTitle(boardDTO.getBoardTitle());
        boardEntity.setBoardKind(boardDTO.getBoardKind());
        boardEntity.setBoardContents(boardDTO.getBoardContents());
        boardEntity.setBoardWriter(boardDTO.getBoardWriter());
        boardEntity.setBoardHits(boardDTO.getBoardHits());
        boardEntity.setBoardPoint(boardDTO.getBoardPoint());
        boardEntity.setBoardFileAttached(1);
        return boardEntity;
    }
}
