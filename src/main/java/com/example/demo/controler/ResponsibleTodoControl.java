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
import com.example.demo.form.ResponsibleTodoForm;
import com.example.demo.response.TodoOnResponsibleResponse;
import com.example.demo.response.UserInTodoOnResponsibleResponse;
import com.example.demo.security.UserDetailsImp;
import com.example.demo.service.ResponsibleTodoService;

@RequestMapping(UrlConfig.ROOT_URL_V1 + "/todo")
@RestController
public class ResponsibleTodoControl {
	@Autowired
	ResponsibleTodoService service;
	
	@PostMapping("{todoOnProjectId}/responsible/{userName}")
	public void insertResponsibleTodo(@AuthenticationPrincipal UserDetailsImp user,
			@PathVariable("todoOnProjectId") Integer todoOnProjectId,
			@PathVariable("userName") String userName) {
		service.insert(user.getUserId(), todoOnProjectId, userName);
	}
	
	@GetMapping("responsible_todo")
	public List<TodoOnResponsibleResponse> getResponsibleTodoList(@AuthenticationPrincipal UserDetailsImp user, @RequestBody ResponsibleTodoForm form) {
		return service.getList(user.getUserId(), form);
	}
	
	@GetMapping("{todoOnProjectId}/responsible_todo")
	public TodoOnResponsibleResponse getResponsibleTodo(@AuthenticationPrincipal UserDetailsImp user, @PathVariable("todoOnProjectId") Integer todoOnProjectId) {
		return service.get(user.getUserId(), todoOnProjectId);
	}
	
	@GetMapping("{todoOnProjectId}/responsible")
	public List<UserInTodoOnResponsibleResponse> getResponsiblePeopleList(@AuthenticationPrincipal UserDetailsImp user, @PathVariable("todoOnProjectId") Integer todoOnProjectId) {
		return service.getResponsiblePeopleList(user.getUserId(), todoOnProjectId);
	}
	
	@DeleteMapping("{todoOnProjectId}/responsible/{userName}")
	public void deleteResponsibleTodo(@AuthenticationPrincipal UserDetailsImp user,
			@PathVariable("todoOnProjectId") Integer todoOnProjectId,
			@PathVariable("userName") String userName) {
		service.delete(user.getUserId(), todoOnProjectId, userName);
	}
	
	@GetMapping("{todoOnProjectId}/responsible/exit")
	public void exit(@AuthenticationPrincipal UserDetailsImp user, @PathVariable("todoOnProjectId") Integer todoOnProjectId) {
		service.exit(user.getUserId(), todoOnProjectId);
	}
		
	@PutMapping("{todoOnProjectId}/responsible/is_completed/all")
	public void setIsCompletedAll(@AuthenticationPrincipal UserDetailsImp user, 
			@RequestBody ResponsibleTodoForm form,
			@PathVariable("todoOnProjectId") Integer todoOnProjectId) throws ValidateException  {
		form.validatePutIsCompleted();
		service.setIsCompletedAll(user.getUserId(), todoOnProjectId);
	}
	
	@PutMapping("{todoOnProjectId}/responsible/is_completed")
	public void setIsCompleted(@AuthenticationPrincipal UserDetailsImp user, 
			@RequestBody ResponsibleTodoForm form,
			@PathVariable("todoOnProjectId") Integer todoOnProjectId) throws ValidateException  {
		form.validatePutIsCompletedOnUser();
		service.setIsCompleted(user.getUserId(), todoOnProjectId);
	}
}
