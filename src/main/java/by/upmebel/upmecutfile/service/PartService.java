package by.upmebel.upmecutfile.service;


import by.upmebel.upmecutfile.model.Hole;
import by.upmebel.upmecutfile.model.Part;
import by.upmebel.upmecutfile.projection.PartSize;
import by.upmebel.upmecutfile.repository.HoleRepository;
import by.upmebel.upmecutfile.repository.PartRepository;
import jakarta.persistence.EntityNotFoundException;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.validation.annotation.Validated;

import java.util.LinkedList;
import java.util.List;
import java.util.NoSuchElementException;

@Slf4j
@Service
@Validated
@RequiredArgsConstructor
public class PartService {
    private final PartRepository partRepository;
    private final HoleRepository holeRepository;

    public Part save(@Valid Part part) {
        return partRepository.save(part);
    }

    public List<Part> getAll() {
        return partRepository.findAll();
    }

    public Part findById(@Valid Integer id) {
        return partRepository.findById(id)
                .orElseThrow(() -> new NoSuchElementException("Детали с id " + id + " нет в базе данных."));
    }

    public Part update(@Valid Part part) {
        if (partRepository.existsById(part.getId())) {
            saveResizeHoles(part);
            return partRepository.save(part);
        }
        throw new NoSuchElementException("Детали с id " + part.getId() + " нет в базе данных.");
    }

    public void delete(@Valid Integer partId) {
        partRepository.deleteById(partId);
    }

    private void saveResizeHoles(@Valid Part part) {
        PartSize oldPartSizes = partRepository.getSizesById(part.getId());
        List<Hole> holes = holeRepository.findPartHoles(part.getId());

        List<Hole> holeList = new LinkedList<>();
        for (Hole hole : holes) {
            double L = hole.getCoordinateL() * part.getL() / oldPartSizes.getL();
            double B = hole.getCoordinateB() * part.getB() / oldPartSizes.getB();
            double H = hole.getCoordinateH() * part.getH() / oldPartSizes.getH();
            hole.setCoordinateL(L);
            hole.setCoordinateB(B);
            hole.setCoordinateH(H);
            holeList.add(hole);
        }
        holeRepository.saveAll(holeList);
    }
}
