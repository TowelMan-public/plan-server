package com.example.demo.repository;

import java.util.Date;
import java.util.List;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import com.example.demo.entity.SubscriberInPublicProjectEntity;
import com.example.demo.entity.TodoOnResponsibleEntity;

@Mapper
public interface OriginalForNoticeMapper {
	public List<SubscriberInPublicProjectEntity> getApproachingPubliceriodProject(@Param("lastRunDate") Date lastRunDate, @Param("nowRunDate") Date nowRunDate);
	
	public List<SubscriberInPublicProjectEntity> getDealyPeriodPublicProject(@Param("lastRunDate") Date lastRunDate, @Param("nowRunDate") Date nowRunDate);
	
	public List<TodoOnResponsibleEntity> getApproachingPeriodTodo(@Param("lastRunDate") Date lastRunDate, @Param("nowRunDate") Date nowRunDate);
	
	public List<TodoOnResponsibleEntity> getStartedTodo(@Param("lastRunDate") Date lastRunDate, @Param("nowRunDate") Date nowRunDate);
	
	public List<TodoOnResponsibleEntity> getDelayApproachingTodo(@Param("lastRunDate") Date lastRunDate, @Param("nowRunDate") Date nowRunDate);
	
	public void deletePublicProjectNoticeAuto(); 
	
	public void deleteTodoNotice(); 
	
	public void deleteUnNeededNotice(); 
	
	public void deleteNoticeParent();
}
