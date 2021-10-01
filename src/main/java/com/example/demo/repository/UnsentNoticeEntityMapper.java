package com.example.demo.repository;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import com.example.demo.dto.UnsentNoticeEntityExample;
import com.example.demo.entity.UnsentNoticeEntity;

@Mapper
public interface UnsentNoticeEntityMapper {

    long countByExample(UnsentNoticeEntityExample example);

    int deleteByExample(UnsentNoticeEntityExample example);

    int deleteByPrimaryKey(Integer unsendNoticeId);

    int insert(UnsentNoticeEntity record);

    int insertSelective(UnsentNoticeEntity record);

    List<UnsentNoticeEntity> selectByExample(UnsentNoticeEntityExample example);

    UnsentNoticeEntity selectByPrimaryKey(Integer unsendNoticeId);

    int updateByExampleSelective(@Param("record") UnsentNoticeEntity record, @Param("example") UnsentNoticeEntityExample example);

    int updateByExample(@Param("record") UnsentNoticeEntity record, @Param("example") UnsentNoticeEntityExample example);

    int updateByPrimaryKeySelective(UnsentNoticeEntity record);

    int updateByPrimaryKey(UnsentNoticeEntity record);
}