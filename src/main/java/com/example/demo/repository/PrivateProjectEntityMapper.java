package com.example.demo.repository;

import com.example.demo.dto.PrivateProjectEntityExample;
import com.example.demo.entity.PrivateProjectEntity;

import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface PrivateProjectEntityMapper {
	
    long countByExample(PrivateProjectEntityExample example);

    int deleteByExample(PrivateProjectEntityExample example);

    int deleteByPrimaryKey(Integer projectId);

    int insert(PrivateProjectEntity record);

    int insertSelective(PrivateProjectEntity record);

    List<PrivateProjectEntity> selectByExample(PrivateProjectEntityExample example);

    PrivateProjectEntity selectByPrimaryKey(Integer projectId);

    int updateByExampleSelective(@Param("record") PrivateProjectEntity record, @Param("example") PrivateProjectEntityExample example);

    int updateByExample(@Param("record") PrivateProjectEntity record, @Param("example") PrivateProjectEntityExample example);

    int updateByPrimaryKeySelective(PrivateProjectEntity record);

    int updateByPrimaryKey(PrivateProjectEntity record);
}