package com.icia.finalproject.dto;

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
}
