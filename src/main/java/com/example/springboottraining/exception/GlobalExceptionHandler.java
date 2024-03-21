package com.example.springboottraining.exception;

import com.example.springboottraining.entity.ToDo;
import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import lombok.extern.java.Log;
import org.aspectj.bridge.Message;
import org.springframework.context.MessageSource;
import org.springframework.context.i18n.LocaleContextHolder;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@Log
@ControllerAdvice
@RequiredArgsConstructor
public class GlobalExceptionHandler {

    private final MessageSource messageSource;

    @ExceptionHandler({ToDoNotFoundException.class})
    public ResponseEntity<Object> handleToDoNotFoundException(ToDoNotFoundException e){
        log.warning("ToDo not found");
        String errorMessage = messageSource.getMessage("error.id-not-found", null, LocaleContextHolder.getLocale());
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(errorMessage);
    }

    @ExceptionHandler({EntityNotFoundException.class})
    public ResponseEntity<Object> handleToDoNotFoundException(EntityNotFoundException e){
        log.warning("Entity not found");
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(e.getMessage());
    }

}
