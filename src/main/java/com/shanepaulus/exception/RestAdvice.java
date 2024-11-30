package com.shanepaulus.exception;

import com.shanepaulus.model.ErrorMessage;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.time.LocalDateTime;

/**
 * @author Shane Paulus
 * <p>
 * Date Created : 23-May-2023.
 */

@RestControllerAdvice
@Slf4j
public class RestAdvice {
    @ExceptionHandler(UserNotFoundException.class)
    @ResponseStatus(HttpStatus.NOT_FOUND)
    public ErrorMessage handleUserNotFoundException(UserNotFoundException exc) {
        log.error(exc.getMessage());
        return new ErrorMessage(exc.getMessage(), LocalDateTime.now());
    }

}
