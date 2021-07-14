package com.backpac.controller;

import com.backpac.domain.Gender;
import com.backpac.dto.SignUpDto;
import com.backpac.jwt.TokenProvider;
import com.backpac.jwt.config.JwtConfig;
import com.backpac.repository.MemberRepository;
import com.backpac.service.MemberService;
import com.google.gson.Gson;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.result.MockMvcResultHandlers;
import org.springframework.transaction.annotation.Transactional;

import static org.junit.jupiter.api.Assertions.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@Transactional
@SpringBootTest
@AutoConfigureMockMvc
class MemberControllerTest {

    @Autowired MockMvc mockMvc;
    @Autowired JwtConfig jwtConfig;
    @Autowired TokenProvider tokenProvider;
    @Autowired MemberService memberService;
    @Autowired MemberRepository memberRepository;

    @BeforeEach
    void beforeEach() {
        SignUpDto signUpDto = SignUpDto.builder()
                .name("jihan")
                .email("hello@hi.com")
                .gender(Gender.F)
                .password("password1234")
                .nickname("janjanee")
                .phoneNumber("01034345656")
                .build();
        memberService.saveNewMember(signUpDto);
    }

    @AfterEach
    void afterEach() {
        memberRepository.deleteAll();
    }

    @DisplayName("단일 회원 상세 정보 조회")
    @Test
    void findMember () throws Exception {
        mockMvc.perform(get("/api/members")
                .param("id", "janjanee")
                .header(jwtConfig.getAuthorizationHeader(),
                        jwtConfig.getTokenPrefix() + tokenProvider.createToken("hello@hi.com")))
                .andDo(MockMvcResultHandlers.print())
                .andExpect(status().isOk())
                .andExpect(jsonPath("name").value("jihan"))
                .andExpect(jsonPath("email").value("hello@hi.com"));
    }
}