package com.railforge.common.service;

import com.railforge.common.request.dto.RegisterRequestDTO;
import com.railforge.common.response.dto.AuthResponseDTO;

public interface AuthService {
	AuthResponseDTO register(RegisterRequestDTO registerRequestDTO) throws Exception;
}
