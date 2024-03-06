package by.upmebel.upmecutfile.model;

import lombok.*;
import lombok.experimental.FieldDefaults;


@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
public class PartSizeInfo {
    private double l;
    private double b;
    private double h;
}



