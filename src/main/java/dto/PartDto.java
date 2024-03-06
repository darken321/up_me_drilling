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

    double l;

    double b;

    double h;

    List<HoleDto> holes;
}
