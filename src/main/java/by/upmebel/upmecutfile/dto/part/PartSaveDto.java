package by.upmebel.upmecutfile.dto.part;

import lombok.*;
import lombok.experimental.FieldDefaults;
import lombok.experimental.SuperBuilder;
import org.springframework.validation.annotation.Validated;

/**
 * DTO для сохранения детали.
 * Содержит размеры детали.
 */

@Data
@EqualsAndHashCode(callSuper = true)
@SuperBuilder
@Validated
@NoArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
public class PartSaveDto extends BasePartDto {
}
