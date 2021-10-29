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
import com.example.demo.form.ProjectForm;
import com.example.demo.response.PublicProjectResponse;
import com.example.demo.security.UserDetailsImp;
import com.example.demo.service.PublicProjectService;

@RequestMapping(UrlConfig.ROOT_URL_V1 + "/project")
@RestController
public class ProjectControl {
	@Autowired
	PublicProjectService service;
	
	@PostMapping
	public Integer insertProject(@AuthenticationPrincipal UserDetailsImp user, @RequestBody ProjectForm form)
			throws ValidateException {
		form.validatePost();
		return service.insert(user.getUserId(), form);
	}
	
	@GetMapping("{publicProjectId}")
	public PublicProjectResponse getPublicProject(@AuthenticationPrincipal UserDetailsImp user, @PathVariable("publicProjectId") Integer publicProjectId) {
		return service.get(user.getUserId(), publicProjectId);
	}
	
	@GetMapping
	public List<PublicProjectResponse> getPublicProjectList(@AuthenticationPrincipal UserDetailsImp user) {
		return service.getList(user.getUserId());
	}
	
	@PutMapping("{publicProjectId}")
	public void updatePublicProject(@AuthenticationPrincipal UserDetailsImp user,
			@PathVariable("publicProjectId") Integer publicProjectId,
			@RequestBody ProjectForm form) {
		if(form.getProjectName() != null && !form.getProjectName().isBlank()) {
			service.updateProjectName(user.getUserId(), publicProjectId, form.getProjectName());
		}
		if(form.getStartDate() != null) {
			service.updateStartDate(user.getUserId(), publicProjectId, form.getStartDate());
		}
		if(form.getFinishDate() != null) {
			service.updateFinishDate(user.getUserId(), publicProjectId, form.getFinishDate());	
		}
	}
	
	@DeleteMapping("{publicProjectId}")
	public void deletePublicProject(@AuthenticationPrincipal UserDetailsImp user, @PathVariable("publicProjectId") Integer publicProjectId) {
		service.delete(user.getUserId(), publicProjectId);
	}
	
	@PostMapping("{publicProjectId}/is_completed")
	public void setIsCompleted(@AuthenticationPrincipal UserDetailsImp user,
			@PathVariable("publicProjectId") Integer publicProjectId,
			@RequestBody ProjectForm form) throws ValidateException {
		form.validatePutIsCompleted();
		service.setIsCompleted(user.getUserId(), publicProjectId, form.getIsCompleted());
	}
	
	
	@GetMapping("solicited")
	public List<PublicProjectResponse> getSolicitedPublicProjectList(@AuthenticationPrincipal UserDetailsImp user) {
		return service.getSolicited(user.getUserId());
	}
}
