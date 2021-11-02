package com.example.demo.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.example.demo.configurer.AuthorityListInPublicProject;
import com.example.demo.entity.ContentEntity;
import com.example.demo.entity.PublicProjectEntity;
import com.example.demo.entity.SubscriberInPublicProjectEntity;
import com.example.demo.entity.TodoOnProjectEntity;
import com.example.demo.entity.TodoOnResponsibleEntity;
import com.example.demo.exception.BadRequestException;
import com.example.demo.exception.NotFoundValueException;
import com.example.demo.exception.NotHaveAuthorityToOperateProjectException;
import com.example.demo.exception.NotJoinedPublicProjectException;
import com.example.demo.form.ContentForm;
import com.example.demo.logic.ContentLogic;
import com.example.demo.logic.PrivateProjectLogic;
import com.example.demo.logic.PublicProjectLogic;
import com.example.demo.logic.SubscriberLogic;
import com.example.demo.logic.TodoOnProjectLogic;
import com.example.demo.logic.TodoOnResponsibeLogic;
import com.example.demo.logic.UserLogic;
import com.example.demo.response.ContentResponse;

import lombok.Getter;
import lombok.Setter;

/**
 * 内容のビジネスロジック
 * @author towelman
 *
 */
@Service
public class ContentService {
	@Autowired
	ContentLogic contentLogic;
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
	PrivateProjectLogic privateProjectLogic;
	
	/**
	 * 内容の新規登録
	 * @param userId ユーザID
	 * @param form　内容の情報
	 * @return　新しい内容ID
	 * @throws BadRequestException 不適切なな「やること」IDが指定された。
	 * @throws NotHaveAuthorityToOperateProjectException
	 * @throws NotFoundValueException
	 * @throws NotJoinedPublicProjectException
	 */
	@Transactional
	public Integer insert(Integer userId, ContentForm form) 
			throws BadRequestException, NotHaveAuthorityToOperateProjectException, NotFoundValueException, NotJoinedPublicProjectException {
		this.validateUserCanOperateByTodoId(userId, form.getTodoId());
		return contentLogic.insert(form.getTodoId(), form.getContentTitle(), form.getContentExplanation());
	}

	/**
	 * 「やること」の内容たちを取得する
	 * @param userId ユーザID
	 * @param todoId 「やること」ID
	 * @return 内容リスト
	 * @throws BadRequestException 不適切なな「やること」IDが指定された。
	 * @throws NotFoundValueException
	 * @throws NotJoinedPublicProjectException
	 */
	@Transactional
	public List<ContentResponse> getList(Integer userId, Integer todoId)
			throws BadRequestException, NotFoundValueException, NotJoinedPublicProjectException {
		this.validateUserCanLookByTodoId(userId, todoId);
		var contentList = contentLogic.getList(todoId);
		List<ContentResponse> responseList = new ArrayList<>();
		
		for(var content: contentList) 
			responseList.add(new ContentResponse(content));
		
		return responseList;
	}

	/**
	 * 内容を取得する
	 * @param userId ユーザID
	 * @param contentId 内容ID
	 * @return 内容
	 * @throws BadRequestException 不適切なな「やること」IDが指定された。
	 * @throws NotFoundValueException
	 * @throws NotJoinedPublicProjectException
	 */
	@Transactional
	public ContentResponse get(Integer userId, Integer contentId) 
			throws BadRequestException, NotFoundValueException, NotJoinedPublicProjectException {
		var result = this.validateUserCanLook(userId, contentId);
		return new ContentResponse(result.getContent());
	}

	/**
	 * 内容名を変更する
	 * @param userId ユーザID
	 * @param contentId 内容ID
	 * @param contentTitle 内容名
	 * @throws BadRequestException 不適切なな「やること」IDが指定された。
	 * @throws NotFoundValueException
	 * @throws NotJoinedPublicProjectException
	 * @throws NotHaveAuthorityToOperateProjectException
	 */
	@Transactional
	public void updateTitle(Integer userId, Integer contentId, String contentTitle)
			throws BadRequestException, NotFoundValueException, NotJoinedPublicProjectException, NotHaveAuthorityToOperateProjectException {
		this.validateUserCanOperate(userId, contentId);
		contentLogic.updateTitle(contentId, contentTitle);
	}

