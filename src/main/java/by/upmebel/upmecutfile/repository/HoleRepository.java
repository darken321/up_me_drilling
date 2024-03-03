package by.upmebel.upmecutfile.repository;

import by.upmebel.upmecutfile.model.Hole;
import jakarta.transaction.Transactional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
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

    /**
     * удаляет отверстия по номеру детали partId
     * @param partId
     */
    @Transactional
    @Modifying
    @Query(value = "DELETE FROM holes WHERE part_id = :partIdParam", nativeQuery = true)
    void deleteHolesByPartId(@Param("partIdParam") int partId);
}
