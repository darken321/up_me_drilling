package by.upmebel.upmecutfile.web.controller.user;


import by.upmebel.upmecutfile.mapper.HoleMapper;
import by.upmebel.upmecutfile.model.Hole;
import by.upmebel.upmecutfile.service.HoleService;
import dto.HoleDto;
import dto.HoleSaveDto;
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
@RequestMapping("api/v1/hole")
public class HoleApi {
    private final HoleService holeService;
    private final HoleMapper holeMapper;

    @GetMapping
    public List<HoleDto> getByFilters(@RequestParam(required = false) Integer id) {
        if (id != null) {
            return holeMapper.toDto(Collections.singletonList(holeService.findById(id)));
        }
        return holeMapper.toDto(holeService.getAll());
    }
}
