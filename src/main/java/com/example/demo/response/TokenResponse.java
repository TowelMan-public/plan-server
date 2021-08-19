package com.example.demo.response;

import lombok.Data;

/**
 * レスポンスとして返すトークンたち
 */
@Data
public class TokenResponse {
	private String refreshToken;
	private String authenticationToken;
}
