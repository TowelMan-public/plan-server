package com.example.demo.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.example.demo.configurer.AuthorityListInPublicProject;
import com.example.demo.entity.PublicProjectEntity;
import com.example.demo.entity.SubscriberInPublicProjectEntity;
import com.example.demo.entity.TodoOnProjectEntity;
import com.example.demo.exception.AlreadySelectedAsTodoResponsibleException;
import com.example.demo.exception.BadRequestException;
import com.example.demo.exception.NotFoundValueException;
import com.example.demo.exception.NotHaveAuthorityToOperateProjectException;
import com.example.demo.exception.NotJoinedPublicProjectException;
import com.example.demo.exception.NotSelectedAsTodoResponsibleException;
import com.example.demo.form.ResponsibleTodoForm;
import com.example.demo.logic.ContentLogic;
import com.example.demo.logic.NoticeLogic;
import com.example.demo.logic.PublicProjectLogic;
import com.example.demo.logic.SubscriberLogic;
import com.example.demo.logic.TodoOnProjectLogic;
import com.example.demo.logic.TodoOnResponsibeLogic;
import com.example.demo.logic.UserLogic;
import com.example.demo.response.TodoOnResponsibleResponse;
import com.example.demo.response.UserInTodoOnResponsibleResponse;

import lombok.Getter;
import lombok.Setter;

/**
 * 「やること」の担当者のビジネスロジック
 * @author towelman
 *
 */
@Service
public class ResponsibleTodoService {
	@Autowired
	TodoOnProjectLogic todoOnProjectLogic;
	@Autowired
	TodoOnResponsibeLogic todoOnResponsibeLogic;
	@Autowired
	SubscriberLogic subscriberLogic;
	@Autowired
	PublicProjectLogic publicProjectLogic;
	@Autowired
	UserLogic userLogic;
	@Autowired
	NoticeLogic noticeLogic;
	@Autowired
	ContentLogic contentLogic;
	
	/**
	 * 「やること」の担当者を追加
	 * @param userId ユーザID
	 * @param todoOnProjectId 「やること」ID
	 * @param userName ユーザ名
	 * @throws NotFoundValueException
	 * @throws NotJoinedPublicProjectException
	 * @throws NotHaveAuthorityToOperateProjectException
	 * @throws AlreadySelectedAsTodoResponsibleException
	 * @throws BadRequestException 既に完了しているパブリックプロジェクトの「やること」が指定されている時に投げられる例外
	 */
	@Transactional
	public void insert(Integer userId, Integer todoOnProjectId, String userName)
			throws NotFoundValueException, NotJoinedPublicProjectException, NotHaveAuthorityToOperateProjectException, AlreadySelectedAsTodoResponsibleException, BadRequestException {
		var result = this.validateUserCanSuperOperate(userId, todoOnProjectId);
		var userEntity = userLogic.getUserByUserName(userName);
		subscriberLogic.validateUserJoinPublicProject(result.getPublicProjectId(), userEntity.getUserId());
		todoOnResponsibeLogic.validateNotSelected(todoOnProjectId, userEntity.getUserId());
		
		if(result.getTodoOnProject().getIsCompleted())
			throw new BadRequestException("This todo is in a project that has already been completed.");
		
		var newTodoId = todoOnResponsibeLogic.insert(todoOnProjectId, result.getPublicProjectId(), userEntity.getUserId());
		
		if(result.getTodoOnProject().getIsCopyContentsToUsers()) {
			var contentList = contentLogic.getList(todoOnProjectId);
			for(var content: contentList) {
				if(!content.getIsCompleted())
					contentLogic.insert(newTodoId, content.getContentTitle(), content.getContentExplanation());		
			}
		}
		
		noticeLogic.createInsertedTodoNotice(userEntity.getUserId(), todoOnProjectId);
	}

