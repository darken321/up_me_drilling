package by.upmebel.upmecutfile.service;


import by.upmebel.upmecutfile.model.Hole;
import by.upmebel.upmecutfile.repository.HoleRepository;
import by.upmebel.upmecutfile.repository.PartRepository;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.validation.annotation.Validated;

import java.util.List;
import java.util.NoSuchElementException;

/**
 * Сервисный класс для управления CRUD операциями над сущностями {@link Hole}.
 * Взаимодействует с {@link HoleRepository} и {@link PartRepository}
 * для выполнения операций с базой данных.
 */

@Slf4j
@Service
@Validated
@RequiredArgsConstructor
public class HoleService {
    private final HoleRepository holeRepository;
    private final PartRepository partRepository;

    /**
     * Сохраняет отверстие в базе данных. Перед сохранением проверяет наличие связанной детали.
     *
     * @param hole Сущность отверстия, которую необходимо сохранить.
     * @return Сохраненная сущность отверстия.
     * @throws NoSuchElementException если связанная деталь не найдена в базе данных.
     */
    public Hole save(@Valid Hole hole) {
        if (partRepository.existsById(hole.getPart().getId())) {
            return holeRepository.save(hole);
        }
        throw new NoSuchElementException("Детали с id " + hole.getPart().getId() + " нет в базе данных.");
    }

    /**
     * Возвращает список всех отверстий из базы данных.
     *
     * @return Список отверстий.
     */
    public List<Hole> getAll() {
        return holeRepository.findAll();
    }

    /**
     * Находит отверстие по его идентификатору.
     *
     * @param id Идентификатор отверстия.
     * @return Найденная сущность отверстия.
     * @throws NoSuchElementException если отверстие не найдено в базе данных.
     */
    public Hole findById(@Valid Integer id) {
        return holeRepository.findById(id)
                .orElseThrow(() -> new NoSuchElementException("Отверстия с id " + id + " нет в базе данных."));
    }

    /**
     * Обновляет информацию об отверстии в базе данных. Проверяет наличие связанной детали и самого отверстия.
     *
     * @param hole Сущность отверстия с обновленными данными.
     * @return Обновленная сущность отверстия.
     * @throws NoSuchElementException если связанная деталь или отверстие не найдены в базе данных.
     */
    public Hole update(@Valid Hole hole) {
        if (!partRepository.existsById(hole.getPart().getId())) {
            throw new NoSuchElementException("Детали с id " + hole.getPart().getId() + " нет в базе данных.");
        } else
        if (!holeRepository.existsById(hole.getId())) {
            throw new NoSuchElementException("Отверстия с id " + hole.getId() + " нет в базе данных.");
        }
        return holeRepository.save(hole);
    }

    /**
     * Удаляет отверстие из базы данных по его идентификатору.
     *
     * @param id Идентификатор отверстия, которое необходимо удалить.
     */
    public void delete(@Valid Integer id) {
        holeRepository.deleteById(id);
    }

    public List<Hole> findPartHoles(@Valid Integer partId) {
        return holeRepository.findPartHoles(partId);
    }


}
