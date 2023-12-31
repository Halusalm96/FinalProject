package com.icia.finalproject.dto;

import com.icia.finalproject.entity.BoardEntity;
import com.icia.finalproject.entity.BoardFileEntity;
import com.icia.finalproject.entity.LikeEntity;
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
public class BoardDTO {
    private Long id;
    private String boardTitle;
    private String boardKind;
    private String boardContents;
    private String boardWriter;
    private int boardFileAttached;
    private int boardHits;
    private List<MultipartFile> boardFile;
    private String createdAt;
    private List<String> originalFileName = new ArrayList<>();
    private List<String> storedFileName = new ArrayList<>();
    private int isLike = 0;
    private int likeCount;

    public static BoardDTO toBoardList(BoardEntity boardEntity, List<LikeEntity> likeEntityList) {
        BoardDTO boardDTO = new BoardDTO();
        boardDTO.setId(boardEntity.getId());
        boardDTO.setBoardTitle(boardEntity.getBoardTitle());
        boardDTO.setBoardKind(boardEntity.getBoardKind());
        boardDTO.setBoardContents(boardEntity.getBoardContents());
        boardDTO.setBoardWriter(boardEntity.getBoardWriter());
        boardDTO.setBoardHits(boardEntity.getBoardHits());
        boardDTO.setCreatedAt((UtilClass.dateTimeFormat(boardEntity.getCreatedAt())));
        boardDTO.setLikeCount(boardEntity.getLikeEntityList().size());
        for (LikeEntity likeEntity : likeEntityList) {
            if (likeEntity.getBoardEntity().getId().equals(boardEntity.getId())) {
                boardDTO.setIsLike(1);
            }
        }
        if (boardEntity.getBoardFileAttached() == 1) {
            for (BoardFileEntity boardFileEntity : boardEntity.getBoardFileEntityList()) {
                boardDTO.getOriginalFileName().add(boardFileEntity.getOriginalFileName());
                boardDTO.getStoredFileName().add(boardFileEntity.getStoredFileName());
            }
            boardDTO.setBoardFileAttached(1);
        } else {
            boardDTO.setBoardFileAttached(0);
        }
        return boardDTO;
    }
}
