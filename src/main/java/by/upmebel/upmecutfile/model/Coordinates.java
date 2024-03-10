package by.upmebel.upmecutfile.model;

import jakarta.validation.constraints.PositiveOrZero;
import lombok.*;
import lombok.experimental.FieldDefaults;
import org.springframework.validation.annotation.Validated;


/**
 * Класс координат отверстия
 */
@Data
@Builder
@Validated
@AllArgsConstructor
@NoArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
public class Coordinates {

    @PositiveOrZero(message = "Координата должна быть не отрицательной")
    double l;

    @PositiveOrZero(message = "Координата должна быть не отрицательной")
    double b;

    @PositiveOrZero(message = "Координата должна быть не отрицательной")
    double h;
}
