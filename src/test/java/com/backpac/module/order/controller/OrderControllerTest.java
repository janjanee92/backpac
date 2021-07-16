package com.backpac.module.order.controller;

import com.backpac.infra.jwt.TokenProvider;
import com.backpac.infra.jwt.config.JwtConfig;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.result.MockMvcResultHandlers;
import org.springframework.transaction.annotation.Transactional;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@Transactional
@SpringBootTest
@AutoConfigureMockMvc
class OrderControllerTest {

    @Autowired MockMvc mockMvc;
    @Autowired JwtConfig jwtConfig;
    @Autowired TokenProvider tokenProvider;

    public String testToken = "";

    @BeforeEach
    void beforeEach() {
        testToken = jwtConfig.getTokenPrefix() + tokenProvider.createToken("test");
    }

    @DisplayName("주문 조회 - 단일 회원")
    @Test
    void findOrder () throws Exception {
        mockMvc.perform(get("/api/orders/member/1")
                .header(jwtConfig.getAuthorizationHeader(), testToken))
                .andDo(MockMvcResultHandlers.print())
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.[0].productName").value("목걸이"))
                .andExpect(jsonPath("$.[1].productName").value("반지"));
    }
}