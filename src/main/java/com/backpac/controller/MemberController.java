package com.backpac.controller;

import com.backpac.dto.MemberRequest;
import com.backpac.dto.MemberResponse;
import com.backpac.service.MemberService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RestController
@RequiredArgsConstructor
@RequestMapping("api/members")
public class MemberController {

    private final MemberService memberService;

    @PostMapping("/sign-up")
    public Long signUp( @Valid @RequestBody MemberRequest memberRequest) {
        return memberService.saveNewMember(memberRequest);
    }

    @PostMapping("/login")
    public MemberResponse login(@RequestBody MemberRequest memberRequest) {
        return memberService.login(memberRequest);
    }

    @GetMapping(path = "{id}")
    public String getMember(@PathVariable("id") Long id) {
        return "hello";
    }

}
