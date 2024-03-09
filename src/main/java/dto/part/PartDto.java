package dto.part;

import dto.hole.HoleDto;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import lombok.*;
import lombok.experimental.FieldDefaults;
import org.springframework.validation.annotation.Validated;

import java.util.List;

@Data
@Builder
@Validated
@AllArgsConstructor
@NoArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
public class PartDto {

    @Positive(message = "id должен быть положительный")
    int id;

    @Positive(message = "Размер должен быть больше ноля")
    double l;

    @Positive(message = "Размер должен быть больше ноля")
    double b;

    @Positive(message = "Размер должен быть больше ноля")
    double h;

    @NotNull
    List<HoleDto> holes;
}
