package com.icia.finalproject.controller;

import com.icia.finalproject.dto.BoardDTO;
import com.icia.finalproject.dto.CommentDTO;
import com.icia.finalproject.dto.LikeDTO;
import com.icia.finalproject.service.BoardService;
import com.icia.finalproject.service.CommentService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpSession;
import java.util.List;
import java.util.NoSuchElementException;

@Controller
@RequiredArgsConstructor
public class BoardController {

    private final BoardService boardService;
    private final CommentService commentService;

    @GetMapping("/board/list")
    public String boardList(Model model, HttpSession session) {
        Long memberId = (Long) session.getAttribute("loginId");
        List<BoardDTO> boardDTOList = boardService.findAll(memberId);
        model.addAttribute("boardList", boardDTOList);
        return "/boardPages/boardList";
    }

    @GetMapping("/board/{id}")
    public String findById(@PathVariable("id") Long id, Model model, HttpSession session) {
        boardService.increaseHits(id);
        Long memberId = (Long) session.getAttribute("loginId");
        try {
            BoardDTO boardDTO = boardService.findById(id,memberId);
            model.addAttribute("boardDTO", boardDTO);
            List<CommentDTO> commentDTOList = commentService.findAll(id);
            if (commentDTOList.size() > 0) {
                model.addAttribute("commentDTO", commentDTOList);
            } else {
                model.addAttribute("commentDTO", null);
            }
            return "/boardPages/boardDetail";
        } catch (NoSuchElementException e) {
            return "/boardPages/boardNotFound";
        }
    }

    @GetMapping("/board/save")
    public String boardSave() {
        return "/boardPages/boardSave";
    }

    @PostMapping("/board/save")
    public String save(@ModelAttribute BoardDTO boardDTO) throws Exception {
        boardService.save(boardDTO);
        return "redirect:/board/list";
    }

    @GetMapping("/board/update/{id}")
    public String update(@PathVariable("id") Long id, Model model,HttpSession session) {
        Long memberId = (Long) session.getAttribute("loginId");
        BoardDTO boardDTO = boardService.findById(id,memberId);
        model.addAttribute("board", boardDTO);
        return "/boardPages/boardUpdate";
    }

    @GetMapping("/board/delete/{id}")
    public String delete(@PathVariable("id") Long id) {
        boardService.delete(id);
        return "redirect:/board/list";
    }

    @PutMapping("/board/{id}")
    public ResponseEntity update(@RequestBody BoardDTO boardDTO) {
        boardService.update(boardDTO);
        return new ResponseEntity(HttpStatus.OK);
    }

    @PostMapping("/board/like")
    public ResponseEntity like(@RequestBody LikeDTO likeDTO) {
        boolean checkResult = boardService.likeCheck(likeDTO);
        if (checkResult)
            boardService.like(likeDTO);
        List<BoardDTO> boardDTOList = boardService.findAll(likeDTO.getMemberId());
        return new ResponseEntity<>(boardDTOList, HttpStatus.OK);
    }

    @PostMapping("/board/unlike")
    public ResponseEntity unLike(@RequestBody LikeDTO likeDTO) {
        boardService.unLike(likeDTO);
        List<BoardDTO> boardDTOList = boardService.findAll(likeDTO.getMemberId());
        return new ResponseEntity<>(boardDTOList, HttpStatus.OK);
    }
}
