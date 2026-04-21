package com.kodiatech.traore.feature.profiles.validation;

import jakarta.validation.Constraint;

import java.lang.annotation.*;

@Target({ElementType.FIELD, ElementType.PARAMETER})
@Retention(RetentionPolicy.RUNTIME)
@Documented
@Constraint(validatedBy = UtilisateurTypeValidator.class)
public @interface ValidateUtilisateurType {
    String message();


}
//https://github.com/Java-Techie-jt/custom-validation
//https://www.youtube.com/watch?v=P5sAaFY3O2w