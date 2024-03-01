package com.vahner.airticketsapp.controller.rest;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.vahner.airticketsapp.dto.AccountDto;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.context.jdbc.Sql;
import org.springframework.test.web.servlet.MockMvc;

import java.math.BigDecimal;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
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
        AccountDto accountDto = new AccountDto(
                "df81a563-bbc3-11ee-b62b-744ca1623356",
                "Ruslan",
                "12323",
                "ACTIVE",
                new BigDecimal("00.00"),
                "ruslan@gmail.com",
                "Ruslan",
                "+4917123034564"
        );

        mockMvc.perform(post("/accounts/create")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(accountDto)))
                .andExpect(status().isCreated())
                .andExpect(jsonPath("$.username").value("Ruslan"))
                .andExpect(jsonPath("$.email").value("ruslan@gamail.com"));
    }

}