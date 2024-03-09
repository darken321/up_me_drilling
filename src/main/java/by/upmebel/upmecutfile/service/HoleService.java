package by.upmebel.upmecutfile.service;


import by.upmebel.upmecutfile.model.Hole;
import by.upmebel.upmecutfile.repository.HoleRepository;
import by.upmebel.upmecutfile.repository.PartRepository;
import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;

@Slf4j
@Service
@RequiredArgsConstructor
public class HoleService {
    private final HoleRepository holeRepository;
    private final PartRepository partRepository;

    public Hole save(Hole hole) {
        if (partRepository.existsById(hole.getPart().getId())) {
            return holeRepository.save(hole);
        }
        throw new EntityNotFoundException("Детали с id " + hole.getPart().getId() + " нет в базе данных.");
    }

    public List<Hole> getAll() {
        return holeRepository.findAll();
    }

    public Hole findById(Integer id) {
        return holeRepository.findById(id).orElseThrow();
    }

    public Hole update(Hole hole) {
        if (!partRepository.existsById(hole.getPart().getId())) {
            throw new EntityNotFoundException("Детали с Id " + hole.getPart().getId() + " нет в базе данных.");
        } else
        if (!holeRepository.existsById(hole.getId())) {
            throw new EntityNotFoundException("Отверстия с Id " + hole.getId() + " нет в базе данных.");
        }
        return holeRepository.save(hole);
    }

    public void delete(Integer id) {
        holeRepository.deleteById(id);
    }
}
