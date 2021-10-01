package com.example.demo.repository;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import com.example.demo.dto.ProjectNoticeEntityExample;
import com.example.demo.entity.ProjectNoticeEntity;

@Mapper
public interface ProjectNoticeEntityMapper {

    long countByExample(ProjectNoticeEntityExample example);

    int deleteByExample(ProjectNoticeEntityExample example);

    int deleteByPrimaryKey(Integer noticeId);

    int insert(ProjectNoticeEntity record);

    int insertSelective(ProjectNoticeEntity record);

    List<ProjectNoticeEntity> selectByExample(ProjectNoticeEntityExample example);

    ProjectNoticeEntity selectByPrimaryKey(Integer noticeId);

    int updateByExampleSelective(@Param("record") ProjectNoticeEntity record, @Param("example") ProjectNoticeEntityExample example);

    int updateByExample(@Param("record") ProjectNoticeEntity record, @Param("example") ProjectNoticeEntityExample example);

    int updateByPrimaryKeySelective(ProjectNoticeEntity record);

    int updateByPrimaryKey(ProjectNoticeEntity record);
}