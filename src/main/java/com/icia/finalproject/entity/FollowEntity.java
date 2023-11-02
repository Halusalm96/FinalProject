package com.icia.finalproject.entity;

import javax.persistence.*;

import lombok.*;

@Getter
@Setter
@Entity
@Table(uniqueConstraints = {
        @UniqueConstraint(
                name = "follow_table",
                columnNames = {"toUserId", "member_id"}
        )
}
)
public class FollowEntity {

    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Id
    private Long id;
    @Column
    private Long toUserId;
    @JoinColumn(name = "member_id")
    @ManyToOne(fetch = FetchType.LAZY)
    private MemberEntity memberEntity;

    public static FollowEntity toSave(MemberEntity memberEntity, Long toUserId) {
        FollowEntity followEntity = new FollowEntity();
        followEntity.setToUserId(toUserId);
        followEntity.setMemberEntity(memberEntity);
        return followEntity;
    }
}