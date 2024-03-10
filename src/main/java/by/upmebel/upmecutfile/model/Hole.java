package by.upmebel.upmecutfile.model;

import jakarta.persistence.*;
import jakarta.validation.constraints.*;
import lombok.*;
import lombok.experimental.FieldDefaults;
import org.springframework.validation.annotation.Validated;

/**
 * Класс Hole представляет собой сущность отверстия.
 * Содержит информацию о параметрах отверстия и связанной с ним деталью.
 * Каждое отверстие имеет уникальный идентификатор и принадлежит одной детали,
 * представленной классом Part.
 */

@Entity
@Data
@Builder
@Validated
@AllArgsConstructor
@NoArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
@Table(name = "hole")
public class Hole {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    int id;

    @NotNull
    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "part_id")
    Part part;

    @Positive
    @Positive(message = "Диаметр должен быть больше ноля")
    @Column(name = "diameter")
    double diameter;

    @Positive
    @Positive(message = "Глубина должна быть больше ноля")
    @Column(name = "depth")
    double depth;

    @Positive
    @Positive(message = "Скорость должна быть больше ноля")
    @Column(name = "drill_entry_speed")
    double drillEntrySpeed;

    @Positive(message = "Скорость должна быть больше ноля")
    @Column(name = "drill_exit_speed")
    double drillExitSpeed;

    @PositiveOrZero(message = "Координата должна быть не отрицательной")
    @Column(name = "coordinate_l")
    double coordinateL;

    @PositiveOrZero(message = "Координата должна быть не отрицательной")
    @Column(name = "coordinate_b")
    double coordinateB;

    @PositiveOrZero(message = "Координата должна быть не отрицательной")
    @Column(name = "coordinate_h")
    double coordinateH;
}