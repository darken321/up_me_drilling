package by.upmebel.upmecutfile;

import by.upmebel.upmecutfile.repository.HoleRepository;
import by.upmebel.upmecutfile.repository.PartRepository;
import by.upmebel.upmecutfile.service.HoleService;
import by.upmebel.upmecutfile.service.PartService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;

@SpringBootTest
public class HoleServiceTests {

    @Autowired
    HoleService holeService;
    @Autowired
    PartService partService;
    @Autowired
    HoleRepository holeRepository;
    @Autowired
    PartRepository partRepository;

//    @Test
//    @Transactional
//    void saveSuccessfullyTest(){
//
//    }


}
