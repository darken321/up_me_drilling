package by.upmebel.upmecutfile.dto.hole;

import jakarta.validation.constraints.Positive;
import lombok.*;
import lombok.experimental.FieldDefaults;
import org.springframework.validation.annotation.Validated;

/**
 * DTO для обновления отверстия.
 * Содержит id отверстия для обновления и прочие параметры.
 */

@Data
@EqualsAndHashCode(callSuper = true)
@Validated
@AllArgsConstructor
@NoArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
public class HoleUpdateDto extends HoleSaveDto {

    @Positive(message = "id должен быть положительный")
    int id;

}