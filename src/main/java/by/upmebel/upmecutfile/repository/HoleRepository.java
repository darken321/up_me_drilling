package by.upmebel.upmecutfile.repository;

import by.upmebel.upmecutfile.model.Hole;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface HoleRepository extends JpaRepository<Hole, Integer> {
    /**
     * возвращает список отверстий, которые принадлежат к одной детали
     */
    @Query(value = """
            SELECT *
            FROM holes
            WHERE part_id = :id
            """
            , nativeQuery = true)
    List<Hole> findPartHoles(@Param("id") Integer partId);
}
