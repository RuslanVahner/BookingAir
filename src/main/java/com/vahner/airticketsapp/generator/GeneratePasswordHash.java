package com.vahner.airticketsapp.generator;

import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

public class GeneratePasswordHash {
    public static void main(String[] args) {
        BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();
        String rawPassword = "password1";
        String rawPassword2 = "password2";
        String encodedPassword = encoder.encode(rawPassword);
        String encodedPassword2 = encoder.encode(rawPassword2);
        System.out.println("Hash password: " + encodedPassword);
        System.out.println("Hash password: " + encodedPassword2);
    }
}
