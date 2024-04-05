package com.vahner.airticketsapp.mapper;

import com.vahner.airticketsapp.dto.UserCreateDTO;
import com.vahner.airticketsapp.dto.UserUpdateDTO;
import com.vahner.airticketsapp.entity.AppUser;
import org.mapstruct.AfterMapping;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingTarget;

@Mapper(componentModel = "spring")
public interface AppUserMapper {

    AppUser userCreateDto(UserCreateDTO userCreate);

    @Mapping(target = "password", ignore = true)
    void userUpdateDto(UserUpdateDTO userUpdateDTO,@MappingTarget AppUser appUser);

    @AfterMapping
    default void updateUserPassword(UserUpdateDTO userUpdateDTO, @MappingTarget AppUser appUser) {
        if (userUpdateDTO.getPassword() != null) {
            appUser.setPassword(userUpdateDTO.getPassword());
        }
    }
}
