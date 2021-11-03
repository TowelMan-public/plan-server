package com.example.demo.logic;

import java.util.Date;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.example.demo.entity.OtherConfigEntity;
import com.example.demo.repository.OtherConfigEntityMapper;
import com.example.demo.utility.CommonUtility;

/**
 * そのほかの、一時的に使う情報などを永続化した奴の共通処理
 * @author towelman
 *
 */
@Component
public class OtherConfigLogic {
	@Autowired
	OtherConfigEntityMapper mapper;
	@Autowired
	CommonUtility utility;
		
	private static final String LAST_RUN_NOTICE_TASK_DATE_STRING = "LAST_RUN_NOTICE_TASK_DATE_STRING";
	
	private static final String DATE_TO_STRING_FORMAT = "yyyy-M-d hh:mm:ss";
	
	/**
	 * 最後にNoticeTaskを実行したときの日付の取得
	 * @return 日付
	 */
	public Date getLastRunNoticeTaskDate() {
		var dateString = mapper.selectByPrimaryKey(LAST_RUN_NOTICE_TASK_DATE_STRING).getOtherConfigValue();
		
		if(dateString.isEmpty())
			return null;
		else
			return utility.stringToDate(dateString, DATE_TO_STRING_FORMAT);
	}
	
	/**
	 * 最後にNoticeTaskを実行したときの日付のセット
	 * @param date 日付
	 */
	public void setLastRunNoticeTaskDate(Date date) {
		var entity = new OtherConfigEntity();
		entity.setOtherConfigKey(LAST_RUN_NOTICE_TASK_DATE_STRING);
		entity.setOtherConfigValue(utility.dateToString(date, DATE_TO_STRING_FORMAT));
		
		mapper.updateByPrimaryKey(entity);
	}
}
