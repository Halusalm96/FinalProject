package com.icia.finalproject.controller;

import com.icia.finalproject.dto.BoardDTO;
import com.icia.finalproject.dto.CommentDTO;
import com.icia.finalproject.service.BoardService;
import com.icia.finalproject.service.CommentService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import javax.servlet.http.HttpSession;
import java.util.List;
import java.util.NoSuchElementException;

@Controller
@RequiredArgsConstructor
public class BoardController {

    private final BoardService boardService;
    private final CommentService commentService;
    @GetMapping("/board/list")
    public String boardList(Model model) {
        List<BoardDTO> boardDTOList = boardService.findAll();
        model.addAttribute("boardList",boardDTOList);
        return "/boardPages/boardList";
    }
    @GetMapping("/board/{id}")
    public String findById(@PathVariable("id") Long id, Model model, HttpSession session) {
        boardService.increaseHits(id);
        Long memberId = (Long) session.getAttribute("loginId");
        try {
            BoardDTO boardDTO = boardService.findById(id);
            model.addAttribute("boardDTO", boardDTO);
            List<CommentDTO> commentDTOList = commentService.findAll(memberId, id);
            if (commentDTOList.size() > 0) {
                model.addAttribute("commentDTO", commentDTOList);
            } else {
                model.addAttribute("commentDTO", null);
            }
            return "boardPages/boardDetail";
        } catch (NoSuchElementException e) {
            return "boardPages/boardNotFound";
        }
    }
    @GetMapping("/board/save")
    public String boardSave(){
        return "/boardPages/boardSave";
    }
    @PostMapping("/board/save")
    public String save(@ModelAttribute BoardDTO boardDTO) throws Exception {
        boardService.save(boardDTO);
        return "redirect:/board/list";
    }
}
