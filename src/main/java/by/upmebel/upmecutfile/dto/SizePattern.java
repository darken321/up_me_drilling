package by.upmebel.upmecutfile.dto;

import by.upmebel.upmecutfile.utils.StringPatterns;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Positive;
import lombok.*;
import lombok.experimental.FieldDefaults;
import org.springframework.validation.annotation.Validated;

/**
 * Класс для хранения информации о паттерне для расчета координат отверстий в зависимости от размера детали.
 * variable - какой размер детали учитывается
 * operator - операция над размером
 * value - числовое значение, на которое производится операция.
 */

@Validated
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
public class SizePattern {

    @Pattern(regexp = StringPatterns.DIMENSIONS_PATTERN , message = "поле должно содержать L, B, H или быть пустым")
    String variable;

    @Pattern(regexp = StringPatterns.OPERATORS_PATTERN , message = "поле должно содержать  *, +, -, / или быть пустым ")
    String operator;

    @Positive
    double value;
}
