package com.example.demo.security;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.security.web.authentication.logout.HttpStatusReturningLogoutSuccessHandler;

import com.example.demo.configurer.UrlConfig;

/**
 * SpringSecurityの全体的な設定<br>
 * WebSecurityConfigurerAdapterを実装している
 * @see org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter
 */
@Configuration
public class SecurityConfig extends WebSecurityConfigurerAdapter {
	private static final String GET_TOKEN_URL_V1 = UrlConfig.ROOT_URL_V1 + "/user/token";
	private static final String INSERT_USER_URL_V1 = UrlConfig.ROOT_URL_V1 + "/user";
	
	// ログイン以降の認証認可のためのFilter
	@Autowired
    JwtAuthenticationFilter jwtAuthenticationFilter;
	@Autowired
	AuthenticationEntryPointImp authenticationEntryPointImp;

	/**
	 * 認証を無視するURLの設定
	 */
    @Override
    public void configure(WebSecurity web) throws Exception { 
    	web
    		.ignoring()
	    		.antMatchers(HttpMethod.POST, GET_TOKEN_URL_V1)
	            .antMatchers(HttpMethod.POST, INSERT_USER_URL_V1);
    }
    
    /**
     * 認証・URLのアクセス設定・Filterの設定など多くの設定を行っている
     */
    @Override
    protected void configure(final HttpSecurity httpSecurity) throws Exception {
        httpSecurity
	                .httpBasic().disable()
	                .csrf().disable()
	                .sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS)
                .and()
                .authorizeRequests()
	                //ログイン不要でアクセス可能に設定
	                .antMatchers(HttpMethod.POST, GET_TOKEN_URL_V1).permitAll()
	                .antMatchers(HttpMethod.POST, INSERT_USER_URL_V1).permitAll()
	                //上記以外は直リンク禁止
	                .anyRequest().authenticated()
                .and()
                .exceptionHandling()
                	.authenticationEntryPoint(authenticationEntryPointImp)
                .and()
                // デフォルトのFilter設定を変える
                .addFilterBefore(jwtAuthenticationFilter, UsernamePasswordAuthenticationFilter.class);
    }
    
    @Bean
    @Override
    public AuthenticationManager authenticationManagerBean() throws Exception {
        return super.authenticationManagerBean();
    }
    
    /**
     * パスワードのアルゴリズムをBCryptに設定
     */
    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }
    
    @Bean
    public HttpStatusReturningLogoutSuccessHandler httpStatusReturningLogoutSuccessHandler() {
    	return new HttpStatusReturningLogoutSuccessHandler();
    }
    
}