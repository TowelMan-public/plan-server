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
import com.example.demo.exception.AlreadySelectedAsTodoResponsibleException;
import com.example.demo.exception.BadRequestException;
import com.example.demo.exception.NotFoundValueException;
import com.example.demo.exception.NotHaveAuthorityToOperateProjectException;
import com.example.demo.exception.NotJoinedPublicProjectException;
import com.example.demo.exception.NotSelectedAsTodoResponsibleException;
import com.example.demo.exception.ValidateException;
import com.example.demo.form.ResponsibleTodoForm;
import com.example.demo.response.TodoOnResponsibleResponse;
import com.example.demo.response.UserInTodoOnResponsibleResponse;
import com.example.demo.security.UserDetailsImp;
import com.example.demo.service.ResponsibleTodoService;

/**
 * 「やること」の担当者に関するAPIのアクセスポイント
 * 
 * @version 1
 * @since 1
 * @author towelman
 *
 */
@RequestMapping(UrlConfig.ROOT_URL_V1 + "/todo")
@RestController
public class ResponsibleTodoControl {
	@Autowired
	ResponsibleTodoService service;
	
	/**
	 * 「やること」の担当者を追加する
	 * @param user
	 * @param todoOnProjectId
	 * @param userName
	 * @throws NotFoundValueException
	 * @throws NotJoinedPublicProjectException
	 * @throws NotHaveAuthorityToOperateProjectException
	 * @throws AlreadySelectedAsTodoResponsibleException
	 * @throws BadRequestException 
	 */
	@PostMapping("{todoOnProjectId}/responsible/{userName}")
	public void insertResponsibleTodo(@AuthenticationPrincipal UserDetailsImp user,
			@PathVariable("todoOnProjectId") Integer todoOnProjectId,
			@PathVariable("userName") String userName) 
					throws NotFoundValueException, NotJoinedPublicProjectException, NotHaveAuthorityToOperateProjectException, AlreadySelectedAsTodoResponsibleException, BadRequestException {
		service.insert(user.getUserId(), todoOnProjectId, userName);
	}
	
	/**
	 * 担当者向け「やること」リストを複数の条件から検索し、取得する
	 * @param user
	 * @param form
	 * @return 担当者向け「やること」リスト
	 * @throws NotFoundValueException
	 * @throws NotJoinedPublicProjectException
	 * @throws NotSelectedAsTodoResponsibleException
	 */
	@GetMapping("responsible_todo")
	public List<TodoOnResponsibleResponse> getResponsibleTodoList(@AuthenticationPrincipal UserDetailsImp user, @RequestBody ResponsibleTodoForm form) 
			throws NotFoundValueException, NotJoinedPublicProjectException, NotSelectedAsTodoResponsibleException {
		return service.getList(user.getUserId(), form);
	}
	
	/**
	 * 「やること」の担当者を取得する
	 * @param user
	 * @param todoOnProjectId
	 * @return 「やること」の担当者
	 * @throws NotFoundValueException
	 * @throws NotJoinedPublicProjectException
	 * @throws NotSelectedAsTodoResponsibleException
	 */
	@GetMapping("{todoOnProjectId}/responsible_todo")
	public TodoOnResponsibleResponse getResponsibleTodo(@AuthenticationPrincipal UserDetailsImp user, @PathVariable("todoOnProjectId") Integer todoOnProjectId) 
			throws NotFoundValueException, NotJoinedPublicProjectException, NotSelectedAsTodoResponsibleException {
		return service.get(user.getUserId(), todoOnProjectId);
	}
	
	/**
	 * 「やること」の担当者リストを取得する
	 * @param user
	 * @param todoOnProjectId
	 * @return 「やること」の担当者リスト
	 * @throws NotFoundValueException
	 * @throws NotJoinedPublicProjectException
	 */
	@GetMapping("{todoOnProjectId}/responsible")
	public List<UserInTodoOnResponsibleResponse> getResponsiblePeopleList(@AuthenticationPrincipal UserDetailsImp user, @PathVariable("todoOnProjectId") Integer todoOnProjectId) 
			throws NotFoundValueException, NotJoinedPublicProjectException {
		return service.getResponsiblePeopleList(user.getUserId(), todoOnProjectId);
	}
	
	/**
	 * 「やること」の担当者を除外する
	 * @param user
	 * @param todoOnProjectId
	 * @param userName
	 * @throws NotFoundValueException
	 * @throws NotJoinedPublicProjectException
	 * @throws NotHaveAuthorityToOperateProjectException
	 * @throws NotSelectedAsTodoResponsibleException
	 */
	@DeleteMapping("{todoOnProjectId}/responsible/{userName}")
	public void deleteResponsibleTodo(@AuthenticationPrincipal UserDetailsImp user,
			@PathVariable("todoOnProjectId") Integer todoOnProjectId,
			@PathVariable("userName") String userName) 
					throws NotFoundValueException, NotJoinedPublicProjectException, NotHaveAuthorityToOperateProjectException, NotSelectedAsTodoResponsibleException {
		service.delete(user.getUserId(), todoOnProjectId, userName);
	}
	
	/**
	 * 「やること」の担当者から辞退する
	 * @param user
	 * @param todoOnProjectId
	 * @throws NotFoundValueException
	 * @throws NotJoinedPublicProjectException
	 * @throws NotSelectedAsTodoResponsibleException
	 */
	@GetMapping("{todoOnProjectId}/responsible/exit")
	public void exit(@AuthenticationPrincipal UserDetailsImp user, @PathVariable("todoOnProjectId") Integer todoOnProjectId) 
			throws NotFoundValueException, NotJoinedPublicProjectException, NotSelectedAsTodoResponsibleException {
		service.exit(user.getUserId(), todoOnProjectId);
	}
		
	/**
	 * 全ての担当者に完了状況をセットする
	 * @param user
	 * @param form
	 * @param todoOnProjectId
	 * @throws ValidateException
	 * @throws NotFoundValueException
	 * @throws NotJoinedPublicProjectException
	 * @throws NotHaveAuthorityToOperateProjectException
	 */
	@PutMapping("{todoOnProjectId}/responsible/is_completed/all")
	public void setIsCompletedAll(@AuthenticationPrincipal UserDetailsImp user, 
			@RequestBody ResponsibleTodoForm form,
			@PathVariable("todoOnProjectId") Integer todoOnProjectId)
					throws ValidateException, NotFoundValueException, NotJoinedPublicProjectException, NotHaveAuthorityToOperateProjectException  {
		form.validatePutIsCompleted();
		service.setIsCompletedAll(user.getUserId(), todoOnProjectId, form.getIsCompleted());
	}
	
	/**
	 * 完了状況をセットする
	 * @param user
	 * @param form
	 * @param todoOnProjectId
	 * @throws ValidateException
	 * @throws NotFoundValueException
	 * @throws NotJoinedPublicProjectException
	 * @throws NotSelectedAsTodoResponsibleException
	 */
	@PutMapping("{todoOnProjectId}/responsible/is_completed")
	public void setIsCompleted(@AuthenticationPrincipal UserDetailsImp user, 
			@RequestBody ResponsibleTodoForm form,
			@PathVariable("todoOnProjectId") Integer todoOnProjectId)
					throws ValidateException, NotFoundValueException, NotJoinedPublicProjectException, NotSelectedAsTodoResponsibleException  {
		form.validatePutIsCompletedOnUser();
		service.setIsCompleted(user.getUserId(), todoOnProjectId, form.getIsCompleted());
	}
}
