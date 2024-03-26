package by.upmebel.upmecutfile.dto.part;

import jakarta.validation.constraints.Positive;
import lombok.*;
import lombok.experimental.FieldDefaults;
import lombok.experimental.SuperBuilder;
import org.springframework.validation.annotation.Validated;


/**
 * DTO для обновления детали.
 * Содержит id и размеры детали.
 */

@Data
@EqualsAndHashCode(callSuper = true)
@SuperBuilder
@Validated
@NoArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
public class PartUpdateDto extends PartSaveDto {

    @Positive(message = "id должен быть положительный")
    int id;

}
