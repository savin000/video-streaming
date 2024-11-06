package ru.savin.videostreaming.exception;

import lombok.extern.slf4j.Slf4j;
import org.springdoc.api.ErrorMessage;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import ru.savin.videostreaming.constant.GlobalExceptionHandlerMessageConstant;

@Slf4j
@RestControllerAdvice
public class GlobalExceptionHandler {
    @ExceptionHandler(Exception.class)
    public ResponseEntity<ErrorMessage> handleException(Exception e) {
        log.error(GlobalExceptionHandlerMessageConstant.BASE_MESSAGE, e);
        return ResponseEntity
                .status(HttpStatus.INTERNAL_SERVER_ERROR)
                .body(new ErrorMessage(GlobalExceptionHandlerMessageConstant.BASE_MESSAGE + e.getMessage()));
    }
}
