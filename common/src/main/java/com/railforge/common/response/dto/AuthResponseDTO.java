package com.railforge.common.response.dto;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class AuthResponseDTO {
	
	private String accessToken;
    private String refreshToken;
    private String tokenType = "Bearer";
    private long expiresIn;        // seconds
    private String userId;
    private String email;
    private String role;

}
