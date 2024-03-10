package by.upmebel.upmecutfile.repository;

import by.upmebel.upmecutfile.model.Part;
import by.upmebel.upmecutfile.projection.PartSize;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;


/**
 * Интерфейс репозитория для работы с сущностями {@link Part}.
 *  Предоставляет методы для выполнения операций CRUD над деталями,
 */

public interface PartRepository extends JpaRepository<Part, Integer> {

    /**
     * Получает размеры (длину, ширину и высоту) детали по её идентификатору.
     *
     * @param partId Идентификатор детали, размеры которой требуется получить.
     * @return Объект {@link PartSize}, содержащий размеры детали.
     */
    @Query(value = """
            SELECT l, b, h
            FROM part
            WHERE id = :partId
            """
            , nativeQuery = true)
    PartSize getSizesById(@Param("partId") int partId);

}


