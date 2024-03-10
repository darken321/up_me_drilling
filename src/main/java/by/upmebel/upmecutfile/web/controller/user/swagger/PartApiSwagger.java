package by.upmebel.upmecutfile.web.controller.user.swagger;

import dto.part.PartDto;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.media.ArraySchema;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import org.springframework.http.MediaType;

import java.util.List;

@Tag(name = "API деталей")
public interface PartApiSwagger {

    @Operation(summary = "Получить список деталей ")
//так нужно описать каждый ответ 200, 404 и прочие
    @ApiResponse(responseCode = "200", description = "Детали получены успешно",
                 content = @Content(mediaType = MediaType.APPLICATION_JSON_VALUE,
                 array = @ArraySchema(schema = @Schema(implementation = PartDto.class))))
    List<PartDto> getByFilters(@Valid @Parameter(description = "Id детали", required = false) Integer id);
}