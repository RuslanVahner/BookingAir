package com.vahner.airticketsapp.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.vahner.airticketsapp.dto.UserCreateDTO;
import com.vahner.airticketsapp.entity.AppUser;
import com.vahner.airticketsapp.entity.enums.Role;
import com.vahner.airticketsapp.service.interf.AppUserService;
import org.hibernate.annotations.processing.SQL;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.data.jpa.JpaRepositoriesAutoConfiguration;
import org.springframework.boot.autoconfigure.orm.jpa.HibernateJpaAutoConfiguration;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.test.context.jdbc.Sql;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@AutoConfigureMockMvc
@WebMvcTest(controllers = AppUserController.class, excludeAutoConfiguration = {
        JpaRepositoriesAutoConfiguration.class,
        HibernateJpaAutoConfiguration.class
})
@Disabled("Test class for AppUserController")
@Sql("/createTestDB.sql")
@Sql("/addTestData.sql")
class AppUserControllerTest {

    @MockBean
    private AppUserService appUserService;

    @Autowired
    ObjectMapper objectMapper;

    @Autowired
    private MockMvc mockMvc;

    @Test
    void createUser() throws Exception {
        UserCreateDTO dto = new UserCreateDTO(
                "ruslan",
                "password",
                "ruslan@gmail.com");

        String userDtoJson = objectMapper.writeValueAsString(dto);

        AppUser createdUser = new AppUser();
        createdUser.setUsername(dto.getUsername());
        createdUser.setPassword(dto.getPassword());
        createdUser.setEmail(dto.getEmail());
        createdUser.setRole(Role.USER);

        when(appUserService.createUser(any(UserCreateDTO.class))).thenReturn(createdUser);

        MvcResult createUserResult = mockMvc
                .perform(post("/api/users/register")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(userDtoJson))
                .andExpect(status().isOk())
                .andReturn();

        String userResultJson = createUserResult.getResponse().getContentAsString();
        UserCreateDTO userResult = objectMapper.readValue(userResultJson, UserCreateDTO.class);

        Assertions.assertEquals(HttpStatus.OK.value(), createUserResult.getResponse().getStatus());
        Assertions.assertEquals(dto.getUsername(), userResult.getUsername());
        Assertions.assertEquals(dto.getEmail(), userResult.getEmail());

    }

    @Test
    void loginUser() {
    }

    @Test
    void updateUser() {
    }

    @Test
    void getAllUsers() {
    }

    @Test
    void getUserById() {
    }

    @Test
    void deleteUser() {
    }
}