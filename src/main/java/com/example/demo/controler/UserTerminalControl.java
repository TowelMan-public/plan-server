package com.example.demo.controler;

import java.util.List;

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
import com.example.demo.exception.ValidateException;
import com.example.demo.form.UserTerminalForm;
import com.example.demo.response.TerminalResponse;
import com.example.demo.security.UserDetailsImp;
import com.example.demo.service.UserTerminalService;

@RequestMapping(UrlConfig.ROOT_URL_V1 + "/user/terminal")
@RestController
public class UserTerminalControl {
	@Autowired
	UserTerminalService service;
	
	@PostMapping
	public void inesrtTerminal(@AuthenticationPrincipal UserDetailsImp user, @RequestBody UserTerminalForm form) 
			throws ValidateException{
		form.validatePost();
		
		service.insert(user.getUserId(), form.getTerminalName());
	}
	
	@GetMapping
	public List<TerminalResponse> getTerminalList(@AuthenticationPrincipal UserDetailsImp user) {
		return service.getTerminalList(user.getUserId());
	}
	
	@PutMapping
	public void updateTerminalName(@AuthenticationPrincipal UserDetailsImp user, @RequestBody UserTerminalForm form) 
			throws ValidateException{
		form.validatePut();
		
		service.updateTerminalName(user.getUserId(), form.getOldTerminalName(), form.getNewTerminalName());
	}
	
	@DeleteMapping
	public void deleteTerminal(@AuthenticationPrincipal UserDetailsImp user, @RequestBody UserTerminalForm form) 
			throws ValidateException{
		form.validateDelete();
		
		service.delete(user.getUserId(), form.getTerminalName());
	}
}
