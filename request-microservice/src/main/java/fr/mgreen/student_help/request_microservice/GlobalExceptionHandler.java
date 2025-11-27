package fr.mgreen.student_help.request_microservice;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler({org.hibernate.exception.ConstraintViolationException.class})
    public ResponseEntity<String> handleHibernateConstraintViolation(Exception ex) {

        String message = "Invalid request: " + ex.getMessage();
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(message);
    }

    @ExceptionHandler({jakarta.validation.ConstraintViolationException.class})
    public ResponseEntity<String> handleJakartaConstraintViolation(Exception ex) {

        String message = "Invalid request: " + ex.getMessage();
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(message);
    }
}
