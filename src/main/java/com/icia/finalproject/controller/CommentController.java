package com.icia.finalproject.controller;

import com.icia.finalproject.dto.CommentDTO;
import com.icia.finalproject.dto.LikeDTO;
import com.icia.finalproject.service.CommentService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

import javax.servlet.http.HttpSession;
import java.util.List;

@Controller
@RequiredArgsConstructor
public class CommentController {
    private final CommentService commentService;

    @PostMapping("/comment/save")
    public ResponseEntity commentSave(@RequestBody CommentDTO commentDTO, HttpSession session){
        Long memberId = (Long) session.getAttribute("loginId");
        try {
            commentService.save(commentDTO);
            List<CommentDTO> commentDTOList = commentService.findAll(memberId, commentDTO.getId());
            return new ResponseEntity<>(commentDTOList, HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }
    @PostMapping("/comment/like")
    public ResponseEntity like(@RequestBody LikeDTO likeDTO) {
        boolean checkResult = commentService.likeCheck(likeDTO);
        if (checkResult)
            commentService.like(likeDTO);
        List<CommentDTO> commentDTOList = commentService.findAll(likeDTO.getMemberId(), likeDTO.getBoardId());
        return new ResponseEntity<>(commentDTOList, HttpStatus.OK);
    }

    @PostMapping("/comment/unlike")
    public ResponseEntity unLike(@RequestBody LikeDTO likeDTO) {
        commentService.unLike(likeDTO);
        List<CommentDTO> commentDTOList = commentService.findAll(likeDTO.getMemberId(), likeDTO.getBoardId());
        return new ResponseEntity<>(commentDTOList, HttpStatus.OK);
    }
}
