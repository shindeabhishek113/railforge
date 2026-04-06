package com.railforge.common.controller;

import java.util.Collections;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.railforge.common.request.dto.RegisterRequestDTO;
import com.railforge.common.service.AuthService;

import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;

@Tag(name = "Auth Controller", description = "")
@RestController
@RequestMapping("/auth")
public class AuthController {
	
	@Autowired
	private AuthService authService;
	
	@PostMapping("/register")
    public Map<String, Object> register(@Valid @RequestBody RegisterRequestDTO request) throws Exception {
		return Collections.singletonMap("authResponse", authService.register(request));
    }

}

