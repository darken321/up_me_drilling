package by.upmebel.upmecutfile.web.controller.user;


import by.upmebel.upmecutfile.mapper.HoleMapper;
import by.upmebel.upmecutfile.model.Hole;
import by.upmebel.upmecutfile.service.HoleService;
import dto.HoleDto;
import dto.HoleSaveDto;
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

    //CREATE
    @PostMapping
    public HoleDto save(@RequestBody HoleSaveDto dto) {
        Hole hole = holeMapper.fromDto(dto);

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

    //TODO UPDATE

    //DELETE
    @DeleteMapping
    public void delete(@RequestParam Integer id) {
        holeService.delete(id);
    }

}
/*
Тело Post запроса имеет вид:
{
        "part_id": "3",
        "diameter": "10",
        "depth": "5",
        "drillEntrySpeed": "100",
        "drillExitSpeed": "50",
        "lPatterns": [
        {"variable": "L", "operator": "/", "value": 2},
        {"variable": "L", "operator": "/", "value": 1},
        {"variable": "H", "operator": "-", "value": 1},
        {"variable": "", "operator": "", "value": 100}
        ],
        "bPatterns": [
        {"variable": "B", "operator": "*", "value": 2},
        {"variable": "H", "operator": "-", "value": 1}
        ],
        "hPatterns": [
        {"variable": "H", "operator": "-", "value": 1},
        {"variable": "", "operator": "", "value": 30}
        ]
        }
*/
