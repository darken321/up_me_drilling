package by.upmebel.upmecutfile.mapper;


import by.upmebel.upmecutfile.model.Part;
import by.upmebel.upmecutfile.repository.HoleRepository;
import by.upmebel.upmecutfile.service.HoleService;
import dto.hole.HoleDto;
import dto.part.PartDto;
import dto.part.PartSaveDto;
import dto.part.PartUpdateDto;
import lombok.AllArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.IntStream;


@Component
@AllArgsConstructor
public class PartMapper {

    private final ModelMapper modelMapper;
    private final HoleMapper holeMapper;
    private final HoleService holeService;

    public Part fromDto(PartSaveDto partSaveDto) {
        return modelMapper.map(partSaveDto, Part.class);
    }

    public Part fromDto(PartUpdateDto partUpdateDto) {
        return modelMapper.map(partUpdateDto, Part.class);
    }

//    public List<Part> fromDto(List<PartSaveDto> dtoList) {
//        return dtoList.stream()
//                .map(this::fromDto)
//                .toList();
//    }

    public PartDto toDto(Part part, List<HoleDto> holes) {
        PartDto partDto = modelMapper.map(part, PartDto.class);
        partDto.setHoles(holes);
        return partDto;
    }


    public List<PartDto> toDto(List<Part> parts, List<List<HoleDto>> holesLists) {
        return IntStream.range(0, parts.size())
                .mapToObj(i -> toDto(parts.get(i), holesLists.get(i)))
                .toList();
    }

    public List<PartDto> toDtoList(List<Part> parts) {
        return parts.stream()
                .map(part -> toDto(part, holeMapper.toDto(holeService.findPartHoles(part.getId()))))
                .toList();
    }
}
