package by.upmebel.upmecutfile.validation;

import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;

import java.util.regex.Pattern;

public class DoubleFormatValidator implements ConstraintValidator<DoubleFormat, Double> {

    private static final Pattern PATTERN = Pattern.compile("[0-9]+(\\.[0-9]+)?");

    @Override
    public boolean isValid(Double value, ConstraintValidatorContext context) {
        if (value == null) {
            return true;
        }
        String valueStr = String.valueOf(value);
        return PATTERN.matcher(valueStr).matches() && !valueStr.startsWith(".");
    }
}

