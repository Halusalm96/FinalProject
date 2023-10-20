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
public class MeetDTO {
    private Long id;
    private String meetWriter;
    private String meetTitle;
    private String meetContents;
    private String meetKind;
    private int meetNumber;
    private int meetFileAttached;
    private int meetHits;
    private String meetMap;
    private int meetPoint;
    private List<MultipartFile> meetFile;
    private String createdAt;
    private List<String> originalFileName = new ArrayList<>();
    private List<String> storedFileName = new ArrayList<>();
}
