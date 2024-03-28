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

/**
 * Сервисный класс для управления CRUD операциями над сущностями {@link Part}.
 * Включает в себя логику для изменения размеров связанных отверстий
 * при обновлении размеров детали. Взаимодействует с {@link PartRepository}
 * и {@link HoleRepository} для выполнения операций с базой данных.
 */
@Slf4j
@Service
@Validated
@RequiredArgsConstructor
public class PartService {
    private final PartRepository partRepository;
    private final HoleRepository holeRepository;

    /**
     * Сохраняет деталь в базе данных.
     *
     * @param part Сущность детали, которую необходимо сохранить.
     * @return Сохраненная сущность детали.
     */
    public Part save(@Valid Part part) {
        return partRepository.save(part);
    }

    /**
     * Возвращает список всех деталей из базы данных.
     *
     * @return Список деталей.
     */
    public List<Part> getAll() {
        return partRepository.findAll();
    }

    /**
     * Находит деталь по её идентификатору.
     *
     * @param id Идентификатор детали.
     * @return Найденная сущность детали.
     * @throws NoSuchElementException если деталь не найдена в базе данных.
     */
    public Part findById(@Valid Integer id) {
        return partRepository.findById(id)
                .orElseThrow(() -> new NoSuchElementException("Детали с id " + id + " нет в базе данных."));
    }

    /**
     * Обновляет информацию о детали в базе данных. Перед сохранением обновленной детали
     * автоматически корректирует размеры связанных отверстий в соответствии с новыми размерами детали.
     *
     * @param part Сущность детали с обновленными данными.
     * @return Обновленная сущность детали.
     * @throws NoSuchElementException если деталь не найдена в базе данных.
     */
    public Part update(@Valid Part part) {
        if (partRepository.existsById(part.getPartId())) {
            saveResizeHoles(part);
            return partRepository.save(part);
        }
        throw new NoSuchElementException("Детали с id " + part.getPartId() + " нет в базе данных.");
    }

    /**
     * Удаляет деталь из базы данных по её идентификатору.
     *
     * @param partId Идентификатор детали, которую необходимо удалить.
     */
    public void delete(@Valid Integer partId) {
        partRepository.deleteById(partId);
    }

    /**
     * Метод для изменения размеров связанных отверстий при обновлении детали.
     * Рассчитывает новые координаты отверстий на основе измененных размеров
     * детали и сохраняет обновленные отверстия. Координаты отверстий
     * масштабируются пропорционально изменению каждого размера детали.
     *
     * @param part Обновленная сущность детали.
     */
    private void saveResizeHoles(@Valid Part part) {
        PartSize oldPartSizes = partRepository.getSizesById(part.getPartId());
        List<Hole> holes = holeRepository.findPartHoles(part.getPartId());

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