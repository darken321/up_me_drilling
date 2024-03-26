package by.upmebel.upmecutfile.web.controller.user;

import by.upmebel.upmecutfile.mapper.ExceptionMapper;
import by.upmebel.upmecutfile.mapper.PartMapper;
import by.upmebel.upmecutfile.model.Part;
import by.upmebel.upmecutfile.service.PartService;
import com.fasterxml.jackson.databind.ObjectMapper;
import dto.part.PartDto;
import dto.part.PartUpdateDto;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.context.annotation.Import;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.BDDMockito.given;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.put;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest(PartApi.class)
@Import(ExceptionMapper.class)
public class PartApiUpdateTests {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private PartService partService;

    @MockBean
    private PartMapper partMapper;

    @Autowired
    private ObjectMapper objectMapper;

    private PartUpdateDto partUpdateDto;
    private Part part;
    private PartDto partDto;

    @BeforeEach
    void setUp() {
        partUpdateDto = new PartUpdateDto();
        partUpdateDto.setId(1);
        partUpdateDto.setL(10.0);
        partUpdateDto.setB(20.0);
        partUpdateDto.setH(30.0);

        part = new Part();
        part.setId(1);
        part.setL(10.0);
        part.setB(20.0);
        part.setH(30.0);

        partDto = new PartDto();
        partDto.setId(1);
        partDto.setL(10.0);
        partDto.setB(20.0);
        partDto.setH(30.0);
        partDto.setHoles(null); // что отверстий нет
    }

    @Test
    void updatePart_ReturnsUpdatedPartDto() throws Exception {
        given(partMapper.fromDto(any(PartUpdateDto.class))).willReturn(part);
        given(partService.update(any(Part.class))).willReturn(part);
        given(partMapper.toDto(any(Part.class))).willReturn(partDto);

        mockMvc.perform(put("/api/v1/part")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(partUpdateDto)))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.id").value(partDto.getId()))
                .andExpect(jsonPath("$.l").value(partDto.getL()))
                .andExpect(jsonPath("$.b").value(partDto.getB()))
                .andExpect(jsonPath("$.h").value(partDto.getH()))
                .andExpect(jsonPath("$.holes").isEmpty());
    }
}
