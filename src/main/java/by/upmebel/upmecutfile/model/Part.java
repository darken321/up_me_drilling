package by.upmebel.upmecutfile.model;

import jakarta.persistence.*;
import jakarta.validation.constraints.Positive;
import lombok.*;
import lombok.experimental.FieldDefaults;

import java.util.ArrayList;
import java.util.List;

@Entity
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
@Table(name = "part")
public class Part {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    int id;

    @OneToMany(mappedBy = "part", cascade = CascadeType.ALL)
    List<Hole> holes = new ArrayList<>();

    @Positive
    @Column(name = "l")
    double l;

    @Positive
    @Column(name = "b")
    double b;

    @Positive
    @Column(name = "h")
    double h;

}
