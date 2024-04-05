package com.vahner.airticketsapp.mapper;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.mapstruct.factory.Mappers;

import java.util.UUID;

import static org.junit.jupiter.api.Assertions.*;
@DisplayName("Test for UUIDMapper")
class UUIDMapperTest {

    private UUIDMapper uuidMapper;

    @BeforeEach
    public void setup(){
        uuidMapper = Mappers.getMapper(UUIDMapper.class);
    }

    @Test
    void testToString() {
        UUID uuid = UUID.randomUUID();

        String result = uuidMapper.toString(uuid);

        assertEquals(uuid.toString(), result);
    }
}