package com.icia.finalproject.controller;

import com.icia.finalproject.dto.MemberDTO;
import com.icia.finalproject.service.FollowService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;


@Controller
@RequiredArgsConstructor
public class FollowController {

    private final FollowService followService;

    @PostMapping("/api/subscribe/{toUserId}")
    public ResponseEntity<String> followSave(@PathVariable Long toUserId, @ModelAttribute MemberDTO memberDTO) throws Exception{
        followService.save(toUserId, memberDTO);
        return ResponseEntity.ok().body("팔로워 성공");


    }

    @DeleteMapping("/api/subscribe/{toUserId}")
    public ResponseEntity<String> unSubscribe(@PathVariable Long toUserId, @ModelAttribute MemberDTO memberDTO){
        followService.delete(toUserId, memberDTO.getId());
        return ResponseEntity.ok().body("팔로워 해제");


    }

}