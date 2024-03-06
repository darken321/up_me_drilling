package by.upmebel.upmecutfile.utils;

import dto.Pattern;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
@RequiredArgsConstructor
public class PatternConverter {

    public Double convert(List<Pattern> patterns, Double L, Double B, Double H) {
        double result = 0.0;

        for (Pattern pattern : patterns) {
            switch (pattern.getVariable()) {
                case "L" -> {
                    switch (pattern.getOperator()) {
                        case "+" -> result += L + pattern.getValue();
                        case "-" -> result += L - pattern.getValue();
                        case "*" -> result += L * pattern.getValue();
                        case "/" -> result += L / pattern.getValue();
                    }
                }
                case "B" -> {
                    switch (pattern.getOperator()) {
                        case "+" -> result += B + pattern.getValue();
                        case "-" -> result += B - pattern.getValue();
                        case "*" -> result += B * pattern.getValue();
                        case "/" -> result += B / pattern.getValue();
                    }
                }
                case "H" -> {
                    switch (pattern.getOperator()) {
                        case "+" -> result += H + pattern.getValue();
                        case "-" -> result += H - pattern.getValue();
                        case "*" -> result += H * pattern.getValue();
                        case "/" -> result += H / pattern.getValue();
                    }
                }
                case "" -> result += pattern.getValue();
            }
        }
        return result;

    }
}