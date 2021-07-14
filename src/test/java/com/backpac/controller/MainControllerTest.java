package com.backpac.controller;

import com.backpac.domain.Gender;
import com.backpac.dto.SignUpDto;
import com.google.gson.Gson;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.annotation.Rollback;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.result.MockMvcResultHandlers;
import org.springframework.transaction.annotation.Transactional;

import static org.junit.jupiter.api.Assertions.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@Transactional
@SpringBootTest
@AutoConfigureMockMvc
@Rollback(value = false)
class MainControllerTest {

    @Autowired
    MockMvc mockMvc;

    @DisplayName("회원가입")
    @Test
    void signUp () throws Exception {
        SignUpDto member = SignUpDto.builder()
                .name("jihan")
                .email("hi@hello.com")
                .password("password1234")
                .nickname("janjanee")
                .phoneNumber("01012341234")
                .gender(Gender.F)
                .build();

        Gson gson = new Gson();
        String content = gson.toJson(member);

        mockMvc.perform(post("/sign-up")
                .contentType(MediaType.APPLICATION_JSON)
                .content(content))
                .andDo(MockMvcResultHandlers.print())
                .andExpect(status().isCreated());

    }
}