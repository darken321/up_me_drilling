package by.upmebel.upmecutfile.service;


import by.upmebel.upmecutfile.model.Hole;
import by.upmebel.upmecutfile.repository.HoleRepository;
import by.upmebel.upmecutfile.repository.PartRepository;
import jakarta.persistence.EntityNotFoundException;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.validation.annotation.Validated;

import java.util.List;
import java.util.NoSuchElementException;

@Slf4j
@Service
@Validated
@RequiredArgsConstructor
public class HoleService {
    private final HoleRepository holeRepository;
    private final PartRepository partRepository;

    public Hole save(@Valid Hole hole) {
        if (partRepository.existsById(hole.getPart().getId())) {
            return holeRepository.save(hole);
        }
        throw new EntityNotFoundException("Детали с id " + hole.getPart().getId() + " нет в базе данных.");
    }

    public List<Hole> getAll() {
        return holeRepository.findAll();
    }

    public Hole findById(@Valid Integer id) {
        return holeRepository.findById(id)
                .orElseThrow(() -> new NoSuchElementException("Отверстия с id " + id + " нет в базе данных."));
    }

    public Hole update(@Valid Hole hole) {
        if (!partRepository.existsById(hole.getPart().getId())) {
            throw new NoSuchElementException("Детали с id " + hole.getPart().getId() + " нет в базе данных.");
        } else
        if (!holeRepository.existsById(hole.getId())) {
            throw new NoSuchElementException("Отверстия с id " + hole.getId() + " нет в базе данных.");
        }
        return holeRepository.save(hole);
    }

    public void delete(@Valid Integer id) {
        holeRepository.deleteById(id);
    }
}
