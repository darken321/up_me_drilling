package dto.hole;


import jakarta.validation.constraints.Positive;
import jakarta.validation.constraints.PositiveOrZero;
import lombok.*;
import lombok.experimental.FieldDefaults;
import lombok.experimental.SuperBuilder;
import org.springframework.validation.annotation.Validated;

/**
 * DTO для отверстия, которое отдается на фронт.
 * Содержит id детали и отверстия, координаты и прочие параметры.
 */

@Data
@EqualsAndHashCode(callSuper = true)
@Validated
@SuperBuilder
@AllArgsConstructor
@NoArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
public class HoleDto extends BaseHoleDto {

    @Positive(message = "id должен быть положительный")
    int holeId;

    @PositiveOrZero(message = "Координата должна быть не отрицательной")
    double coordinateL;

    @PositiveOrZero(message = "Координата должна быть не отрицательной")
    double coordinateB;

    @PositiveOrZero(message = "Координата должна быть не отрицательной")
    double coordinateH;

}
