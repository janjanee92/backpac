package com.backpac;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.Rollback;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.transaction.annotation.Transactional;

import static org.springframework.security.test.web.servlet.request.SecurityMockMvcRequestPostProcessors.csrf;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@Transactional
@SpringBootTest
@AutoConfigureMockMvc
@Rollback(value = false)
class BackpacHwApplicationTests {

    @Autowired
    MockMvc mockMvc;

    @DisplayName("회원 가입 - 입력값 정상")
    @Test
    void signUpSubmit_correct_input() throws Exception {
        mockMvc.perform(post("/sign-up")
                .param("name", "jihan")
                .param("email", "janjanee92@gmail.com")
                .param("password", "1234567dvxcsd89")
                .param("nickname", "janjanee")
                .param("phoneNumber", "01012343452")
                .param("gender", "F")
                .with(csrf()))
                .andExpect(status().isOk());
    }

}
