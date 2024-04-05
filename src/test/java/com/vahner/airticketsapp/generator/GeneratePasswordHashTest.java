package com.vahner.airticketsapp.generator;

import org.junit.jupiter.api.Test;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

import static org.junit.jupiter.api.Assertions.*;

class GeneratePasswordHashTest {

    @Test
    public void testPasswordHashing() {
        BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();
        String rawPassword = "password1";
        String rawPassword2 = "password2";

        String encodedPassword = encoder.encode(rawPassword);
        String encodedPassword2 = encoder.encode(rawPassword2);

        assertNotEquals(encodedPassword, encodedPassword2);

        assertTrue(encoder.matches(rawPassword, encodedPassword));
        assertTrue(encoder.matches(rawPassword2, encodedPassword2));
    }
}