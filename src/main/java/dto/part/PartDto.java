package dto.part;

import dto.hole.HoleDto;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import lombok.*;
import lombok.experimental.FieldDefaults;
import lombok.experimental.SuperBuilder;
import org.springframework.validation.annotation.Validated;

import java.util.List;

/**
 * DTO для детали, которое отдается на фронт.
 * Содержит id, размеры детали и список отверстий с данными.
 */
@Data
@EqualsAndHashCode(callSuper = true)
@SuperBuilder
@Validated
@AllArgsConstructor
@NoArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
public class PartDto extends BasePartDto {

    @Positive(message = "id должен быть положительный")
    int id;

    @NotNull
    List<HoleDto> holes;
}
