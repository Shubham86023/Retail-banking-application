package com.skjs.customerservice.Model.Enums.Validation;

import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;

public class EnumValidator implements ConstraintValidator<EnumValid, Enum<?>> {

    private EnumValid annotation;

    @Override
    public void initialize(EnumValid constraintAnnotation) {
        this.annotation = constraintAnnotation;
    }

    @Override
    public boolean isValid(Enum<?> value, ConstraintValidatorContext context) {
        // Null values are not valid
        if (value == null) {
            return false;
        }

        // Check for empty string values
        if (value.toString().trim().isEmpty()) {
            return true; // Handle empty values as valid (you can adjust this logic as needed)
        }

        Object[] enumValues = annotation.enumClass().getEnumConstants();
        for (Object enumValue : enumValues) {
            if (enumValue.toString().equals(value.toString())) {
                return true;
            }
        }
        return false;
    }
}
