package com.boogeyman.app.constraints.validation;

import com.boogeyman.app.constraints.Email;
import com.boogeyman.app.types.RegexPatternTypes;
import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;
import lombok.extern.slf4j.Slf4j;

@Slf4j
public class EmailValidator implements ConstraintValidator<Email, String>, RegexPatternTypes {

    @Override
    public boolean isValid(String s, ConstraintValidatorContext constraintValidatorContext) {
        log.info("Email validation!");
        if(s != null && s.length() != 0 && s.matches(RegexPatternTypes.EMAIL_REGEX)){
            return true;
        }
        return false;
    }
}
