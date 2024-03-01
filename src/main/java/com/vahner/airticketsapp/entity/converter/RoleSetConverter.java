package com.vahner.airticketsapp.entity.converter;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.ObjectWriter;
import com.vahner.airticketsapp.entity.enums.RoleType;
import jakarta.persistence.AttributeConverter;
import jakarta.persistence.Converter;
import lombok.extern.slf4j.Slf4j;

import java.io.IOException;
import java.util.HashSet;
import java.util.Set;

@Slf4j
@Converter
public class RoleSetConverter implements AttributeConverter<Set<RoleType>, String> {

    @Override
    public String convertToDatabaseColumn(Set<RoleType> roles) {
        String roleInfoJson = null;
        try {
            ObjectWriter ow = new ObjectMapper().writer().withDefaultPrettyPrinter();
            roleInfoJson = ow.writeValueAsString(roles);
        } catch (final JsonProcessingException e) {
            log.error("JSON writing error", e);
        }

        return roleInfoJson;

    }

    @Override
    public Set<RoleType> convertToEntityAttribute(String rolesInfoJson) {
        Set<RoleType> roleSet = null;
        try {
            roleSet = new ObjectMapper().readValue(rolesInfoJson,
                    new TypeReference<HashSet<RoleType>>() {
                    });
        } catch (final IOException e) {
            log.error("JSON reading error", e);
        }

        return roleSet;
    }

}