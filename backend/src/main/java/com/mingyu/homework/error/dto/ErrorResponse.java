package com.mingyu.homework.error.dto;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.mingyu.homework.error.exception.BaseException;
import com.mingyu.homework.error.exception.ValidationException;
import lombok.Builder;
import lombok.Getter;
import org.springframework.http.HttpStatus;

import java.time.LocalDateTime;
import java.util.Map;

@Getter
@Builder
@JsonInclude(JsonInclude.Include.NON_NULL)
public class ErrorResponse {
    private final LocalDateTime timestamp;
    private final int status;
    private final String error;
    private final String code;
    private final String message;
    private final String path;
    private final Map<String, String> fieldErrors;

    public static ErrorResponse from(BaseException exception, String path) {
        ErrorResponseBuilder builder = ErrorResponse.builder()
                .timestamp(LocalDateTime.now())
                .status(exception.getStatus().value())
                .error(exception.getStatus().getReasonPhrase())
                .code(exception.getErrorCode())
                .message(exception.getMessage())
                .path(path);

        if (exception instanceof ValidationException) {
            builder.fieldErrors(((ValidationException) exception).getFieldErrors());
        }

        return builder.build();
    }

    public static ErrorResponse from(Exception exception, HttpStatus status, String path) {
        return ErrorResponse.builder()
                .timestamp(LocalDateTime.now())
                .status(status.value())
                .error(status.getReasonPhrase())
                .code("INTERNAL_SERVER_ERROR")
                .message(exception.getMessage())
                .path(path)
                .build();
    }
}