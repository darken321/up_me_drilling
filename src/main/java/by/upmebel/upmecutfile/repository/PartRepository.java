package by.upmebel.upmecutfile.repository;

import by.upmebel.upmecutfile.model.Part;
import by.upmebel.upmecutfile.projection.PartSizeInfo;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface PartRepository extends JpaRepository<Part, Integer> {

    @Query(value = """
            SELECT l, b, h
            FROM parts
            WHERE id = :partId
            """
            , nativeQuery = true)
    Object getSizesById(@Param("partId") Integer partId);

}

