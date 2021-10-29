package com.example.demo.repository;

import java.util.Date;
import java.util.List;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import com.example.demo.entity.SubscriberInPublicProjectEntity;
import com.example.demo.entity.TodoOnResponsibleEntity;
import com.example.demo.response.NoticeResponse;

@Mapper
public interface OriginalForNoticeMapper {
	public List<SubscriberInPublicProjectEntity> getApproachingPubliceriodProject(@Param("lastRunDate") Date lastRunDate, @Param("nowRunDate") Date nowRunDate);
	
	public List<SubscriberInPublicProjectEntity> getDealyPeriodPublicProject(@Param("lastRunDate") Date lastRunDate, @Param("nowRunDate") Date nowRunDate);
	
	public List<TodoOnResponsibleEntity> getApproachingPeriodTodo(@Param("lastRunDate") Date lastRunDate, @Param("nowRunDate") Date nowRunDate);
	
	public List<TodoOnResponsibleEntity> getStartedTodo(@Param("lastRunDate") Date lastRunDate, @Param("nowRunDate") Date nowRunDate);
	
	public List<TodoOnResponsibleEntity> getDelayApproachingTodo(@Param("lastRunDate") Date lastRunDate, @Param("nowRunDate") Date nowRunDate);
	
	public void erasePublicProjectNoticeAuto(); 
	
	public void eraseTodoNoticeAuto(); 
	
	public void eraseProjectNoticeOfDeletedUser(); 
	
	public void eraseTodoNoticeOfDeletedUser();
	
	public void eraseUnNeededNotice();
	
	public void eraseNoticeParent();

	public List<NoticeResponse> getUnsentNoticeList(@Param("terminalId") Integer terminalId);

	public List<NoticeResponse> getNoticeList(@Param("userId") Integer userId);
}
