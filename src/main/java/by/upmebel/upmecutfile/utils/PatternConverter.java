package by.upmebel.upmecutfile.utils;

import by.upmebel.upmecutfile.projection.PartSize;
import dto.Pattern;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Map;

@Component
@RequiredArgsConstructor
public class PatternConverter {

    @FunctionalInterface
    interface Operation {
        double apply(double variable, double value);
    }

    public Double convert(List<Pattern> patterns, PartSize partSizes) {

        double result = 0.0;

        Map<String, Operation> operations = Map.of(
                "+", (variable, value) -> variable + value,
                "-", (variable, value) -> variable - value,
                "*", (variable, value) -> variable * value,
                "/", (variable, value) -> variable / value
        );

        for (Pattern pattern : patterns) {
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