package com.example.springtest_week1.controller;

import com.example.springtest_week1.dto.MemberInfoResponseDto;
import com.example.springtest_week1.service.MemberService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RequiredArgsConstructor
@RestController
public class MemberController {
    private final MemberService memberService;


    @GetMapping("/member/{id}")
    public MemberInfoResponseDto getMemberInfo(@PathVariable Long id) {
        return memberService.findMember(id);
    }

    @GetMapping("/member")
    public List<MemberInfoResponseDto> getMemberList() {
        List<MemberInfoResponseDto> memberInfoResponseDtoList = memberService.findAllMember();
        return memberInfoResponseDtoList;
    }
}
