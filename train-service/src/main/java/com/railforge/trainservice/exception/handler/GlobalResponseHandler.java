package com.railforge.trainservice.exception.handler;

import java.util.List;

import org.springframework.core.MethodParameter;
import org.springframework.http.MediaType;
import org.springframework.http.converter.HttpMessageConverter;
import org.springframework.http.server.ServerHttpRequest;
import org.springframework.http.server.ServerHttpResponse;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.servlet.mvc.method.annotation.ResponseBodyAdvice;

import lombok.extern.slf4j.Slf4j;

@RestControllerAdvice
@Slf4j
public class GlobalResponseHandler implements ResponseBodyAdvice<Object> {

	// Swagger internal classes to skip
	private static final List<String> SWAGGER_PACKAGES = List.of(
			"org.springdoc",
			"org.springframework.boot.actuate"
			);

	@Override
	public boolean supports(MethodParameter returnType, Class<? extends HttpMessageConverter<?>> converterType) {
		// Skip if already wrapped
		if (returnType.getParameterType().equals(ApiResponse.class)) return false;

		// Skip Swagger/Actuator internal endpoints
		String declaringClass = returnType.getDeclaringClass().getName();
		for (String pkg : SWAGGER_PACKAGES) {
			if (declaringClass.startsWith(pkg)) return false;
		}

		return true;
	}

	@Override
	public Object beforeBodyWrite(Object body,
			MethodParameter returnType,
			MediaType selectedContentType,
			Class<? extends HttpMessageConverter<?>> selectedConverterType,
					ServerHttpRequest request,
					ServerHttpResponse response) {

		// Handle String type separately (Jackson can't wrap String directly)
		if (body instanceof String) {
			return ApiResponse.success(body, "Success");
		}

		// Skip wrapping byte arrays (file downloads etc.)
		if (body instanceof byte[]) return body;

		log.info("Response wrapped for: {}", request.getURI().getPath());
		return ApiResponse.success(body, "Success");
	}
}
