package com.example.demo.response;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

/**
 * レスポンスとして返すトークンたち
 */
@Data
@EqualsAndHashCode
@NoArgsConstructor
public class TokenResponse {
	private String refreshToken;
	private String authenticationToken;
}
