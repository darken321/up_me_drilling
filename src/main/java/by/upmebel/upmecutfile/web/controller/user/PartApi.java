package by.upmebel.upmecutfile.web.controller.user;


import by.upmebel.upmecutfile.mapper.HoleMapper;
import by.upmebel.upmecutfile.mapper.PartMapper;
import by.upmebel.upmecutfile.model.Part;
import by.upmebel.upmecutfile.service.HoleService;
import by.upmebel.upmecutfile.service.PartService;
import by.upmebel.upmecutfile.web.controller.user.swagger.PartApiSwagger;
import dto.hole.HoleDto;
import dto.part.PartDto;
import dto.part.PartSaveDto;
import dto.part.PartUpdateDto;
import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;

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
    private final HoleService holeService;
    private final PartMapper partMapper;
    private final HoleMapper holeMapper;


    /**
     * Получает DTO детали со списком отверстий
     *
     * @param partId Идентификатор детали, для которой требуется получить DTO.
     * @return {@link PartDto} с заполненным списком отверстий.
     * @throws NoSuchElementException если деталь с указанным идентификатором не найдена.
     */
    private PartDto getPartDtoWithHoles(int partId) {
        Part part = partService.findById(partId);
        List<HoleDto> holes = holeMapper.toDto(holeService.findPartHoles(partId));
        return partMapper.toDto(part, holes);
    }

    /**
     * Сохраняет новую деталь в базе данных на основе данных DTO.
     * Преобразует DTO в сущность {@link Part} и сохраняет через сервис.
     *
     * @param dto DTO для создания новой детали, содержащее все необходимые параметры.
     * @return DTO сохраненной детали с присвоенным идентификатором.
     */

    @PostMapping
    public PartDto save(@RequestBody @Valid PartSaveDto dto) {
        Part part = partMapper.fromDto(dto);
        Part savedPart = partService.save(part);
        return getPartDtoWithHoles(savedPart.getId());
    }

    /**
     * Возвращает список DTO деталей.
     * Если идентификатор не указан, возвращает все детали.
     *
     * @param partId Идентификатор детали для фильтрации. Необязательный параметр.
     * @return Список DTO деталей, соответствующих заданному фильтру.
     * @throws NoSuchElementException если деталь не найдена в базе данных.
     */
    @GetMapping
    public List<PartDto> getByFilters(@RequestParam(required = false) @Valid Integer partId) {
        if (partId != null) {
            // Получение одной детали по ID и её отверстий
            return List.of(getPartDtoWithHoles(partId));
        } else {
            //TODO перетащить логику из маппера сюда
            // Получение всех деталей и их отверстий
            List<Part> parts = partService.getAll();
            return partMapper.toDtoList(parts);
        }
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
        Part part = partMapper.fromDto(dto);
        Part updatedPart = partService.update(part);
        return getPartDtoWithHoles(updatedPart.getId());
    }

    /**
     * Удаляет деталь из базы данных по её идентификатору.
     * Вместе с деталью удаляются принадлежащие ей отверстия.
     *
     * @param partId Идентификатор детали, которую необходимо удалить.
     */
    @DeleteMapping
    public void delete(@RequestParam @Valid int partId) {
        partService.delete(partId);
    }
}