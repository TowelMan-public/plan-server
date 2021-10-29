package com.example.demo.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.example.demo.exception.NotFoundValueException;
import com.example.demo.logic.NoticeLogic;
import com.example.demo.logic.TodoOnProjectLogic;
import com.example.demo.logic.UserTerminalLogic;
import com.example.demo.logic.SubscriberLogic;
import com.example.demo.response.NoticeResponse;

/**
 * 通知に関するビジネスロジック
 * @author towelman
 *
 */
@Service
public class NoticeService {
	@Autowired
	NoticeLogic noticeLogic;
	@Autowired
	UserTerminalLogic userTerminalLogic;
	@Autowired
	SubscriberLogic subscriberLogic;
	@Autowired
	TodoOnProjectLogic todoOnProjectLogic;
	
	/**
	 * 未送信（未取得）の通知リストを取得する
	 * @param userId ユーザーID
	 * @param terminalName 機種名
	 * @return 未送信（未取得）の通知リスト
	 * @throws NotFoundValueException 存在しない機種名が指定された
	 */
	@Transactional
	public List<NoticeResponse> getUnsendedNotice(Integer userId, String terminalName) throws NotFoundValueException {
		var terminalId = userTerminalLogic.get(userId, terminalName).getTerminalId();
		
		var responsibleList = exclusionUnnecessaryTodoNotice(userId, noticeLogic.getUnsendedNoticeList(terminalId));
		noticeLogic.deleteUnsentNoticeByTerminal(terminalId);
		return responsibleList;
	}
	
	/**
	 * 通知リストを取得する
	 * @param userId ユーザーID
	 * @return 通知リスト
	 */
	public List<NoticeResponse> getNotice(Integer userId) {
		 return exclusionUnnecessaryTodoNotice(userId, noticeLogic.getNoticeList(userId));
	}
	
	/**
	 * 除外するべき「やること」に関する通知を通知リストから除外する
	 * @param userId ユーザーID
	 * @param rowResponseList 除外するべき「やること」に関する通知が除外されていない通知リスト
	 * @return 除外するべき「やること」に関する通知がすべて除外された通知リスト
	 */
	private List<NoticeResponse> exclusionUnnecessaryTodoNotice(Integer userId, List<NoticeResponse> rowResponseList){
		List<NoticeResponse> afterResponseList = new ArrayList<>();
		
		for(var response: rowResponseList) {
			if(response.getNoticeType().equals(NoticeResponse.TODO_NOTICE)) {
				var todoOnProjectEntity = todoOnProjectLogic.getNonThrow(response.getId());
				if(!subscriberLogic.haveNormalAuthority(userId, todoOnProjectEntity.getProjectId()))
					continue;
			}
			
			afterResponseList.add(response);
		}
		
		return afterResponseList;
	}

}
