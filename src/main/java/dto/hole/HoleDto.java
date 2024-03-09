package dto.hole;


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
public class HoleDto {

    @Positive(message = "id должен быть положительный")
    int holeId;

    @Positive(message = "id должен быть положительный")
    long partId;

    @Positive(message = "диаметр должен быть положительный")
    double diameter;

    @Positive(message = "глубина должна быть положительной")
    double depth;

    @Positive(message = "скорость должна быть положительной")
    double drillEntrySpeed;

    @Positive(message = "скорость должна быть положительной")
    double drillExitSpeed;

    @PositiveOrZero(message = "Координата должна быть не отрицательной")
    double coordinateL;

    @PositiveOrZero(message = "Координата должна быть не отрицательной")
    double coordinateB;

    @PositiveOrZero(message = "Координата должна быть не отрицательной")
    double coordinateH;

}
