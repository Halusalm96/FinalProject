package com.icia.finalproject.service;

import com.icia.finalproject.dto.BoardDTO;
import com.icia.finalproject.dto.LikeDTO;
import com.icia.finalproject.entity.BoardEntity;
import com.icia.finalproject.entity.BoardFileEntity;
import com.icia.finalproject.entity.LikeEntity;
import com.icia.finalproject.entity.MemberEntity;
import com.icia.finalproject.repository.BoardFileRepository;
import com.icia.finalproject.repository.BoardRepository;
import com.icia.finalproject.repository.LikeRepository;
import com.icia.finalproject.repository.MemberRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.NoSuchElementException;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class BoardService {

    private final BoardRepository boardRepository;
//    @Autowired
    private final BoardFileRepository boardFileRepository;

    private final MemberRepository memberRepository;
    private final LikeRepository likeRepository;

    @Transactional
    public List<BoardDTO> findAll(Long memberId) {

        // 해당 게시글의 댓글 목록
        List<BoardEntity> boardEntityList = boardRepository.findAll();
        MemberEntity memberEntity = memberRepository.findById(memberId).orElseThrow(() -> new NoSuchElementException());
        // 로그인 회원이 해당 게시글에 좋아요를 한 좋아요 목록
        List<LikeEntity> likeEntityList = likeRepository.findByMemberEntity(memberEntity);
        // 댓글 DTO 변환시 로그인한 회원이 각 댓글에 좋아요를 했냐 안했냐를 따져야 함
        List<BoardDTO> boardDTOList = new ArrayList<>();
        boardEntityList.forEach(board -> {
            boardDTOList.add(BoardDTO.toBoardList(board, likeEntityList));
        });
        return boardDTOList;
    }

    @Transactional
    public BoardDTO findById(Long boardId, Long memberId) {
        Optional<BoardEntity> boardEntityOptional = boardRepository.findById(boardId);
        Optional<MemberEntity> memberEntityOptional = memberRepository.findById(memberId);
        if (boardEntityOptional.isPresent() && memberEntityOptional.isPresent()) {
            BoardEntity boardEntity = boardEntityOptional.get();
            MemberEntity memberEntity = memberEntityOptional.get();
            List<LikeEntity> likeEntityList = likeRepository.findByMemberEntityAndBoardEntity(memberEntity, boardEntity);
            BoardDTO boardDTO = BoardDTO.toBoardList(boardEntity, likeEntityList);
            return boardDTO;
        } else {
            return null;
        }
    }

    @Transactional
    public void save(BoardDTO boardDTO) throws IOException {
        if(boardDTO.getBoardFile().get(0).isEmpty()) {
            MemberEntity memberEntity = memberRepository.findByMemberNickName(boardDTO.getBoardWriter()).orElseThrow(() -> new NoSuchElementException());
            BoardEntity boardEntity = BoardEntity.toSave(memberEntity,boardDTO);
            boardRepository.save(boardEntity);
        } else {
            MemberEntity memberEntity = memberRepository.findByMemberNickName(boardDTO.getBoardWriter()).orElseThrow(() -> new NoSuchElementException());
            BoardEntity boardEntity = BoardEntity.toBoardEntityWithFIle(memberEntity,boardDTO);
            BoardEntity saveEntity = boardRepository.save(boardEntity);
            for(MultipartFile boardFIle : boardDTO.getBoardFile()) {
                String originalFilename = boardFIle.getOriginalFilename();
                String storedFileName = System.currentTimeMillis() + "_" + originalFilename;
                String savePath = "\\C:\\Date\\spring_boot_img\\" + storedFileName;
                boardFIle.transferTo(new File(savePath));
                BoardFileEntity boardFileEntity = BoardFileEntity.toSaveBoardFile(saveEntity, originalFilename, storedFileName);
                boardFileRepository.save(boardFileEntity);
            }
        }
    }

    @Transactional
    public void increaseHits(Long id) {
        boardRepository.increaseHits(id);
    }

    public void delete(Long id) {
        boardRepository.deleteById(id);
    }
    public void update(BoardDTO boardDTO) {
        MemberEntity memberEntity = memberRepository.findByMemberNickName(boardDTO.getBoardWriter()).orElseThrow(() -> new NoSuchElementException());
        BoardEntity boardEntity = BoardEntity.toUpdateEntity(memberEntity, boardDTO);
        boardRepository.save(boardEntity);
    }
    public boolean likeCheck(LikeDTO likeDTO) {
        MemberEntity memberEntity = memberRepository.findById(likeDTO.getMemberId()).orElseThrow(() -> new NoSuchElementException());
        BoardEntity boardEntity = boardRepository.findById(likeDTO.getBoardId()).orElseThrow(() -> new NoSuchElementException());
        List<LikeEntity> optionalLikeEntity = likeRepository.findByMemberEntityAndBoardEntity(memberEntity, boardEntity);
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
        LikeEntity likeEntity = LikeEntity.toLikeEntity(memberEntity, boardEntity);
        return likeRepository.save(likeEntity).getId();
    }

    // 좋아요 취소 처리
    @Transactional
    public void unLike(LikeDTO likeDTO) {
        MemberEntity memberEntity = memberRepository.findById(likeDTO.getMemberId()).orElseThrow(() -> new NoSuchElementException());
        BoardEntity boardEntity = boardRepository.findById(likeDTO.getBoardId()).orElseThrow(() -> new NoSuchElementException());
        likeRepository.deleteByMemberEntityAndBoardEntity(memberEntity, boardEntity);
    }
}
