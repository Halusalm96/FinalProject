package com.icia.finalproject.dto;

import lombok.*;

@Setter
@Getter
@Builder
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class MeetApplicationDTO {
    private Long id;
    private String applicationWriter;
    private int memberPoint;
}
