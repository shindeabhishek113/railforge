package com.railforge.trainservice.exception.handler;

import org.springframework.http.HttpStatus;

import lombok.Getter;

//Base exception
@Getter
public class AppException extends RuntimeException {

    private static final long serialVersionUID = 1L;
	private final HttpStatus status;
    private final String errorCode;

    public AppException(String message, HttpStatus status, String errorCode) {
        super(message);
        this.status = status;
        this.errorCode = errorCode;
    }

    // quick factory methods
    public static AppException notFound(String message) {
        return new AppException(message, HttpStatus.NOT_FOUND, "NOT_FOUND");
    }

    public static AppException badRequest(String message) {
        return new AppException(message, HttpStatus.BAD_REQUEST, "BAD_REQUEST");
    }

    public static AppException conflict(String message) {
        return new AppException(message, HttpStatus.CONFLICT, "CONFLICT");
    }
}

