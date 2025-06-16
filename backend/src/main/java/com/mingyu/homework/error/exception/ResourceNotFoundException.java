package com.mingyu.homework.error.exception;

import org.springframework.http.HttpStatus;

public class ResourceNotFoundException extends BaseException {
    private static final HttpStatus STATUS = HttpStatus.NOT_FOUND;
    private static final String ERROR_CODE = "RESOURCE_NOT_FOUND";

    public ResourceNotFoundException(String message) {
        super(STATUS, ERROR_CODE, message);
    }

    public ResourceNotFoundException(String message, Throwable cause) {
        super(STATUS, ERROR_CODE, message, cause);
    }

    public static ResourceNotFoundException postNotFound(String postId) {
        return new ResourceNotFoundException("게시글 " + postId + " 는 찾을 수 없는 아이디 입니다.");
    }
}