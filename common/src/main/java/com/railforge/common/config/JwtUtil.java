package com.railforge.common.config;

import java.util.Date;

import javax.crypto.SecretKey;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.ExpiredJwtException;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.MalformedJwtException;
import io.jsonwebtoken.UnsupportedJwtException;
import io.jsonwebtoken.io.Decoders;
import io.jsonwebtoken.security.Keys;

@Component
public class JwtUtil {

	@Value("${jwt.secret}")
	private String secret;

	@Value("${jwt.access-token-expiry:3600000}")   // 1 hour default
	private long accessTokenExpiry;

	@Value("${jwt.refresh-token-expiry:604800000}") // 7 days default
	private long refreshTokenExpiry;

	// ─── Signing Key ─────────────────────────────────────────
	private SecretKey getSigningKey() {
		byte[] keyBytes = Decoders.BASE64.decode(secret);
		return Keys.hmacShaKeyFor(keyBytes);
	}

	// ─── Generate Access Token ────────────────────────────────
	public String generateAccessToken(String userId, String email, String role) {
		return buildToken(userId, email, role, accessTokenExpiry);
	}

	// ─── Generate Refresh Token ───────────────────────────────
	public String generateRefreshToken(String userId, String email, String role) {
		return buildToken(userId, email, role, refreshTokenExpiry);
	}

	// ─── Core Builder ─────────────────────────────────────────
	private String buildToken(String userId, String email,
			String role, long expiry) {
		return Jwts.builder()
				.subject(userId)                        
				.claim("email", email)
				.claim("role", role)
				.issuedAt(new Date())                   
				.expiration(new Date(                   
						System.currentTimeMillis() + expiry))
				.signWith(getSigningKey())               
				.compact();
	}

	// ─── Validate & Extract All Claims ───────────────────────
	public Claims validateAndExtract(String token) {
		return Jwts.parser()                            
				.verifyWith(getSigningKey())             
				.build()
				.parseSignedClaims(token)               
				.getPayload();                          	
	}

	// ─── Individual Extractors ────────────────────────────────
	public String extractUserId(String token) {
		return validateAndExtract(token).getSubject();
	}

	public String extractEmail(String token) {
		return validateAndExtract(token).get("email", String.class);
	}

	public String extractRole(String token) {
		return validateAndExtract(token).get("role", String.class);
	}

	public Date extractExpiration(String token) {
		return validateAndExtract(token).getExpiration();
	}

	public long getRemainingExpiry(String token) {
		Date expiration = extractExpiration(token);
		return expiration.getTime() - System.currentTimeMillis();
	}

	// ─── Validation ───────────────────────────────────────────
	public boolean isTokenValid(String token) {
		try {
			validateAndExtract(token);
			return true;
		} catch (ExpiredJwtException e) {
			throw new RuntimeException("Token has expired");
		} catch (UnsupportedJwtException e) {
			throw new RuntimeException("Token format not supported");
		} catch (MalformedJwtException e) {
			throw new RuntimeException("Token is malformed");
		} catch (SecurityException e) {
			throw new RuntimeException("Invalid token signature");
		} catch (IllegalArgumentException e) {
			throw new RuntimeException("Token is empty or null");
		}
	}

	public boolean isTokenExpired(String token) {
		try {
			return extractExpiration(token).before(new Date());
		} catch (ExpiredJwtException e) {
			return true;
		}
	}
}