	/**
	 * 担当者向けの「やること」リストを取得する
	 * @param userId ユーザID
	 * @param form 検索条件
	 * @return 担当者向けの「やること」リスト
	 * @throws NotFoundValueException
	 * @throws NotJoinedPublicProjectException
	 * @throws NotSelectedAsTodoResponsibleException
	 */
	public List<TodoOnResponsibleResponse> getList(Integer userId, ResponsibleTodoForm form)
			throws NotFoundValueException, NotJoinedPublicProjectException, NotSelectedAsTodoResponsibleException {
		List<Integer> projectIdList = new ArrayList<>();
		if(form.getPublicProjectId() != null) {
			publicProjectLogic.validatePublicProjectIdFound(form.getPublicProjectId());
			subscriberLogic.validateSubscriberFound(form.getPublicProjectId(), userId);
			projectIdList.add(form.getPublicProjectId());
		}else {
			var subscriberList = subscriberLogic.getSubscriberListByUserId(userId);
			
			for(var subscriber: subscriberList) {
				if(publicProjectLogic.isFound(subscriber.getPublicProjectId()))
					projectIdList.add(subscriber.getPublicProjectId());
			}
		}
		
		List<TodoOnResponsibleResponse> responseList = new ArrayList<>();
		for(var projectId: projectIdList) {
			var todoOnProjectList = todoOnProjectLogic.getListByExampleIf(projectId, form.getStartDate(), form.getFinishDate(), form.getIsInclideCompletedTodo());
			
			for(var todoOnProject: todoOnProjectList) {
				var todoOnResponsible = todoOnResponsibeLogic.get(todoOnProject.getTodoOnProjectId(), userId);
				responseList.add(new TodoOnResponsibleResponse(todoOnResponsible, todoOnProject));
			}
		}
		
		return responseList;
	}

	/**
	 * 「やること」の担当者を取得する
	 * @param userId ユーザID
	 * @param todoOnProjectId 「やること」ID
	 * @return 「やること」の担当者
	 * @throws NotFoundValueException
	 * @throws NotJoinedPublicProjectException
	 * @throws NotSelectedAsTodoResponsibleException
	 */
	@Transactional
	public TodoOnResponsibleResponse get(Integer userId, Integer todoOnProjectId) 
			throws NotFoundValueException, NotJoinedPublicProjectException, NotSelectedAsTodoResponsibleException {
		var result = this.validateUserCanLook(userId, todoOnProjectId);
		var todoOnResponsibe = todoOnResponsibeLogic.get(todoOnProjectId, userId);
		
		return new TodoOnResponsibleResponse(todoOnResponsibe, result.getTodoOnProject());
	}

	/**
	 * 「やること」の担当者リストを取得する
	 * @param userId ユーザID
	 * @param todoOnProjectId 「やること」ID
	 * @return 「やること」の担当者リスト
	 * @throws NotFoundValueException
	 * @throws NotJoinedPublicProjectException
	 */
	public List<UserInTodoOnResponsibleResponse> getResponsiblePeopleList(Integer userId, Integer todoOnProjectId)
			throws NotFoundValueException, NotJoinedPublicProjectException {
		this.validateUserCanLook(userId, todoOnProjectId);
		var todoOnResponsibeList = todoOnResponsibeLogic.getTodoOnResponsibleList(todoOnProjectId);
		List<UserInTodoOnResponsibleResponse> responseList = new ArrayList<>();
		
		for(var todoOnResponsible: todoOnResponsibeList){
			var userEntity = userLogic.getUserByUserId(todoOnResponsible.getUserId());
			if(userEntity != null)
				responseList.add(new UserInTodoOnResponsibleResponse(todoOnResponsible, userEntity));
		}
		
		return responseList;
	}

