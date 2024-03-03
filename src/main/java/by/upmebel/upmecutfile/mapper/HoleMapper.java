package by.upmebel.upmecutfile.mapper;


import by.upmebel.upmecutfile.model.Hole;
import dto.HoleDto;
import dto.HoleSaveDto;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.List;


@Component
@AllArgsConstructor
public class HoleMapper {

    // Принимает DTO от контроллера и отдаёт Hole
    public Hole fromDto (HoleSaveDto dto) {
        return Hole.builder()
                .diameter(dto.getDiameter())
                .depth(dto.getDepth())
                .drillEntrySpeed(dto.getDrillEntrySpeed())
                .drillExitSpeed(dto.getDrillExitSpeed())
                .coordinateH(dto.getCoordinateH())
                .coordinateL(dto.getCoordinateL())
                .coordinateB(dto.getCoordinateB())
                .build();
    }

    // Принимает пачку DTO от контроллера и отдаёт пачку Hole
    public List<Hole> fromDto(List<HoleSaveDto> dtoList) {
        return dtoList.stream()
                .map(this::fromDto)
                .toList();
    }

    public HoleDto toDto(Hole hole) {
        return HoleDto.builder()
                .holeId(hole.getId())
                .diameter(hole.getDiameter())
                .depth(hole.getDepth())
                .drillEntrySpeed(hole.getDrillEntrySpeed())
                .drillExitSpeed(hole.getDrillExitSpeed())
                .coordinateH(hole.getCoordinateH())
                .coordinateL(hole.getCoordinateL())
                .coordinateB(hole.getCoordinateB())
                .build();
    }

    public List<HoleDto> toDto(List<Hole> holes) {
        return holes.stream()
                .map(this::toDto)
                .toList();
    }
}
