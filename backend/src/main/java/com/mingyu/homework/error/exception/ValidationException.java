package com.mingyu.homework.error.exception;

import org.springframework.http.HttpStatus;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;

import java.util.HashMap;
import java.util.Map;
import java.util.stream.Collectors;

public class ValidationException extends BaseException {
    private static final HttpStatus STATUS = HttpStatus.BAD_REQUEST;
    private static final String ERROR_CODE = "VALIDATION_ERROR";
    
    private final Map<String, String> fieldErrors;

    public ValidationException(String message, Map<String, String> fieldErrors) {
        super(STATUS, ERROR_CODE, message);
        this.fieldErrors = fieldErrors;
    }

    public ValidationException(String message, Map<String, String> fieldErrors, Throwable cause) {
        super(STATUS, ERROR_CODE, message, cause);
        this.fieldErrors = fieldErrors;
    }

    public Map<String, String> getFieldErrors() {
        return fieldErrors;
    }

    public static ValidationException fromBindingResult(BindingResult bindingResult) {
        Map<String, String> fieldErrors = bindingResult.getFieldErrors().stream()
                .collect(Collectors.toMap(
                        FieldError::getField,
                        error -> error.getDefaultMessage() != null ? error.getDefaultMessage() : "Invalid value",
                        (existing, replacement) -> existing + "; " + replacement
                ));
        
        return new ValidationException("요청에 대한 유효성 검증에 실패하였습니다.", fieldErrors);
    }
    
    public static ValidationException withFieldError(String field, String message) {
        Map<String, String> fieldErrors = new HashMap<>();
        fieldErrors.put(field, message);
        return new ValidationException("해당 필드는 유효성 검증에 실패하였습니다." + field, fieldErrors);
    }
}