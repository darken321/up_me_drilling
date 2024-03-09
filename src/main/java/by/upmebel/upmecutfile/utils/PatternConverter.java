package by.upmebel.upmecutfile.utils;

import by.upmebel.upmecutfile.projection.PartSize;
import dto.SizePattern;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import org.springframework.validation.annotation.Validated;

import java.util.List;
import java.util.Map;

@Component
@Validated
@RequiredArgsConstructor
public class PatternConverter {

    @FunctionalInterface
    interface Operation {
        double apply(double variable, double value);
    }

    public Double convert(@Valid List<SizePattern> patterns, @Valid PartSize partSizes) {
        double result = 0.0;

        Map<String, Operation> operations = Map.of(
                "+", (variable, value) -> variable + value,
                "-", (variable, value) -> variable - value,
                "*", (variable, value) -> variable * value,
                "/", (variable, value) -> variable / value
        );

        for (SizePattern pattern : patterns) {
            Operation operation = operations.getOrDefault(pattern.getOperator(), (variable, value) -> variable);
            switch (pattern.getVariable()) {
                case "L" -> result += operation.apply(partSizes.getL(), pattern.getValue());
                case "B" -> result += operation.apply(partSizes.getB(), pattern.getValue());
                case "H" -> result += operation.apply(partSizes.getH(), pattern.getValue());
                case "" -> result += pattern.getValue();
            }
        }

        return result;
    }
}