	/**
	 * 内容の説明を変更する
	 * @param userId ユーザID
	 * @param contentId 内容ID
	 * @param contentExplanation 内容の説明
	 * @throws BadRequestException 不適切なな「やること」IDが指定された。
	 * @throws NotFoundValueException
	 * @throws NotJoinedPublicProjectException
	 * @throws NotHaveAuthorityToOperateProjectException
	 */
	@Transactional
	public void updateExplanation(Integer userId, Integer contentId, String contentExplanation)
			throws BadRequestException, NotFoundValueException, NotJoinedPublicProjectException, NotHaveAuthorityToOperateProjectException {
		this.validateUserCanOperate(userId, contentId);
		contentLogic.updateExplanation(contentId, contentExplanation);
	}

	/**
	 * 内容の削除
	 * @param userId ユーザID
	 * @param contentId 内容ID
	 * @throws BadRequestException 不適切なな「やること」IDが指定された。
	 * @throws NotFoundValueException 
	 * @throws NotJoinedPublicProjectException
	 * @throws NotHaveAuthorityToOperateProjectException
	 */
	@Transactional
	public void delete(Integer userId, Integer contentId) 
			throws BadRequestException, NotFoundValueException, NotJoinedPublicProjectException, NotHaveAuthorityToOperateProjectException {
		this.validateUserCanOperate(userId, contentId);
		contentLogic.delete(contentId);
	}

	/**
	 * 内容に完了状況をセットする
	 * @param userId ユーザID
	 * @param contentId 内容ID
	 * @param isCompleted 完了状況
	 * @throws BadRequestException 不適切なな「やること」IDが指定された。
	 * @throws NotFoundValueException
	 * @throws NotJoinedPublicProjectException
	 * @throws NotHaveAuthorityToOperateProjectException
	 */
	@Transactional
	public void setIsCompleted(Integer userId, Integer contentId, Boolean isCompleted)
			throws BadRequestException, NotFoundValueException, NotJoinedPublicProjectException, NotHaveAuthorityToOperateProjectException {
		this.validateUserCanOperate(userId, contentId);
		contentLogic.updateIsCompleted(contentId, isCompleted);
	}

	/**
	 * 内容について、そのユーザーが見る権限があるかを検証する
	 * @param userId ユーザID
	 * @param contentId 内容ID
	 * @return 検証結果
	 * @throws BadRequestException 不適切なな「やること」IDが指定された。
	 * @throws NotFoundValueException
	 * @throws NotJoinedPublicProjectException
	 */
	private ValidateResult validateUserCanLook(Integer userId, Integer contentId)
			throws BadRequestException, NotFoundValueException, NotJoinedPublicProjectException {
		var content = contentLogic.get(contentId);
		var result = this.validateUserCanLookByTodoId(userId, content.getTodoId());
		result.setContent(content);
		
		return result;
	}
	
	/**
	 * 内容について、そのユーザーが操作を加える権限があるかを検証する
	 * @param userId ユーザID
	 * @param contentId 内容ID
	 * @return 検証結果
	 * @throws BadRequestException 不適切なな「やること」IDが指定された。
	 * @throws NotFoundValueException 
	 * @throws NotJoinedPublicProjectException 
	 * @throws NotHaveAuthorityToOperateProjectException 
	 */
	private ValidateResult validateUserCanOperate(Integer userId, Integer contentId)
			throws BadRequestException, NotFoundValueException, NotJoinedPublicProjectException, NotHaveAuthorityToOperateProjectException {
		var result = this.validateUserCanLook(userId, contentId);
				
		if(!result.isPrivateProject() && result.isTodoOnProject()
				&& result.getUserAuthorityInPublicProject().getAuthorityId() != AuthorityListInPublicProject.SUPER) {
			throw new NotHaveAuthorityToOperateProjectException();
		}
		
		return result;
	}
	
