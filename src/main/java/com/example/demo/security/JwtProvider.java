package com.example.demo.security;

import java.util.Calendar;
import java.util.Date;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

import com.example.demo.exception.FailureCreateAuthenticationTokenException;
import com.example.demo.response.TokenResponse;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jws;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import lombok.Getter;
import lombok.Setter;

/**
 * 認証用トークンを実際に扱う機能を提供するクラスである。
 */
@ConfigurationProperties(prefix="secret")
@Component
public class JwtProvider {
	@Getter
	@Setter
	private String authenticationTokenSecretKey;
	
	@Getter
	@Setter
	private String refreshTokenSecretKey;


    //@Autowired
    private UserDetailsServiceImp service;
    @Autowired
	PasswordEncoder passwordEncoder;

    /**
     * User情報からJWT（認証用トークン）を作成する
     * @param user Userオブジェクト
     * @return JWT（認証用トークン）
     */
    private String createAuthenticationToken(UserDetailsImp user) {    	
        // ClaimとしてIDを載せる
        var claims = Jwts.claims().setSubject(user.getUserId().toString());
        
        // トークンの開始時間と満了時間を決める
        var calender = Calendar.getInstance();
        var nowDate = new Date();
        calender.setTime(nowDate);
        calender.add(Calendar.MINUTE, 30);
        Date exp = calender.getTime();
        // JWTの作成
        return Jwts.builder()
                .setClaims(claims)
                .setIssuedAt(nowDate)
                .setExpiration(exp)
                .signWith(SignatureAlgorithm.HS256, authenticationTokenSecretKey)
                .compact();
    }

    /**
     * User情報からJWT（リフレッシュトークン）を作成する
     * @param user Userオブジェクト
     * @return JWT（リフレッシュトークン）
     */
    private String createRefreshToken(UserDetailsImp user) {    	
        // ClaimとしてIDを載せる
        var claims = Jwts.claims().setSubject(user.getUserId().toString());
        
        // トークンの開始時間と満了時間を決める
        var calender = Calendar.getInstance();
        var nowDate = new Date();
        calender.setTime(nowDate);
        calender.add(Calendar.DAY_OF_MONTH, 15);
        Date exp = calender.getTime();
        // JWTの作成
        return Jwts.builder()
                .setClaims(claims)
                .setIssuedAt(nowDate)
                .setExpiration(exp)
                .signWith(SignatureAlgorithm.HS256, refreshTokenSecretKey)
                .compact();
    }
    
    /**
     * リフレッシュトークンから認証用トークンと新しいリフレッシュトークンを提供する。<br>
     * @param refreshToken リフレッシュトークン
     * @return レスポンスとして返すトークンたち
     * @throws FailureCreateAuthenticationTokenException　トークンの生成に失敗した
     */
    public TokenResponse getTokens(String refreshToken) throws FailureCreateAuthenticationTokenException {
    	if(validateRefreshToken(refreshToken)) {
    		UserDetailsImp user;
    		user.setUserId(getUserIdByRefreshToken(refreshToken));
    		
	    	var tokenResponse = new TokenResponse();
	    	tokenResponse.setAuthenticationToken(createAuthenticationToken(user));
	    	tokenResponse.setRefreshToken(createRefreshToken(user));
	    	return tokenResponse;
    	}else {
    		throw new FailureCreateAuthenticationTokenException();
    	}
    }
    
    /**
     * ユーザー名と生パスワードから認証用トークンとリフレッシュトークンを提供する。<br>
     * @param userName ユーザー名
     * @param password 生パスワード（暗号化等されていないパスワードのこと）
     * @return レスポンスとして返すトークンたち
     * @throws FailureCreateAuthenticationTokenException　トークンの生成に失敗した
     */
    public TokenResponse getTokens(String userName, String password) throws FailureCreateAuthenticationTokenException {
    	UserDetailsImp user;
    	
    	//ユーザー取得
    	try {
	    	user = service.loadUserByUserName(userName);
    	}
    	catch(Exception e) {
    		throw new FailureCreateAuthenticationTokenException();
    	}
    	
    	if( !passwordEncoder.matches(password, user.getPassword()) )
    		throw new FailureCreateAuthenticationTokenException();
    	
    	var tokenResponse = new TokenResponse();
    	tokenResponse.setAuthenticationToken(createAuthenticationToken(user));
    	tokenResponse.setRefreshToken(createRefreshToken(user));
    	return tokenResponse;
    }
    
    /**
     * 認証用トークンからユーザー情報を取得する
     * @param token JWT(認証用トークン)
     * @return 成功ならユーザー情報を返す。<br>
     *	 失敗ならnullを返す。
     */
    public Authentication getAuthenticationByAuthenticationToken(final String token) {
    	try {
		    final UserDetails userDetails = service.loadUserByUserId(getUserIdByAuthenticationToken(token));
		    return new UsernamePasswordAuthenticationToken(userDetails, "", userDetails.getAuthorities());
    	}
    	catch(Exception e) {
    		return null;
    	}
    }
    
    /**
     * 認証用トークンからユーザーIDを取得する
     * @param token 認証用トークン
     * @return ユーザーID
     */
    private Integer getUserIdByAuthenticationToken(final String token) {
    	return Integer.valueOf(
    			Jwts.parser()
	    			.setSigningKey(authenticationTokenSecretKey)
	        		.parseClaimsJws(token)
		        		.getBody()
		        		.getSubject());
    }

    /**
     * 認証用トークンからユーザーIDを取得する
     * @param token 認証用トークン
     * @return ユーザーID
     */
    private Integer getUserIdByRefreshToken(final String token) {
    	return Integer.valueOf(
    			Jwts.parser()
	    			.setSigningKey(refreshTokenSecretKey)
	        		.parseClaimsJws(token)
		        		.getBody()
		        		.getSubject());
    }
    
    /**
     * リクエストのヘッダーから認証用トークンを取得する
     * @param request リクエスト
     * @return 認証用トークン
     */
    public String resolveAuthenticationToken(final HttpServletRequest request) {
        return request.getHeader("X-AUTH-TOKEN");
    }

    /**
     * 認証用トークンについて、機関と、ユーザーが有効なものであるかをチェックする
     * @param token 認証用トークン
     * @return 成功ならtrue<br>
     *	 失敗ならfalse
     */
    public boolean validateAuthenticationToken(final String token) {
        try {
            final Jws<Claims> claims = Jwts.parser().setSigningKey(authenticationTokenSecretKey).parseClaimsJws(token);
            
            return !claims.getBody().getExpiration().before(new Date()) &&
            		service.loadUserByUserId(getUserIdByAuthenticationToken(token)) != null;
        } catch (Exception e) {
            return false;
        }
    }
    
    /**
     * リフレッシュトークンについて、機関と、ユーザーが有効なものであるかをチェックする
     * @param token リフレッシュトークン
     * @return 成功ならtrue<br>
     *	 失敗ならfalse
     */
    private boolean validateRefreshToken(final String token) {
        try {
            final Jws<Claims> claims = Jwts.parser().setSigningKey(refreshTokenSecretKey).parseClaimsJws(token);
            
            return !claims.getBody().getExpiration().before(new Date()) &&
            		service.loadUserByUserId(getUserIdByRefreshToken(token)) != null;
        } catch (Exception e) {
            return false;
        }
    }
}