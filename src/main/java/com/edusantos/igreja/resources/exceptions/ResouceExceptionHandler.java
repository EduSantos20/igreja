package com.edusantos.igreja.resources.exceptions;

import com.edusantos.igreja.service.execeptions.DataBaseException;
import com.edusantos.igreja.service.execeptions.ResourceNotFoundException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import java.time.Instant;

@ControllerAdvice
public class ResouceExceptionHandler {

    @ExceptionHandler(ResourceNotFoundException.class)
    public ResponseEntity<StandardError> resourceNotFound(ResourceNotFoundException e, HttpServletRequest request){
        HttpStatus status = HttpStatus.NOT_FOUND;
        StandardError err = new StandardError(
                Instant.now(),              // Momento do erro
                status.value(),             // Código HTTP (404)
                "Resource not found",        // Título do erro
                e.getMessage(),              // Mensagem da exceção
                request.getRequestURI()      // Caminho da requisição
        );

        return ResponseEntity.status(status).body(err);
    }

    @ExceptionHandler(DataBaseException.class)
    public ResponseEntity<StandardError> database(DataBaseException e, HttpServletRequest request){
        HttpStatus status = HttpStatus.BAD_REQUEST;
        StandardError err = new StandardError(
                Instant.now(),
                status.value(),
                "Database error",
                e.getMessage(),
                request.getRequestURI()
        );

        return ResponseEntity.status(status).body(err);
    }

    @ExceptionHandler(Exception.class)
    public ResponseEntity<StandardError> genericException(Exception e, HttpServletRequest request){
        HttpStatus status = HttpStatus.INTERNAL_SERVER_ERROR;
        StandardError err = new StandardError(
                Instant.now(),
                status.value(),
                "Unexpected error",
                e.getMessage(),
                request.getRequestURI()
        );

        return ResponseEntity.status(status).body(err);
    }
}
