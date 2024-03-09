package by.upmebel.upmecutfile.mapper;


import by.upmebel.upmecutfile.model.Coordinates;
import by.upmebel.upmecutfile.model.Hole;
import by.upmebel.upmecutfile.model.Part;
import by.upmebel.upmecutfile.repository.PartRepository;
import by.upmebel.upmecutfile.service.PartService;
import by.upmebel.upmecutfile.utils.PatternConverter;
import dto.hole.HoleDto;
import dto.hole.HoleSaveDto;
import dto.hole.HoleUpdateDto;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

import java.util.List;

@Slf4j
@Component
@AllArgsConstructor
public class HoleMapper {
    private final PartRepository partRepository;
    private final PatternConverter patternConverter;
    private final PartService partService;

    public Hole fromDto(HoleUpdateDto dto, Coordinates coordinates) {
        Part part = partRepository.getReferenceById(dto.getPart_id());

        return Hole.builder()
                .id(dto.getId())
                .part(part)
                .diameter(dto.getDiameter())
                .depth(dto.getDepth())
                .drillEntrySpeed(dto.getDrillEntrySpeed())
                .drillExitSpeed(dto.getDrillExitSpeed())
                .coordinateL(coordinates.getL())
                .coordinateB(coordinates.getB())
                .coordinateH(coordinates.getH())
                .build();
    }
    public Hole fromDto(HoleSaveDto dto, Coordinates coordinates) {
        Part part = partRepository.getReferenceById(dto.getPart_id());

        return Hole.builder()
                .part(part)
                .diameter(dto.getDiameter())
                .depth(dto.getDepth())
                .drillEntrySpeed(dto.getDrillEntrySpeed())
                .drillExitSpeed(dto.getDrillExitSpeed())
                .coordinateL(coordinates.getL())
                .coordinateB(coordinates.getB())
                .coordinateH(coordinates.getH())
                .build();
    }

    // Принимает пачку DTO от контроллера и отдаёт пачку Hole
//    public List<Hole> fromDto(List<HoleSaveDto> dtoList) {
//        return dtoList.stream()
//                .map(this::fromDto)
//                .toList();
//    }

    public HoleDto toDto(Hole hole) {
        return HoleDto.builder()
                .holeId(hole.getId())
                .partId(hole.getPart().getId())
                .diameter(hole.getDiameter())
                .depth(hole.getDepth())
                .drillEntrySpeed(hole.getDrillEntrySpeed())
                .drillExitSpeed(hole.getDrillExitSpeed())
                .coordinateL(hole.getCoordinateL())
                .coordinateB(hole.getCoordinateB())
                .coordinateH(hole.getCoordinateH())
                .build();
    }

    public List<HoleDto> toDto(List<Hole> holes) {
        return holes.stream()
                .map(this::toDto)
                .toList();
    }
}
