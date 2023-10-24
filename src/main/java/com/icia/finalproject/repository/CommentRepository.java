package com.icia.finalproject.repository;

import com.icia.finalproject.entity.BoardEntity;
import com.icia.finalproject.entity.CommentEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface CommentRepository extends JpaRepository<CommentEntity,Long> {
    List<CommentEntity> findByBoardEntityId(Long id);

    List<CommentEntity> findByBoardEntityOrderByIdDesc(BoardEntity boardEntity);
}
