package dto.hole;

import dto.SizePattern;
import jakarta.validation.Valid;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import lombok.*;
import lombok.experimental.FieldDefaults;
import org.springframework.validation.annotation.Validated;

import java.util.List;

@Validated
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
public class HoleSaveDto {

    @Positive
    int part_id;

    @Positive(message = "диаметр должен быть положительный")
    double diameter;

    @Positive(message = "глубина должна быть положительной")
    double depth;

    @Positive(message = "скорость должна быть положительной")
    double drillEntrySpeed;

    @Positive(message = "скорость должна быть положительной")
    double drillExitSpeed;

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