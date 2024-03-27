package by.upmebel.upmecutfile.mapper;


import by.upmebel.upmecutfile.dto.hole.HoleDto;
import by.upmebel.upmecutfile.dto.hole.HoleSaveDto;
import by.upmebel.upmecutfile.dto.hole.HoleUpdateDto;
import by.upmebel.upmecutfile.model.Coordinates;
import by.upmebel.upmecutfile.model.Hole;
import by.upmebel.upmecutfile.model.Part;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

import java.util.List;

@Slf4j
@Component
@AllArgsConstructor
public class HoleMapper {

    public Hole fromDto(HoleUpdateDto dto, Coordinates coordinates, Part part) {
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

    public Hole fromDto(HoleSaveDto dto, Coordinates coordinates, Part part) {
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
