package com.railforge.trainservice.exception.handler;

import java.util.Map;

import org.slf4j.MDC;

import com.fasterxml.jackson.annotation.JsonInclude;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
@JsonInclude(JsonInclude.Include.NON_NULL)
public class ApiResponse<T> {

    private boolean success;
    private String message;
    private T data;
    private ErrorDetails error;
    private long timestamp;
    private String traceId;

    public static <T> ApiResponse<T> success(T data, String message) {
        return ApiResponse.<T>builder()
                .success(true)
                .message(message)
                .data(data)
                .timestamp(System.currentTimeMillis())
                .traceId(MDC.get("traceId"))
                .build();
    }

    public static <T> ApiResponse<T> failure(String message, ErrorDetails error) {
        return ApiResponse.<T>builder()
                .success(false)
                .message(message)
                .error(error)
                .timestamp(System.currentTimeMillis())
                .traceId(MDC.get("traceId"))
                .build();
    }

    @Data
    @Builder
    public static class ErrorDetails {
        private String code;
        private String path;
        private int status;
        private Map<String, String> validationErrors;
    }
}
