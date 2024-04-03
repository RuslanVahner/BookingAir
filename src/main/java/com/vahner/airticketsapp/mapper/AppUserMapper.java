package com.vahner.airticketsapp.mapper;

import com.vahner.airticketsapp.dto.UserCreateDTO;
import com.vahner.airticketsapp.dto.UserUpdateDTO;
import com.vahner.airticketsapp.entity.AppUser;
import org.mapstruct.Mapper;
import org.mapstruct.MappingTarget;

@Mapper(componentModel = "spring")
public interface AppUserMapper {

    AppUser userCreateDto(UserCreateDTO userCreate);

    void userUpdateDto(UserUpdateDTO userUpdateDTO,@MappingTarget AppUser appUser);
}
