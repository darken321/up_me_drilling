package by.upmebel.upmecutfile.validation;

import jakarta.validation.Constraint;
import jakarta.validation.Payload;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Target({ElementType.FIELD})
@Retention(RetentionPolicy.RUNTIME)
@Constraint(validatedBy = DoubleFormatValidator.class)
public @interface DoubleFormat {
    String message() default "Invalid format for double value";
    Class<?>[] groups() default {};
    Class<? extends Payload>[] payload() default {};
}

