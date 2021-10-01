package com.example.demo.repository;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import com.example.demo.dto.OtherConfigEntityExample;
import com.example.demo.entity.OtherConfigEntity;

@Mapper
public interface OtherConfigEntityMapper {

    long countByExample(OtherConfigEntityExample example);

    int deleteByExample(OtherConfigEntityExample example);

    int deleteByPrimaryKey(String otherConfigKey);

    int insert(OtherConfigEntity record);

    int insertSelective(OtherConfigEntity record);

    List<OtherConfigEntity> selectByExample(OtherConfigEntityExample example);

    OtherConfigEntity selectByPrimaryKey(String otherConfigKey);

    int updateByExampleSelective(@Param("record") OtherConfigEntity record, @Param("example") OtherConfigEntityExample example);

    int updateByExample(@Param("record") OtherConfigEntity record, @Param("example") OtherConfigEntityExample example);

    int updateByPrimaryKeySelective(OtherConfigEntity record);

    int updateByPrimaryKey(OtherConfigEntity record);
}