package by.upmebel.upmecutfile;

import by.upmebel.upmecutfile.model.Part;
import by.upmebel.upmecutfile.model.Hole;
import by.upmebel.upmecutfile.repository.PartRepository;
import by.upmebel.upmecutfile.repository.HoleRepository;
import jakarta.annotation.PostConstruct;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

@Slf4j
@AllArgsConstructor
@Component
public class PopulateDB {
    private final PartRepository partRepository;
    private final HoleRepository holeRepository;


    @PostConstruct
    public void init() {
        //Добавляю 3 детали
        Part furniturePart;

        for (int i = 20; i <= 60; i+=10) {
            furniturePart = Part.builder()
                    .h(i)
                    .l(i*1.5)
                    .b(18.0)
                    .build();
            partRepository.save(furniturePart);
        }

        //Добавляю 3 отверстия
        Hole hole;
        hole = Hole.builder()
                .part(partRepository.findById(2).orElseThrow())
                .coordinateH(0)
                .coordinateL(15)
                .coordinateB(9)
                .depth(10)
                .diameter(6)
                .drillEntrySpeed(5)
                .drillExitSpeed(8)
                .build();
        holeRepository.save(hole);

        hole = Hole.builder()
                .part(partRepository.findById(2).orElseThrow())
                .coordinateH(10)
                .coordinateL(0)
                .coordinateB(9)
                .depth(20)
                .diameter(5)
                .drillEntrySpeed(5)
                .drillExitSpeed(8)
                .build();
        holeRepository.save(hole);

        hole = Hole.builder()
                .part(partRepository.findById(5).orElseThrow())
                .coordinateH(0)
                .coordinateL(15)
                .coordinateB(9)
                .depth(22)
                .diameter(4)
                .drillEntrySpeed(5)
                .drillExitSpeed(8)
                .build();
        holeRepository.save(hole);
    }
}
