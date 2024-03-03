package by.upmebel.upmecutfile.repository;

import by.upmebel.upmecutfile.model.Part;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface PartRepository extends JpaRepository<Part, Integer> {

    List<Part> getPartByIdIs(int id);
}
