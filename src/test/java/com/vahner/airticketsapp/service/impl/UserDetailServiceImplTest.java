package com.vahner.airticketsapp.service.impl;

import com.vahner.airticketsapp.entity.AppUser;
import com.vahner.airticketsapp.repository.AppUserRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class UserDetailServiceImplTest {

    @Mock
    private AppUserRepository appUserRepository;

    @InjectMocks
    private UserDetailServiceImpl userDetailService;

    private AppUser appUser;

    @BeforeEach
    void setUp() {
        appUser = new AppUser();
        appUser.setUsername("testUser");
        appUser.setPassword("password");
    }

    @Test
    void loadUserByUsername_UserExists_ReturnsUserDetails() {
        when(appUserRepository.findByUsername(anyString())).thenReturn(Optional.of(appUser));

        UserDetails userDetails = userDetailService.loadUserByUsername("testUser");

        assertNotNull(userDetails);
        assertEquals("testUser", userDetails.getUsername());
    }

    @Test
    void loadUserByUsername_UserDoesNotExist_ThrowsUsernameNotFoundException() {
        when(appUserRepository.findByUsername(anyString())).thenReturn(Optional.empty());

        assertThrows(UsernameNotFoundException.class, () -> {
            userDetailService.loadUserByUsername("unknownUser");
        });
    }
}