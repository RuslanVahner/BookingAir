package com.vahner.airticketsapp.controller.page;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.vahner.airticketsapp.dto.CartDto;
import com.vahner.airticketsapp.entity.Cart;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.jdbc.Sql;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;


import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@Sql("/createTestDB.sql")
@Sql("/addTestData.sql")
@SpringBootTest
@AutoConfigureMockMvc
class CartControllerTest {

    @Autowired
    private ObjectMapper objectMapper;

    @Autowired
    private MockMvc mockMvc;



}