package dto.part;

import jakarta.validation.constraints.Positive;
import lombok.*;
import lombok.experimental.FieldDefaults;
import org.springframework.validation.annotation.Validated;

/**
 * DTO для сохранения детали
 */

@Data
@Builder
@Validated
@AllArgsConstructor
@NoArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
public class PartSaveDto {

    @Positive(message = "Размер должен быть больше ноля")
    double l;

    @Positive(message = "Размер должен быть больше ноля")
    double b;

    @Positive(message = "Размер должен быть больше ноля")
    double h;
}
