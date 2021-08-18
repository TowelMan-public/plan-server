package com.example.demo.repository;

import com.example.demo.dto.TodoNoticeEntityExample;
import com.example.demo.entity.TodoNoticeEntity;

import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface TodoNoticeEntityMapper {

    long countByExample(TodoNoticeEntityExample example);

    int deleteByExample(TodoNoticeEntityExample example);

    int deleteByPrimaryKey(Integer noticeId);

    int insert(TodoNoticeEntity record);

    int insertSelective(TodoNoticeEntity record);

    List<TodoNoticeEntity> selectByExample(TodoNoticeEntityExample example);

    TodoNoticeEntity selectByPrimaryKey(Integer noticeId);

    int updateByExampleSelective(@Param("record") TodoNoticeEntity record, @Param("example") TodoNoticeEntityExample example);

    int updateByExample(@Param("record") TodoNoticeEntity record, @Param("example") TodoNoticeEntityExample example);

    int updateByPrimaryKeySelective(TodoNoticeEntity record);

    int updateByPrimaryKey(TodoNoticeEntity record);
}