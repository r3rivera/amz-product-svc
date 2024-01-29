package com.boogeyman.app.constraints.validation;

import com.boogeyman.app.constraints.PhoneNumber;
import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;
import lombok.extern.slf4j.Slf4j;

@Slf4j
public class PhoneNumberValidator implements ConstraintValidator<PhoneNumber, String> {

    @Override
    public boolean isValid(String s, ConstraintValidatorContext constraintValidatorContext) {
        log.info("Phone number validation!");
        if(s != null && s.length() != 0 && s.matches("^\\d{10}$")){
            return true;
        }
        return false;
    }
}
