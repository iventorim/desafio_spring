package com.meli.socialmeli.exception.handler;

import com.meli.socialmeli.dto.ExceptionDTO;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import javax.persistence.EntityNotFoundException;

@ControllerAdvice
public class EntityNotFoundHandler {

    @ExceptionHandler(EntityNotFoundException.class)
    public ResponseEntity<ExceptionDTO> defaultHandler(EntityNotFoundException e){

        String msg = "Id não foi encontrado, para o elemento buscado.";

        return ResponseEntity.badRequest().body( new ExceptionDTO(msg, HttpStatus.NOT_FOUND.value()));
    }
}
