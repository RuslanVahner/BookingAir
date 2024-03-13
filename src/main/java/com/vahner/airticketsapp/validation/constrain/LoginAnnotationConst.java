package com.vahner.airticketsapp.validation.constrain;


import com.vahner.airticketsapp.validation.interf.Login;
import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;

import java.util.regex.Pattern;


public class LoginAnnotationConst implements ConstraintValidator<Login, String> {

    private static final Pattern LOGIN_PATTERN =
            Pattern.compile("^[a-zA-Z0-9]+$");

    @Override
    public boolean isValid(String newLogin, ConstraintValidatorContext context) {
        return newLogin != null && LOGIN_PATTERN.matcher(newLogin).matches();
    }
}