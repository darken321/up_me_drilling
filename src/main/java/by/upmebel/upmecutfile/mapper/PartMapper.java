package by.upmebel.upmecutfile.mapper;


import by.upmebel.upmecutfile.model.Hole;
import by.upmebel.upmecutfile.model.Part;
import by.upmebel.upmecutfile.repository.HoleRepository;
import dto.HoleDto;
import dto.HoleSaveDto;
import dto.PartDto;
import dto.PartSaveDto;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.List;


@Component
@AllArgsConstructor
public class PartMapper {
    HoleRepository holeRepository;
    HoleMapper holeMapper;

    // Принимает DTO от контроллера с API и отдаёт Part
    public Part fromDto (PartSaveDto dto) {
        return Part.builder()
                .h(dto.getH())
                .l(dto.getL())
                .b(dto.getB())
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
                .h(part.getH())
                .l(part.getL())
                .b(part.getB())
                .holes(holes)
                .build();
    }


    public List<PartDto> toDto(List<Part> parts) {
        return parts.stream()
                .map(this::toDto)
                .toList();
    }
}
