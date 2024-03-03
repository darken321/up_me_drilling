package by.upmebel.upmecutfile.web.controller.user;


import by.upmebel.upmecutfile.mapper.PartMapper;
import by.upmebel.upmecutfile.service.PartService;
import dto.PartDto;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

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

    @GetMapping
    public List<PartDto> getByFilters(@RequestParam(required = false) Integer id) {
        if (id != null) {
            return partMapper.toDto(Collections.singletonList(partService.findById(id)));
        }
        return partMapper.toDto(partService.getAll());
    }
}
