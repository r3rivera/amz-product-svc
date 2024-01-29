package com.boogeyman.app.constraints.validation;

import com.boogeyman.app.constraints.RangeCheck;
import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.BeanWrapperImpl;

/**
 * Since this is applied on the class level, the ConstraintValidator must
 * specify the annotation type and Object
 */
@Slf4j
public class RangeCheckValidator implements ConstraintValidator<RangeCheck, Object> {
    private String startDateField;
    private String endDateField;

    @Override
    public void initialize(RangeCheck constrains) {
        startDateField = constrains.startRange();
        endDateField = constrains.endRange();
    }

    @Override
    public boolean isValid(Object classObj, ConstraintValidatorContext constraintValidatorContext) {
        log.info("Validating date range!");
        Object start = new BeanWrapperImpl(classObj).getPropertyValue(startDateField);
        Object end = new BeanWrapperImpl(classObj).getPropertyValue(endDateField);
        return false;
    }
}
