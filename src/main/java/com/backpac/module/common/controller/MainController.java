package com.backpac.module.common.controller;

import com.backpac.module.member.dto.SignUpDto;
import com.backpac.module.member.service.MemberService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;

@RestController
@RequiredArgsConstructor
public class MainController {

    private final MemberService memberService;

    /**
     * 회원가입
     */
    @PostMapping("/sign-up")
    public ResponseEntity<Long> signUp(@Valid @RequestBody SignUpDto signUpDto) {
        Long id = memberService.saveNewMember(signUpDto);
        return new ResponseEntity<>(id, HttpStatus.CREATED);
    }
}
