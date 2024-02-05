package com.vahner.airticketsapp.validation.constrain;

import com.vahner.airticketsapp.validation.interf.Uuid;
import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;

import java.util.Optional;

public class UuidAnnotationConst implements ConstraintValidator<Uuid, String> {

    private static final String TEMPLATE
            = "^\\p{XDigit}{8}-\\p{XDigit}{4}-\\p{XDigit}{4}-\\p{XDigit}{4}-\\p{XDigit}{12}$";

    @Override
    public void initialize(Uuid constraintAnnotation) {
        ConstraintValidator.super.initialize(constraintAnnotation);
    }

    @Override
    public boolean isValid(String value, ConstraintValidatorContext constraintValidatorContext) {
        return Optional.ofNullable(value)
                .filter(uuid -> !uuid.isBlank())
                .map(uuid -> uuid.matches(TEMPLATE))
                .orElse(false);
    }
}