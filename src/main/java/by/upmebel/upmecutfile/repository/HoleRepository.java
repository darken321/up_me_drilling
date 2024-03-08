package by.upmebel.upmecutfile.repository;

import by.upmebel.upmecutfile.model.Hole;
import org.springframework.data.jpa.repository.EntityGraph;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;
import java.util.Optional;

public interface HoleRepository extends JpaRepository<Hole, Integer> {
    /**
     * возвращает список отверстий, которые принадлежат к одной детали
     */
    @Query(value = """
            SELECT *
            FROM hole
            WHERE part_id = :id
            """
            , nativeQuery = true)
    List<Hole> findPartHoles(@Param("id") int partId);


    @EntityGraph(attributePaths = {"part"})
    Optional<Hole> findHoleById(int id);
}
