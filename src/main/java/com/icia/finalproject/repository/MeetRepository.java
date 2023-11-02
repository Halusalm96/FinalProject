package com.icia.finalproject.repository;

import com.icia.finalproject.entity.MeetEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface MeetRepository extends JpaRepository<MeetEntity,Long> {
    @Modifying
    @Query(value = "update MeetEntity b set b.meetHits=b.meetHits+1 where b.id=:id")
    void increaseHits(@Param("id") Long id);
    
}
