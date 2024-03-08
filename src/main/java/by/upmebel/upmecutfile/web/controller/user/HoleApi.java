package by.upmebel.upmecutfile.web.controller.user;


import by.upmebel.upmecutfile.mapper.HoleMapper;
import by.upmebel.upmecutfile.model.Coordinates;
import by.upmebel.upmecutfile.model.Hole;
import by.upmebel.upmecutfile.projection.PartSize;
import by.upmebel.upmecutfile.repository.PartRepository;
import by.upmebel.upmecutfile.service.HoleService;
import by.upmebel.upmecutfile.utils.PatternConverter;
import dto.hole.HoleDto;
import dto.hole.HoleSaveDto;
import dto.hole.HoleUpdateDto;
import jakarta.persistence.EntityNotFoundException;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.Collections;
import java.util.List;

@Slf4j
@Validated
@RestController
@AllArgsConstructor
@RequestMapping("api/v1/hole")
public class HoleApi {
    private final HoleService holeService;
    private final HoleMapper holeMapper;
    private final PartRepository partRepository;
    private final PatternConverter patternConverter;

    //CREATE
    @PostMapping
    public HoleDto save(@RequestBody HoleSaveDto dto) {

        Hole hole = holeMapper.fromDto(dto, getCoordinates(dto));
        Hole save = holeService.save(hole);
        return holeMapper.toDto(save);
    }

    //READ
    @GetMapping
    public List<HoleDto> getByFilters(@RequestParam(required = false) Integer id) {
        if (id != null) {
            return holeMapper.toDto(Collections.singletonList(holeService.findById(id)));
        }
        return holeMapper.toDto(holeService.getAll());
    }

    //TODO вынести save и update в абстрактный класс
    @PutMapping
    public HoleDto update(@RequestBody HoleUpdateDto dto) {
        //из DTO получил отверстие с координатами и подсчетом из размеров детали
        Hole hole = holeMapper.fromDto(dto, getCoordinates(dto));
        //сохранил его в БД
        Hole save = holeService.update(hole);
        return holeMapper.toDto(save);
    }

    //DELETE
    @DeleteMapping
    public void delete(@RequestParam Integer id) {
        holeService.delete(id);
    }

    /**
     * Принимает dto и возвращает три координаты отверстия
     *
     * @param dto отверстия
     * @return три координаты отверстия Coordinates
     */

    private Coordinates getCoordinates(HoleSaveDto dto) {
        //проверка что такая деталь есть
        if (!partRepository.existsById(dto.getPart_id())) {
            throw new EntityNotFoundException("Детали с id " + dto.getPart_id() + " нет в базе данных.");
        }

        //взял размеры детали по id детали
        PartSize sizes = partRepository.getSizesById(dto.getPart_id());

        //посчитал координаты исходя из паттернов и размеров
        Coordinates coordinates = new Coordinates();
        coordinates.setL(patternConverter.convert(dto.getLPatterns(), sizes));
        coordinates.setB(patternConverter.convert(dto.getBPatterns(), sizes));
        coordinates.setH(patternConverter.convert(dto.getHPatterns(), sizes));

        return coordinates;
    }
}