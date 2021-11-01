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
import com.example.demo.exception.BadRequestException;
import com.example.demo.exception.NotFoundValueException;
import com.example.demo.exception.NotHaveAuthorityToOperateProjectException;
import com.example.demo.exception.NotJoinedPublicProjectException;
import com.example.demo.exception.NotSelectedAsTodoResponsibleException;
import com.example.demo.exception.ValidateException;
import com.example.demo.form.TodoForm;
import com.example.demo.response.TodoOnProjectResponse;
import com.example.demo.security.UserDetailsImp;
import com.example.demo.service.TodoService;

/**
 * 「やること」に関するAPIのアクセスポイント
 * 
 * @version 1
 * @since 1
 * @author towelman
 *
 */
@RequestMapping(UrlConfig.ROOT_URL_V1 + "/todo")
@RestController
public class TodoControl {
	@Autowired
	TodoService service;
	
	/**
	 * 「やること」の新規登録
	 * @param user
	 * @param form
	 * @return 新しい「やること」ID
	 * @throws ValidateException
	 * @throws BadRequestException 
	 * @throws NotHaveAuthorityToOperateProjectException 
	 * @throws NotJoinedPublicProjectException 
	 * @throws NotFoundValueException 
	 */
	@PostMapping
	public Integer inertTodo(@AuthenticationPrincipal UserDetailsImp user, @RequestBody TodoForm form)
			throws ValidateException, NotFoundValueException, NotJoinedPublicProjectException, NotHaveAuthorityToOperateProjectException, BadRequestException {
		form.validatePost();
		return service.insert(user.getUserId(), form);
	}
	
	/**
	 * 「やること」の取得
	 * @param user
	 * @param todoOnProjectId
	 * @return 「やること」
	 * @throws NotJoinedPublicProjectException 
	 * @throws NotFoundValueException 
	 */
	@GetMapping("{todoOnProjectId}")
	public TodoOnProjectResponse getTodo(@AuthenticationPrincipal UserDetailsImp user, @PathVariable("todoOnProjectId") Integer todoOnProjectId) 
			throws NotFoundValueException, NotJoinedPublicProjectException {
		return service.get(user.getUserId(), todoOnProjectId);
	}
	
	/**
	 * 「やること」の複数条件による検索
	 * @param user
	 * @param form
	 * @return 「やること」リスト
	 * @throws NotJoinedPublicProjectException 
	 * @throws NotFoundValueException 
	 */
	@GetMapping
	public List<TodoOnProjectResponse> getTodoList(@AuthenticationPrincipal UserDetailsImp user, TodoForm form) 
			throws NotFoundValueException, NotJoinedPublicProjectException {
		return service.getList(user.getUserId(), form);
	}
	
	/**
	 * 「やること」に変更を加える
	 * @param user
	 * @param form
	 * @param todoOnProjectId
	 * @throws NotHaveAuthorityToOperateProjectException 
	 * @throws NotJoinedPublicProjectException 
	 * @throws NotFoundValueException 
	 * @throws BadRequestException 
	 * @throws NotSelectedAsTodoResponsibleException 
	 */
	@PutMapping("{todoOnProjectId}")
	public void updateTodo(@AuthenticationPrincipal UserDetailsImp user,
			@RequestBody TodoForm form,
			@PathVariable("todoOnProjectId") Integer todoOnProjectId)
					throws BadRequestException, NotFoundValueException, NotJoinedPublicProjectException, NotHaveAuthorityToOperateProjectException, 
					NotSelectedAsTodoResponsibleException {
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
	
	/**
	 * 「やること」を削除する
	 * @param user
	 * @param todoOnProjectId
	 * @throws NotHaveAuthorityToOperateProjectException 
	 * @throws NotJoinedPublicProjectException 
	 * @throws NotFoundValueException 
	 */
	@DeleteMapping("{todoOnProjectId}")
	public void deleteTodo(@AuthenticationPrincipal UserDetailsImp user, @PathVariable("todoOnProjectId") Integer todoOnProjectId) 
			throws NotFoundValueException, NotJoinedPublicProjectException, NotHaveAuthorityToOperateProjectException {
		service.delete(user.getUserId(), todoOnProjectId);
	}
	
	/**
	 * 「やること」に完了状況をセットする
	 * @param user
	 * @param form
	 * @param todoOnProjectId
	 * @throws ValidateException
	 * @throws NotHaveAuthorityToOperateProjectException 
	 * @throws NotJoinedPublicProjectException 
	 * @throws NotFoundValueException 
	 */
	@PutMapping("{todoOnProjectId}/is_completed")
	public void setIsCompleted(@AuthenticationPrincipal UserDetailsImp user,
			@RequestBody TodoForm form,
			@PathVariable("todoOnProjectId") Integer todoOnProjectId) 
					throws ValidateException, NotFoundValueException, NotJoinedPublicProjectException, NotHaveAuthorityToOperateProjectException {
		form.validatePutIsCompleted();
		service.setIsCompleted(user.getUserId(), todoOnProjectId, form.getIsCompleted());
	}
}
