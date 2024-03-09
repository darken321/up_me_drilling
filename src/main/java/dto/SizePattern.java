package dto;

import by.upmebel.upmecutfile.utils.StringPatterns;
import by.upmebel.upmecutfile.validation.DoubleFormat;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Positive;
import jakarta.validation.constraints.PositiveOrZero;
import lombok.*;
import lombok.experimental.FieldDefaults;
import org.springframework.validation.annotation.Validated;

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
