package by.upmebel.upmecutfile.repository;

import by.upmebel.upmecutfile.model.Hole;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface HoleRepository extends JpaRepository<Hole, Integer> {
    /**
     * Возвращает список отверстий, принадлежащих к детали с указанным идентификатором.
     * Использует нативный SQL-запрос для извлечения данных из таблицы hole.
     *
     * @param partId Идентификатор детали, для которой требуется найти отверстия.
     * @return Список отверстий, принадлежащих указанной детали.
     */
    @Query(value = """
            SELECT *
            FROM hole
            WHERE part_id = :id
            """
            , nativeQuery = true)
    List<Hole> findPartHoles(@Param("id") int partId);

}
