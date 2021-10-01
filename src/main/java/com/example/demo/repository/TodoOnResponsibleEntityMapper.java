package com.example.demo.repository;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import com.example.demo.dto.TodoOnResponsibleEntityExample;
import com.example.demo.entity.TodoOnResponsibleEntity;

@Mapper
public interface TodoOnResponsibleEntityMapper {

    long countByExample(TodoOnResponsibleEntityExample example);

    int deleteByExample(TodoOnResponsibleEntityExample example);

    int deleteByPrimaryKey(Integer todoOnResponsibleId);

    int insert(TodoOnResponsibleEntity record);

    int insertSelective(TodoOnResponsibleEntity record);

    List<TodoOnResponsibleEntity> selectByExample(TodoOnResponsibleEntityExample example);

    TodoOnResponsibleEntity selectByPrimaryKey(Integer todoOnResponsibleId);

    int updateByExampleSelective(@Param("record") TodoOnResponsibleEntity record, @Param("example") TodoOnResponsibleEntityExample example);

    int updateByExample(@Param("record") TodoOnResponsibleEntity record, @Param("example") TodoOnResponsibleEntityExample example);

    int updateByPrimaryKeySelective(TodoOnResponsibleEntity record);

    int updateByPrimaryKey(TodoOnResponsibleEntity record);
}