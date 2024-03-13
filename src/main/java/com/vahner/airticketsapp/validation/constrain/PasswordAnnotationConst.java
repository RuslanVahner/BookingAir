package com.vahner.airticketsapp.validation.constrain;

import com.vahner.airticketsapp.validation.interf.Password;
import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;

import java.util.regex.Pattern;


public class PasswordAnnotationConst implements ConstraintValidator<Password, String> {

    private static final Pattern PASSWORD_PATTERN =
            Pattern.compile("^(?=.*[0-9])(?=.*[A-Z]).{8,}$");


    @Override
    public boolean isValid(String newPassword, ConstraintValidatorContext context) {
        return newPassword != null && PASSWORD_PATTERN.matcher(newPassword).matches();
    }

}