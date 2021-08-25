package com.example.demo.repository;

import com.example.demo.dto.ContentEntityExample;
import com.example.demo.entity.ContentEntity;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

@Mapper
public interface ContentEntityMapper {
	
    long countByExample(ContentEntityExample example);

    int deleteByExample(ContentEntityExample example);

    int deleteByPrimaryKey(Integer contentId);

    int insert(ContentEntity record);

    int insertSelective(ContentEntity record);

    List<ContentEntity> selectByExample(ContentEntityExample example);

    ContentEntity selectByPrimaryKey(Integer contentId);

    int updateByExampleSelective(@Param("record") ContentEntity record, @Param("example") ContentEntityExample example);

    int updateByExample(@Param("record") ContentEntity record, @Param("example") ContentEntityExample example);

    int updateByPrimaryKeySelective(ContentEntity record);

    int updateByPrimaryKey(ContentEntity record);
}