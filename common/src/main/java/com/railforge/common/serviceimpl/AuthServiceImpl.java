package com.railforge.common.serviceimpl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.railforge.common.config.JwtUtil;
import com.railforge.common.repository.UserRepository;
import com.railforge.common.request.dto.RegisterRequestDTO;
import com.railforge.common.response.dto.AuthResponseDTO;
import com.railforge.common.service.AuthService;

@Service
public class AuthServiceImpl implements AuthService {
	
	@Autowired
	private UserRepository userRepository;
	
	@Autowired
    private PasswordEncoder passwordEncoder;
	
	
    private JwtUtil jwtUtil;

	@Override
	public AuthResponseDTO register(RegisterRequestDTO registerRequestDTO) throws Exception {
		return null;
	}
}
