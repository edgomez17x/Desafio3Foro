package com.alurachallenge.Foro.errores;

import jakarta.persistence.EntityNotFoundException;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class TratadorErrores {

    @ExceptionHandler(EntityNotFoundException.class)
    public ResponseEntity<Object> tratarError404(){
        return ResponseEntity.notFound().build();
    }

    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<Object> tratarError400(MethodArgumentNotValidException manve){
        var fieldError = manve.getFieldErrors().stream().map(ErrorValidacionRec::new).toList();
        return ResponseEntity.badRequest().body(fieldError);
    }

    @ExceptionHandler(ValidacionException.class)
    public ResponseEntity<Object> tratarErrorValidacionNegocio(ValidacionException vi){
        return ResponseEntity.badRequest().body(vi.getMessage());
    }

    private record ErrorValidacionRec(
            String campo, String error
    ){
        public ErrorValidacionRec(FieldError fieldError){
            this(fieldError.getField(), fieldError.getDefaultMessage());
        }
    }

}
