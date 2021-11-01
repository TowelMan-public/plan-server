package com.example.demo.logic;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.example.demo.dto.ContentEntityExample;
import com.example.demo.entity.ContentEntity;
import com.example.demo.repository.ContentEntityMapper;

/**
 * 内容の共通処理
 * @author towelman
 *
 */
@Component
public class ContentLogic {
	@Autowired
	ContentEntityMapper contentEntityMapper;

	/**
	 * 内容リストの取得
	 * @param todoId 「やること」ID
	 * @return 内容リスト
	 */
	public List<ContentEntity> getList(Integer todoId) {
		var dto = new ContentEntityExample();
		dto.or()
			.andTodoIdEqualTo(todoId)
			.andIsDeletedEqualTo(false);
		
		return contentEntityMapper.selectByExample(dto);
	}

	/**
	 * 内容の登録
	 * @param todoId 「やること」ID
	 * @param contentTitle 内容のタイトル
	 * @param contentExplanation 内容の説明
	 * @return 新しい内容ID
	 */
	public Integer insert(Integer todoId, String contentTitle, String contentExplanation) {
		var entity = new ContentEntity();
		entity.setTodoId(todoId);
		entity.setContentTitle(contentTitle);
		entity.setContentExplanation(contentExplanation);
		entity.setIsCompleted(false);
		entity.setIsDeleted(false);
		
		contentEntityMapper.insertSelective(entity);
		return entity.getContentId();
	}
}
