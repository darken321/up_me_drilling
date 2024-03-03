package dto;


import lombok.*;
import lombok.experimental.FieldDefaults;

//DTO для выдачи на API
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
public class HoleDto {

    Integer holeId;

    double diameter;

    double depth;

    double drillEntrySpeed;

    double drillExitSpeed;

    double coordinateL;

    double coordinateB;

    double coordinateH;

}
