package by.upmebel.upmecutfile.mapper;


import by.upmebel.upmecutfile.dto.hole.HoleDto;
import by.upmebel.upmecutfile.dto.part.PartDto;
import by.upmebel.upmecutfile.dto.part.PartSaveDto;
import by.upmebel.upmecutfile.dto.part.PartUpdateDto;
import by.upmebel.upmecutfile.model.Part;
import lombok.AllArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;


@Component
@AllArgsConstructor
public class PartMapper {

    private final ModelMapper modelMapper;

    public Part fromDto(PartSaveDto dto) {
        return modelMapper.map(dto, Part.class);
    }

    public Part fromDto(PartUpdateDto dto) {
        return modelMapper.map(dto, Part.class);
    }

    public List<Part> fromDto(List<PartSaveDto> dtoList) {
        return dtoList.stream()
                .map(this::fromDto)
                .toList();
    }

    public PartDto toDto(Part part, List<HoleDto> holes) {
        return PartDto.builder()
                .id(part.getPartId())
                .l(part.getL())
                .b(part.getB())
                .h(part.getH())
                .holes(holes)
                .build();
    }


    public List<PartDto> allToDto(List<Part> parts, List<List<HoleDto>> holesLists) {
        List<PartDto> partDtos = new ArrayList<>();
        for (int i = 0; i < parts.size(); i++) {
            Part part = parts.get(i);
            List<HoleDto> holes = holesLists.get(i);
            partDtos.add(toDto(part, holes));
        }
        return partDtos;
    }
}
