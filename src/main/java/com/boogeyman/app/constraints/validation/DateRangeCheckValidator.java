package com.boogeyman.app.constraints.validation;

import com.boogeyman.app.constraints.DateRangeCheck;
import com.boogeyman.app.types.DatePatternTypes;
import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.BeanWrapperImpl;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Locale;

/**
 * Since this is applied on the class level, the ConstraintValidator must
 * specify the annotation type and Object
 */
@Slf4j
public class DateRangeCheckValidator implements DatePatternTypes, ConstraintValidator<DateRangeCheck, Object> {

    private String startDateField;
    private String endDateField;

    @Override
    public void initialize(DateRangeCheck constrains) {
        startDateField = constrains.startRange();
        endDateField = constrains.endRange();
    }

    @Override
    public boolean isValid(Object classObj, ConstraintValidatorContext constraintValidatorContext) {
        log.info("Validating date range!");
        final String start = String.valueOf(new BeanWrapperImpl(classObj).getPropertyValue(startDateField));
        final String end = String.valueOf(new BeanWrapperImpl(classObj).getPropertyValue(endDateField));
        final LocalDateTime startDt = convertString(start);
        final LocalDateTime endDt = convertString(end);
        return startDt.isBefore(endDt);
    }

    private LocalDateTime convertString(String value){
        final DateTimeFormatter formatter = DateTimeFormatter.ofPattern(DEFAULT_DATE_TIME_PATTERN, Locale.US);
        return LocalDateTime.parse(value, formatter);

    }
}
