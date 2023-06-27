package com.example.protoFoodV2.exceptions;

import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.security.InvalidParameterException;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

@RestControllerAdvice
@Slf4j
public class ApplicationExceptionHandler {

//    @ResponseStatus(HttpStatus.NOT_FOUND)
    @ExceptionHandler(RenderableException.class)
    public ResponseEntity<?> handleRenderableException(RenderableException ex) {
        Map<String, String> errorMap = new HashMap<>();

        errorMap.put("message", ex.getMessage());

        log.error("Exception : ");
        ex.printStackTrace();
        return new ResponseEntity<>(errorMap, ex.getErrorCode().getStatus());
    }

    @ExceptionHandler(EntityNotFoundException.class)
    public ResponseEntity<?> handleEntityNotFoundException(EntityNotFoundException ene) {
        Map<String, String> errorMap = new HashMap<>();

        errorMap.put("message", ene.getMessage());

        log.error("Exception : ");
        ene.printStackTrace();
        return new ResponseEntity<>(errorMap, HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler(InvalidParameterException.class)
    public ResponseEntity<?> handleInvalidParameterException(InvalidParameterException ipe) {
        Map<String, String> errorMap = new HashMap<>();

        errorMap.put("message", ipe.getMessage());

        log.error("Exception : ");
        ipe.printStackTrace();
        return new ResponseEntity<>(errorMap, HttpStatus.NOT_ACCEPTABLE);
    }
}
