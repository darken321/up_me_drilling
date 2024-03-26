package by.upmebel.upmecutfile.mapper;


import by.upmebel.upmecutfile.model.Coordinates;
import by.upmebel.upmecutfile.model.Hole;
import by.upmebel.upmecutfile.model.Part;
import by.upmebel.upmecutfile.repository.PartRepository;
import by.upmebel.upmecutfile.dto.hole.HoleDto;
import by.upmebel.upmecutfile.dto.hole.HoleSaveDto;
import by.upmebel.upmecutfile.dto.hole.HoleUpdateDto;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Component;

import java.util.List;

@Slf4j
@Component
@AllArgsConstructor
public class HoleMapper {
    private final PartRepository partRepository;
    private final ModelMapper modelMapper;


    public Hole fromDto(HoleUpdateDto dto, Coordinates coordinates) {
        Part part = partRepository.getReferenceById(dto.getPartId());
        Hole hole = modelMapper.map(dto, Hole.class);
        hole.setPart(part);
        hole.setCoordinateL(coordinates.getL());
        hole.setCoordinateB(coordinates.getB());
        hole.setCoordinateH(coordinates.getH());
        return hole;
    }

    public Hole fromDto(HoleSaveDto dto, Coordinates coordinates) {
        Part part = partRepository.getReferenceById(dto.getPartId());
        Hole hole = modelMapper.map(dto, Hole.class);
        hole.setPart(part);
        hole.setCoordinateL(coordinates.getL());
        hole.setCoordinateB(coordinates.getB());
        hole.setCoordinateH(coordinates.getH());
        return hole;
    }


    public HoleDto toDto(Hole hole) {
        HoleDto holeDto = modelMapper.map(hole, HoleDto.class);
        holeDto.setHoleId(hole.getId());
        return holeDto;
    }

    public List<HoleDto> toDto(List<Hole> holes) {
        return holes.stream()
                .map(this::toDto)
                .toList();
    }
}
