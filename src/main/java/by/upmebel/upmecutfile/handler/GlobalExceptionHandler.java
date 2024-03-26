package by.upmebel.upmecutfile.handler;


import by.upmebel.upmecutfile.mapper.ExceptionMapper;
import by.upmebel.upmecutfile.dto.exception.ExceptionDto;
import jakarta.persistence.EntityExistsException;
import jakarta.validation.ConstraintViolationException;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.method.annotation.MethodArgumentTypeMismatchException;

import java.time.ZonedDateTime;
import java.util.NoSuchElementException;
import java.util.UUID;

/**
 * Обработчик ошибок
 */
@RestControllerAdvice
@AllArgsConstructor
public class GlobalExceptionHandler {
    ExceptionMapper dtoMapper;

    @ExceptionHandler(Exception.class)
    @ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
    public ExceptionDto handleException(Exception exception) {
        return createExceptionDto(exception);
    }

    @ExceptionHandler(NoSuchElementException.class)
    @ResponseStatus(HttpStatus.NOT_FOUND)
    public ExceptionDto handleNotFoundException(NoSuchElementException exception) {
        return createExceptionDto(exception);
    }

    @ExceptionHandler(EntityExistsException.class)
    @ResponseStatus(HttpStatus.CONFLICT)
    public ExceptionDto handleExistException(EntityExistsException exception) {
        return createExceptionDto(exception);
    }

    @ExceptionHandler(HttpMessageNotReadableException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public ExceptionDto handleNotReadableException(HttpMessageNotReadableException exception) {
        return ExceptionDto.builder()
                .message("Неверный формат входных данных, невозможно преобразовать входные данные в число.")
                .uuid(UUID.randomUUID())
                .type(exception.getClass().getSimpleName())
                .exceptionServerTime(ZonedDateTime.now())
                .build();
    }

    @ExceptionHandler(ConstraintViolationException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public ExceptionDto handleValidationException(ConstraintViolationException exception) {

        return ExceptionDto.builder()
                .message(exception.getConstraintViolations().stream().toList().get(0).getMessage())
                .uuid(UUID.randomUUID())
                .type(exception.getClass().getSimpleName())
                .exceptionServerTime(ZonedDateTime.now())
                .build();
    }

    @ExceptionHandler(MethodArgumentNotValidException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public ExceptionDto handleNotValidArgumentException(MethodArgumentNotValidException exception) {
        return ExceptionDto.builder()
                .message(exception.getBindingResult().getAllErrors().get(0).getDefaultMessage())
                .uuid(UUID.randomUUID())
                .type(exception.getClass().getSimpleName())
                .exceptionServerTime(ZonedDateTime.now())
                .build();
    }

    @ExceptionHandler(MethodArgumentTypeMismatchException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public ExceptionDto handleTypeMismatchException(MethodArgumentTypeMismatchException exception) {
        ExceptionDto build = ExceptionDto.builder()
                .message("Неверный параметр " + exception.getName() + " равный " + exception.getValue())
                .uuid(UUID.randomUUID())
                .type(exception.getClass().getSimpleName())
                .exceptionServerTime(ZonedDateTime.now())
                .build();
        return build;
    }

    private ExceptionDto createExceptionDto(Exception exception) {
        ExceptionDto exceptionDto = dtoMapper.exceptionToDto(exception);
        return exceptionDto;
    }
}
