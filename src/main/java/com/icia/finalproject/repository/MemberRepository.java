package com.icia.finalproject.repository;

import com.icia.finalproject.entity.MemberEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface MemberRepository  extends JpaRepository<MemberEntity,Long> {
}
