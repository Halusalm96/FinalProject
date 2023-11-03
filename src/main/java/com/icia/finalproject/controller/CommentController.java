package com.icia.finalproject.controller;

import com.icia.finalproject.dto.CommentDTO;
import com.icia.finalproject.dto.LikeDTO;
import com.icia.finalproject.service.CommentService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpSession;
import java.util.List;

@Controller
@RequiredArgsConstructor
public class CommentController {
    private final CommentService commentService;

    @PostMapping("/comment/save")
    public ResponseEntity commentSave(@ModelAttribute CommentDTO commentDTO, HttpSession session) {
        Long memberId = (Long) session.getAttribute("loginId");
        try {
            commentService.save(commentDTO);
            List<CommentDTO> commentDTOList = commentService.findAll(commentDTO.getBoardId());
            return new ResponseEntity<>(commentDTOList, HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }
    @GetMapping("/comment/delete/{id}")
    public String commentDelete (@PathVariable("id") Long id) {
        commentService.delete(id);
        return "redirect:/board/list";
    }
}
