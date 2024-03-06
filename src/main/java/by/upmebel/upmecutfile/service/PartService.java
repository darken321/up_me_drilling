package by.upmebel.upmecutfile.service;


import by.upmebel.upmecutfile.model.Hole;
import by.upmebel.upmecutfile.model.Part;
import by.upmebel.upmecutfile.repository.HoleRepository;
import by.upmebel.upmecutfile.repository.PartRepository;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;

@Slf4j
@Service
@RequiredArgsConstructor
public class PartService {
    private final PartRepository partRepository;

    public List<Part> getAll() {
        return partRepository.findAll();
    }

    public Part findById(Integer id) {
        return partRepository.findById(id).orElseThrow();
    }

    public Part save(Part part) {
        return partRepository.save(part);
    }

    public void delete(Integer partId) {
        partRepository.deleteById(partId);
    }
}
