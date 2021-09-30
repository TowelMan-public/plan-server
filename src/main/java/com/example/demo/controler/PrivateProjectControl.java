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
import com.example.demo.form.PrivateProjectForm;
import com.example.demo.response.PrivateProjectResponse;
import com.example.demo.security.UserDetailsImp;
import com.example.demo.service.PrivateProjectService;

@RequestMapping(UrlConfig.ROOT_URL_V1 + "/project/private")
@RestController
public class PrivateProjectControl {
	@Autowired
	PrivateProjectService service;
	
	@PostMapping
	public Integer inertPrivateProject(@AuthenticationPrincipal UserDetailsImp user, @RequestBody PrivateProjectForm form)
			throws ValidateException {
		form.validatePost();
		
		return service.insert(user.getUserId(), form.getProjectName());
	}
	
	@GetMapping
	public List<PrivateProjectResponse> getPrivateProjectList(@AuthenticationPrincipal UserDetailsImp user) {
		return service.getList(user.getUserId());
	}
	
	@GetMapping("{privateProjectId}")
	public PrivateProjectResponse getPrivateProject(@AuthenticationPrincipal UserDetailsImp user, @PathVariable("privateProjectId") Integer privateProjectId) {
		return service.get(user.getUserId(), privateProjectId);
	}
	
	@PutMapping("{privateProjectId}")
	public void updatePrivateProject(@AuthenticationPrincipal UserDetailsImp user,
			@PathVariable("privateProjectId") Integer privateProjectId, @RequestBody PrivateProjectForm form) {
		
		if(form.getProjectName() != null && !form.getProjectName().isBlank()) {
			service.updateProjectName(user.getUserId(), privateProjectId, form.getProjectName());
		}
	}
	
	@DeleteMapping("{privateProjectId}")
	public void deletePrivateProject(@AuthenticationPrincipal UserDetailsImp user, @PathVariable("privateProjectId") Integer privateProjectId) {
		service.delete(user.getUserId(), privateProjectId);
	}
}
