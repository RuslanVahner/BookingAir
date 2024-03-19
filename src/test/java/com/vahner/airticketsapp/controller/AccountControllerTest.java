package com.vahner.airticketsapp.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.vahner.airticketsapp.dto.AccountDto;
import com.vahner.airticketsapp.entity.enums.AccountStatus;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.jdbc.Sql;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

import java.math.BigDecimal;

import static org.springframework.security.test.web.servlet.setup.SecurityMockMvcConfigurers.springSecurity;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@AutoConfigureMockMvc
@Sql("/createTestDB.sql")
@Sql("/addTestData.sql")
class AccountControllerTest {

//    @MockBean
//    private JwtFilter jwtFilter;
    @Autowired
    ObjectMapper objectMapper;
    @Autowired
    MockMvc mockMvc;
    @Autowired
    WebApplicationContext context;

    @BeforeEach
    void setup() {
        this.mockMvc =
                MockMvcBuilders.webAppContextSetup(context)
                        .apply(springSecurity())
                        .build();
    }

    @Test
    void createAccount() throws Exception {
        AccountDto dto = new AccountDto();
        dto.setLogin("login");
        dto.setPassword("12343");
        dto.setOwner("Ruslan Ruslan");
        dto.setBalance(BigDecimal.ZERO);
        dto.setStatus(AccountStatus.ACTIVE);

        String accountDtoJson = objectMapper.writeValueAsString(dto);

        MvcResult createAccountResult = mockMvc
                .perform(post("/api/account/createAccount")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(accountDtoJson))
                .andExpect(status().isCreated())
                .andReturn();

        String accountResultJson = createAccountResult.getResponse().getContentAsString();
        AccountDto accountResult = objectMapper.readValue(accountResultJson, AccountDto.class);


        Assertions.assertEquals(201, createAccountResult.getResponse().getStatus());
        Assertions.assertEquals(dto.getLogin(), accountResult.getLogin());
        Assertions.assertEquals(dto.getPassword(), accountResult.getPassword());
        Assertions.assertEquals(dto.getBalance(), accountResult.getBalance());
        Assertions.assertEquals(dto.getStatus(), accountResult.getStatus());
        Assertions.assertEquals(dto.getOwner(), accountResult.getOwner());

    }
}