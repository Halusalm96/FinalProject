package com.icia.finalproject.dto;

import lombok.*;

@Setter
@Getter
@Builder
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class MemberDTO {
    private Long id;
    private String memberEmail;
    private String memberPassword;
    private String memberName;
    private String memberBirth;
    private String memberArea;
    private String memberNickName;
    private String memberMobile;
    private int memberFileAttached;
    private int memberPoint;
    private String createdAt;
}
