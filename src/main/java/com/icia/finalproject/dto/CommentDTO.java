package com.icia.finalproject.dto;

import com.icia.finalproject.entity.CommentEntity;
import com.icia.finalproject.util.UtilClass;
import jdk.jshell.execution.Util;
import lombok.*;

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
    private String createdAt;

    public static CommentDTO toCommentList(CommentEntity commentEntity) {
        CommentDTO commentDTO = new CommentDTO();
        commentDTO.setId(commentEntity.getId());
        commentDTO.setCommentWriter(commentEntity.getCommentWriter());
        commentDTO.setCommentContents(commentEntity.getCommentContents());
        commentDTO.setCreatedAt((UtilClass.dateTimeFormat(commentEntity.getCreatedAt())));
        return commentDTO;
    }
}
