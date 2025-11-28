package fr.mgreen.student_help.profile_microservice;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.MissingRequestHeaderException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import java.util.HashMap;
import java.util.Map;

@ControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler({org.hibernate.exception.ConstraintViolationException.class})
    public ResponseEntity<String> handleHibernateConstraintViolation(Exception ex) {

        String message = "Invalid request: " + ex.getMessage();
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(message);
    }

    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<?> handleValidation(MethodArgumentNotValidException ex) {
        Map<String, String> errors = new HashMap<>();
        ex.getBindingResult().getFieldErrors()
                .forEach(err -> errors.put(err.getField(), err.getDefaultMessage()));
        return ResponseEntity.badRequest().body(errors);
    }

    @ExceptionHandler(MissingRequestHeaderException.class)
    public ResponseEntity<?> handleMissingHeader(MissingRequestHeaderException ex) {
        return ResponseEntity.status(401).body(ex.getMessage());
    }
}
