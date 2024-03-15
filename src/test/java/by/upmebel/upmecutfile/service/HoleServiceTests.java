package by.upmebel.upmecutfile.service;

import by.upmebel.upmecutfile.model.Hole;
import by.upmebel.upmecutfile.model.Part;
import by.upmebel.upmecutfile.repository.HoleRepository;
import by.upmebel.upmecutfile.repository.PartRepository;
import jakarta.validation.ConstraintViolationException;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.NoSuchElementException;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

@Slf4j
@SpringBootTest
public class HoleServiceTests {

    @Autowired
    private HoleService holeService;

    @Autowired
    private HoleRepository holeRepository;

    @Autowired
    private PartRepository partRepository;

    @Autowired
    private PartService partService;

    private Part savedPart;

    private Part part;

    private Hole hole;

    private Hole savedHole;

    @BeforeEach
    void setUp() {
        part = Part.builder()
                .l(10)
                .b(20)
                .h(30)
                .build();
        savedPart = partRepository.save(part);

        hole = Hole.builder()
                .part(savedPart)
                .diameter(10)
                .depth(20)
                .drillEntrySpeed(30)
                .drillExitSpeed(40)
                .coordinateL(50)
                .coordinateB(60)
                .coordinateH(70)
                .build();

        savedHole = holeService.save(hole);
    }

    @Test
    @Transactional
    void testSaveHole() {
        assertThat(savedHole).isNotNull();
        assertThat(savedHole.getId()).isGreaterThan(0);
        assertThat(savedHole.getPart()).isEqualTo(part);
        assertThat(savedHole).usingRecursiveComparison().isEqualTo(hole);

        Hole actualDBHole = holeService.findById(hole.getId());
        assertThat(actualDBHole).usingRecursiveComparison().isEqualTo(hole);

    }

    @Test
    @Transactional
    void testGetAllHoles() {
        int holesSizeBefore = holeService.getAll().size();

        Hole hole2 = Hole.builder()
                .part(part)
                .coordinateL(15)
                .coordinateB(9)
                .coordinateH(0)
                .depth(10)
                .diameter(6)
                .drillEntrySpeed(5)
                .drillExitSpeed(8)
                .build();
        holeService.save(hole2);

        List<Hole> holes = holeService.getAll();
        assertThat(holes).hasSizeGreaterThanOrEqualTo(2);
        assertThat(holes.size()-holesSizeBefore).isEqualTo(1);
    }

    @Test
    @Transactional
    void testFindById() {
        Hole foundHole = holeService.findById(hole.getId());

        assertThat(foundHole).isNotNull();
        assertThat(foundHole.getId()).isEqualTo(hole.getId());
    }

    @Test
    @Transactional
    void testUpdateHole() {
        hole.setCoordinateL(38.89);
        Hole updatedHole = holeService.update(hole);
        assertThat(updatedHole.getCoordinateL()).isEqualTo(38.89);
    }

    @Test
    @Transactional
    void testDeleteHole() {
        holeService.delete(hole.getId());

        assertThatThrownBy(() -> holeService.findById(hole.getId()))
                .isInstanceOf(NoSuchElementException.class);
    }

    @Test
    @Transactional
    void testConstraintViolationExceptionHole() {
        hole.setDiameter(0);
        assertThatThrownBy(() -> holeService.save(hole))
                .isInstanceOf(ConstraintViolationException.class);
    }

    @Test
    @Transactional
    void testUpdateNonExistThrowsException() {
        Part nonExistentPart = Part.builder().id(Integer.MAX_VALUE).build();
        hole.setPart(nonExistentPart);
        assertThatThrownBy(() -> holeService.update(hole))
                .isInstanceOf(NoSuchElementException.class);
    }

    //TODO добавить тесты на  проверку что при изменении размера детали координата отверстия тоже пропорционально меняется

}
