package com.vahner.airticketsapp.mapper;

import com.vahner.airticketsapp.dto.UserCreateDTO;
import com.vahner.airticketsapp.dto.UserUpdateDTO;
import com.vahner.airticketsapp.entity.AppUser;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.mapstruct.AfterMapping;
import org.mapstruct.MappingTarget;
import org.mapstruct.factory.Mappers;

import static org.junit.jupiter.api.Assertions.*;

@DisplayName("Test class for AppUserMapper")
class AppUserMapperTest {

    private AppUserMapper mapper;

    @BeforeEach
    public void setUp() {
        mapper = Mappers.getMapper(AppUserMapper.class);
    }


    @Test
    void userCreateDtoToAppUser() {
        UserCreateDTO dto = new UserCreateDTO();
        dto.setUsername("testUser");
        dto.setPassword("testPassword");
        dto.setEmail("test@example.com");

        AppUser user = mapper.userCreateDto(dto);

        assertEquals(dto.getUsername(), user.getUsername());
        assertEquals(dto.getPassword(), user.getPassword());
        assertEquals(dto.getEmail(), user.getEmail());
    }

    @Test
    void userUpdateDtoToAppUser() {

        AppUser user = new AppUser();
        user.setUsername("user");
        user.setPassword("password");
        user.setEmail("user@gmail.com");

        UserUpdateDTO dto = new UserUpdateDTO();
        dto.setEmail("updatedUserd@icloud.com");
        dto.setPassword("newPassword");

        mapper.userUpdateDto(dto, user);

        assertEquals("newPassword", user.getPassword());
        assertEquals(dto.getEmail(), user.getEmail());
    }
}