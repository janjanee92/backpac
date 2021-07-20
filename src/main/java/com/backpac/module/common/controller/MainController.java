package com.backpac.module.common.controller;

import com.backpac.module.common.dto.SignInDto;
import com.backpac.module.common.dto.SignUpDto;
import com.backpac.module.member.service.MemberService;
import io.swagger.annotations.*;
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

    @PostMapping("/sign-in")
    @ApiOperation(value = "로그인", notes =
            "회원가입 후 사용자 정보(이메일 or 닉네임 / 비밀번호)로 로그인을 합니다.<br/>" +
            "로그인이 성공하면 응답헤더에 인증된 JWT가 반환됩니다. (ex - authorization: Bearer eyJhbGci...)<br/>" +
            "토큰을 복사하여 Authorize에 입력합니다."
    )
    public ResponseEntity<String> signIn(@RequestBody SignInDto signInDto) {
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @PostMapping("/sign-up")
    @ApiOperation(value = "회원가입", notes = "사용자 정보를 입력하여 회원가입을 합니다.")
    public ResponseEntity<Long> signUp(@Valid @RequestBody SignUpDto signUpDto) {
        Long id = memberService.saveNewMember(signUpDto);
        return new ResponseEntity<>(id, HttpStatus.CREATED);
    }

}
