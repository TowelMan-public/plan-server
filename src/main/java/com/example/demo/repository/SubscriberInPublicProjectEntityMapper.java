package com.example.demo.repository;

import com.example.demo.dto.SubscriberInPublicProjectEntityExample;
import com.example.demo.entity.SubscriberInPublicProjectEntity;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

@Mapper
public interface SubscriberInPublicProjectEntityMapper {

    long countByExample(SubscriberInPublicProjectEntityExample example);

    int deleteByExample(SubscriberInPublicProjectEntityExample example);

    int deleteByPrimaryKey(Integer publicProjectId);

    int insert(SubscriberInPublicProjectEntity record);

    int insertSelective(SubscriberInPublicProjectEntity record);

    List<SubscriberInPublicProjectEntity> selectByExample(SubscriberInPublicProjectEntityExample example);

    SubscriberInPublicProjectEntity selectByPrimaryKey(Integer publicProjectId);

    int updateByExampleSelective(@Param("record") SubscriberInPublicProjectEntity record, @Param("example") SubscriberInPublicProjectEntityExample example);

    int updateByExample(@Param("record") SubscriberInPublicProjectEntity record, @Param("example") SubscriberInPublicProjectEntityExample example);

    int updateByPrimaryKeySelective(SubscriberInPublicProjectEntity record);

    int updateByPrimaryKey(SubscriberInPublicProjectEntity record);
}