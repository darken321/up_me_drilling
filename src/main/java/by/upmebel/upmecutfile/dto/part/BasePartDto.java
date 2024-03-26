package by.upmebel.upmecutfile.dto.part;

import jakarta.validation.constraints.Positive;
import lombok.*;
import lombok.experimental.FieldDefaults;
import lombok.experimental.SuperBuilder;

/**
 * DTO c базовыми полями детали
 */

@Data
@SuperBuilder
@NoArgsConstructor
@AllArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
public class BasePartDto {

    @Positive(message = "Размер должен быть больше ноля")
    double l;

    @Positive(message = "Размер должен быть больше ноля")
    double b;

    @Positive(message = "Размер должен быть больше ноля")
    double h;
}