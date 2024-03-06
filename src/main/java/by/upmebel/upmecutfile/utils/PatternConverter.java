package by.upmebel.upmecutfile.utils;

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

    public Double convert(List<Pattern> patterns, Double L, Double B, Double H) {
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
                case "L" -> result += operation.apply(L, pattern.getValue());
                case "B" -> result += operation.apply(B, pattern.getValue());
                case "H" -> result += operation.apply(H, pattern.getValue());
                case "" -> result += pattern.getValue();
            }
        }

        return result;
    }
}