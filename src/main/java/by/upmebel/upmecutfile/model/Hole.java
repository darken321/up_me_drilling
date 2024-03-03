package by.upmebel.upmecutfile.model;

import jakarta.persistence.*;
import lombok.*;
import lombok.experimental.FieldDefaults;

@Entity
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
@Table(name = "holes")
public class Hole {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    int id;

    @ManyToOne
    @JoinColumn(name = "part_id")
    Part part;

    @Column(name = "diameter")
    double diameter;

    @Column(name = "depth")
    double depth;

    @Column(name = "drill_entry_speed")
    double drillEntrySpeed;

    @Column(name = "drill_exit_speed")
    double drillExitSpeed;

    @Column(name = "coordinate_l")
    double coordinateL;

    @Column(name = "coordinate_b")
    double coordinateB;

    @Column(name = "coordinate_h")
    double coordinateH;
}