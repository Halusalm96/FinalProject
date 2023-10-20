package com.icia.finalproject.dto;

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
    private int boardPoint;
    private List<MultipartFile> boardFile;
    private String createdAt;
    private List<String> originalFileName = new ArrayList<>();
    private List<String> storedFileName = new ArrayList<>();
}
