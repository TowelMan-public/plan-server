package com.example.demo.logic;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.example.demo.entity.TodoOnProjectEntity;
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
	
	/**
	 * 「やること」を取得する<br>
	 * 存在しない「やること」IDが指定されても問題ない場合以外には使わないでください。
	 * @param todoOnProjectId 「やること」ID
	 * @return 「やること」、存在しない場合null
	 */
	public TodoOnProjectEntity getNonThrow(Integer todoOnProjectId) {
		return todoOnProjectEntityMapper.selectByPrimaryKey(todoOnProjectId);
	}

}
