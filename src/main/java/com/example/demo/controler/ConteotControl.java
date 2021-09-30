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
import com.example.demo.form.ContentForm;
import com.example.demo.response.ContentResponse;
import com.example.demo.security.UserDetailsImp;
import com.example.demo.service.ContentService;

@RequestMapping(UrlConfig.ROOT_URL_V1 + "/content")
@RestController
public class ConteotControl {
	@Autowired
	ContentService service;
	
	@PostMapping
	public Integer insertContent(@AuthenticationPrincipal UserDetailsImp user, @RequestBody ContentForm form) 
			throws ValidateException {
		form.validatePost();
		return service.insert(user.getUserId(), form);
	}
	
	@GetMapping
	public List<ContentResponse> getContentList(@AuthenticationPrincipal UserDetailsImp user, ContentForm form)
			throws ValidateException {
		form.validateGet();
		return service.getList(user.getUserId(), form.getTodoId());
	}
	
	@GetMapping("{contentId}")
	public ContentResponse getContentList(@AuthenticationPrincipal UserDetailsImp user, @PathVariable("contentId") Integer contentId) {
		return service.get(user.getUserId(), contentId);
	}
	
	@PutMapping("{contentId}")
	public void updateContent(@AuthenticationPrincipal UserDetailsImp user, 
			@RequestBody ContentForm form,
			@PathVariable("contentId") Integer contentId) throws ValidateException{
		if(form.getContentTitle() != null && !form.getContentTitle().isBlank()) {
			service.updateTitle(user.getUserId(), contentId, form.getContentTitle());
		}
		if(form.getContentExplanation() != null && !form.getContentExplanation().isBlank()) {
			service.updateExplanation(user.getUserId(), contentId, form.getContentExplanation());
		}
	}
	
	@DeleteMapping("{contentId}")
	public void deleteContent(@AuthenticationPrincipal UserDetailsImp user, @PathVariable("contentId") Integer contentId) {
		service.delete(user.getUserId(), contentId);
	}
	
	@PutMapping("{contentId}/is_completed")
	public void setIsCompleted(@AuthenticationPrincipal UserDetailsImp user, 
			@RequestBody ContentForm form,
			@PathVariable("contentId") Integer contentId) throws ValidateException{
		form.validatePutIsCompleted();
		service.setIsCompleted(user.getUserId(), contentId, form.getIsCompleted());
	}
}