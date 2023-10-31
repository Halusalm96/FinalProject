package com.icia.finalproject.controller;

import com.icia.finalproject.dto.MeetDTO;
import com.icia.finalproject.service.BoardService;
import com.icia.finalproject.service.CommentService;
import com.icia.finalproject.service.MeetService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;

import java.util.List;

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
}
