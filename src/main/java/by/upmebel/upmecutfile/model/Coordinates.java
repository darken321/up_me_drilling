package by.upmebel.upmecutfile.model;

import lombok.*;
import lombok.experimental.FieldDefaults;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
public class Coordinates {

    double l;

    double b;

    double h;

}
