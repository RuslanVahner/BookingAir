package com.vahner.airticketsapp.service.impl;


import com.vahner.airticketsapp.dto.UserCreateDTO;
import com.vahner.airticketsapp.dto.UserUpdateDTO;
import com.vahner.airticketsapp.entity.AppUser;
import com.vahner.airticketsapp.entity.enums.Role;
import com.vahner.airticketsapp.mapper.AppUserMapper;
import com.vahner.airticketsapp.repository.AppUserRepository;
import com.vahner.airticketsapp.service.interf.AppUserService;
import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Slf4j
@Service
@RequiredArgsConstructor
public class AppUserServiceImpl implements AppUserService {

    private final AppUserMapper appUserMapper;
    private final PasswordEncoder passwordEncoder;
    private final AppUserRepository appUserRepository;

    @Override
    public AppUser createUser(UserCreateDTO userCreate) {
        log.info("Creating new user with username: {}", userCreate.getUsername());
        AppUser appUser = appUserMapper.userCreateDto(userCreate);
        appUser.setPassword(passwordEncoder.encode(userCreate.getPassword()));
        appUser.setRole(Role.USER);
        return appUserRepository.save(appUser);
    }

    @Override
    public AppUser updateUser(UUID id, UserUpdateDTO userUpdateDTO) {
        log.info("Updating user with ID: {}", id);
        AppUser user = appUserRepository.findById(id)
                .orElseThrow(() -> new UsernameNotFoundException("User not found."));
        appUserMapper.userUpdateDto(userUpdateDTO, user);
        if (userUpdateDTO.getPassword() != null && !userUpdateDTO.getPassword().isEmpty()) {
            user.setPassword(passwordEncoder.encode(userUpdateDTO.getPassword()));
        }
        return appUserRepository.save(user);
    }

    @Override
    public boolean authenticateUser(String username, String password) {
        log.info("Attempting to authenticate user: {}", username);
        Optional<AppUser> userOptional = appUserRepository.findByUsername(username);
        if (userOptional.isPresent()) {
            AppUser user = userOptional.get();
            return passwordEncoder.matches(password, user.getPassword());
        }
        return false;
    }

    @Override
    public List<AppUser> getAllUsers() {
        log.info("Retrieving all users");
        return appUserRepository.findAll();
    }

    @Override
    public AppUser getUserById(UUID id) {
        log.info("Retrieving user with ID: {}", id);
        return appUserRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("User not found"));
    }

    @Override
    public void deleteUser(UUID id) {
        log.info("Deleting user with ID: {}", id);
        appUserRepository.deleteById(id);
    }

}