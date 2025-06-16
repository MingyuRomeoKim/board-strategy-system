package com.mingyu.homework.error.handler;

import com.mingyu.homework.error.dto.ErrorResponse;
import com.mingyu.homework.error.exception.BaseException;
import com.mingyu.homework.error.exception.ValidationException;
import jakarta.servlet.http.HttpServletRequest;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindException;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.method.annotation.MethodArgumentTypeMismatchException;

@Slf4j
@RestControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(BaseException.class)
    public ResponseEntity<ErrorResponse> handleBaseException(BaseException ex, HttpServletRequest request) {
        log.error("BaseException occurred: {}", ex.getMessage(), ex);
        ErrorResponse response = ErrorResponse.from(ex, request.getRequestURI());
        return new ResponseEntity<>(response, ex.getStatus());
    }

    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<ErrorResponse> handleMethodArgumentNotValidException(
            MethodArgumentNotValidException ex, HttpServletRequest request) {
        log.error("Validation error occurred: {}", ex.getMessage(), ex);
        ValidationException validationException = ValidationException.fromBindingResult(ex.getBindingResult());
        ErrorResponse response = ErrorResponse.from(validationException, request.getRequestURI());
        return new ResponseEntity<>(response, validationException.getStatus());
    }

    @ExceptionHandler(BindException.class)
    public ResponseEntity<ErrorResponse> handleBindException(
            BindException ex, HttpServletRequest request) {
        log.error("Binding error occurred: {}", ex.getMessage(), ex);
        ValidationException validationException = ValidationException.fromBindingResult(ex.getBindingResult());
        ErrorResponse response = ErrorResponse.from(validationException, request.getRequestURI());
        return new ResponseEntity<>(response, validationException.getStatus());
    }

    @ExceptionHandler(MethodArgumentTypeMismatchException.class)
    public ResponseEntity<ErrorResponse> handleMethodArgumentTypeMismatchException(
            MethodArgumentTypeMismatchException ex, HttpServletRequest request) {
        log.error("Type mismatch error occurred: {}", ex.getMessage(), ex);
        String paramName = ex.getName();
        String message = String.format("Parameter '%s' should be of type %s", 
                paramName, ex.getRequiredType() != null ? ex.getRequiredType().getSimpleName() : "unknown");
        
        ValidationException validationException = ValidationException.withFieldError(paramName, message);
        ErrorResponse response = ErrorResponse.from(validationException, request.getRequestURI());
        return new ResponseEntity<>(response, validationException.getStatus());
    }

    @ExceptionHandler(Exception.class)
    public ResponseEntity<ErrorResponse> handleException(Exception ex, HttpServletRequest request) {
        log.error("Unexpected error occurred: {}", ex.getMessage(), ex);
        HttpStatus status = HttpStatus.INTERNAL_SERVER_ERROR;
        ErrorResponse response = ErrorResponse.from(ex, status, request.getRequestURI());
        return new ResponseEntity<>(response, status);
    }
}