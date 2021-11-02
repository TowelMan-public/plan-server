package com.example.demo.logic;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.example.demo.dto.TodoOnResponsibleEntityExample;
import com.example.demo.entity.TodoEntity;
import com.example.demo.entity.TodoOnResponsibleEntity;
import com.example.demo.exception.AlreadySelectedAsTodoResponsibleException;
import com.example.demo.exception.NotFoundValueException;
import com.example.demo.exception.NotSelectedAsTodoResponsibleException;
import com.example.demo.repository.TodoEntityMapper;
import com.example.demo.repository.TodoOnResponsibleEntityMapper;

/**
 * 「やること」担当者の共通処理
 * @author towelman
 *
 */
@Component
public class TodoOnResponsibeLogic {
	@Autowired
	TodoOnResponsibleEntityMapper todoOnResponsibleEntityMapper;
	@Autowired
	TodoEntityMapper todoEntityMapper;
	
	/**
	 * 「やること」の担当者リストを取得する
	 * @param todoOnProjectId 「やること」ID
	 * @return 「やること」の担当者リスト
	 */
	public List<TodoOnResponsibleEntity> getTodoOnResponsibleList(Integer todoOnProjectId) {
		var dto = new TodoOnResponsibleEntityExample();
		dto.or()
			.andTodoOnProjectIdEqualTo(todoOnProjectId);
		
		return todoOnResponsibleEntityMapper.selectByExample(dto);
	}

	/**
	 * 「やること」に抜擢されているかを調べる
	 * @param todoOnProjectId 「やること」ID
	 * @param userId ユーザID
	 * @throws NotSelectedAsTodoResponsibleException
	 */
	public void validateSelected(Integer todoOnProjectId, Integer userId) throws NotSelectedAsTodoResponsibleException {
		this.get(todoOnProjectId, userId);
	}

	/**
	 * まだ「やること」の担当者に選ばれていないことを検証する
	 * @param todoOnProjectId 「やること」ID
	 * @param userId ユーザID
	 * @throws AlreadySelectedAsTodoResponsibleException
	 */
	public void validateNotSelected(Integer todoOnProjectId, Integer userId) throws AlreadySelectedAsTodoResponsibleException {
		var dto = new TodoOnResponsibleEntityExample();
		dto.or()
			.andTodoOnProjectIdEqualTo(todoOnProjectId)
			.andUserIdEqualTo(userId)
			.andIsDeletedEqualTo(false);
		
		if(!todoOnResponsibleEntityMapper.selectByExample(dto).isEmpty())
			throw new AlreadySelectedAsTodoResponsibleException();
	}

	/**
	 * 「やること」の担当者を追加する
	 * @param todoOnProjectId 「やること」ID
	 * @param publicProjectId パブリックプロジェクトID
	 * @param userId ユーザID
	 * @return 新しい、担当者向け「やること」のID
	 */
	public Integer insert(Integer todoOnProjectId, Integer publicProjectId, Integer userId) {
		var parentEntity = new TodoEntity();
		todoEntityMapper.insertSelective(parentEntity);
		
		var entity = new TodoOnResponsibleEntity();
		entity.setTodoOnProjectId(todoOnProjectId);
		entity.setProjectId(publicProjectId);
		entity.setTodoOnResponsibleId(parentEntity.getTodoId());
		entity.setUserId(userId);
		entity.setIsCompleted(false);
		entity.setIsDeleted(false);
		
		todoOnResponsibleEntityMapper.insert(entity);
		return parentEntity.getTodoId();
	}

	/**
	 * 「やること」の担当者を取得する
	 * @param todoOnProjectId 「やること」ID
	 * @param userId ユーザID
	 * @return 「やること」の担当者
	 * @throws NotSelectedAsTodoResponsibleException
	 */
	public TodoOnResponsibleEntity get(Integer todoOnProjectId, Integer userId) throws NotSelectedAsTodoResponsibleException {
		var dto = new TodoOnResponsibleEntityExample();
		dto.or()
			.andTodoOnProjectIdEqualTo(todoOnProjectId)
			.andUserIdEqualTo(userId)
			.andIsDeletedEqualTo(false);
		
		var entityList = todoOnResponsibleEntityMapper.selectByExample(dto);
		if(entityList.isEmpty())
			throw new NotSelectedAsTodoResponsibleException();
		else
			return entityList.get(0);
			
	}

	/**
	 * 「やること」の担当者を担当から外す
	 * @param todoOnProjectId 「やること」ID
	 * @param userId ユーザID
	 */
	public void delete(Integer todoOnProjectId, Integer userId) {
		var dto = new TodoOnResponsibleEntityExample();
		dto.or()
			.andTodoOnProjectIdEqualTo(todoOnProjectId)
			.andUserIdEqualTo(userId);
		
		var entity = new TodoOnResponsibleEntity();
		entity.setIsDeleted(true);
		
		todoOnResponsibleEntityMapper.updateByExampleSelective(entity, dto);
	}

	/**
	 * 「やること」の担当者１人が、完了状況をセットする
	 * @param todoOnProjectId 「やること」ID 
	 * @param userId ユーザID
	 * @param isCompleted 完了状況
	 */
	public void updateIsCompleted(Integer todoOnProjectId, Integer userId, Boolean isCompleted) {
		var dto = new TodoOnResponsibleEntityExample();
		dto.or()
			.andTodoOnProjectIdEqualTo(todoOnProjectId)
			.andUserIdEqualTo(userId);
		
		var entity = new TodoOnResponsibleEntity();
		entity.setIsCompleted(isCompleted);
		
		todoOnResponsibleEntityMapper.updateByExampleSelective(entity, dto);
	}

	/**
	 * 担当者向け「やること」を取得する
	 * @param todoOnResponsibleId 担当者向け「やること」ID
	 * @return 担当者向け「やること」
	 * @throws NotFoundValueException 指定された担当者向け「やること」が見つからない時に投げられる例外
	 */
	public TodoOnResponsibleEntity getByTodoOnResponsibleId(Integer todoOnResponsibleId) throws NotFoundValueException {
		var dto = new TodoOnResponsibleEntityExample();
		dto.or()
			.andTodoOnResponsibleIdEqualTo(todoOnResponsibleId)
			.andIsDeletedEqualTo(false);
		
		var entityList = todoOnResponsibleEntityMapper.selectByExample(dto);
		if(entityList.isEmpty())
			throw new NotFoundValueException("todoOnResponsibleId", todoOnResponsibleId.toString());
		else
			return entityList.get(0);
	}

}
