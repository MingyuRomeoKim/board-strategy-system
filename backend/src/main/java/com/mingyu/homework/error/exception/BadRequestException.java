package com.mingyu.homework.error.exception;

import org.springframework.http.HttpStatus;

public class BadRequestException extends BaseException {
    private static final HttpStatus STATUS = HttpStatus.BAD_REQUEST;
    private static final String ERROR_CODE = "BAD_REQUEST";

    public BadRequestException(String message) {
        super(STATUS, ERROR_CODE, message);
    }

    public BadRequestException(String message, Throwable cause) {
        super(STATUS, ERROR_CODE, message, cause);
    }

    public static BadRequestException invalidParameter(String paramName, String reason) {
        return new BadRequestException("유효하지 않는 parameter '" + paramName + "': " + reason);
    }

    public static BadRequestException invalidStrategy(String strategy) {
        return new BadRequestException("유효하지 않는 Strategy 타입 값 : " + strategy);
    }
}