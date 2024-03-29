package by.upmebel.upmecutfile.config;

import by.upmebel.upmecutfile.model.Part;
import by.upmebel.upmecutfile.model.Hole;
import by.upmebel.upmecutfile.repository.PartRepository;
import by.upmebel.upmecutfile.repository.HoleRepository;
import jakarta.annotation.PostConstruct;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

/**
 * Класс добавляет в БД 5 деталей и 3 отверстия в них
 */
@Slf4j
@AllArgsConstructor
@Component
public class PopulateDB {
    private final PartRepository partRepository;
    private final HoleRepository holeRepository;


    @PostConstruct
    public void init() {
        //Добавляю 5 деталей
        Part furniturePart;
        for (int i = 10; i <= 30; i+=5) {
            furniturePart = Part.builder()
                    .l(i*1.5)
                    .b(18.0)
                    .h(i)
                    .build();
            partRepository.save(furniturePart);
        }

        //Добавляю 3 отверстия
        Hole hole;
        hole = Hole.builder()
                .part(partRepository.findById(2).orElseThrow())
                .coordinateL(15)
                .coordinateB(9)
                .coordinateH(0)
                .depth(10)
                .diameter(6)
                .drillEntrySpeed(5)
                .drillExitSpeed(8)
                .build();
        holeRepository.save(hole);

        hole = Hole.builder()
                .part(partRepository.findById(2).orElseThrow())
                .coordinateL(0)
                .coordinateB(9)
                .coordinateH(10)
                .depth(20)
                .diameter(5)
                .drillEntrySpeed(5)
                .drillExitSpeed(8)
                .build();
        holeRepository.save(hole);

        hole = Hole.builder()
                .part(partRepository.findById(5).orElseThrow())
                .coordinateL(15)
                .coordinateB(9)
                .coordinateH(0)
                .depth(22)
                .diameter(4)
                .drillEntrySpeed(5)
                .drillExitSpeed(8)
                .build();
        holeRepository.save(hole);
    }
}
