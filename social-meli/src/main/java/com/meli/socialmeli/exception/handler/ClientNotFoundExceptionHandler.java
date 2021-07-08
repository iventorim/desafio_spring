package com.meli.socialmeli.exception.handler;

import com.meli.socialmeli.dto.ExceptionDTO;
import com.meli.socialmeli.exception.ClientNotFoundException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
public class ClientNotFoundExceptionHandler {

    @ExceptionHandler(ClientNotFoundException.class)
    public ResponseEntity<ExceptionDTO> defaultHandler(ClientNotFoundException e) {
        return ResponseEntity
                .status(HttpStatus.NOT_FOUND)
                .body(new ExceptionDTO(e.getMessage(), HttpStatus.NOT_FOUND.value()));
    }
}
