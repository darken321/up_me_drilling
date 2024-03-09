package by.upmebel.upmecutfile.mapper;


import by.upmebel.upmecutfile.model.Part;
import by.upmebel.upmecutfile.repository.HoleRepository;
import dto.hole.HoleDto;
import dto.part.PartDto;
import dto.part.PartSaveDto;
import dto.part.PartUpdateDto;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.List;


@Component
@AllArgsConstructor
public class PartMapper {
    private final HoleRepository holeRepository;
    private final HoleMapper holeMapper;

    // Принимает DTO от контроллера с API и отдаёт Part
    public Part fromDto(PartSaveDto dto) {
        return Part.builder()
                .l(dto.getL())
                .b(dto.getB())
                .h(dto.getH())
                .build();
    }

    public Part fromDto(PartUpdateDto dto) {
        return Part.builder()
                .id(dto.getId())
                .l(dto.getL())
                .b(dto.getB())
                .h(dto.getH())
                .build();
    }

    // Принимает пачку DTO от контроллера и отдаёт пачку Part
    public List<Part> fromDto(List<PartSaveDto> dtoList) {
        return dtoList.stream()
                .map(this::fromDto)
                .toList();
    }

    //Принимает Part и отдает Dto в API
    public PartDto toDto(Part part) {
        List<HoleDto> holes = holeMapper.toDto(holeRepository.findPartHoles(part.getId()));
        return PartDto.builder()
                .id(part.getId())
                .l(part.getL())
                .b(part.getB())
                .h(part.getH())
                .holes(holes)
                .build();
    }


    public List<PartDto> toDto(List<Part> parts) {
        return parts.stream()
                .map(this::toDto)
                .toList();
    }
}
