package com.example.demo.logic;

import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.example.demo.dto.TodoOnProjectEntityExample;
import com.example.demo.entity.TodoEntity;
import com.example.demo.entity.TodoOnProjectEntity;
import com.example.demo.exception.NotFoundValueException;
import com.example.demo.repository.TodoEntityMapper;
import com.example.demo.repository.TodoOnProjectEntityMapper;

/**
 * 「やること」の共通処理
 * @author towelman
 *
 */
@Component
public class TodoOnProjectLogic {
	@Autowired
	TodoOnProjectEntityMapper todoOnProjectEntityMapper;
	@Autowired
	TodoEntityMapper todoEntityMapper;
	
	/**
	 * 「やること」を取得する<br>
	 * 存在しない「やること」IDが指定されても問題ない場合以外には使わないでください。
	 * @param todoOnProjectId 「やること」ID 「やること」ID
	 * @return 「やること」、存在しない場合null
	 */
	public TodoOnProjectEntity getNonThrow(Integer todoOnProjectId) {
		var entity = todoOnProjectEntityMapper.selectByPrimaryKey(todoOnProjectId);
		
		if(entity == null || entity.getIsDeleted())
			return null;
		else
			return entity;
	}


	/**
	 * 「やること」の新規作成
	 * @param projectId プロジェクトID
	 * @param todoName 「やること」の名前
	 * @param startDate 「やること」の開始日時
	 * @param finishDate 「やること」の締め切り日時
	 * @param isCopyContentsToResponsible 担当者向け「やること」にも、内容をコピーさせるかの設定。有効にするにはtrue、無効ならfalse
	 * @return 新しい「やること」ID
	 */
	public Integer insert(Integer projectId, String todoName, Date startDate, Date finishDate,
			Boolean isCopyContentsToResponsible) {
		var parentEntity = new TodoEntity();
		todoEntityMapper.insertSelective(parentEntity);
		
		var entity = new TodoOnProjectEntity();
		entity.setTodoOnProjectId(parentEntity.getTodoId());
		entity.setProjectId(projectId);
		entity.setTodoName(todoName);
		entity.setStartDate(startDate);
		entity.setFinishDate(finishDate);
		entity.setIsCopyContentsToUsers(isCopyContentsToResponsible);
		entity.setIsCompleted(false);
		entity.setIsDeleted(false);
		
		todoOnProjectEntityMapper.insertSelective(entity);
		return parentEntity.getTodoId();
	}

	/**
	 * 「やること」を取得します
	 * @param todoOnProjectId 「やること」ID
	 * @return 「やること」
	 * @throws NotFoundValueException
	 */
	public TodoOnProjectEntity get(Integer todoOnProjectId) throws NotFoundValueException {
		var entity = todoOnProjectEntityMapper.selectByPrimaryKey(todoOnProjectId);
		
		if(entity == null)
			throw new NotFoundValueException("todoOnProjectId", todoOnProjectId.toString());
		else
			return entity;
	}

	/**
	 * IsCopyContentsToResponsible（担当者向け「やること」にも、内容をコピーさせるかの設定を変える）
	 * @param todoOnProjectId 「やること」ID
	 * @param isCopyContentsToResponsible 担当者向け「やること」にも、内容をコピーさせるかの設定。有効にするにはtrue、無効ならfalse
	 */
	public void updateIsCopyContentsToResponsible(Integer todoOnProjectId, Boolean isCopyContentsToResponsible) {
		var entity = new TodoOnProjectEntity();
		entity.setTodoOnProjectId(todoOnProjectId);
		entity.setIsCopyContentsToUsers(isCopyContentsToResponsible);
		
		todoOnProjectEntityMapper.updateByPrimaryKeySelective(entity);
	}

	/**
	 * 「やること」の名前を変更します
	 * @param todoOnProjectId 「やること」ID
	 * @param todoName 「やること」の名前
	 */
	public void updateName(Integer todoOnProjectId, String todoName) {
		var entity = new TodoOnProjectEntity();
		entity.setTodoOnProjectId(todoOnProjectId);
		entity.setTodoName(todoName);
		
		todoOnProjectEntityMapper.updateByPrimaryKeySelective(entity);
	}

	/**
	 * 「やること」開始日時を変更します
	 * @param todoOnProjectId 「やること」ID
	 * @param startDate 「やること」の開始日時
	 */
	public void updateStartDate(Integer todoOnProjectId, Date startDate) {
		var entity = new TodoOnProjectEntity();
		entity.setTodoOnProjectId(todoOnProjectId);
		entity.setStartDate(startDate);
		
		todoOnProjectEntityMapper.updateByPrimaryKeySelective(entity);
	}

	/**
	 * 「やること」の締め切り日時を変更します
	 * @param todoOnProjectId 「やること」ID
	 * @param finishDate 「やること」の締め切り日時
	 */
	public void updateFinishDate(Integer todoOnProjectId, Date finishDate) {
		var entity = new TodoOnProjectEntity();
		entity.setTodoOnProjectId(todoOnProjectId);
		entity.setFinishDate(finishDate);
		
		todoOnProjectEntityMapper.updateByPrimaryKeySelective(entity);
	}

	/**
	 * 「やること」を削除します
	 * @param todoOnProjectId 「やること」ID
	 */
	public void delte(Integer todoOnProjectId) {
		var entity = new TodoOnProjectEntity();
		entity.setTodoOnProjectId(todoOnProjectId);
		entity.setIsDeleted(true);
		
		todoOnProjectEntityMapper.updateByPrimaryKeySelective(entity);
	}

	/**
	 * 「やること」に完了状況をセットします
	 * @param todoOnProjectId 「やること」ID
	 * @param isCompleted
	 */
	public void updateIsCompleted(Integer todoOnProjectId, Boolean isCompleted) {
		var entity = new TodoOnProjectEntity();
		entity.setTodoOnProjectId(todoOnProjectId);
		entity.setIsCompleted(isCompleted);
		
		todoOnProjectEntityMapper.updateByPrimaryKeySelective(entity);
	}


	/**
	 * 指定されたプロジェクト内の「やること」リストを条件に合わせて取得します
	 * <br>※projectID以外nullの指定が可能で、それらが検索条件となります。	
	 * @param projectId プロジェクトID
	 * @param startDate 「やること」の開始日時
	 * @param finishDate 「やること」の締め切り日時
	 * @param isInclideCompletedTodo
	 * @return 「やること」リスト
	 */
	public List<TodoOnProjectEntity> getListByExampleIf(Integer projectId, Date startDate, Date finishDate,
			Boolean isInclideCompletedTodo) {
		var dto = new TodoOnProjectEntityExample();
		var or = dto.or()
				.andProjectIdEqualTo(projectId)
				.andIsDeletedEqualTo(false);
		
		if(startDate != null)
			or.andStartDateGreaterThanOrEqualTo(startDate);
		if(finishDate != null)
			or.andFinishDateLessThanOrEqualTo(finishDate);
		if(isInclideCompletedTodo == null || !isInclideCompletedTodo)
			or.andIsCompletedEqualTo(false);		
		
		return todoOnProjectEntityMapper.selectByExample(dto);
	}
}
