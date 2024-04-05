package com.vahner.airticketsapp.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.vahner.airticketsapp.dto.UserCreateDTO;
import com.vahner.airticketsapp.dto.UserUpdateDTO;
import com.vahner.airticketsapp.entity.AppUser;
import com.vahner.airticketsapp.entity.enums.Role;
import com.vahner.airticketsapp.service.interf.AppUserService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

import static org.hamcrest.Matchers.is;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;
import static org.springframework.security.test.web.servlet.request.SecurityMockMvcRequestPostProcessors.csrf;
import static org.springframework.security.test.web.servlet.request.SecurityMockMvcRequestPostProcessors.user;
import static org.springframework.security.test.web.servlet.setup.SecurityMockMvcConfigurers.springSecurity;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.put;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@Disabled("Test class for AppUserController")
@EntityScan("com.vahner.airticketsapp.entity")
@WebMvcTest(AppUserController.class)
@AutoConfigureMockMvc()
class AppUserControllerTest {

    @MockBean
    private AppUserService appUserService;

    @MockBean
    private UserDetailsService userDetailsService;

    @Autowired
    ObjectMapper objectMapper;

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private WebApplicationContext context;

    @BeforeEach
    void setup() {
        this.mockMvc =
                MockMvcBuilders.webAppContextSetup(context)
                        .apply(springSecurity())
                        .build();
    }

    @Test
    void getUserById() throws Exception {
        UUID userId = UUID.fromString("0adeefab-5efd-414d-9874-d8719fdbc96c");
        AppUser user = new AppUser();
        user.setId(userId);
        user.setUsername("testUser");
        user.setPassword("password");
        user.setEmail("test@example.com");
        user.setRole(Role.USER);

        when(appUserService.getUserById(userId)).thenReturn(user);

        mockMvc.perform(get("/api/users/" + userId)
                        .with(user("user").password("password").roles("USER")))
                .andExpect(status().isOk())
                .andDo(print())
                .andExpect(jsonPath("$.id").value(userId.toString()))
                .andExpect(jsonPath("$.username").value(user.getUsername()))
                .andExpect(jsonPath("$.email").value(user.getEmail()))
                .andExpect(jsonPath("$.role").value(user.getRole().toString()));
    }

    @Test
    void getAllUsers() throws Exception {
        List<AppUser> userList = new ArrayList<>();
        userList.add(new AppUser(UUID.fromString(
                "0adeefab-5efd-414d-9874-d8719fdbc96c"),
                "userTest1",
                "password",
                "user1@gamil.com",
                Role.USER));
        userList.add(new AppUser(UUID.fromString(
                "1bdeffab-5efd-424d-9874-d8719fdbc97d"),
                "userTest2",
                "password",
                "user2@gamail.com",
                Role.USER));

        when(appUserService.getAllUsers()).thenReturn(userList);

        mockMvc.perform(get("/api/users")
                        .with(user("admin").roles("ADMIN")))
                .andExpect(status().isOk());
    }

    @Test
    void updateUserTest() throws Exception {
        UUID userId = UUID.fromString("0adeefab-5efd-414d-9874-d8719fdbc96c");
        UserUpdateDTO userUpdateDTO = new UserUpdateDTO();
        userUpdateDTO.setPassword("updatedPassword");
        userUpdateDTO.setEmail("updated@example.com");

        String userUpdateDTOJson = objectMapper.writeValueAsString(userUpdateDTO);

        mockMvc.perform(put("/api/users/update/{id}", userId.toString())
                        .with(csrf())
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(userUpdateDTOJson)
                        .with(user("user").password("password").roles("USER")))
                .andExpect(status().isOk());
    }

    @Test
    public void registerUser_SuccessfulRegistration() throws Exception {
        UUID userId = UUID.fromString("0adeefab-5efd-414d-9874-d8719fdbc96c");
        UserCreateDTO newUser = new UserCreateDTO();
        newUser.setUsername("newUserTest");
        newUser.setPassword("newPasswordTest");
        newUser.setEmail("newUserTest@gmail.com");

        AppUser appUser = new AppUser(
                userId,
                newUser.getUsername(),
                newUser.getPassword(),
                newUser.getEmail(),
                Role.USER);

        when(appUserService.createUser(any(UserCreateDTO.class))).thenReturn(appUser);

        String newUserJson = objectMapper.writeValueAsString(newUser);

        mockMvc.perform(post("/api/users/register")
                        .with(csrf())
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(newUserJson)
                        .with(user("newUser").password("newPassword").roles("USER")))
                .andExpect(status().isCreated())
                .andExpect(jsonPath("$.username", is(appUser.getUsername())))
                .andExpect(jsonPath("$.email", is(appUser.getEmail())))
                .andExpect(jsonPath("$.id", is(userId.toString())));
    }

//    @Test
//    public void loginUser_SuccessfulAuthentication() throws Exception {
//        UserCreateDTO userLogin = new UserCreateDTO();
//        userLogin.setUsername("user1");
//        userLogin.setPassword("password1");
//
//        String userLoginJson = objectMapper.writeValueAsString(userLogin);
//
//        mockMvc.perform(post("/api/users/login")
//                        .contentType(MediaType.APPLICATION_JSON)
//                        .content(userLoginJson))
//                .andExpect(status().isOk());
//    }
//
//    @Test
//    public void loginUser_UnsuccessfulAuthentication() throws Exception {
//        UserCreateDTO userLogin = new UserCreateDTO();
//
//        String userLoginJson = objectMapper.writeValueAsString(userLogin);
//
//        mockMvc.perform(post("/api/login")
//                        .contentType(MediaType.APPLICATION_JSON)
//                        .content(userLoginJson))
//                .andExpect(status().isUnauthorized());
//    }
}