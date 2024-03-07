package by.upmebel.upmecutfile.web.controller.user;


import by.upmebel.upmecutfile.mapper.PartMapper;
import by.upmebel.upmecutfile.model.Part;
import by.upmebel.upmecutfile.service.PartService;
import dto.PartDto;
import dto.PartSaveDto;
import dto.PartUpdateDto;
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
@RequestMapping("api/v1/part")
public class PartApi {
    private final PartService partService;
    private final PartMapper partMapper;

    //CREATE
    @PostMapping
    public PartDto save(@RequestBody PartSaveDto dto) {
        Part part = partMapper.fromDto(dto);
        Part save = partService.save(part);
        return partMapper.toDto(save);
    }

    //READ одна или все детали
    @GetMapping
    public List<PartDto> getByFilters(@RequestParam(required = false) Integer id) {
        if (id != null) {
            return partMapper.toDto(Collections.singletonList(partService.findById(id)));
        }
        return partMapper.toDto(partService.getAll());
    }

    @PutMapping
    public PartDto update(@RequestBody PartUpdateDto dto) {
        Part part = partMapper.fromDto(dto);
        Part save = partService.update(part);
        return partMapper.toDto(save);
    }

    //DELETE
    @DeleteMapping
    public void delete(@RequestParam Integer id) {
        partService.delete(id);
    }
}
