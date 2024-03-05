package dto;

import lombok.*;
import lombok.experimental.FieldDefaults;

import java.util.Map;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
public class HoleSaveDto {
    //TODO добавить проверку ошибок из stopSaveDto

    Integer part_id;

    double diameter;

    double depth;

    double drillEntrySpeed;

    double drillExitSpeed;

    double coordinateL;

    double coordinateB;

    double coordinateH;

    Pattern pattern;

    @Data
    @Builder
    @AllArgsConstructor
    @NoArgsConstructor
    @FieldDefaults(level = AccessLevel.PRIVATE)
    public static class Pattern {
        Map<String, Double> parameters;
        String operation;
    }
}
//Привер Json для этой структуры данных:
//{
//    "part_id": "1",
//    "diameter": "1",
//    "depth": "2",
//    "drillEntrySpeed": "3",
//    "drillExitSpeed": "4",
//    "coordinateX": "5",
//    "coordinateY": "6",
//    "coordinateZ": "7",
//    "pattern": {
//        "parameters": {
//            "L": 2,
//            "B": 4,
//            "H": 0.5
//        },
//        "operation": "+-*"
//     }
//}