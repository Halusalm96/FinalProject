package com.icia.finalproject.controller;

import com.icia.finalproject.dto.MemberDTO;
import com.icia.finalproject.service.MemberService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

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
            return "memberPages/memberMain";
        } else {
            return "/Home";
        }
    }
}
