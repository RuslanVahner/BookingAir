package com.vahner.airticketsapp.service.interf;

import com.vahner.airticketsapp.dto.UserCreateDTO;
import com.vahner.airticketsapp.dto.UserUpdateDTO;
import com.vahner.airticketsapp.entity.AppUser;

import java.util.List;
import java.util.UUID;

public interface AppUserService {
    AppUser createUser(UserCreateDTO userCreate);

    AppUser updateUser(UUID id, UserUpdateDTO userUpdate);

    List<AppUser> getAllUsers();

    AppUser getUserById(UUID id);

    void deleteUser(UUID id);

    boolean authenticateUser(String username, String password);
}
