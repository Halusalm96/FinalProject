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
    private int isLike = 0;
    private int likeCount;

    public static CommentDTO toCommentList(CommentEntity commentEntity, List<LikeEntity> likeEntityList) {
        CommentDTO commentDTO = new CommentDTO();
        commentDTO.setId(commentEntity.getId());
        commentDTO.setCommentWriter(commentEntity.getCommentWriter());
        commentDTO.setCommentContents(commentEntity.getCommentContents());
        commentDTO.setCreatedAt((UtilClass.dateTimeFormat(commentEntity.getCreatedAt())));
        commentDTO.setLikeCount(commentEntity.getLikeEntityList().size());
        for (LikeEntity likeEntity: likeEntityList) {
            if (likeEntity.getCommentEntity().getId().equals(commentEntity.getId())) {
                commentDTO.setIsLike(1);
            }
        }
        return commentDTO;
    }
}
