package com.example.demo.logic;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.example.demo.dto.TodoOnResponsibleEntityExample;
import com.example.demo.entity.TodoOnResponsibleEntity;
import com.example.demo.exception.NotSelectedAsTodoResponsibleException;
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
		var dto = new TodoOnResponsibleEntityExample();
		dto.or()
			.andTodoOnProjectIdEqualTo(todoOnProjectId)
			.andUserIdEqualTo(userId)
			.andIsDeletedEqualTo(false);
		
		if(todoOnResponsibleEntityMapper.selectByExample(dto).isEmpty())
			throw new NotSelectedAsTodoResponsibleException();
	}

}
