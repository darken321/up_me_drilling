package by.upmebel.upmecutfile.model;

import jakarta.persistence.*;
import jakarta.validation.constraints.DecimalMax;
import jakarta.validation.constraints.DecimalMin;
import jakarta.validation.constraints.Positive;
import lombok.*;
import lombok.experimental.FieldDefaults;
import org.springframework.validation.annotation.Validated;

import java.util.ArrayList;
import java.util.List;

/**
 * Класс Part представляет собой сущность детали.
 * Содержит информацию о размерах детали и связанных с ней отверстиях.
 * Каждая деталь имеет уникальный идентификатор и может включать в себя множество отверстий,
 * представленных классом {@link Hole}.
 */

@Entity
@Data
@Builder
@Validated
@AllArgsConstructor
@NoArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
@Table(name = "part")
public class Part {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    int partId;

    @OneToMany(mappedBy = "part", cascade = CascadeType.ALL)
    List<Hole> holes = new ArrayList<>();

    @Positive(message = "Размер должен быть больше ноля")
    @Column(name = "l")
    double l;

    @Positive(message = "Размер b должен быть больше ноля")
    @Column(name = "b")
    double b;

    @DecimalMin(value = "4.0", message = "Высота должна быть больше 4")
    @DecimalMax(value = "50.0", message = "Высота должна быть меньше 50")
    @Column(name = "h")
    double h;

}
