package dto.hole;

import dto.Pattern;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import lombok.*;
import lombok.experimental.FieldDefaults;

import java.util.List;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
public class HoleSaveDto {

    @Positive
    int part_id;

    @Positive
    double diameter;

    @Positive
    double depth;

    @Positive
    double drillEntrySpeed;

    @Positive
    double drillExitSpeed;

    @NotNull
    List<Pattern> lPatterns;

    @NotNull
    List<Pattern> bPatterns;

    @NotNull
    List<Pattern> hPatterns;

}