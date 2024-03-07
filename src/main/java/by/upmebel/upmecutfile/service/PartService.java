package by.upmebel.upmecutfile.service;


import by.upmebel.upmecutfile.model.Part;
import by.upmebel.upmecutfile.repository.PartRepository;
import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;

@Slf4j
@Service
@RequiredArgsConstructor
public class PartService {
    private final PartRepository partRepository;

    public Part save(Part part) {
        return partRepository.save(part);
    }

    public List<Part> getAll() {
        return partRepository.findAll();
    }

    public Part findById(Integer id) {
        return partRepository.findById(id).orElseThrow();
    }

    public Part update(Part part) {
        if (partRepository.existsById(part.getId())) {
            return partRepository.save(part);
        }
        throw new EntityNotFoundException("Детали с Id " + part.getId()  + " нет в базе данных.");
    }

    public void delete(Integer partId) {
        partRepository.deleteById(partId);
    }
}