	/**
	 * 「やること」の担当者を除外する
	 * @param userId ユーザID
	 * @param todoOnProjectId 「やること」ID
	 * @param userName ユーザ名
	 * @throws NotFoundValueException
	 * @throws NotJoinedPublicProjectException
	 * @throws NotHaveAuthorityToOperateProjectException
	 * @throws NotSelectedAsTodoResponsibleException
	 */
	@Transactional
	public void delete(Integer userId, Integer todoOnProjectId, String userName) 
			throws NotFoundValueException, NotJoinedPublicProjectException, NotHaveAuthorityToOperateProjectException, NotSelectedAsTodoResponsibleException {
		this.validateUserCanSuperOperate(userId, todoOnProjectId);
		var userEntity = userLogic.getUserByUserName(userName);
		todoOnResponsibeLogic.validateSelected(todoOnProjectId, userEntity.getUserId());
		
		todoOnResponsibeLogic.delete(todoOnProjectId, userEntity.getUserId());
		noticeLogic.eraseTodoNotice(userEntity.getUserId(), todoOnProjectId);
	}

	/**
	 * 「やること」の担当者を辞退する
	 * @param userId ユーザID
	 * @param todoOnProjectId 「やること」ID
	 * @throws NotFoundValueException
	 * @throws NotJoinedPublicProjectException
	 * @throws NotSelectedAsTodoResponsibleException
	 */
	@Transactional
	public void exit(Integer userId, Integer todoOnProjectId)
			throws NotFoundValueException, NotJoinedPublicProjectException, NotSelectedAsTodoResponsibleException {
		this.validateUserCanNormalOperate(userId, todoOnProjectId);
	
		todoOnResponsibeLogic.delete(todoOnProjectId, userId);
		noticeLogic.eraseTodoNotice(userId, todoOnProjectId);
	}

	/**
	 * 「やること」の担当者全員に完了状況をセットする
	 * @param userId ユーザID
	 * @param todoOnProjectId 「やること」ID
	 * @param isCompleted 完了状況
	 * @throws NotFoundValueException
	 * @throws NotJoinedPublicProjectException
	 * @throws NotHaveAuthorityToOperateProjectException
	 */
	@Transactional
	public void setIsCompletedAll(Integer userId, Integer todoOnProjectId, Boolean isCompleted)
			throws NotFoundValueException, NotJoinedPublicProjectException, NotHaveAuthorityToOperateProjectException {
		var result = this.validateUserCanSuperOperate(userId, todoOnProjectId);
		
		var todoOnResponsibleList = todoOnResponsibeLogic.getTodoOnResponsibleList(todoOnProjectId);
		var todoOnProject = result.getTodoOnProject();
		if(!todoOnProject.getIsCompleted().booleanValue() && isCompleted) {
			for(var todoOnResposible: todoOnResponsibleList) {
				todoOnResponsibeLogic.updateIsCompleted(todoOnProjectId, todoOnResposible.getUserId(), true);
				todoOnProjectLogic.updateIsCompleted(todoOnProjectId, true);
				noticeLogic.eraseTodoNotice(todoOnResposible.getUserId(), todoOnProjectId);
				noticeLogic.createCompletedTodoNotice(todoOnResposible.getUserId(), todoOnProjectId);
			}
		}else if(todoOnProject.getIsCompleted().booleanValue() && !isCompleted) {
			for(var todoOnResposible: todoOnResponsibleList) {
				todoOnResponsibeLogic.updateIsCompleted(todoOnProjectId, todoOnResposible.getUserId(), false);
				todoOnProjectLogic.updateIsCompleted(todoOnProjectId, false);
				noticeLogic.eraseTodoNotice(todoOnResposible.getUserId(), todoOnProjectId);
				noticeLogic.createUnCompletedTodoNotice(todoOnResposible.getUserId(), todoOnProjectId);
			}
		}
	}

	/**
	 * 「やること」の担当者が完了状況をセットする
	 * @param userId ユーザID
	 * @param todoOnProjectId 「やること」ID
	 * @param isCompleted 完了状況
	 * @throws NotFoundValueException
	 * @throws NotJoinedPublicProjectException
	 * @throws NotSelectedAsTodoResponsibleException
	 */
	@Transactional
	public void setIsCompleted(Integer userId, Integer todoOnProjectId, Boolean isCompleted)
			throws NotFoundValueException, NotJoinedPublicProjectException, NotSelectedAsTodoResponsibleException {
		this.validateUserCanNormalOperate(userId, todoOnProjectId);
		
		todoOnResponsibeLogic.updateIsCompleted(todoOnProjectId, userId, isCompleted);
	}

