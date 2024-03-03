package dto;

import lombok.*;
import lombok.experimental.FieldDefaults;

import java.util.List;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
public class PartDto {

    Integer id;

    double h;

    double l;

    double b;

    List<HoleDto> holes;
}
