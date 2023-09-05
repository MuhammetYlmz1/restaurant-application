package com.muhammet.restaurantapplication.validator;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

public class PhoneNumberValidator implements ConstraintValidator<PhoneNumber, String> {

    @Override
    public boolean isValid(String phoneNumber, ConstraintValidatorContext context) {
        if (phoneNumber == null || phoneNumber.isEmpty()) {
            return true;
        }

        String numericPhoneNumber = phoneNumber.replaceAll("[^0-9]", "");
        return numericPhoneNumber.length() == 10;
    }
}
