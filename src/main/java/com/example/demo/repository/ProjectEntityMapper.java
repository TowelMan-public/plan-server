package com.example.demo.repository;

import com.example.demo.dto.ProjectEntityExample;
import com.example.demo.entity.ProjectEntity;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

@Mapper
public interface ProjectEntityMapper {

    long countByExample(ProjectEntityExample example);

    int deleteByExample(ProjectEntityExample example);

    int deleteByPrimaryKey(Integer projectId);

    int insert(ProjectEntity record);

    int insertSelective(ProjectEntity record);

    List<ProjectEntity> selectByExample(ProjectEntityExample example);

    int updateByExampleSelective(@Param("record") ProjectEntity record, @Param("example") ProjectEntityExample example);

    int updateByExample(@Param("record") ProjectEntity record, @Param("example") ProjectEntityExample example);
}