package com.example.demo.repository;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import com.example.demo.dto.TodoOnProjectEntityExample;
import com.example.demo.entity.TodoOnProjectEntity;

@Mapper
public interface TodoOnProjectEntityMapper {
	
    long countByExample(TodoOnProjectEntityExample example);

    int deleteByExample(TodoOnProjectEntityExample example);

    int deleteByPrimaryKey(Integer todoOnProjectId);

    int insert(TodoOnProjectEntity record);

    int insertSelective(TodoOnProjectEntity record);

    List<TodoOnProjectEntity> selectByExample(TodoOnProjectEntityExample example);

    TodoOnProjectEntity selectByPrimaryKey(Integer todoOnProjectId);

    int updateByExampleSelective(@Param("record") TodoOnProjectEntity record, @Param("example") TodoOnProjectEntityExample example);

    int updateByExample(@Param("record") TodoOnProjectEntity record, @Param("example") TodoOnProjectEntityExample example);

    int updateByPrimaryKeySelective(TodoOnProjectEntity record);

    int updateByPrimaryKey(TodoOnProjectEntity record);
}