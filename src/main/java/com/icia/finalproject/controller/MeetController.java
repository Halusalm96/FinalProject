package com.icia.finalproject.controller;

import com.icia.finalproject.dto.BoardDTO;
import com.icia.finalproject.dto.CommentDTO;
import com.icia.finalproject.dto.MeetDTO;
import com.icia.finalproject.service.BoardService;
import com.icia.finalproject.service.CommentService;
import com.icia.finalproject.service.MeetService;
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
public class MeetController {
    private final MeetService meetService;
    @GetMapping("/meet/list")
    public String meetList(Model model) {
        List<MeetDTO> meetDTOList = meetService.findAll();
        model.addAttribute("meetList",meetDTOList);
        return "/meetPages/meetList";
    }
    @GetMapping("/meet/save")
    public String meetSave() {
        return "/meetPages/meetSave";
    }
    @PostMapping("/meet/save")
    public String save(@ModelAttribute MeetDTO meetDTO) throws Exception {
        meetService.save(meetDTO);
        return "redirect:/meet/list";
    }
    @GetMapping("/meet/{id}")
    public String findById(@PathVariable("id") Long id, Model model, HttpSession session) {
        meetService.increaseHits(id);
        try {
            MeetDTO meetDTO = meetService.findById(id);
            model.addAttribute("meetDTO", meetDTO);
            return "/meetPages/meetDetail";
        } catch (NoSuchElementException e) {
            return "/meetPages/meetNotFound";
        }
    }
    @GetMapping("/meet/update/{id}")
    public String update(@PathVariable("id") Long id, Model model) {
        MeetDTO meetDTO = meetService.findById(id);
        model.addAttribute("meet", meetDTO);
        return "/meetPages/meetUpdate";
    }
    @GetMapping("/meet/delete/{id}")
    public String delete(@PathVariable("id") Long id) {
        meetService.delete(id);
        return "redirect:/meet/list";
    }
    @PutMapping("/meet/{id}")
    public ResponseEntity update(@RequestBody MeetDTO meetDTO) {
        meetService.update(meetDTO);
        return new ResponseEntity(HttpStatus.OK);
    }
}
