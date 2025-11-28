package fr.mgreen.student_help.orchestrator;

import feign.FeignException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler({FeignException.class})
    public ResponseEntity<String> handleFeignException(Exception ex) {
        FeignException fex = (FeignException) ex;
        return ResponseEntity.status(fex.status()).body(fex.contentUTF8());
    }

}
