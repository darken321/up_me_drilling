package by.upmebel.upmecutfile.web.controller.user;


import by.upmebel.upmecutfile.dto.hole.HoleDto;
import by.upmebel.upmecutfile.dto.part.PartDto;
import by.upmebel.upmecutfile.dto.part.PartSaveDto;
import by.upmebel.upmecutfile.dto.part.PartUpdateDto;
import by.upmebel.upmecutfile.mapper.HoleMapper;
import by.upmebel.upmecutfile.mapper.PartMapper;
import by.upmebel.upmecutfile.model.Part;
import by.upmebel.upmecutfile.repository.HoleRepository;
import by.upmebel.upmecutfile.service.PartService;
import by.upmebel.upmecutfile.web.controller.user.swagger.PartApiSwagger;
import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.NoSuchElementException;

/**
 * CRUD REST API контроллер для управления данными об отверстиях в деталях для объектов {@link Part}.
 */

@Slf4j
@RestController
@AllArgsConstructor
@RequestMapping("api/v1/part")
public class PartApi implements PartApiSwagger {
    private final PartService partService;
    private final PartMapper partMapper;
    private final HoleMapper holeMapper;
    private final HoleRepository holeRepository;

    /**
     * Сохраняет новую деталь в базе данных на основе данных DTO.
     * Преобразует DTO в сущность {@link Part} и сохраняет через сервис.
     *
     * @param dto DTO для создания новой детали, содержащее все необходимые параметры.
     * @return DTO сохраненной детали с присвоенным идентификатором.
     */

    @PostMapping
    public PartDto save(@RequestBody @Valid PartSaveDto dto) {
        List<HoleDto> holes = new ArrayList<>();
        Part part = partMapper.fromDto(dto);
        Part save = partService.save(part);
        return partMapper.toDto(save, holes);
    }

    /**
     * Возвращает список DTO деталей.
     * Если идентификатор не указан, возвращает все детали.
     *
     * @param id Идентификатор детали для фильтрации. Необязательный параметр.
     * @return Список DTO деталей, соответствующих заданному фильтру.
     * @throws NoSuchElementException если деталь не найдена в базе данных.
     */
    @GetMapping
    public List<PartDto> getByFilters(@RequestParam(required = false) @Valid Integer id) {
        List<Part> parts;
        if (id != null) {
            parts = Collections.singletonList(partService.findById(id));
        } else {
            parts = partService.getAll();
        }

        List<List<HoleDto>> holesLists = parts.stream()
                .map(part -> holeMapper.toDto(holeRepository.findPartHoles(part.getId())))
                .toList();

        return partMapper.allToDto(parts, holesLists);
    }

    /**
     * Обновляет существующую деталь на основе данных DTO.
     * Преобразует DTO в сущность {@link Part} и сохраняет через сервис.
     * В сервисе пропорционально изменению размера детали меняются координаты отверстий.
     *
     * @param dto DTO для обновления детали, содержащее идентификатор и новые параметры.
     * @return DTO обновленной детали.
     * @throws NoSuchElementException если деталь не найдена в базе данных.
     */
    @PutMapping
    public PartDto update(@RequestBody @Valid PartUpdateDto dto) {
        List<HoleDto> holes = holeMapper.toDto(holeRepository.findPartHoles(dto.getId()));
        Part part = partMapper.fromDto(dto);
        Part save = partService.update(part);
        return partMapper.toDto(save, holes);
    }

    /**
     * Удаляет деталь из базы данных по её идентификатору.
     * Вместе с деталью удаляются принадлежащие ей отверстия.
     *
     * @param id Идентификатор детали, которую необходимо удалить.
     */
    @DeleteMapping
    public void delete(@RequestParam @Valid int id) {
        partService.delete(id);
    }
}