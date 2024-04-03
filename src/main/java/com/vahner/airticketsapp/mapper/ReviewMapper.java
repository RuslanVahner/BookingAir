package com.vahner.airticketsapp.mapper;

import com.vahner.airticketsapp.dto.ReviewCreateDTO;
import com.vahner.airticketsapp.entity.Review;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface ReviewMapper {

    Review reviewCreateDto(ReviewCreateDTO reviewCreateDTO);
}
