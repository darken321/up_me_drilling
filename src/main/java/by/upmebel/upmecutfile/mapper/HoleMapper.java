package by.upmebel.upmecutfile.mapper;


import by.upmebel.upmecutfile.model.Hole;
import by.upmebel.upmecutfile.model.Part;
import by.upmebel.upmecutfile.projection.PartSizeInfo;
import by.upmebel.upmecutfile.repository.PartRepository;
import by.upmebel.upmecutfile.utils.PatternConverter;
import dto.HoleDto;
import dto.HoleSaveDto;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

import java.util.List;

@Slf4j
@Component
@AllArgsConstructor
public class HoleMapper {
    PartRepository partRepository;
    PatternConverter patternConverter;

    // Принимает DTO от контроллера и отдаёт Hole
    public Hole fromDto (HoleSaveDto dto) {
        Part part = partRepository.getReferenceById(dto.getPart_id());
        Object sizes = partRepository.getSizesById(dto.getPart_id());
        Double sizeL = (Double)((Object[]) sizes)[0];
        Double sizeB = (Double)((Object[]) sizes)[1];
        Double sizeH = (Double)((Object[]) sizes)[2];

        return Hole.builder()
                .part(part)
                .diameter(dto.getDiameter())
                .depth(dto.getDepth())
                .drillEntrySpeed(dto.getDrillEntrySpeed())
                .drillExitSpeed(dto.getDrillExitSpeed())
                .coordinateL(patternConverter.convert(dto.getLPatterns(),sizeL,sizeB,sizeH))
                .coordinateB(patternConverter.convert(dto.getBPatterns(),sizeL,sizeB,sizeH))
                .coordinateH(patternConverter.convert(dto.getHPatterns(),sizeL,sizeB,sizeH))
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
