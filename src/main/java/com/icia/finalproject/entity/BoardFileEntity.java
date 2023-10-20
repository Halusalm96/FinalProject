package com.icia.finalproject.entity;

import lombok.AccessLevel;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;

@Entity
@Getter
@Setter(AccessLevel.PRIVATE)
@Table(name = "board_file_table")
public class BoardFileEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(length = 100)
    private String originalFileName;
    @Column(length = 100)
    private String storedFileName;
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "board_id", referencedColumnName = "id")
    private BoardEntity boardEntity;

    public static BoardFileEntity toSaveBoardFile(BoardEntity saveEntity, String originalFilename, String storedFileName) {
        BoardFileEntity boardFileEntity = new BoardFileEntity();
        boardFileEntity.setOriginalFileName(originalFilename);
        boardFileEntity.setStoredFileName(storedFileName);
        boardFileEntity.setBoardEntity(saveEntity);
        return boardFileEntity;
    }
}
