package com.icia.finalproject.entity;

import com.icia.finalproject.dto.MemberDTO;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Entity
@Getter
@Setter(AccessLevel.PRIVATE)
@Table(name = "member_table")
public class MemberEntity extends BaseEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(nullable = false, unique = true, length = 50)
    private String memberEmail;
    @Column(nullable = false, length = 50)
    private String memberPassword;
    @Column(nullable = false, length = 30)
    private String memberName;
    @Column(length = 30)
    private String memberMobile;
    @Column(length = 20)
    private String memberArea;
    @Column(nullable = false, length = 50)
    private String memberNickName;
    @Column
    private int memberPoint;
    @Column
    private int memberFileAttached;
    @Column(length = 30)
    private String memberBirth;
    @OneToMany(mappedBy = "memberEntity", cascade = CascadeType.ALL, orphanRemoval = true, fetch = FetchType.EAGER)
    private List<MemberFileEntity> memberFileEntityList = new ArrayList<>();

    public static MemberEntity save(MemberDTO memberDTO) {
        MemberEntity memberEntity = new MemberEntity();
        memberEntity.setMemberEmail(memberDTO.getMemberEmail());
        memberEntity.setMemberPassword(memberDTO.getMemberPassword());
        memberEntity.setMemberName(memberDTO.getMemberName());
        memberEntity.setMemberBirth(memberDTO.getMemberBirth());
        memberEntity.setMemberArea(memberDTO.getMemberArea());
        memberEntity.setMemberMobile(memberDTO.getMemberMobile());
        memberEntity.setMemberNickName(memberDTO.getMemberNickName());
        memberEntity.setMemberPoint(0);
        memberEntity.setMemberFileAttached(0);
        return memberEntity;
    }

    public static MemberEntity update(MemberDTO memberDTO) {
        MemberEntity memberEntity = new MemberEntity();
        memberEntity.setId(memberDTO.getId());
        memberEntity.setMemberEmail(memberDTO.getMemberEmail());
        memberEntity.setMemberPassword(memberDTO.getMemberPassword());
        memberEntity.setMemberName(memberDTO.getMemberName());
        memberEntity.setMemberBirth(memberDTO.getMemberBirth());
        memberEntity.setMemberArea(memberDTO.getMemberArea());
        memberEntity.setMemberMobile(memberDTO.getMemberMobile());
        memberEntity.setMemberNickName(memberDTO.getMemberNickName());
        memberEntity.setMemberPoint(memberDTO.getMemberPoint());
        memberEntity.setMemberFileAttached(memberDTO.getMemberFileAttached());
        return memberEntity;
    }

    public static MemberEntity toMemberEntityWithFile(MemberDTO memberDTO) {
        MemberEntity memberEntity = new MemberEntity();
        memberEntity.setMemberEmail(memberDTO.getMemberEmail());
        memberEntity.setMemberPassword(memberDTO.getMemberPassword());
        memberEntity.setMemberName(memberDTO.getMemberName());
        memberEntity.setMemberBirth(memberDTO.getMemberBirth());
        memberEntity.setMemberArea(memberDTO.getMemberArea());
        memberEntity.setMemberMobile(memberDTO.getMemberMobile());
        memberEntity.setMemberNickName(memberDTO.getMemberNickName());
        memberEntity.setMemberPoint(0);
        memberEntity.setMemberFileAttached(1);
        return memberEntity;
    }
}
