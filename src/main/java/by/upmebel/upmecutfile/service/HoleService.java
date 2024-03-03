package by.upmebel.upmecutfile.service;


import by.upmebel.upmecutfile.model.Hole;
import by.upmebel.upmecutfile.repository.HoleRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
@Service
@RequiredArgsConstructor
public class HoleService {
    private final HoleRepository holeRepository;

    public List<Hole> getAll() {
        return holeRepository.findAll();
    }

    public Hole findById(Integer id) {
        return holeRepository.findById(id).orElseThrow();
    }

    public Hole save(Hole hole) {
        //TODO проверить что есть деталь к которой принадлежит отверстие
        return holeRepository.save(hole);
    }
}
