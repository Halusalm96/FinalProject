package com.icia.finalproject.repository;

import com.icia.finalproject.entity.BoardEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface BoardRepository extends JpaRepository<BoardEntity,Long> {
    @Modifying
    @Query(value = "update BoardEntity b set b.boardHits=b.boardHits+1 where b.id=:id")
    void increaseHits(@Param("id") Long id);

//    List<BoardEntity> findByBoardEntityOrderByIdDesc(BoardEntity boardEntity);
}