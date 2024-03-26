package by.upmebel.upmecutfile.web.controller.user;

import by.upmebel.upmecutfile.mapper.ExceptionMapper;
import by.upmebel.upmecutfile.mapper.PartMapper;
import by.upmebel.upmecutfile.model.Part;
import by.upmebel.upmecutfile.service.PartService;
import com.fasterxml.jackson.databind.ObjectMapper;
import by.upmebel.upmecutfile.dto.part.PartDto;
import by.upmebel.upmecutfile.dto.part.PartSaveDto;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.context.annotation.Import;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import java.util.Arrays;
import java.util.List;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.verify;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest(PartApi.class)
@Import(ExceptionMapper.class)
public class PartApiTests {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private PartService partService;

    @MockBean
    private PartMapper partMapper;

    @Autowired
    private ObjectMapper objectMapper;

    private PartSaveDto partSaveDto;
    private Part part;
    private PartDto partDto;

    @BeforeEach
    void setUp() {
        partSaveDto = PartSaveDto.builder()
                .l(10.0)
                .b(20.0)
                .h(30.0)
                .build();

        part = Part.builder()
                .id(1)
                .l(10.0)
                .b(20.0)
                .h(30.0)
                .build();

        partDto = PartDto.builder()
                .id(1)
                .l(10.0)
                .b(20.0)
                .h(30.0)
                .holes(null)
                .build();
    }

    @Test
    void savePart_ReturnsPartDto() throws Exception {
        given(partMapper.fromDto(any(PartSaveDto.class))).willReturn(part);
        given(partService.save(any(Part.class))).willReturn(part);
        given(partMapper.toDto(any(Part.class))).willReturn(partDto);

        mockMvc.perform(post("/api/v1/part")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(partSaveDto)))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.id").value(partDto.getId()))
                .andExpect(jsonPath("$.l").value(partDto.getL()))
                .andExpect(jsonPath("$.b").value(partDto.getB()))
                .andExpect(jsonPath("$.h").value(partDto.getH()))
                .andExpect(jsonPath("$.holes").isEmpty());

        verify(partMapper).fromDto(eq(partSaveDto));
        verify(partService).save(eq(part));
        verify(partMapper).toDto(eq(part));
    }

    @Test
    void testGetByFiltersWhenIdIsNullThenReturnPartDtoList() throws Exception {
        given(partService.getAll()).willReturn(Arrays.asList(part, part));
        given(partMapper.toDto(any(List.class))).willReturn(Arrays.asList(partDto, partDto));

        mockMvc.perform(get("/api/v1/part"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$[0].id").value(partDto.getId()))
                .andExpect(jsonPath("$[0].l").value(partDto.getL()))
                .andExpect(jsonPath("$[0].b").value(partDto.getB()))
                .andExpect(jsonPath("$[0].h").value(partDto.getH()))
                .andExpect(jsonPath("$[0].holes").isEmpty())
                .andExpect(jsonPath("$[1].id").value(partDto.getId()))
                .andExpect(jsonPath("$[1].l").value(partDto.getL()))
                .andExpect(jsonPath("$[1].b").value(partDto.getB()))
                .andExpect(jsonPath("$[1].h").value(partDto.getH()))
                .andExpect(jsonPath("$[1].holes").isEmpty());

        verify(partService).getAll();
        verify(partMapper).toDto(eq(Arrays.asList(part, part)));
    }

    @Test
    void testDeleteWhenValidIdThenReturnEmptyBodyAndStatusOk() throws Exception {
        mockMvc.perform(delete("/api/v1/part")
                        .param("id", "1"))
                .andExpect(status().isOk());

        verify(partService).delete(eq(1));
    }
}