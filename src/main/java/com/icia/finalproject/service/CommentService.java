package com.icia.finalproject.service;

import com.icia.finalproject.dto.CommentDTO;
import com.icia.finalproject.entity.BoardEntity;
import com.icia.finalproject.entity.CommentEntity;
import com.icia.finalproject.entity.MemberEntity;
import com.icia.finalproject.repository.BoardRepository;
import com.icia.finalproject.repository.CommentRepository;
import com.icia.finalproject.repository.MemberRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.NoSuchElementException;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class CommentService {

    private final CommentRepository commentRepository;
    private final BoardRepository boardRepository;
    private final MemberRepository memberRepository;

    public Long save(CommentDTO commentDTO) throws IOException {
        MemberEntity memberEntity = memberRepository.findByMemberNickName(commentDTO.getCommentWriter()).orElseThrow(() -> new NoSuchElementException());
        BoardEntity boardEntity = boardRepository.findById(commentDTO.getBoardId()).orElseThrow(() -> new NoSuchElementException());
        CommentEntity commentEntity = CommentEntity.toSave(memberEntity, boardEntity, commentDTO);
        return commentRepository.save(commentEntity).getId();
    }

    @Transactional
    public List<CommentDTO> findAll(Long boardId) {
        BoardEntity boardEntity = boardRepository.findById(boardId).orElseThrow(() -> new NoSuchElementException());
        List<CommentEntity> commentEntityList = commentRepository.findByBoardEntityOrderByIdDesc(boardEntity);
        List<CommentDTO> commentDTOList = new ArrayList<>();
        commentEntityList.forEach(comment -> {
            commentDTOList.add(CommentDTO.toCommentList(comment));
        });
//        for (CommentEntity commentEntity : commentEntityList) {
//            commentDTOList.add(CommentDTO.toCommentList(commentEntity));
//        }
        return commentDTOList;
    }

    public void delete(Long id) {
        commentRepository.deleteById(id);
    }
}
