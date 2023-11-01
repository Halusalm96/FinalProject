package com.icia.finalproject.controller;

import com.icia.finalproject.dto.CommentDTO;
import com.icia.finalproject.dto.LikeDTO;
import com.icia.finalproject.service.CommentService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

import javax.servlet.http.HttpSession;
import java.util.List;

@Controller
@RequiredArgsConstructor
public class CommentController {
    private final CommentService commentService;

    @PostMapping("/comment/save")
    public String commentSave(@ModelAttribute CommentDTO commentDTO, Model model) throws Exception {
        commentService.save(commentDTO);
        List<CommentDTO> commentDTOList = commentService.findAll(commentDTO.getBoardId());
        model.addAttribute("commentDTO", commentDTOList);
        return "redirect:/board/{commentDTO.getBoardId()}";
    }
}
