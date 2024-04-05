package com.vahner.airticketsapp.service.impl;

import com.vahner.airticketsapp.entity.AppUser;
import com.vahner.airticketsapp.repository.AppUserRepository;
import com.vahner.airticketsapp.mapper.AppUserMapper;
import com.vahner.airticketsapp.dto.UserCreateDTO;
import com.vahner.airticketsapp.dto.UserUpdateDTO;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.ArgumentCaptor;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.security.crypto.password.PasswordEncoder;

import static org.hibernate.validator.internal.util.Contracts.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

@ExtendWith(MockitoExtension.class)
@DisplayName("Test class for AppUserServiceImpl")
class AppUserServiceImplTest {

    @Mock
    AppUserRepository appUserRepository;

    @Mock
    private PasswordEncoder passwordEncoder;

    @Mock
    AppUserMapper mapper;

    @InjectMocks
    AppUserServiceImpl appUserService;

    private UUID userId;

    private AppUser appUser;

    private UserCreateDTO userCreateDTO;

    @BeforeEach
    void setUp() {
        userId = UUID.randomUUID();
        userCreateDTO = new UserCreateDTO(
                "testUsername",
                "testPassword",
                "testEmail@gamail.com");
        appUser = new AppUser();
        appUser.setId(userId);
        appUser.setUsername(userCreateDTO.getUsername());
        appUser.setPassword(userCreateDTO.getPassword());
        appUser.setEmail(userCreateDTO.getEmail());
    }

    @Test
    void createUser() {
        UserCreateDTO userCreateDTO = new UserCreateDTO(
                "testUsername",
                "testPassword",
                "testEmail@gamail.com");
        AppUser appUser = new AppUser();
        appUser.setUsername(userCreateDTO.getUsername());
        appUser.setEmail(userCreateDTO.getEmail());
        appUser.setPassword(userCreateDTO.getPassword());

        when(mapper.userCreateDto(any(UserCreateDTO.class))).thenReturn(appUser);
        when(passwordEncoder.encode(anyString())).thenReturn("encodedPassword");
        when(appUserRepository.save(any(AppUser.class))).thenReturn(appUser);

        AppUser createdUser = appUserService.createUser(userCreateDTO);

        assertNotNull(createdUser);
        assertEquals("encodedPassword", createdUser.getPassword());
        verify(appUserRepository, times(1)).save(appUser);
    }

    @Test
    void updateUserSuccessfully() {
        UUID userId = UUID.fromString("0adeefab-5efd-414d-9874-d8719fdbc96c");
        UserUpdateDTO userUpdateDTO = new UserUpdateDTO();
        userUpdateDTO.setEmail("updated@example.com");
        userUpdateDTO.setPassword("newPassword");

        AppUser existingUser = new AppUser();
        existingUser.setId(userId);
        existingUser.setUsername("existingUser");
        existingUser.setEmail("existing@example.com");
        existingUser.setPassword("oldPassword");

        when(appUserRepository.findById(userId)).thenReturn(Optional.of(existingUser));
        when(passwordEncoder.encode(userUpdateDTO.getPassword())).thenReturn("encodedNewPassword");
        when(appUserRepository.save(any(AppUser.class))).thenAnswer(invocation -> invocation.getArgument(0));

        AppUser updatedUser = appUserService.updateUser(userId, userUpdateDTO);

        ArgumentCaptor<AppUser> userCaptor = ArgumentCaptor.forClass(AppUser.class);
        verify(appUserRepository).save(userCaptor.capture());
        AppUser savedUser = userCaptor.getValue();

        assertNotNull(updatedUser);
        assertEquals("encodedNewPassword", savedUser.getPassword());
        verify(passwordEncoder).encode("newPassword");
        verify(appUserRepository).save(existingUser);
    }


    @Test
    void getAllUsersSuccessfully() {
        List<AppUser> users = new ArrayList<>();
        users.add(new AppUser());
        users.add(new AppUser());

        when(appUserRepository.findAll()).thenReturn(users);

        List<AppUser> retrievedUsers = appUserService.getAllUsers();

        assertNotNull(retrievedUsers);
        assertEquals(2, retrievedUsers.size());
        verify(appUserRepository, times(1)).findAll();
    }

    @Test
    void getUserByIdSuccessfully() {
        UUID userId = UUID.fromString("0adeefab-5efd-414d-9874-d8719fdbc444");
        AppUser user = new AppUser();
        user.setId(userId);

        when(appUserRepository.findById(userId)).thenReturn(Optional.of(user));

        AppUser retrievedUser = appUserService.getUserById(userId);

        assertNotNull(retrievedUser);
        assertEquals(userId, retrievedUser.getId());
        verify(appUserRepository, times(1)).findById(userId);
    }

    @Test
    void deleteUserSuccessfully() {
        UUID userId = UUID.fromString("0adeefab-5555-414d-9874-d8719fdbc96c");
        doNothing().when(appUserRepository).deleteById(userId);

        appUserService.deleteUser(userId);

        verify(appUserRepository, times(1)).deleteById(userId);
    }

}
