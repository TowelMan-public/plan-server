package com.example.demo.repository;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import com.example.demo.dto.NoticeEntityExample;
import com.example.demo.entity.NoticeEntity;

@Mapper
public interface NoticeEntityMapper {

    long countByExample(NoticeEntityExample example);

    int deleteByExample(NoticeEntityExample example);

    int deleteByPrimaryKey(Integer noticeId);

    int insert(NoticeEntity record);

    int insertSelective(NoticeEntity record);

    List<NoticeEntity> selectByExample(NoticeEntityExample example);

    NoticeEntity selectByPrimaryKey(Integer noticeId);

    int updateByExampleSelective(@Param("record") NoticeEntity record, @Param("example") NoticeEntityExample example);

    int updateByExample(@Param("record") NoticeEntity record, @Param("example") NoticeEntityExample example);

    int updateByPrimaryKeySelective(NoticeEntity record);

    int updateByPrimaryKey(NoticeEntity record);
}