package dto;


import by.upmebel.upmecutfile.model.Part;
import lombok.*;
import lombok.experimental.FieldDefaults;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
public class HoleSaveDto {
    //TODO добавить проверку ошибок из stopSaveDto

    Part part;

    double diameter;

    double depth;

    double drillEntrySpeed;

    double drillExitSpeed;

    double coordinateL;

    double coordinateB;

    double coordinateH;

}
