package com.example.demo.service;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.example.demo.configurer.AuthorityListInPublicProject;
import com.example.demo.entity.PublicProjectEntity;
import com.example.demo.entity.SubscriberInPublicProjectEntity;
import com.example.demo.entity.TodoOnProjectEntity;
import com.example.demo.entity.TodoOnResponsibleEntity;
import com.example.demo.exception.BadRequestException;
import com.example.demo.exception.NotFoundValueException;
import com.example.demo.exception.NotHaveAuthorityToOperateProjectException;
import com.example.demo.exception.NotJoinedPublicProjectException;
import com.example.demo.exception.NotSelectedAsTodoResponsibleException;
import com.example.demo.form.TodoForm;
import com.example.demo.logic.NoticeLogic;
import com.example.demo.logic.PrivateProjectLogic;
import com.example.demo.logic.PublicProjectLogic;
import com.example.demo.logic.SubscriberLogic;
import com.example.demo.logic.TodoOnProjectLogic;
import com.example.demo.logic.TodoOnResponsibeLogic;
import com.example.demo.logic.UserLogic;
import com.example.demo.response.TodoOnProjectResponse;

import lombok.Getter;
import lombok.Setter;

/**
 * 「やること」のビジネスロジック
 * @author towelman
 *
 */
@Service
public class TodoService {
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
	PrivateProjectLogic privateProjectLogic;
		
	/**
	 * 「やること」の新規作成
	 * @param userId ユーザID
	 * @param form 新しく作る「やること」の情報
	 * @return 新しい「やること」のID
	 * @throws NotFoundValueException
	 * @throws NotJoinedPublicProjectException
	 * @throws NotHaveAuthorityToOperateProjectException
	 * @throws BadRequestException 日付の範囲がおかしいときに投げられる例外
	 */
	@Transactional
	public Integer insert(Integer userId, TodoForm form)
			throws NotFoundValueException, NotJoinedPublicProjectException, NotHaveAuthorityToOperateProjectException, BadRequestException {
		var result = this.validateUserCanSuperOperate(userId, form.getProjectId(), null);
		
		if(!result.isPrivateProject())
			this.validateDateRangeInPulicProject(result.getPublicProjectEntity(), form.getStartDate(), form.getFinishDate());
		
		return todoOnProjectLogic.insert(form.getProjectId(), form.getTodoName(), form.getStartDate(),
				form.getFinishDate(), form.getIsCopyContentsToResponsible());
	}

	/**
	 * 「やること」を取得する
	 * @param userId ユーザID
	 * @param todoOnProjectId 「やること」ID
	 * @return 「やること」
	 * @throws NotFoundValueException
	 * @throws NotJoinedPublicProjectException
	 */
	@Transactional
	public TodoOnProjectResponse get(Integer userId, Integer todoOnProjectId)
			throws NotFoundValueException, NotJoinedPublicProjectException {
		var result = this.validateUserCanLook(userId, null, todoOnProjectId);
		
		noticeLogic.eraseTodoNotice(userId, todoOnProjectId);
		return new TodoOnProjectResponse(result.getTodoOnProject());
	}

	/**
	 * 「やること」を複数の条件で取得する
	 * @param userId ユーザID
	 * @param form 検索条件
	 * @return 「やること」リスト
	 * @throws NotFoundValueException
	 * @throws NotJoinedPublicProjectException
	 */
	@Transactional
	public List<TodoOnProjectResponse> getList(Integer userId, TodoForm form) throws NotFoundValueException, NotJoinedPublicProjectException {
		//projectの取得
		List<Integer> projectIdList = new ArrayList<>();
		if(form.getProjectId() != null) {
			this.validateUserCanLook(userId, form.getProjectId(), null);
			projectIdList.add(form.getProjectId());
		}else {
			if(form.getIsInPrivateProjectOnly() == null || form.getIsInPrivateProjectOnly()) {
				var projectList = privateProjectLogic.getList(userId);
				
				for(var project: projectList)
					projectIdList.add(project.getProjectId());
			}
			if(form.getIsInPrivateProjectOnly() == null || !form.getIsInPrivateProjectOnly()) {
				var subscriberList = subscriberLogic.getSubscriberListByUserId(userId);
				
				for(var subscriber: subscriberList) {
					if(publicProjectLogic.isFound(subscriber.getPublicProjectId()))
						projectIdList.add(subscriber.getPublicProjectId());
				}
			}
		}
		
		List<TodoOnProjectResponse> responseList = new ArrayList<>();
		for(var projectId: projectIdList) {
			var todoOnProjectList = todoOnProjectLogic.getListByExampleIf(projectId, form.getStartDate(), form.getFinishDate(), form.getIsInclideCompletedTodo());
			
			for(var todoOnProject: todoOnProjectList)
				responseList.add(new TodoOnProjectResponse(todoOnProject));
		}
		
		return responseList;
	}

