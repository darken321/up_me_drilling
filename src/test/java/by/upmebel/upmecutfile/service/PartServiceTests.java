package by.upmebel.upmecutfile.service;

import by.upmebel.upmecutfile.model.Part;
import by.upmebel.upmecutfile.repository.PartRepository;
import jakarta.validation.ConstraintViolationException;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.NoSuchElementException;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

@SpringBootTest
public class PartServiceTests {

    @Autowired
    private PartService partService;

    @Autowired
    private PartRepository partRepository;

    private Part savedPart;

    private Part part;


    @BeforeEach
    void setUp() {
        part = Part.builder()
                .l(10)
                .b(20)
                .h(30)
                .build();
        savedPart = partService.save(part);
    }

    @Test
    @Transactional
    void testSavePart() {

        assertThat(savedPart.getPartId()).isNotNull().isGreaterThan(0);
        assertThat(part).usingRecursiveComparison().isEqualTo(savedPart);

        Part actualDBPart = partRepository.findById(savedPart.getPartId()).orElseThrow();
        assertThat(actualDBPart).usingRecursiveComparison().isEqualTo(part);
    }

    @Test
    @Transactional
    void testGetAllParts() {
        int partsSizeBefore = partService.getAll().size();

        Part part2 = Part.builder()
                .l(30)
                .b(40)
                .h(50)
                .build();
        partService.save(part2);

        List<Part> parts = partService.getAll();
        assertThat(parts).hasSizeGreaterThanOrEqualTo(2);
        assertThat(parts.size() - partsSizeBefore).isEqualTo(1);

    }

    @Test
    @Transactional
    void testFindPartById() {

        Part foundPart = partService.findById(part.getPartId());

        assertThat(foundPart).isNotNull();
        assertThat(foundPart.getPartId()).isEqualTo(part.getPartId());
    }

    @Test
    @Transactional
    void testUpdate() {

        savedPart.setL(15);
        Part updatedPart = partService.update(savedPart);

        assertThat(updatedPart.getL()).isEqualTo(15);
        Part partFromDb = partService.findById(updatedPart.getPartId());
        assertThat(partFromDb).usingRecursiveComparison().isEqualTo(updatedPart);

    }

    @Test
    @Transactional
    void testDelete() {

        partService.delete(part.getPartId());

        Part finalPart = part;
        assertThatThrownBy(() -> partService.findById(finalPart.getPartId()))
                .isInstanceOf(NoSuchElementException.class);
    }

    @Test
    @Transactional
    void testConstraintViolationException() {
        part.setL(0);
        assertThatThrownBy(() -> partService.save(part))
                .isInstanceOf(ConstraintViolationException.class);
    }

    @Test
    @Transactional
    void testUpdateNonExistThrowsException() {
        partService.delete(part.getPartId());
        part.setL(15);
        assertThatThrownBy(() -> partService.update(part))
                .isInstanceOf(NoSuchElementException.class);
    }

}