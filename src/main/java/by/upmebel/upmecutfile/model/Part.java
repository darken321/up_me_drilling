package by.upmebel.upmecutfile.model;

import jakarta.persistence.*;
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

    @Column(name = "l")
    double l;

    @Column(name = "b")
    double b;

    @Column(name = "h")
    double h;

}
