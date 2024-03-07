package dto.part;

import dto.hole.HoleDto;
import lombok.*;
import lombok.experimental.FieldDefaults;

import java.util.List;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
public class PartDto {

    int id;

    double l;

    double b;

    double h;

    List<HoleDto> holes;
}
