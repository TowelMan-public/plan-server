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
import com.example.demo.form.TodoForm;
import com.example.demo.response.TodoOnProjectResponse;
import com.example.demo.security.UserDetailsImp;
import com.example.demo.service.TodoService;

@RequestMapping(UrlConfig.ROOT_URL_V1 + "/todo")
@RestController
public class TodoControl {
	@Autowired
	TodoService service;
	
	@PostMapping
	public Integer inertTodo(@AuthenticationPrincipal UserDetailsImp user, @RequestBody TodoForm form)
			throws ValidateException {
		form.validatePost();
		return service.insert(user.getUserId(), form);
	}
	
	@GetMapping("{todoOnProjectId}")
	public TodoOnProjectResponse getTodo(@AuthenticationPrincipal UserDetailsImp user, @PathVariable("todoOnProjectId") Integer todoOnProjectId) {
		return service.get(user.getUserId(), todoOnProjectId);
	}
	
	@GetMapping
	public List<TodoOnProjectResponse> getTodoList(@AuthenticationPrincipal UserDetailsImp user, TodoForm form) {
		return service.getList(user.getUserId(), form);
	}
	
	@PutMapping("{todoOnProjectId}")
	public void updateTodo(@AuthenticationPrincipal UserDetailsImp user,
			@RequestBody TodoForm form,
			@PathVariable("todoOnProjectId") Integer todoOnProjectId) {
		if(form.getIsCopyContentsToResponsible() != null) {
			service.updateIsCopyContentsToResponsible(user.getUserId(), todoOnProjectId, form.getIsCopyContentsToResponsible());
		}
		if(form.getTodoName() != null && !form.getTodoName().isBlank()) {
			service.updateTodoName(user.getUserId(), todoOnProjectId, form.getTodoName());
		}
		if(form.getStartDate() != null) {
			service.updateStartDate(user.getUserId(), todoOnProjectId, form.getStartDate());
		}
		if(form.getFinishDate() != null) {
			service.updateFinishDate(user.getUserId(), todoOnProjectId, form.getFinishDate());
		}
	}
	
	@DeleteMapping("{todoOnProjectId}")
	public void deleteTodo(@AuthenticationPrincipal UserDetailsImp user, @PathVariable("todoOnProjectId") Integer todoOnProjectId) {
		service.delete(user.getUserId(), todoOnProjectId);
	}
	
	@PutMapping("{todoOnProjectId}/is_completed")
	public void setIsCompleted(@AuthenticationPrincipal UserDetailsImp user,
			@RequestBody TodoForm form,
			@PathVariable("todoOnProjectId") Integer todoOnProjectId) throws ValidateException {
		form.validatePutIsCompleted();
		service.setIsCompleted(user.getUserId(), todoOnProjectId, form.getIsCompleted());
	}
}
