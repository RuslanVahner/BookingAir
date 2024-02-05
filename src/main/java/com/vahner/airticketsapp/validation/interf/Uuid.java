package com.vahner.airticketsapp.validation.interf;

import com.vahner.airticketsapp.validation.constrain.UuidAnnotationConst;
import jakarta.validation.Constraint;
import jakarta.validation.Payload;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Target(ElementType.PARAMETER)
@Retention(RetentionPolicy.CLASS)
@Constraint(validatedBy = {UuidAnnotationConst.class})
public @interface Uuid {
    String message() default "It's not found UUID format";

    Class<?>[] groups() default {};

    Class<? extends Payload>[] payload() default {};
}
