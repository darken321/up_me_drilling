package dto.part;

import jakarta.validation.constraints.Positive;
import lombok.*;
import lombok.experimental.FieldDefaults;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
public class PartSaveDto {

    @Positive
    double l;

    @Positive
    double b;

    @Positive
    double h;
}
