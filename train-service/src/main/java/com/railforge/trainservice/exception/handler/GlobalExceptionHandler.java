package com.railforge.trainservice.exception.handler;

import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.Map;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import jakarta.servlet.http.HttpServletRequest;
import lombok.extern.slf4j.Slf4j;


@RestControllerAdvice
@Slf4j
public class GlobalExceptionHandler {

	// Handle your custom app exceptions
	@ExceptionHandler(AppException.class)
	public ResponseEntity<ErrorResponse> handleAppException(
			AppException ex, HttpServletRequest request) {

		log.error("AppException: {}", ex.getMessage());

		ErrorResponse response = ErrorResponse.builder()
				.status(ex.getStatus().value())
				.error(ex.getStatus().getReasonPhrase())
				.message(ex.getMessage())
				.path(request.getRequestURI())
				.timestamp(LocalDateTime.now())
				.build();

		return ResponseEntity.status(ex.getStatus()).body(response);
	}

	// Handle validation errors (@Valid / @Validated)
	@ExceptionHandler(MethodArgumentNotValidException.class)
	public ResponseEntity<ErrorResponse> handleValidationException(
			MethodArgumentNotValidException ex, HttpServletRequest request) {

		Map<String, String> fieldErrors = new HashMap<>();
		ex.getBindingResult().getFieldErrors()
		.forEach(err -> fieldErrors.put(err.getField(), err.getDefaultMessage()));

		ErrorResponse response = ErrorResponse.builder()
				.status(HttpStatus.BAD_REQUEST.value())
				.error("Validation Failed")
				.message("One or more fields are invalid")
				.path(request.getRequestURI())
				.timestamp(LocalDateTime.now())
				.validationErrors(fieldErrors)
				.build();

		return ResponseEntity.badRequest().body(response);
	}

	// Handle access denied
	@ExceptionHandler(AccessDeniedException.class)
	public ResponseEntity<ErrorResponse> handleAccessDenied(
			AccessDeniedException ex, HttpServletRequest request) {

		ErrorResponse response = ErrorResponse.builder()
				.status(HttpStatus.FORBIDDEN.value())
				.error("Forbidden")
				.message("You don't have permission to access this resource")
				.path(request.getRequestURI())
				.timestamp(LocalDateTime.now())
				.build();

		return ResponseEntity.status(HttpStatus.FORBIDDEN).body(response);
	}

	// Catch-all for unhandled exceptions
	@ExceptionHandler(Exception.class)
	public ResponseEntity<ErrorResponse> handleGenericException(
			Exception ex, HttpServletRequest request) {

		log.error("Unhandled exception at {}: ", request.getRequestURI(), ex);

		ErrorResponse response = ErrorResponse.builder()
				.status(HttpStatus.INTERNAL_SERVER_ERROR.value())
				.error("Internal Server Error")
				.message("An unexpected error occurred")
				.path(request.getRequestURI())
				.timestamp(LocalDateTime.now())
				.build();

		return ResponseEntity.internalServerError().body(response);
	}
}
