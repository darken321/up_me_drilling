package by.upmebel.upmecutfile.service;


import by.upmebel.upmecutfile.model.Hole;
import by.upmebel.upmecutfile.repository.HoleRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;
@Slf4j
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
}
