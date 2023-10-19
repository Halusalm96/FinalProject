package com.icia.finalproject.controller;

import com.icia.finalproject.dto.MemberDTO;
import com.icia.finalproject.service.MemberService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpSession;

@Controller
@RequiredArgsConstructor
public class MemberController {

    private final MemberService memberService;

    @GetMapping("/member/save")
    public String saveForm() {
        return "/memberPages/memberSave";
    }

    @PostMapping("/member/save")
    public String save(@ModelAttribute MemberDTO memberDTO) throws Exception {
        memberService.save(memberDTO);
        return "Home";
    }

    @PostMapping("/member/checkEmail")
    public ResponseEntity checkEmail(@RequestBody MemberDTO memberDTO) {
        boolean result = memberService.checkEmail(memberDTO.getMemberEmail());
        if (result) {
            return new ResponseEntity<>(HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.CONFLICT);
        }
    }

    @PostMapping("/member/checkMobile")
    public ResponseEntity checkMobile(@RequestBody MemberDTO memberDTO) {
        boolean result = memberService.checkMobile(memberDTO.getMemberMobile());
        if (result) {
            return new ResponseEntity<>(HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.CONFLICT);
        }
    }

    @PostMapping("/member/checkNickName")
    public ResponseEntity checkNickName(@RequestBody MemberDTO memberDTO) {
        boolean result = memberService.checkNickName(memberDTO.getMemberNickName());
        if (result) {
            return new ResponseEntity<>(HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.CONFLICT);
        }
    }

    @PostMapping("/member/login")
    public String login(@ModelAttribute MemberDTO memberDTO, HttpSession session) {
        boolean loginResult = memberService.login(memberDTO);
        if (loginResult) {
            session.setAttribute("loginEmail", memberDTO.getMemberEmail());
            session.setAttribute("loginId", memberDTO.getId());
            return "/memberPages/memberMain";
        } else {
            return "/memberPages/memberLogin";
        }
    }

    @GetMapping("/member/logout")
    public String logout(HttpSession session) {
        session.removeAttribute("loginEmail");
        return "/Home";
    }

    @GetMapping("/member/detail")
    public String  memberDetail(HttpSession session, Model model) {
        String memberEmail = (String) session.getAttribute("loginEmail");
        MemberDTO memberDTO = memberService.findByMemberEmail(memberEmail);
        model.addAttribute("member", memberDTO);
        return "/memberPages/memberDetail";
    }
    @GetMapping("/member/delete/{id}")
    public String delete(@PathVariable("id") Long id,HttpSession session) {
        memberService.delete(id);
        session.removeAttribute("loginEmail");
        return "/Home";
    }
}
