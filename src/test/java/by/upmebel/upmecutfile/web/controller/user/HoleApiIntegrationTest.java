package by.upmebel.upmecutfile.web.controller.user;

import by.upmebel.upmecutfile.dto.SizePattern;
import by.upmebel.upmecutfile.dto.hole.HoleSaveDto;
import by.upmebel.upmecutfile.model.Part;
import by.upmebel.upmecutfile.repository.HoleRepository;
import by.upmebel.upmecutfile.repository.PartRepository;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.jayway.jsonpath.JsonPath;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertNotEquals;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@AutoConfigureMockMvc
public class HoleApiIntegrationTest {

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private ObjectMapper objectMapper;

    @Autowired
    private PartRepository partRepository;

    @Autowired
    private HoleRepository holeRepository;

    private Part part;

    @BeforeEach
    public void setup() {
        holeRepository.deleteAll();
        partRepository.deleteAll();
        part = partRepository.save(Part.builder()
                .l(100.0)
                .b(50.0)
                .h(10.0)
                .build());
    }

    @Test
    public void whenSaveTwoIdenticalHoles_thenTheyHaveDifferentHoleIds() throws Exception {
        List<SizePattern> lPatterns = new ArrayList<>();
        List<SizePattern> bPatterns = new ArrayList<>();
        List<SizePattern> hPatterns = new ArrayList<>();

        SizePattern lPattern = SizePattern.builder().variable("L").operator("+").value(5.0).build();
        lPatterns.add(lPattern);

        SizePattern bPattern = SizePattern.builder().variable("B").operator("+").value(2.0).build();
        bPatterns.add(bPattern);

        SizePattern hPattern = SizePattern.builder().variable("H").operator("*").value(1.5).build();
        hPatterns.add(hPattern);

        HoleSaveDto hole1 = HoleSaveDto.builder()
                .partId(part.getPartId())
                .diameter(5.0)
                .depth(2.0)
                .drillEntrySpeed(1500.0)
                .drillExitSpeed(1500.0)
                .lPatterns(lPatterns)
                .bPatterns(bPatterns)
                .hPatterns(hPatterns)
                .build();

        String hole1Response = mockMvc.perform(post("/api/v1/hole")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(hole1)))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.holeId").exists())
                .andReturn().getResponse().getContentAsString();

        int hole1Id = JsonPath.parse(hole1Response).read("$.holeId");

        String hole2Response = mockMvc.perform(post("/api/v1/hole")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(hole1)))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.holeId").exists())
                .andReturn().getResponse().getContentAsString();

        int hole2Id = JsonPath.parse(hole2Response).read("$.holeId");

        assertNotEquals(hole1Id, hole2Id, "Два сохраненных отверстия должны иметь разные holeId");
    }
}