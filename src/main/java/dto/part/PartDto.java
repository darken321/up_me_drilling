package dto.part;

import dto.hole.HoleDto;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import lombok.*;
import lombok.experimental.FieldDefaults;
import org.springframework.validation.annotation.Validated;

import java.util.List;

/**
 * DTO для детали, которое отдается на фронт.
 * Содержит размеры детали и список отверстий с данными.
 */
@Data
@Builder
@Validated
@AllArgsConstructor
@NoArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
public class PartDto {

    @Positive(message = "id должен быть положительный")
    int id;

    @Positive(message = "Размер должен быть положительный")
    double l;

    @Positive(message = "Размер должен быть положительный")
    double b;

    @Positive(message = "Размер должен быть положительный")
    double h;

    @NotNull
    List<HoleDto> holes;
}
