package com.meli.socialmeli.exception.handler;

import com.meli.socialmeli.dto.ExceptionDTO;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.method.annotation.MethodArgumentTypeMismatchException;

@ControllerAdvice
public class MethodArgumentTypeMismatchExceptionHandler {

    @ExceptionHandler(MethodArgumentTypeMismatchException.class)
    public ResponseEntity<ExceptionDTO> defaultHandler(MethodArgumentTypeMismatchException e) {

        String error = e.getName() + " deve ser do tipo " + e.getRequiredType().getName();
        return ResponseEntity
                .status(HttpStatus.BAD_REQUEST)
                .body(new ExceptionDTO(error, HttpStatus.BAD_REQUEST.value()));
    }
}
