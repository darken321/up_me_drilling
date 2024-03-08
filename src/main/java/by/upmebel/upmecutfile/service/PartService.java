package by.upmebel.upmecutfile.service;


import by.upmebel.upmecutfile.model.Hole;
import by.upmebel.upmecutfile.model.Part;
import by.upmebel.upmecutfile.projection.PartSize;
import by.upmebel.upmecutfile.repository.HoleRepository;
import by.upmebel.upmecutfile.repository.PartRepository;
import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.LinkedList;
import java.util.List;

@Slf4j
@Service
@RequiredArgsConstructor
public class PartService {
    private final PartRepository partRepository;
    private final HoleRepository holeRepository;

    public Part save(Part part) {
        return partRepository.save(part);
    }

    public List<Part> getAll() {
        return partRepository.findAll();
    }

    public Part findById(Integer id) {
        //TODO обработать ошибку если не нашел
        return partRepository.findById(id).orElseThrow();
    }

    public Part update(Part part) {
        if (partRepository.existsById(part.getId())) {
            saveResizeHoles(part);
            return partRepository.save(part);
        }
        throw new EntityNotFoundException("Детали с id " + part.getId() + " нет в базе данных.");
    }

    public void delete(Integer partId) {
        partRepository.deleteById(partId);
    }

    private void saveResizeHoles(Part part) {
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
