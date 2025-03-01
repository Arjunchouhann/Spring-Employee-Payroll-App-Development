package com.bridgelabz.employeepayrollapp.exceptions;

import com.bridgelabz.employeepayrollapp.dto.ResponseDTO;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Slf4j
@ControllerAdvice
public class EmployeePayrollExceptionHandler {
    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<Map<String, String>> handleValidationException(MethodArgumentNotValidException ex){
        log.error("Invalid Method Argument");
        Map<String, String>errors = new HashMap<>();
        List<FieldError>fieldErrors = ex.getBindingResult().getFieldErrors();

        for (FieldError fieldError : fieldErrors) {
            String field = fieldError.getField();
            String message = fieldError.getDefaultMessage();
            errors.put(field, message);
        }
        return new ResponseEntity<>(errors, HttpStatus.BAD_REQUEST);
    }
    // Handle Employee Not Found Exception
    @ExceptionHandler(EmployeeNotFoundException.class)
    public ResponseEntity<ResponseDTO> handleEmployeeNotFoundException(EmployeeNotFoundException ex) {
        log.error("No employee found with this id");
        ResponseDTO response = new ResponseDTO(ex.getMessage(),HttpStatus.NOT_FOUND.value());
        return new ResponseEntity<>(response, HttpStatus.NOT_FOUND);
    }

    // Handle general runtime exceptions
    @ExceptionHandler(RuntimeException.class)
    public ResponseEntity<ResponseDTO> handleRuntimeException(RuntimeException ex) {
        log.error("Run Time Exception");
        ResponseDTO response = new ResponseDTO(ex.getMessage(),HttpStatus.INTERNAL_SERVER_ERROR.value());
        return new ResponseEntity<>(response, HttpStatus.INTERNAL_SERVER_ERROR);
    }
}