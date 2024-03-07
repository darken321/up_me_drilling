package by.upmebel.upmecutfile.web.controller.user;


import by.upmebel.upmecutfile.mapper.HoleMapper;
import by.upmebel.upmecutfile.model.Coordinates;
import by.upmebel.upmecutfile.model.Hole;
import by.upmebel.upmecutfile.projection.PartSize;
import by.upmebel.upmecutfile.repository.PartRepository;
import by.upmebel.upmecutfile.service.HoleService;
import by.upmebel.upmecutfile.utils.PatternConverter;
import dto.HoleDto;
import dto.HoleSaveDto;
import dto.HoleUpdateDto;
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
    PartRepository partRepository;
    PatternConverter patternConverter;

    //CREATE
    @PostMapping
    public HoleDto save(@RequestBody HoleSaveDto dto) {
        PartSize sizes = partRepository.getSizesById(dto.getPart_id());

        Coordinates coordinates = new Coordinates();
        coordinates.setL(patternConverter.convert(dto.getLPatterns(),sizes));
        coordinates.setB(patternConverter.convert(dto.getBPatterns(),sizes));
        coordinates.setH(patternConverter.convert(dto.getHPatterns(),sizes));

        Hole hole = holeMapper.fromDto(dto, coordinates);

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
        PartSize sizes = partRepository.getSizesById(dto.getPart_id());

        Coordinates coordinates = new Coordinates();
        coordinates.setL(patternConverter.convert(dto.getLPatterns(),sizes));
        coordinates.setB(patternConverter.convert(dto.getBPatterns(),sizes));
        coordinates.setH(patternConverter.convert(dto.getHPatterns(),sizes));

        Hole hole = holeMapper.fromDto(dto, coordinates);

        Hole save = holeService.update(hole);

        return holeMapper.toDto(save);
    }

    //DELETE
    @DeleteMapping
    public void delete(@RequestParam Integer id) {
        holeService.delete(id);
    }

}