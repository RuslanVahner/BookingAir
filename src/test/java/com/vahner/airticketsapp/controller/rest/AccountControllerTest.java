package com.vahner.airticketsapp.controller.rest;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.vahner.airticketsapp.dto.AccountDto;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.jdbc.Sql;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import java.math.BigDecimal;

import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@AutoConfigureMockMvc
@Sql("/createTestDB.sql")
@Sql("/addTestData.sql")
class AccountControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private ObjectMapper objectMapper;

    @Test
    void createAccountTest() throws Exception {
        AccountDto dto = new AccountDto(
                "64f3fec2-da22-11ee-873a-00155dc38234",
                "TestLogin",
                "TestPassword",
                "ACTIVE",
                new BigDecimal("00.00"),
                "test@gmail.com",
                "Ruslan Vahner",
                "+491719203434"
        );

        mockMvc.perform(MockMvcRequestBuilders.post("/api/account/createAccount")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(dto)))
                .andExpect(status().isCreated())
                .andExpect(jsonPath("$.login").value(dto.getLogin()))
                .andExpect(jsonPath("$.password").value(dto.getPassword()))
                .andExpect(jsonPath("$.phone").value(dto.getPhone()));


    }

}