	/**
	 * 担当者にも「やること」の内容をコピーさせるかを変更する
	 * @param userId ユーザID
	 * @param todoOnProjectId 「やること」ID
	 * @param isCopyContentsToResponsible 担当者にも「やること」の内容をコピーさせるかどうか。trueで有効、falseで無効
	 * @throws NotFoundValueException
	 * @throws NotJoinedPublicProjectException
	 * @throws NotSelectedAsTodoResponsibleException
	 */
	@Transactional
	public void updateIsCopyContentsToResponsible(Integer userId, Integer todoOnProjectId,
			Boolean isCopyContentsToResponsible) throws NotFoundValueException, NotJoinedPublicProjectException, NotSelectedAsTodoResponsibleException {
		this.validateUserCanNormalOperate(userId, null, todoOnProjectId);
		
		todoOnProjectLogic.updateIsCopyContentsToResponsible(todoOnProjectId, isCopyContentsToResponsible);
		noticeLogic.eraseTodoNotice(userId, todoOnProjectId);
	}

	/**
	 * 「やること」の名前を変更する
	 * @param userId ユーザID
	 * @param todoOnProjectId 「やること」ID
	 * @param todoName 「やること」の名前
	 * @throws NotFoundValueException
	 * @throws NotJoinedPublicProjectException
	 * @throws NotHaveAuthorityToOperateProjectException
	 */
	@Transactional
	public void updateTodoName(Integer userId, Integer todoOnProjectId, String todoName)
			throws NotFoundValueException, NotJoinedPublicProjectException, NotHaveAuthorityToOperateProjectException {
		this.validateUserCanSuperOperate(userId, null, todoOnProjectId);
		
		todoOnProjectLogic.updateName(todoOnProjectId, todoName);
		noticeLogic.eraseTodoNotice(userId, todoOnProjectId);
	}

	/**
	 * 「やること」の開始日時を変更する
	 * @param userId ユーザID
	 * @param todoOnProjectId 「やること」ID
	 * @param startDate 開始日時
	 * @throws BadRequestException 日付の範囲がおかしいときに投げられる例外
	 * @throws NotFoundValueException
	 * @throws NotJoinedPublicProjectException
	 * @throws NotHaveAuthorityToOperateProjectException
	 */
	@Transactional
	public void updateStartDate(Integer userId, Integer todoOnProjectId, Date startDate)
			throws BadRequestException, NotFoundValueException, NotJoinedPublicProjectException, NotHaveAuthorityToOperateProjectException {
		var result = this.validateUserCanSuperOperate(userId, null, todoOnProjectId);
		
		if(!result.isPrivateProject())
			this.validateDateRangeInPulicProject(result.getPublicProjectEntity(), startDate, null);
		
		var todoOnProject = result.getTodoOnProject();
		if(todoOnProject.getFinishDate().before(startDate))
			throw new BadRequestException("The deadline has been exceeded.");
		
		todoOnProjectLogic.updateStartDate(todoOnProjectId, startDate);
		noticeLogic.eraseTodoNotice(userId, todoOnProjectId);
	}

	/**
	 * 「やること」の締め切り日時を変更する
	 * @param userId ユーザID
	 * @param todoOnProjectId 「やること」ID
	 * @param finishDate 締め切り日時
	 * @throws BadRequestException 日付の範囲がおかしいときに投げられる例外
	 * @throws NotFoundValueException
	 * @throws NotJoinedPublicProjectException
	 * @throws NotHaveAuthorityToOperateProjectException
	 */
	@Transactional
	public void updateFinishDate(Integer userId, Integer todoOnProjectId, Date finishDate)
			throws BadRequestException, NotFoundValueException, NotJoinedPublicProjectException, NotHaveAuthorityToOperateProjectException {
		var result = this.validateUserCanSuperOperate(userId, null, todoOnProjectId);
		
		if(!result.isPrivateProject())
			this.validateDateRangeInPulicProject(result.getPublicProjectEntity(), null, finishDate);
		
		var todoOnProject = result.getTodoOnProject();
		if(todoOnProject.getStartDate().after(finishDate))
			throw new BadRequestException("Before the start date and time");
		
		todoOnProjectLogic.updateFinishDate(todoOnProjectId, finishDate);
		noticeLogic.eraseTodoNotice(userId, todoOnProjectId);
	}

	/**
	 * 「やること」を削除します
	 * @param userId ユーザID
	 * @param todoOnProjectId 「やること」ID
	 * @throws NotFoundValueException
	 * @throws NotJoinedPublicProjectException
	 * @throws NotHaveAuthorityToOperateProjectException
	 */
	@Transactional
	public void delete(Integer userId, Integer todoOnProjectId)
			throws NotFoundValueException, NotJoinedPublicProjectException, NotHaveAuthorityToOperateProjectException {
		this.validateUserCanSuperOperate(userId, null, todoOnProjectId);
		todoOnProjectLogic.delte(todoOnProjectId);
	}

