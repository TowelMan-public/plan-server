package com.example.demo.logic;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.example.demo.dto.ContentEntityExample;
import com.example.demo.entity.ContentEntity;
import com.example.demo.exception.NotFoundValueException;
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

	/**
	 * 内容の取得
	 * @param contentId 内容ID
	 * @return 内容
	 * @throws NotFoundValueException 指定された内容が見つからないときに投げられる
	 */
	public ContentEntity get(Integer contentId) throws NotFoundValueException {
		var dto = new ContentEntityExample();
		dto.or()
			.andContentIdEqualTo(contentId)
			.andIsDeletedEqualTo(false);
		
		var entityList = contentEntityMapper.selectByExample(dto);
		if(entityList.isEmpty())
			throw new NotFoundValueException("contentId", contentId.toString());
		else
			return entityList.get(0);
	}

	/**
	 * 内容の名前を変更する
	 * @param contentId 内容ID
	 * @param contentTitle 内容名
	 */
	public void updateTitle(Integer contentId, String contentTitle) {
		var entity = new ContentEntity();
		entity.setContentId(contentId);
		entity.setContentTitle(contentTitle);
		
		contentEntityMapper.updateByPrimaryKeySelective(entity);
	}

	/**
	 * 内容の説明を変更する
	 * @param contentId 内容ID
	 * @param contentExplanation 内容の説明
	 */
	public void updateExplanation(Integer contentId, String contentExplanation) {
		var entity = new ContentEntity();
		entity.setContentId(contentId);
		entity.setContentExplanation(contentExplanation);
		
		contentEntityMapper.updateByPrimaryKeySelective(entity);
	}

	/**
	 * 内容を削除する
	 * @param contentId 内容ID
	 */
	public void delete(Integer contentId) {
		var entity = new ContentEntity();
		entity.setContentId(contentId);
		entity.setIsDeleted(true);
		
		contentEntityMapper.updateByPrimaryKeySelective(entity);
	}

	/**
	 * 内容に完了状況をセットする
	 * @param contentId 内容ID
	 * @param isCompleted 完了状況
	 */
	public void updateIsCompleted(Integer contentId, Boolean isCompleted) {
		var entity = new ContentEntity();
		entity.setContentId(contentId);
		entity.setIsCompleted(isCompleted);
		
		contentEntityMapper.updateByPrimaryKeySelective(entity);
	}
}
