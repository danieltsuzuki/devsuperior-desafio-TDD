package com.devsuperior.bds02.resource.exceptions;

import com.devsuperior.bds02.servicies.exceptions.DataBaseException;
import com.devsuperior.bds02.servicies.exceptions.ResourceNotFoundException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import javax.servlet.http.HttpServletRequest;
import java.time.Instant;

@ControllerAdvice
public class ResourceExceptionHandler {

    @ExceptionHandler(ResourceNotFoundException.class)
    public ResponseEntity<StadardError> notFound(ResourceNotFoundException e, HttpServletRequest request) {
        HttpStatus status = HttpStatus.NOT_FOUND;
        StadardError error = new StadardError(
                Instant.now(), status.value(), "Resource not found",
                e.getMessage(), request.getRequestURI()
        );
        return ResponseEntity.status(status).body(error);
    }

    @ExceptionHandler(DataBaseException.class)
    public ResponseEntity<StadardError> notFound(DataBaseException e, HttpServletRequest request) {
        HttpStatus status = HttpStatus.BAD_REQUEST;
        StadardError error = new StadardError(
                Instant.now(), status.value(), "Integrity violation",
                e.getMessage(), request.getRequestURI()
        );
        return ResponseEntity.status(status).body(error);
    }
}
