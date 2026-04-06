package com.railforge.common.serviceimpl;

import org.springframework.stereotype.Service;

import com.railforge.common.request.dto.RegisterRequestDTO;
import com.railforge.common.response.dto.AuthResponseDTO;
import com.railforge.common.service.AuthService;

@Service
public class AuthServiceImpl implements AuthService {

	@Override
	public AuthResponseDTO register(RegisterRequestDTO registerRequestDTO) throws Exception {
		return null;
	}
}
