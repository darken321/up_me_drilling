package dto.hole;

import dto.Pattern;
import lombok.*;
import lombok.experimental.FieldDefaults;

import java.util.List;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
public class HoleSaveDto {
    //TODO добавить проверку ошибок из stopSaveDto

    int part_id;

    double diameter;

    double depth;

    double drillEntrySpeed;

    double drillExitSpeed;

    List<Pattern> lPatterns;
    List<Pattern> bPatterns;
    List<Pattern> hPatterns;

}