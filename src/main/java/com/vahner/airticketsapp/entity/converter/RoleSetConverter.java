package com.vahner.airticketsapp.entity.converter;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.ObjectWriter;
import com.vahner.airticketsapp.entity.enums.Role;
import jakarta.persistence.AttributeConverter;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.annotation.Configuration;

import java.io.IOException;
import java.util.HashSet;
import java.util.Set;

@Slf4j
@Configuration
public class RoleSetConverter implements AttributeConverter<Set<Role>, String> {
    @Override
    public String convertToDatabaseColumn(Set<Role> roles) {
        String rolesInfoJson = null;
        try {
            ObjectWriter ow = new ObjectMapper().writer().withDefaultPrettyPrinter();
            rolesInfoJson = ow.writeValueAsString(roles);
        } catch (final JsonProcessingException e) {
            log.error("JSON writing error", e);
        }
        return rolesInfoJson;
    }

    @Override
    public Set<Role> convertToEntityAttribute(String rolesInfoJson) {
        Set<Role> roleSet = null;
        try {
            roleSet = new ObjectMapper().readValue(rolesInfoJson,
                    new TypeReference<HashSet<Role>>() {
                    });
        } catch (final IOException e) {
            log.error("JSON reading error", e);
        }

        return roleSet;
    }
}
