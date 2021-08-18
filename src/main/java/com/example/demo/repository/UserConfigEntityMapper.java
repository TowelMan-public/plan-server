package com.example.demo.repository;

import com.example.demo.dto.UserConfigEntityExample;
import com.example.demo.entity.UserConfigEntity;

import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface UserConfigEntityMapper {

    long countByExample(UserConfigEntityExample example);

    int deleteByExample(UserConfigEntityExample example);

    int deleteByPrimaryKey(Integer userId);

    int insert(UserConfigEntity record);

    int insertSelective(UserConfigEntity record);

    List<UserConfigEntity> selectByExample(UserConfigEntityExample example);

    UserConfigEntity selectByPrimaryKey(Integer userId);

    int updateByExampleSelective(@Param("record") UserConfigEntity record, @Param("example") UserConfigEntityExample example);

    int updateByExample(@Param("record") UserConfigEntity record, @Param("example") UserConfigEntityExample example);

    int updateByPrimaryKeySelective(UserConfigEntity record);

    int updateByPrimaryKey(UserConfigEntity record);
}