package by.upmebel.upmecutfile.dto.hole;

import by.upmebel.upmecutfile.dto.SizePattern;
import jakarta.validation.Valid;
import jakarta.validation.constraints.NotNull;
import lombok.*;
import lombok.experimental.FieldDefaults;
import lombok.experimental.SuperBuilder;
import org.springframework.validation.annotation.Validated;

import java.util.List;


/**
 * DTO для сохранения отверстия в БД.
 * Содержит параметры отверстия, id детали
 * и паттерны для расчета координат отверстия
 */

@Data
@EqualsAndHashCode(callSuper = true)
@Validated
@SuperBuilder
@AllArgsConstructor
@NoArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
public class HoleSaveDto extends BaseHoleDto {

    @NotNull
    @Valid
    List<SizePattern> lPatterns;

    @NotNull
    @Valid
    List<SizePattern> bPatterns;

    @NotNull
    @Valid
    List<SizePattern> hPatterns;

}