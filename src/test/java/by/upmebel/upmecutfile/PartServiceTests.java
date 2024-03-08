package by.upmebel.upmecutfile;

import by.upmebel.upmecutfile.model.Hole;
import by.upmebel.upmecutfile.model.Part;
import by.upmebel.upmecutfile.repository.HoleRepository;
import by.upmebel.upmecutfile.repository.PartRepository;
import by.upmebel.upmecutfile.service.HoleService;
import by.upmebel.upmecutfile.service.PartService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;

import static org.assertj.core.api.Assertions.*;

@SpringBootTest
public class PartServiceTests {

    @Autowired
    HoleService holeService;
    @Autowired
    PartService partService;
    @Autowired
    HoleRepository holeRepository;
    @Autowired
    PartRepository partRepository;

    @Test
    @Transactional
    void saveSuccessfullyTest(){
        //создать деталь
        Part part = Part.builder()
                .l(10)
                .b(20)
                .h(30)
                .build();
        partRepository.save(part);

        Hole expectedHole = Hole.builder()
                .part(part)
                .diameter(15)
                .depth(10)
                .drillEntrySpeed(3)
                .drillExitSpeed(6)
                .coordinateL(5)
                .coordinateB(6)
                .coordinateH(7)
                .build();
        Hole actualHole = holeRepository.save(expectedHole);


        assertThat(actualHole.getId()).isNotNull().isGreaterThan(0);
        assertThat(actualHole).usingRecursiveComparison().isEqualTo(expectedHole);

        Hole actualDBHole = holeRepository.findHoleById(actualHole.getId()).orElseThrow();
        assertThat(actualDBHole).usingRecursiveComparison().isEqualTo(expectedHole);

        assertThat(holeRepository.findHoleById(actualDBHole.getId())).isNotEmpty().get()
                .usingRecursiveComparison().isEqualTo(actualHole);
    }


}