	/**
	 * 「やること」について、そのユーザーが操作する権限があるかを調べる
	 * @param userId ユーザID
	 * @param todoId 「やること」ID
	 * @return 検証結果
	 * @throws BadRequestException 不適切なな「やること」IDが指定された。
	 * @throws NotHaveAuthorityToOperateProjectException
	 * @throws NotFoundValueException
	 * @throws NotJoinedPublicProjectException
	 */
	private ValidateResult validateUserCanOperateByTodoId(Integer userId, Integer todoId)
			throws BadRequestException, NotHaveAuthorityToOperateProjectException, NotFoundValueException, NotJoinedPublicProjectException {
		var result = this.validateUserCanLookByTodoId(userId, todoId);
		
		if(!result.isPrivateProject() && result.isTodoOnProject()
				&& result.getUserAuthorityInPublicProject().getAuthorityId() != AuthorityListInPublicProject.SUPER) {
			throw new NotHaveAuthorityToOperateProjectException();
		}
		
		return result;
	}
	
	/**
	 * 「やること」について、そのユーザーに見る権限があるかを検証する
	 * @param userId ユーザID
	 * @param todoId 「やること」ID
	 * @return 検証結果
	 * @throws BadRequestException 不適切なな「やること」IDが指定された。
	 * @throws NotFoundValueException
	 * @throws NotJoinedPublicProjectException
	 */
	private ValidateResult validateUserCanLookByTodoId(Integer userId, Integer todoId)
			throws BadRequestException, NotFoundValueException, NotJoinedPublicProjectException {
		var result = new ValidateResult();
		
		//TodoIdの検証
		var todoOnProject = todoOnProjectLogic.getNonThrow(todoId);
		if(todoOnProject == null) {
			var todoOnResponsible = todoOnResponsibeLogic.getByTodoOnResponsibleId(todoId);
			if(!todoOnResponsible.getUserId().equals(userId))
				throw new BadRequestException("invalid todoId");
			else
				result.setTodoOnResponsibe(todoOnResponsible);
		}else
			result.setTodoOnProject(todoOnProject);
		
		//projectの検証
		var projectId = result.getProjectId();
		if(!privateProjectLogic.isPrivateProject(projectId, userId)) {
			result.setPublicProjectEntity(publicProjectLogic.get(projectId));
			result.setUserAuthorityInPublicProject(subscriberLogic.getAuthority(projectId, userId));
		}//else 検証成功
		
		return result;
	}
	
	/**
	 * ContentServiceのvalidateUserと頭につく関数を実行したときに成功時に返される、検証結果
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
		
		@Getter
		@Setter
		private TodoOnResponsibleEntity todoOnResponsibe;
		
		@Getter
		@Setter
		private ContentEntity content;
		
		/**
		 * 検証したプロジェクトIDを返す
		 * @return プロジェクトID、なければnullを返す
		 */
		public Integer getProjectId() {
			if(todoOnProject == null) {
				if(todoOnResponsibe == null)
					return null;
				else
					return todoOnResponsibe.getProjectId();
			}
			else
				return todoOnProject.getProjectId();
		}
		
		/**
		 * 指定された「やること」IDを返す
		 * @return 「やること」ID、なければnullを返す
		 */
		public Integer getTodoId() {
			if(todoOnProject == null) {
				if(todoOnResponsibe == null)
					return null;
				else
					return todoOnResponsibe.getTodoOnResponsibleId();
			}
			else
				return todoOnProject.getTodoOnProjectId();
		}
		
		/**
		 * 指定されたプロジェクトがプライベートである場合にtrueを返す
		 * @return 指定されたプロジェクトがPrivateである場合にtrueを返す、それ以外はfalse
		 */
		public boolean isPrivateProject() {
			return publicProjectEntity == null;
		}
		
		/**
		 * 指定された「やること」が担当者向けではない場合にtrueを返す
		 * @return 指定された「やること」が担当者向けではない場合にtrueを返す、それ以外はfalse
		 */
		public boolean isTodoOnProject() {
			return todoOnResponsibe == null;
		}
	}
}
