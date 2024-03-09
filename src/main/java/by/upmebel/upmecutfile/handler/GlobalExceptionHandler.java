package by.upmebel.upmecutfile.handler;


import by.upmebel.upmecutfile.mapper.ExceptionMapper;
import dto.exception.ExceptionDto;
import jakarta.persistence.EntityExistsException;
import jakarta.persistence.EntityNotFoundException;
import jakarta.validation.ConstraintViolationException;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.time.ZonedDateTime;
import java.util.UUID;

/**
 * Обработчик ошибок
 */
@RestControllerAdvice
@AllArgsConstructor
@Slf4j
public class GlobalExceptionHandler {
    ExceptionMapper dtoMapper;

    @ExceptionHandler(Exception.class)
    @ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
    public ExceptionDto handleException(Exception exception) {
        log.error(exception.getMessage(), exception);
        return createExceptionDto(exception);
    }

    @ExceptionHandler(EntityNotFoundException.class)
    @ResponseStatus(HttpStatus.NOT_FOUND)
    public ExceptionDto handleUserNotFoundException(EntityNotFoundException exception) {
        return createExceptionDto(exception);
    }

    @ExceptionHandler(EntityExistsException.class)
    @ResponseStatus(HttpStatus.CONFLICT)
    public ExceptionDto handleUserExistException(EntityExistsException exception) {
        return createExceptionDto(exception);
    }

    @ExceptionHandler(ConstraintViolationException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public ExceptionDto handleValidationException(ConstraintViolationException exception) {

        ExceptionDto build = ExceptionDto.builder()
                .message(exception.getConstraintViolations().stream().toList().get(0).getMessage())
                .uuid(UUID.randomUUID())
                .type(exception.getClass().getSimpleName())
                .exceptionServerTime(ZonedDateTime.now())
                .build();
        return build;
    }

    private ExceptionDto createExceptionDto(Exception exception) {
        ExceptionDto exceptionDto = dtoMapper.exceptionToDto(exception);
        log.warn(String.valueOf(exceptionDto));
        return exceptionDto;
    }
}
