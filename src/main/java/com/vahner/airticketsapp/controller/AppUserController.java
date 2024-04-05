package com.vahner.airticketsapp.controller;

import com.vahner.airticketsapp.dto.UserCreateDTO;
import com.vahner.airticketsapp.dto.UserUpdateDTO;
import com.vahner.airticketsapp.entity.AppUser;
import com.vahner.airticketsapp.service.interf.AppUserService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@Tag(name = "AppUser Controller")
@Validated
@RestController
@RequestMapping("/api/users")
@RequiredArgsConstructor
public class AppUserController {

    private final AppUserService appUserService;

    @PostMapping("/register")
    @Operation(summary = "register new user",
            requestBody = @io.swagger.v3.oas.annotations.parameters.RequestBody(
                    description = "registers a new user with username, password, and email",
                    required = true,
                    content = @Content(
                            mediaType = "application/json",
                            schema = @Schema(implementation = UserCreateDTO.class))
            )
    )
    public ResponseEntity<AppUser> registerUser(@Valid @RequestBody UserCreateDTO userCreateDTO) {
        AppUser appUser = appUserService.createUser(userCreateDTO);
        return new ResponseEntity<>(appUser, HttpStatus.CREATED);
    }

    @PostMapping("/login")
    @Operation(summary = "Login existing user", description = "Login user by providing username and password")
    public ResponseEntity<?> loginUser(@RequestBody UserCreateDTO userLogin) {
        boolean isAuthenticated = appUserService.authenticateUser(userLogin.getUsername(), userLogin.getPassword());

        if (isAuthenticated) {
            return ResponseEntity.ok("User authenticated successfully");
        } else {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("Authentication failed");
        }
    }

    @PutMapping("/update/{id}")
    @Operation(summary = "update user",
            description = "updates the information of an existing user",
            responses = {
                    @ApiResponse(responseCode = "200", description = "successfully updated the user")
            })
    public ResponseEntity<AppUser> updateUser(@PathVariable UUID id, @Valid @RequestBody UserUpdateDTO userUpdateDTO) {
        AppUser updatedUser = appUserService.updateUser(id, userUpdateDTO);
        return ResponseEntity.ok(updatedUser);
    }

    @GetMapping
    @Operation(summary = "get all users",
            description = "retrieve a list of all users",
            responses = {
                    @ApiResponse(responseCode = "200", description = "successfully returned list of users")
            })
    public ResponseEntity<List<AppUser>> getAllUsers() {
        List<AppUser> users = appUserService.getAllUsers();
        return ResponseEntity.ok(users);
    }

    @GetMapping("/{id}")
    @Operation(summary = "get a user by id",
            description = "returns a user from the database for the given id",
            responses = {
                    @ApiResponse(responseCode = "200", description = "User found"),
                    @ApiResponse(responseCode = "404", description = "No user was found with this id")
            })
    public ResponseEntity<AppUser> getUserById(@PathVariable UUID id) {
        AppUser appUser = appUserService.getUserById(id);
        return ResponseEntity.ok(appUser);
    }

    @DeleteMapping("/delete/{id}")
    @Operation(summary = "delete user",
            description = "deletion of an user by the specified id")
    @ApiResponse(responseCode = "204", description = "Successfully deleted the user")
    public ResponseEntity<Void> deleteUser(@PathVariable UUID id) {
        appUserService.deleteUser(id);
        return ResponseEntity.noContent().build();
    }
}