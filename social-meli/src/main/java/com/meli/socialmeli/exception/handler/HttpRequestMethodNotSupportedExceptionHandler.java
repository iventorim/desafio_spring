package com.meli.socialmeli.exception.handler;

import com.meli.socialmeli.dto.ExceptionDTO;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.HttpRequestMethodNotSupportedException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
public class HttpRequestMethodNotSupportedExceptionHandler {

    @ExceptionHandler(HttpRequestMethodNotSupportedException.class)
    public ResponseEntity<ExceptionDTO> defaultHandler(HttpRequestMethodNotSupportedException e){

        String msg = e.getMethod() + " não suportado para essa rota!";

        return ResponseEntity.status(HttpStatus.METHOD_NOT_ALLOWED.value()).body( new ExceptionDTO(msg, HttpStatus.METHOD_NOT_ALLOWED.value()));
    }
}
