package com.kodiatech.traore.profiles.validation;

import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;
import org.hibernate.mapping.Constraint;

import java.util.Arrays;
import java.util.List;

public class UtilisateurTypeValidator implements ConstraintValidator<ValidateUtilisateurType,String> {
    @Override
    public boolean isValid(String utilisateursType, ConstraintValidatorContext constraintValidatorContext) {
        //type que tu precise sinon l'erreur dans mon projet j'ais pas encore besoin
        List<String> utilisateursTypes = Arrays.asList("Permanent", "vendor");
        return utilisateursTypes.contains(utilisateursType);
    }
}
