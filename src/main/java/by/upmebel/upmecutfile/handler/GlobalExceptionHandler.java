package by.upmebel.upmecutfile.handler;


import by.upmebel.upmecutfile.mapper.ExceptionMapper;
import dto.exception.ExceptionDto;
import jakarta.persistence.EntityExistsException;
import jakarta.persistence.EntityNotFoundException;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

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

    private ExceptionDto createExceptionDto(Exception exception) {
        ExceptionDto exceptionDto = dtoMapper.exceptionToDto(exception);
        log.warn(String.valueOf(exceptionDto));
        return exceptionDto;
    }
}
