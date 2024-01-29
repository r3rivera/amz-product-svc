package com.boogeyman.app.constraints;

import com.boogeyman.app.constraints.validation.RangeCheckValidator;
import jakarta.validation.Constraint;
import jakarta.validation.Payload;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;


@Retention(RetentionPolicy.RUNTIME)
@Target({ElementType.TYPE})
@Constraint(validatedBy = {RangeCheckValidator.class})
public @interface RangeCheck {
    String message() default "Start date must be before End date";
    Class<?>[] groups() default {};
    Class<? extends Payload>[] payload() default {};

    String startRange();

    String endRange();

    @Target({ ElementType.TYPE })
    @Retention(RetentionPolicy.RUNTIME)
    @interface List {
        RangeCheck[] value();
    }
}
