package com.example.springboottraining.config;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.web.servlet.MockMvc;

import static org.junit.jupiter.api.Assertions.*;
import static org.springframework.security.test.web.servlet.request.SecurityMockMvcRequestPostProcessors.httpBasic;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@AutoConfigureMockMvc
class WebSecurityConfigTest {

    @Autowired
    private MockMvc mvc;

    @Test
    @WithMockUser
    public void valid() throws Exception {
        mvc.perform(get("/todo/{id}", 1L))
                .andExpect(status().isOk());
    }

    @Test
    public void validUser() throws Exception {
        mvc.perform(get("/todo/{id}", 1L)
                .with(httpBasic("user", "password")))
                .andExpect(status().isOk());
    }

    @Test
//    @WithMockUser(username = "user", password = "user", roles = "USER")
    public void invalidUsername() throws Exception {
        mvc.perform(get("/todo/{id}", 1L)
                .with(httpBasic("user123", "password")))
                .andExpect(status().isUnauthorized());
    }

    @Test
    public void invalidPassword() throws Exception {
        mvc.perform(get("/todo/{id}", 1L)
                        .with(httpBasic("user", "hallo")))
                .andExpect(status().isUnauthorized());
    }


}