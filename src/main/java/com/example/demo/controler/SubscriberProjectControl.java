package com.example.demo.controler;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.configurer.UrlConfig;
import com.example.demo.exception.ValidateException;
import com.example.demo.form.SubscriberInProjectForm;
import com.example.demo.response.SubscriberInPublicProjectResponse;
import com.example.demo.security.UserDetailsImp;
import com.example.demo.service.SubscriberProjectService;

@RequestMapping(UrlConfig.ROOT_URL_V1 + "/project/{publicProjectId}/subscriber")
@RestController
public class SubscriberProjectControl {
	@Autowired
	SubscriberProjectService service;
	
	@PostMapping
	public void insertSubscriber(@AuthenticationPrincipal UserDetailsImp user,
			@PathVariable("publicProjectId") Integer publicProjectId,
			@RequestBody SubscriberInProjectForm form) throws ValidateException {
		form.validatePost();
		service.insert(user.getUserId(), publicProjectId, form.getUserName());
	}
	
	@GetMapping
	public List<SubscriberInPublicProjectResponse> getSubscriberList(@AuthenticationPrincipal UserDetailsImp user, @PathVariable("publicProjectId") Integer publicProjectId) {
		return service.getList(user.getUserId(), publicProjectId);
	}
	
	@PutMapping
	public void updateSubscriber(@AuthenticationPrincipal UserDetailsImp user,
			@PathVariable("publicProjectId") Integer publicProjectId,
			@RequestBody SubscriberInProjectForm form) throws ValidateException {
		form.validatePut();
		service.update(user.getUserId(), publicProjectId, form.getUserName(), form.getAuthorityId());
	}
	
	@DeleteMapping
	public void deleteSubscriber(@AuthenticationPrincipal UserDetailsImp user,
			@PathVariable("publicProjectId") Integer publicProjectId,
			@RequestBody SubscriberInProjectForm form) throws ValidateException {
		form.validateDelete();
		
		service.delete(user.getUserId(), publicProjectId, form.getUserName());
	}
	
	@PostMapping("accept")
	public void accept(@AuthenticationPrincipal UserDetailsImp user, @PathVariable("publicProjectId") Integer publicProjectId) {
		service.accept(user.getUserId(), publicProjectId);
	}
	
	@PostMapping("block")
	public void block(@AuthenticationPrincipal UserDetailsImp user, @PathVariable("publicProjectId") Integer publicProjectId) {
		service.bloak(user.getUserId(), publicProjectId);
	}
	
	@PostMapping("exit")
	public void exitProject(@AuthenticationPrincipal UserDetailsImp user, @PathVariable("publicProjectId") Integer publicProjectId) {
		service.exit(user.getUserId(), publicProjectId);
	}
}
