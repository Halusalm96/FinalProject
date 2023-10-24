package com.icia.finalproject.service;

import com.icia.finalproject.dto.BoardDTO;
import com.icia.finalproject.dto.CommentDTO;
import com.icia.finalproject.dto.LikeDTO;
import com.icia.finalproject.entity.BoardEntity;
import com.icia.finalproject.entity.CommentEntity;
import com.icia.finalproject.entity.LikeEntity;
import com.icia.finalproject.entity.MemberEntity;
import com.icia.finalproject.repository.BoardRepository;
import com.icia.finalproject.repository.CommentRepository;
import com.icia.finalproject.repository.LikeRepository;
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
    private final LikeRepository likeRepository;

    public Long save(CommentDTO commentDTO) throws IOException {
        MemberEntity memberEntity = memberRepository.findByMemberEmail(commentDTO.getCommentWriter()).orElseThrow(() -> new NoSuchElementException());
        BoardEntity boardEntity = boardRepository.findById(commentDTO.getId()).orElseThrow(() -> new NoSuchElementException());
        CommentEntity commentEntity = CommentEntity.toSave(memberEntity, boardEntity, commentDTO);
        return commentRepository.save(commentEntity).getId();
    }

    @Transactional
    public List<CommentDTO> findAll(Long memberId, Long boardId) {
        BoardEntity boardEntity = boardRepository.findById(boardId).orElseThrow(() -> new NoSuchElementException());
        List<CommentEntity> commentEntityList = commentRepository.findByBoardEntityOrderByIdDesc(boardEntity);
        MemberEntity memberEntity = memberRepository.findById(memberId).orElseThrow(() -> new NoSuchElementException());
        List<LikeEntity> likeEntityList = likeRepository.findByMemberEntityAndBoardEntity(memberEntity, boardEntity);
        List<CommentDTO> commentDTOList = new ArrayList<>();
        commentEntityList.forEach(comment -> {
            commentDTOList.add(CommentDTO.toCommentList(comment, likeEntityList));
        });
        return commentDTOList;
    }
    // 좋아요 여부 체크
    public boolean likeCheck(LikeDTO likeDTO) {
        MemberEntity memberEntity = memberRepository.findById(likeDTO.getMemberId()).orElseThrow(() -> new NoSuchElementException());
        CommentEntity commentEntity = commentRepository.findById(likeDTO.getCommentId()).orElseThrow(() -> new NoSuchElementException());
        Optional<LikeEntity> optionalLikeEntity = likeRepository.findByMemberEntityAndCommentEntity(memberEntity, commentEntity);
        if (optionalLikeEntity.isEmpty()) {
            return true;
        } else {
            return false;
        }
    }

    // 좋아요 처리
    public Long like(LikeDTO likeDTO) {
        MemberEntity memberEntity = memberRepository.findById(likeDTO.getMemberId()).orElseThrow(() -> new NoSuchElementException());
        BoardEntity boardEntity = boardRepository.findById(likeDTO.getBoardId()).orElseThrow(() -> new NoSuchElementException());
        CommentEntity commentEntity = commentRepository.findById(likeDTO.getCommentId()).orElseThrow(() -> new NoSuchElementException());
        LikeEntity likeEntity = LikeEntity.toLikeEntity(memberEntity, boardEntity, commentEntity);
        return likeRepository.save(likeEntity).getId();
    }

    // 좋아요 취소 처리
    @Transactional
    public void unLike(LikeDTO likeDTO) {
        MemberEntity memberEntity = memberRepository.findById(likeDTO.getMemberId()).orElseThrow(() -> new NoSuchElementException());
        CommentEntity commentEntity = commentRepository.findById(likeDTO.getCommentId()).orElseThrow(() -> new NoSuchElementException());
        likeRepository.deleteByMemberEntityAndCommentEntity(memberEntity, commentEntity);
    }
}
