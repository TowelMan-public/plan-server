package com.example.demo.repository;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import com.example.demo.dto.PublicProjectEntityExample;
import com.example.demo.entity.PublicProjectEntity;

@Mapper
public interface PublicProjectEntityMapper {

    long countByExample(PublicProjectEntityExample example);

    int deleteByExample(PublicProjectEntityExample example);

    int deleteByPrimaryKey(Integer publicProjectId);

    int insert(PublicProjectEntity record);

    int insertSelective(PublicProjectEntity record);

    List<PublicProjectEntity> selectByExample(PublicProjectEntityExample example);

    PublicProjectEntity selectByPrimaryKey(Integer publicProjectId);

    int updateByExampleSelective(@Param("record") PublicProjectEntity record, @Param("example") PublicProjectEntityExample example);

    int updateByExample(@Param("record") PublicProjectEntity record, @Param("example") PublicProjectEntityExample example);

    int updateByPrimaryKeySelective(PublicProjectEntity record);

    int updateByPrimaryKey(PublicProjectEntity record);
}