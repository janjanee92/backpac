package com.backpac.controller;

import com.backpac.domain.Member;
import com.backpac.dto.MemberForm;
import com.backpac.service.MemberService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;

@RestController
@RequiredArgsConstructor
public class MemberController {

    private final MemberService memberService;

    @PostMapping("/sign-up")
    public Long signUp(@Valid MemberForm memberForm) {
        Member member = memberService.saveNewMember(memberForm);
        return member.getId();
    }
}
