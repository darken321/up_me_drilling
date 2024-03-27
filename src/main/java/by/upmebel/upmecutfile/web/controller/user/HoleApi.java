package by.upmebel.upmecutfile.web.controller.user;


import by.upmebel.upmecutfile.mapper.HoleMapper;
import by.upmebel.upmecutfile.model.Coordinates;
import by.upmebel.upmecutfile.model.Hole;
import by.upmebel.upmecutfile.model.Part;
import by.upmebel.upmecutfile.projection.PartSize;
import by.upmebel.upmecutfile.repository.PartRepository;
import by.upmebel.upmecutfile.service.HoleService;
import by.upmebel.upmecutfile.utils.PatternConverter;
import by.upmebel.upmecutfile.dto.hole.HoleDto;
import by.upmebel.upmecutfile.dto.hole.HoleSaveDto;
import by.upmebel.upmecutfile.dto.hole.HoleUpdateDto;
import jakarta.persistence.EntityNotFoundException;
import jakarta.validation.Valid;
import jakarta.validation.constraints.NotNull;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;

import java.util.Collections;
import java.util.List;

/**
 * CRUD REST API контроллер для управления данными об отверстиях в деталях для объектов {@link Hole}.
 */

@Slf4j
@RestController
@RequiredArgsConstructor
@RequestMapping("api/v1/hole")
public class HoleApi {
    private final HoleService holeService;
    private final HoleMapper holeMapper;
    private final PartRepository partRepository;
    private final PatternConverter patternConverter;

    /**
     * Сохраняет новое отверстие в базе данных на основе предоставленных данных DTO.
     * Вычисляет координаты отверстия с использованием {@link PatternConverter} и размеров детали.
     *
     * @param dto DTO для создания нового отверстия, содержащее все необходимые параметры.
     * @return DTO сохраненного отверстия с присвоенным идентификатором.
     * @throws EntityNotFoundException если связанная деталь не найдена в базе данных.
     */

    @PostMapping
    public HoleDto save(@RequestBody @Valid HoleSaveDto dto) {
        Part part = partRepository.getReferenceById(dto.getPartId());
        Hole hole = holeMapper.fromDto(dto, getCoordinates(dto), part);
        Hole save = holeService.save(hole);
        return holeMapper.toDto(save);
    }

    /**
     * Возвращает список DTO отверстий. Поддерживает фильтрацию по идентификатору детали.
     * Если идентификатор не указан, возвращает все отверстия.
     *
     * @param id Идентификатор детали для фильтрации отверстий. Необязательный параметр.
     * @return Список DTO отверстий, соответствующих заданному фильтру.
     * @throws EntityNotFoundException если отверстие не найдено в базе данных
     */

    @GetMapping
    public List<HoleDto> getByFilters(@RequestParam(required = false) @Valid Integer id) {
        if (id != null) {
            return holeMapper.toDto(Collections.singletonList(holeService.findById(id)));
        }
        return holeMapper.toDto(holeService.getAll());
    }

    /**
     * Обновляет существующее отверстие на основе предоставленных данных DTO.
     * Аналогично методу сохранения, вычисляет координаты отверстия для обновленных данных.
     *
     * @param dto DTO для обновления отверстия, содержащее идентификатор и новые параметры.
     * @return DTO обновленного отверстия.
     * @throws EntityNotFoundException если связанная деталь или само отверстие не найдены в базе данных.
     */
    @PutMapping
    public HoleDto update(@RequestBody @Valid HoleUpdateDto dto) {
        Part part = partRepository.getReferenceById(dto.getPartId());
        Hole hole = holeMapper.fromDto(dto, getCoordinates(dto), part);
        Hole save = holeService.update(hole);
        return holeMapper.toDto(save);
    }


    /**
     * Удаляет отверстие из базы данных по его идентификатору.
     *
     * @param id Идентификатор отверстия, которое необходимо удалить.
     */
    @DeleteMapping
    public void delete(@RequestParam @Valid Integer id) {
        holeService.delete(id);
    }

    /**
     * Вычисляет координаты отверстия на основе размеров детали и заданных паттернов.
     * Используется в методах сохранения и обновления отверстий.
     *
     * @param dto DTO отверстия, содержащее паттерны для вычисления координат.
     * @return Три вычисленные координаты отверстия Coordinates
     * @throws EntityNotFoundException если связанная деталь не найдена в базе данных.
     */
    private Coordinates getCoordinates(@NotNull @Valid HoleSaveDto dto) {
        if (!partRepository.existsById(dto.getPartId())) {
            throw new EntityNotFoundException("Детали с id " + dto.getPartId() + " нет в базе данных.");
        }

        PartSize sizes = partRepository.getSizesById(dto.getPartId());

        return Coordinates.builder()
                .l(patternConverter.convert(dto.getLPatterns(), sizes))
                .b(patternConverter.convert(dto.getBPatterns(), sizes))
                .h(patternConverter.convert(dto.getHPatterns(), sizes))
                .build();
    }
}