package com.backpac.module.member.controller;

import com.backpac.infra.jwt.TokenProvider;
import com.backpac.infra.jwt.config.JwtConfig;
import com.backpac.module.member.repository.MemberRepository;
import com.backpac.module.member.service.MemberService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.result.MockMvcResultHandlers;
import org.springframework.transaction.annotation.Transactional;

import static org.hamcrest.Matchers.hasSize;
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

    public String testToken = "";

    @BeforeEach
    void beforeEach() {
        testToken = jwtConfig.getTokenPrefix() + tokenProvider.createToken("test");
    }

    @DisplayName("단일 회원 상세 정보 조회")
    @Test
    void findMember () throws Exception {
        mockMvc.perform(get("/api/members/3")
                .header(jwtConfig.getAuthorizationHeader(), testToken))
                .andDo(MockMvcResultHandlers.print())
                .andExpect(status().isOk())
                .andExpect(jsonPath("name").value("이짱구"))
                .andExpect(jsonPath("email").value("zzanggu@gmail.com"));
    }

    @DisplayName("여러 회원 목록 조회 - size(5개)")
    @Test
    void findAllMember_withSize () throws Exception {
        mockMvc.perform(get("/api/members?size=5")
                .header(jwtConfig.getAuthorizationHeader(), testToken))
                .andDo(MockMvcResultHandlers.print())
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.content", hasSize(5)));
    }

    @DisplayName("여러 회원 목록 조회 - 검색조건(이름)")
    @Test
    void findAllMember_withNameCond () throws Exception {
        mockMvc.perform(get("/api/members?name=이춘식")
                .header(jwtConfig.getAuthorizationHeader(), testToken))
                .andDo(MockMvcResultHandlers.print())
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.content.[0].productName").value("지갑"));
    }

    @DisplayName("여러 회원 목록 조회 - 검색조건(이메일)")
    @Test
    void findAllMember_withEmailCond () throws Exception {
        mockMvc.perform(get("/api/members?email=chun2@gmail.com")
                .header(jwtConfig.getAuthorizationHeader(), testToken))
                .andDo(MockMvcResultHandlers.print())
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.content.[0].productName").value("지갑"));
    }

    @DisplayName("여러 회원 목록 조회 - 검색조건(이름, 이메일)")
    @Test
    void findAllMember_withNameAndEmailCond () throws Exception {
        mockMvc.perform(get("/api/members?name=이짱구&email=zzanggu@gmail.com")
                .header(jwtConfig.getAuthorizationHeader(), testToken))
                .andDo(MockMvcResultHandlers.print())
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.content.[0].productName").value("스티커"));
    }
}
