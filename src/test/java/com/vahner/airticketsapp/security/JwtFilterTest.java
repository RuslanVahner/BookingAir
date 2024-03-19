package com.vahner.airticketsapp.security;

import com.vahner.airticketsapp.entity.Account;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.web.servlet.MockMvc;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@AutoConfigureMockMvc
class JwtFilterTest {
    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private JwtProvider jwtProvider;

    private final String VALID_USER_ROLE = "USER";

    @Test
    void whenValidJwtToken_thenAccessAllowed() throws Exception {

        Account mockAccount = new Account();
        String VALID_USER_LOGIN = "user";
        mockAccount.setLogin(VALID_USER_LOGIN);

        String validJwtToken = jwtProvider.generateAccessToken(mockAccount);

        mockMvc.perform(get("/")
                        .header("Authorization", "Bearer " + validJwtToken))
                .andExpect(status().isOk());

    }

    @Test
    void whenInvalidJwtToken_thenAccessDenied() throws Exception {
       String invalidJwtToken = "invalidToken";

        mockMvc.perform(get("/")
                        .header("Authorization", "Bearer " + invalidJwtToken))
                .andExpect(status().isUnauthorized());

    }
}