	/**
	 * 「やること」の完了状況をセット
	 * @param userId ユーザID
	 * @param todoOnProjectId 「やること」ID
	 * @param isCompleted 完了状況
	 * @throws NotFoundValueException
	 * @throws NotJoinedPublicProjectException
	 * @throws NotHaveAuthorityToOperateProjectException
	 */
	@Transactional
	public void setIsCompleted(Integer userId, Integer todoOnProjectId, boolean isCompleted) 
			throws NotFoundValueException, NotJoinedPublicProjectException, NotHaveAuthorityToOperateProjectException {
		var result = this.validateUserCanSuperOperate(userId, null, todoOnProjectId);
		todoOnProjectLogic.updateIsCompleted(todoOnProjectId, isCompleted);
		
		var todoOnProject = result.getTodoOnProject();
		if(!todoOnProject.getIsCompleted().booleanValue() && isCompleted) {
			var todoOnResponsibleList = todoOnResponsibeLogic.getTodoOnResponsibleList(todoOnProjectId);
			for(var todoOnResponsible: todoOnResponsibleList) {
				noticeLogic.eraseTodoNotice(todoOnResponsible.getUserId(), todoOnProjectId);
				noticeLogic.createCompletedTodoNotice(todoOnResponsible.getUserId(), todoOnProjectId);
			}
		}else if(todoOnProject.getIsCompleted().booleanValue() && !isCompleted) {
			var todoOnResponsibleList = todoOnResponsibeLogic.getTodoOnResponsibleList(todoOnProjectId);
			for(var todoOnResponsible: todoOnResponsibleList) {
				noticeLogic.eraseTodoNotice(todoOnResponsible.getUserId(), todoOnProjectId);
				noticeLogic.createUnCompletedTodoNotice(todoOnResponsible.getUserId(), todoOnProjectId);
			}
		}
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
	private ValidateResult validateUserCanLook(Integer userId, Integer projectId, Integer todoOnProjectId)
			throws NotFoundValueException, NotJoinedPublicProjectException {
		var result = new ValidateResult();
		
		if(todoOnProjectId != null) {
			result.setTodoOnProject(todoOnProjectLogic.get(todoOnProjectId));
			projectId = result.getProjectId();
		}
		
		//projectのバリデート
		if(!privateProjectLogic.isPrivateProject(projectId, userId)) {
			result.setPublicProjectEntity(publicProjectLogic.get(projectId));
			result.setUserAuthorityInPublicProject(subscriberLogic.getAuthority(projectId, userId));
		}//else 検証成功
		
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
	private ValidateResult validateUserCanNormalOperate(Integer userId, Integer projectId, Integer todoOnProjectId)
			throws NotFoundValueException, NotJoinedPublicProjectException, NotSelectedAsTodoResponsibleException {
		var result = this.validateUserCanLook(userId, projectId, todoOnProjectId);
		
		if(!result.isPrivateProject() && result.getUserAuthorityInPublicProject().getAuthorityId() != AuthorityListInPublicProject.SUPER)
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
	private ValidateResult validateUserCanSuperOperate(Integer userId, Integer projectId, Integer todoOnProjectId)
			throws NotFoundValueException, NotJoinedPublicProjectException, NotHaveAuthorityToOperateProjectException {
		var result = this.validateUserCanLook(userId, projectId, todoOnProjectId);
			
		if(result.isPrivateProject() || result.getUserAuthorityInPublicProject().getAuthorityId() == AuthorityListInPublicProject.SUPER) {
			return result;
		}else
			throw new NotHaveAuthorityToOperateProjectException();
	}
	
	/**
	 * 「やること」に指定する期間がプロジェクトと比べておかしなことがないかどうかを調べる
	 * <br>todoStartとtodoFinishはいずれもnull可能
	 * @param publicProject パブリックプロジェクト
	 * @param todoStart 「やること」の開始日時
	 * @param todoFinish 「やること」の締め切り日時
	 * @throws BadRequestException 日付の範囲がおかしいときに投げられる例外
	 */
	private void validateDateRangeInPulicProject(PublicProjectEntity publicProject, Date todoStart, Date todoFinish) throws BadRequestException {
		if(todoStart != null && publicProject.getStartDate().after(todoStart))
			throw new BadRequestException("Start date and time is beyond the scope of the project");
		if(todoFinish != null && publicProject.getFinishDate().before(todoFinish))
			throw new BadRequestException("Deadline is beyond the scope of the project");
	}
	
	/**
	 * TodoServiceのvalidateUserと頭につく関数を実行したときに成功時に返される、検証結果
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
		 * 検証した結果、プロジェクトがprivateであるかどうかを調べる
		 * @return 検証した結果、プロジェクトがprivateであればtrue、そうでなければfalse
		 */
		public boolean isPrivateProject() {
			return publicProjectEntity == null;
		}
		
		/**
		 * 検証したプロジェクトIDを返す
		 * @return プロジェクトID、なければnullを返す
		 */
		public Integer getProjectId() {
			if(todoOnProject == null)
				return null;
			else
				return todoOnProject.getProjectId();
		}
	}
}
