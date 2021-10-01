package com.example.demo.repository;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import com.example.demo.dto.TodoEntityExample;
import com.example.demo.entity.TodoEntity;

@Mapper
public interface TodoEntityMapper {

    long countByExample(TodoEntityExample example);

    int deleteByExample(TodoEntityExample example);

    int deleteByPrimaryKey(Integer todoId);

    int insert(TodoEntity record);

    int insertSelective(TodoEntity record);

    List<TodoEntity> selectByExample(TodoEntityExample example);

    int updateByExampleSelective(@Param("record") TodoEntity record, @Param("example") TodoEntityExample example);

    int updateByExample(@Param("record") TodoEntity record, @Param("example") TodoEntityExample example);
}