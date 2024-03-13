package com.vahner.airticketsapp.entity.enums;

import com.fasterxml.jackson.annotation.JsonValue;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.GrantedAuthority;

@RequiredArgsConstructor
public enum Role implements GrantedAuthority {
    ADMIN("ADMIN"),
    PASSENGER("PASSENGER");

    private final String role;

    @JsonValue
    public String getValueRole(){
        return role;
    }

    @Override
    public String getAuthority() {
        return role;
    }
}
