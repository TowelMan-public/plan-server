package com.example.demo.response;

import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * レスポンスとして返すトークンたち
 */
@Data
@NoArgsConstructor
public class TokenResponse {
	private String refreshToken;
	private String authenticationToken;
}
