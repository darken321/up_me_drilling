package by.upmebel.upmecutfile.repository;

import by.upmebel.upmecutfile.model.Part;
import by.upmebel.upmecutfile.projection.PartSize;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

public interface PartRepository extends JpaRepository<Part, Integer> {

    @Query(value = """
            SELECT l, b, h
            FROM part
            WHERE id = :partId
            """
            , nativeQuery = true)
    PartSize getSizesById(@Param("partId") int partId);

}


