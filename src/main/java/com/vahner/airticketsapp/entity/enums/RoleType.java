package com.vahner.airticketsapp.entity.enums;

import com.fasterxml.jackson.annotation.JsonValue;
import lombok.Getter;
import org.springframework.security.core.GrantedAuthority;

@Getter
public enum RoleType implements GrantedAuthority {

    ADMIN("ADMIN"),
    PASSENGER("USER");

    private final String value;

    RoleType(String value) {
        this.value = value;
    }

    @JsonValue
    public String getAuthority() {
        return value;
    }
}