package com.icia.finalproject.dto;

import com.icia.finalproject.entity.MemberEntity;
import com.icia.finalproject.entity.MemberFileEntity;
import com.icia.finalproject.util.UtilClass;
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
public class MemberDTO {
    private Long id;
    private String memberEmail;
    private String memberPassword;
    private String memberName;
    private String memberBirth;
    private String memberArea;
    private String memberNickName;
    private String memberMobile;
    private List<MultipartFile> memberFile;
    private int memberFileAttached;
    private int memberPoint;
    private String createdAt;
    private List<String> originalFileName = new ArrayList<>();
    private List<String> storedFileName = new ArrayList<>();

    public static MemberDTO toMemberDTO (MemberEntity memberEntity) {
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
        if (memberEntity.getMemberFileAttached()==1){
            for(MemberFileEntity memberFileEntity : memberEntity.getMemberFileEntityList()) {
                memberDTO.getOriginalFileName().add(memberFileEntity.getOriginalFileName());
                memberDTO.getStoredFileName().add(memberFileEntity.getStoredFileName());
            }
            memberDTO.setMemberFileAttached(1);
        }else{
            memberDTO.setMemberFileAttached(0);
        }
        return memberDTO;
    }
}
