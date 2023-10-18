package com.icia.finalproject.dto;

import com.icia.finalproject.entity.MemberEntity;
import com.icia.finalproject.util.UtilClass;
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

    public static MemberDTO detail (MemberEntity memberEntity) {
        MemberDTO memberDTO = new MemberDTO();
        memberDTO.setId(memberEntity.getId());
        memberDTO.setMemberEmail(memberEntity.getMemberEmail());
        memberDTO.setMemberPassword(memberEntity.getMemberPassword());
        memberDTO.setMemberName(memberEntity.getMemberName());
        memberDTO.setMemberBirth(memberEntity.getMemberBirth());
        memberDTO.setMemberArea(memberEntity.getMemberArea());
        memberDTO.setMemberNickName(memberEntity.getMemberNickName());
        memberDTO.setMemberMobile(memberEntity.getMemberMobile());
        memberDTO.setCreatedAt((UtilClass.dateTimeFormat(memberEntity.getCreatedAt())));
        return memberDTO;
    }
}
