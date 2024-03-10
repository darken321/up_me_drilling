package by.upmebel.upmecutfile.web.controller.user;


import by.upmebel.upmecutfile.mapper.PartMapper;
import by.upmebel.upmecutfile.model.Part;
import by.upmebel.upmecutfile.service.PartService;
import by.upmebel.upmecutfile.web.controller.user.swagger.PartApiSwagger;
import dto.part.PartDto;
import dto.part.PartSaveDto;
import dto.part.PartUpdateDto;
import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;

import java.util.Collections;
import java.util.List;

@Slf4j
@RestController
@AllArgsConstructor
@RequestMapping("api/v1/part")
public class PartApi implements PartApiSwagger {
    private final PartService partService;
    private final PartMapper partMapper;

    @PostMapping
    public PartDto save(@RequestBody @Valid PartSaveDto dto) {
        Part part = partMapper.fromDto(dto);
        Part save = partService.save(part);
        return partMapper.toDto(save);
    }

    @GetMapping
    public List<PartDto> getByFilters(@RequestParam(required = false) @Valid Integer id) {
        if (id != null) {
            return partMapper.toDto(Collections.singletonList(partService.findById(id)));
        }
        return partMapper.toDto(partService.getAll());
    }

    @PutMapping
    public PartDto update(@RequestBody @Valid PartUpdateDto dto) {
        Part part = partMapper.fromDto(dto);
        Part save = partService.update(part);
        return partMapper.toDto(save);
    }

    @DeleteMapping
    public void delete(@RequestParam @Valid int id) {
        partService.delete(id);
    }
}