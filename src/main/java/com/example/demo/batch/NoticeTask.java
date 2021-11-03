package com.example.demo.batch;

import java.util.Date;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import com.example.demo.logic.NoticeLogic;
import com.example.demo.logic.OtherConfigLogic;

/**
 * 自動で生成しなくてはいけない通知を作る処理を定期実行するクラス。
 * @author towelman
 *
 */
@Component
public class NoticeTask {
	@Autowired
	OtherConfigLogic otherConfigLogic;
	@Autowired
	NoticeLogic noticeLogic;
	
	/**
	 * 通知の定期実行したい処理たち
	 */
	@Scheduled(cron="0 0 * * * *")
	public void runNoticeTask() {
		var lastDate = otherConfigLogic.getLastRunNoticeTaskDate();
		var nowDate = new Date();
		
		noticeLogic.createApproachingPeriodTodoNoticeAuto(lastDate, nowDate);
		noticeLogic.createApproachingPubliceriodProjectNoticeAuto(lastDate, nowDate);
		noticeLogic.createDealyPeriodPublicProjectNoticeAuto(lastDate, nowDate);
		noticeLogic.createDelayApproachingTodoNoticeAuto(lastDate, nowDate);
		noticeLogic.createStartedTodoNoticeAuto(lastDate, nowDate);
	}
}