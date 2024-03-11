package dto.hole;

import jakarta.validation.constraints.Positive;
import lombok.*;
import lombok.experimental.FieldDefaults;
import lombok.experimental.SuperBuilder;

/**
 * DTO c базовыми данными отверстия
 */
@Data
@SuperBuilder
@NoArgsConstructor
@AllArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
public class BaseHoleDto {
    @Positive
    int partId;

    @Positive(message = "Диаметр должен быть положительный")
    double diameter;

    @Positive(message = "Глубина должна быть положительной")
    double depth;

    @Positive(message = "Скорость должна быть положительной")
    double drillEntrySpeed;

    @Positive(message = "Скорость должна быть положительной")
    double drillExitSpeed;
}