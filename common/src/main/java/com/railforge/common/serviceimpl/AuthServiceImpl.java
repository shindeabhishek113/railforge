package com.railforge.common.serviceimpl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.railforge.common.config.JwtUtil;
import com.railforge.common.enums.Role;
import com.railforge.common.jpa.repository.UserRepository;
import com.railforge.common.model.entities.User;
import com.railforge.common.request.dto.LoginRequestDTO;
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
    
    @Value("${jwt.access-token-expiry:3600000}")   // 1 hour default
	private long accessTokenExpiry;

	@Override
	public AuthResponseDTO register(RegisterRequestDTO registerRequestDTO) throws Exception {
		// 1. Check duplicate email
        if (userRepository.existsByEmail(registerRequestDTO.getEmail())) {
            throw new RuntimeException("Email already registered");
        }

        // 2. Build user — force role to USER always!
        User user = User.builder()
                .fullName(registerRequestDTO.getFullName())
                .email(registerRequestDTO.getEmail())
                .password(passwordEncoder.encode(registerRequestDTO.getPassword())) // BCrypt!
                .phone(registerRequestDTO.getPhone())
                .role(Role.USER) // ← NEVER trust client for role
                .isActive(true)
                .build();

        User savedUser = userRepository.save(user);

        // 3. Generate tokens
        return buildAuthResponse(savedUser);
	}
	
	// ─── HELPER ──────────────────────────────────────────────
    private AuthResponseDTO buildAuthResponse(User user) {
    	String accessToken  = jwtUtil.generateAccessToken(
                user.getId() + "", user.getEmail(), user.getRole().name());

        String refreshToken = jwtUtil.generateRefreshToken(
                user.getId() + "", user.getEmail(), user.getRole().name());

        return AuthResponseDTO.builder()
                .accessToken(accessToken)
                .refreshToken(refreshToken)
                .expiresIn(accessTokenExpiry / 1000) // convert ms to seconds
                .userId(user.getId() + "")
                .email(user.getEmail())
                .role(user.getRole().name())
                .build();
    }

	@Override
    public AuthResponseDTO login(LoginRequestDTO loginRequestDTO) throws Exception {

		User user = userRepository.findByEmail(loginRequestDTO.getEmail());
		
		if(!passwordEncoder.matches(loginRequestDTO.getPassword(), user.getPassword())) {
			throw new Exception("Invalid credentials");
		}

        return buildAuthResponse(user);
    }
}