	/**
	 * 「やること」を閲覧することができるという条件に当てはまっているかを検証
	 * <br>projectIdとtodoOnProjectIdはどちらかであればnull可能
	 * @param userId ユーザID
	 * @param projectId プロジェクトID
	 * @param todoOnProjectId 「やること」ID
	 * @return 検証結果
	 * @throws NotFoundValueException
	 * @throws NotJoinedPublicProjectException
	 */
	private ValidateResult validateUserCanLook(Integer userId, Integer todoOnProjectId)
			throws NotFoundValueException, NotJoinedPublicProjectException {
		var result = new ValidateResult();
		
		result.setTodoOnProject(todoOnProjectLogic.get(todoOnProjectId));
		var projectId = result.getPublicProjectId();
		
		result.setPublicProjectEntity(publicProjectLogic.get(projectId));
		result.setUserAuthorityInPublicProject(subscriberLogic.getAuthority(projectId, userId));
		
		return result;
	}
	
	/**
	 * 「やること」の担当者、もしくはプロジェクトの権限者であれば操作できるという条件に当てはまっているかを検証する
	 * <br>projectIdはnull可能
	 * @param userId ユーザID
	 * @param projectId プロジェクトID
	 * @param todoOnProjectId 「やること」ID
	 * @return 検証結果
	 * @throws NotFoundValueException
	 * @throws NotJoinedPublicProjectException
	 * @throws NotSelectedAsTodoResponsibleException
	 */
	private ValidateResult validateUserCanNormalOperate(Integer userId, Integer todoOnProjectId)
			throws NotFoundValueException, NotJoinedPublicProjectException, NotSelectedAsTodoResponsibleException {
		var result = this.validateUserCanLook(userId, todoOnProjectId);
		
		todoOnResponsibeLogic.validateSelected(todoOnProjectId, userId);
		
		return result;
	}
	
	/**
	 * プロジェクトの権限者であれば操作できるという条件に当てはまっているかを検証する
	 * <br>projectIdとtodoOnProjectIdはどちらかであればnull可能
	 * @param userId ユーザID
	 * @param projectId プロジェクトID
	 * @param todoOnProjectId 「やること」ID
	 * @return 検証結果
	 * @throws NotFoundValueException
	 * @throws NotJoinedPublicProjectException
	 * @throws NotHaveAuthorityToOperateProjectException
	 */
	private ValidateResult validateUserCanSuperOperate(Integer userId, Integer todoOnProjectId)
			throws NotFoundValueException, NotJoinedPublicProjectException, NotHaveAuthorityToOperateProjectException {
		var result = this.validateUserCanLook(userId, todoOnProjectId);
			
		if(result.getUserAuthorityInPublicProject().getAuthorityId() == AuthorityListInPublicProject.SUPER) {
			return result;
		}else
			throw new NotHaveAuthorityToOperateProjectException();
	}
	
	/**
	 * ResponsibleTodoServiceのvalidateUserと頭につく関数を実行したときに成功時に返される、検証結果
	 * @author towelman
	 *
	 */
	private class ValidateResult{
		@Getter
		@Setter
		private PublicProjectEntity publicProjectEntity;
		
		@Getter
		@Setter
		private SubscriberInPublicProjectEntity userAuthorityInPublicProject;
		
		@Getter
		@Setter
		private TodoOnProjectEntity todoOnProject;
		
		/**
		 * 検証したパブリックプロジェクトIDを返す
		 * @return パブリックプロジェクトID、なければnullを返す
		 */
		public Integer getPublicProjectId() {
			if(todoOnProject == null)
				return null;
			else
				return todoOnProject.getProjectId();
		}
	}
}
