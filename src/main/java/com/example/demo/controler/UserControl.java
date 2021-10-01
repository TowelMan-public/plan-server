package com.example.demo.controler;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.configurer.UrlConfig;
import com.example.demo.exception.FailureCreateAuthenticationTokenException;
import com.example.demo.exception.ValidateException;
import com.example.demo.form.UserForm;
import com.example.demo.response.TokenResponse;
import com.example.demo.response.UserResponse;
import com.example.demo.security.JwtProvider;
import com.example.demo.security.UserDetailsImp;
import com.example.demo.service.UserService;

@RequestMapping(UrlConfig.ROOT_URL_V1 + "/user")
@RestController
public class UserControl {
	@Autowired
	UserService server;
	@Autowired
	JwtProvider provider;
	
	@PostMapping("token")
	public TokenResponse getToken(@RequestBody UserForm form)
			throws ValidateException, FailureCreateAuthenticationTokenException {
		form.validatePostToken();
		
		if(form.getRefreshJwtToken() != null && !form.getRefreshJwtToken().isBlank())
			return provider.getTokens(form.getRefreshJwtToken());
		else
			return provider.getTokens(form.getUserName(), form.getPassword());
	}
	
	@PostMapping
	public void insertUser(@RequestBody UserForm form)
			throws ValidateException {
		form.validatePost();
		
		server.insert(form);
	}
	
	@GetMapping
	public UserResponse getUser(@AuthenticationPrincipal UserDetailsImp user, UserForm form) {
		if(form.getUserName() != null && !form.getUserName().isBlank()) {
			return server.get(form.getUserName());
		}else {
			return server.get(user.getUserId());
		}
	}
	
	@PutMapping
	public void updateUser(@AuthenticationPrincipal UserDetailsImp user, @RequestBody UserForm form) {
		if(form.getUserName() != null && !form.getUserName().isBlank()) {
			server.updateUserName(user.getUserId(), form.getUserName());
		}
		
		if(form.getUserNickName() != null && !form.getUserNickName().isBlank()) {
			server.updateUserNickName(user.getUserId(), form.getUserNickName());
		}
		
		if(form.getPassword() != null && !form.getPassword().isBlank()) {
			server.updatePassword(user.getUserId(), form.getPassword());
		}
	}
	
	@DeleteMapping
	public void deleteUser(@AuthenticationPrincipal UserDetailsImp user) {
		server.delete(user.getUserId());
	}
}
