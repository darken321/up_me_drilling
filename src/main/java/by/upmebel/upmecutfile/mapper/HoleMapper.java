package by.upmebel.upmecutfile.mapper;


import by.upmebel.upmecutfile.model.Coordinates;
import by.upmebel.upmecutfile.model.Hole;
import by.upmebel.upmecutfile.model.Part;
import dto.hole.HoleDto;
import dto.hole.HoleSaveDto;
import dto.hole.HoleUpdateDto;
import lombok.AllArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
@AllArgsConstructor
public class HoleMapper {

    private final ModelMapper modelMapper;

    public Hole fromDto(HoleUpdateDto dto, Coordinates coordinates, Part part) {
        Hole hole = modelMapper.map(dto, Hole.class);
        hole.setCoordinates(coordinates);
        hole.setPart(part);
        return hole;
    }

    public Hole fromDto(HoleSaveDto dto, Coordinates coordinates, Part part) {
        Hole hole = modelMapper.map(dto, Hole.class);
        hole.setCoordinates(coordinates);
        hole.setPart(part);
        return hole;
    }

    public HoleDto toDto(Hole hole) {
        return modelMapper.map(hole, HoleDto.class);
    }

    public List<HoleDto> toDto(List<Hole> holes) {
        return holes.stream()
                .map(this::toDto)
                .toList();
    }
}
