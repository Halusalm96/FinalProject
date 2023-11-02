package com.icia.finalproject.dto;

import lombok.*;

@Setter
@Getter
@Builder
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class FollowDTO {
    private Long id;
    private Long toUserId;
}
