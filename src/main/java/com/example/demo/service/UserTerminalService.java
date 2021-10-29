package com.example.demo.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.example.demo.exception.AlreadyUsedTerminalNameException;
import com.example.demo.exception.NotFoundValueException;
import com.example.demo.logic.NoticeLogic;
import com.example.demo.logic.UserTerminalLogic;
import com.example.demo.response.TerminalResponse;

/**
 * 機種に関するビジネスロジック
 * @author towelman
 *
 */
@Service
public class UserTerminalService {
	@Autowired
	UserTerminalLogic userTerminalLogic;
	@Autowired
	NoticeLogic noticeLogic;
	
	/**
	 * 機種の追加
	 * @param userId ユーザーID
	 * @param terminalName 機種名
	 * @throws AlreadyUsedTerminalNameException 指定された機種名が既に使われている
	 */
	@Transactional
	public void insert(Integer userId, String terminalName) throws AlreadyUsedTerminalNameException {
		userTerminalLogic.validateTerminalNameNotUsed(userId, terminalName);
		userTerminalLogic.insert(userId, terminalName);
	}

	/**
	 * 機種リストを取得する
	 * @param userId ユーザーID
	 * @return 機種リスト
	 */
	public List<TerminalResponse> getTerminalList(Integer userId) {
		var entityList = userTerminalLogic.getTerminalList(userId);
		List<TerminalResponse> responseList = new ArrayList<>();
		
		for(var entity: entityList)
			responseList.add(new TerminalResponse(entity));
		return responseList;
	}

	/**
	 * 機種名を変更する
	 * @param userId ユーザーID
	 * @param oldTerminalName 古い、変更したい機種名
	 * @param newTerminalName 新しい、変更後の機種名
	 * @throws AlreadyUsedTerminalNameException 新しい、変更後の機種名に指定された機種名が既に使われている
	 * @throws NotFoundValueException 古い、変更したい機種名が存在しない
	 */
	@Transactional
	public void updateTerminalName(Integer userId, String oldTerminalName, String newTerminalName) throws AlreadyUsedTerminalNameException, NotFoundValueException {
		var entity = userTerminalLogic.get(userId, oldTerminalName);
		userTerminalLogic.validateTerminalNameNotUsed(userId, newTerminalName);
		
		userTerminalLogic.updateTerminalName(entity.getTerminalId(), newTerminalName);
	}

	/**
	 * 機種を削除する
	 * @param userId ユーザーID
	 * @param terminalName 機種名
	 * @throws NotFoundValueException 機種名が存在しない
	 */
	@Transactional
	public void delete(Integer userId, String terminalName) throws NotFoundValueException {
		var entity = userTerminalLogic.get(userId, terminalName);
		
		noticeLogic.deleteUnsentNoticeByTerminal(entity.getTerminalId());
		userTerminalLogic.delete(entity.getTerminalId());
	}

}
