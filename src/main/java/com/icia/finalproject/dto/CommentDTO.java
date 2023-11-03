package com.icia.finalproject.dto;

import com.icia.finalproject.entity.CommentEntity;
import com.icia.finalproject.entity.LikeEntity;
import com.icia.finalproject.util.UtilClass;
import jdk.jshell.execution.Util;
import lombok.*;

import java.util.List;

@Setter
@Getter
@Builder
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class CommentDTO {
    private Long id;
    private String commentWriter;
    private String commentContents;
    private Long boardId;
    private String createdAt;
    private String updatedAt;

    public static CommentDTO toCommentList(CommentEntity commentEntity) {
        CommentDTO commentDTO = new CommentDTO();
        commentDTO.setId(commentEntity.getId());
        commentDTO.setBoardId(commentEntity.getBoardEntity().getId());
        commentDTO.setCommentWriter(commentEntity.getCommentWriter());
        commentDTO.setCommentContents(commentEntity.getCommentContents());
        commentDTO.setCreatedAt((UtilClass.dateTimeFormat(commentEntity.getCreatedAt())));
        commentDTO.setUpdatedAt(UtilClass.dateTimeFormat(commentEntity.getUpdateAt()));
        return commentDTO;
    }
}
