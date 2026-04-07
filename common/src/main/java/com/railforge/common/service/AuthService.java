package com.railforge.common.service;

import com.railforge.common.request.dto.LoginRequestDTO;
import com.railforge.common.request.dto.RegisterRequestDTO;
import com.railforge.common.response.dto.AuthResponseDTO;

import jakarta.validation.Valid;

public interface AuthService {
	AuthResponseDTO register(RegisterRequestDTO registerRequestDTO) throws Exception;
	AuthResponseDTO login(@Valid LoginRequestDTO loginRequestDTO) throws Exception;
}
