package com.prueba_tecnica.exceptions;

import com.prueba_tecnica.exceptions.custom.CustomBaseException;
import jakarta.validation.ConstraintViolationException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.context.request.WebRequest;

import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@ControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(CustomBaseException.class)
    @ResponseBody
    public ResponseEntity<?> handleCustomBaseException(CustomBaseException ex, WebRequest request){
        Map<String, Object> body = new LinkedHashMap<>();
        body.put("mensaje", ex.getMensaje());
        body.put("codigo", ex.getCodigo());

        return ResponseEntity.status(ex.getCodigo()).body(body);
    }

    @ExceptionHandler(Exception.class)
    @ResponseStatus(HttpStatus.NOT_FOUND)
    public ResponseEntity<?> handleGlobalException(Exception ex, WebRequest request){
        Map<String, Object> body = new LinkedHashMap<>();
        body.put("mensaje", ex.getMessage());
        body.put("codigo", HttpStatus.INTERNAL_SERVER_ERROR.value());

        return new ResponseEntity<>(body, HttpStatus.INTERNAL_SERVER_ERROR);
    }


    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<Map<String, Object>> handleValidationExceptions(MethodArgumentNotValidException ex) {
        Map<String, Object> body = new HashMap<>();

        List<Map<String, String>> errors = ex.getBindingResult().getFieldErrors().stream()
                .map(error -> {
                    Map<String, String> errorDetails = new HashMap<>();
                    errorDetails.put("campo", error.getField());
                    errorDetails.put("error", error.getDefaultMessage());
                    return errorDetails;
                })
                .collect(Collectors.toList());

        body.put("mensaje", "Error de validación");
        body.put("errores", errors);
        body.put("codigo", HttpStatus.BAD_REQUEST.value());

        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(body);
    }

    @ExceptionHandler(ConstraintViolationException.class)
    public ResponseEntity<Map<String, Object>> handleConstraintViolationException(ConstraintViolationException ex) {
        Map<String, Object> body = new HashMap<>();

        List<Map<String, String>> errors = ex.getConstraintViolations().stream()
                .map(violation -> {
                    Map<String, String> errorDetails = new HashMap<>();
                    errorDetails.put("campo", violation.getPropertyPath().toString());
                    errorDetails.put("error", violation.getMessage());
                    return errorDetails;
                })
                .collect(Collectors.toList());

        body.put("mensaje", "Error de validación");
        body.put("errores", errors);
        body.put("codigo", HttpStatus.BAD_REQUEST.value());

        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(body);
    }
}
