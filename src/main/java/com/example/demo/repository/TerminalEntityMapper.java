package com.example.demo.repository;

import com.example.demo.dto.TerminalEntityExample;
import com.example.demo.entity.TerminalEntity;

import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface TerminalEntityMapper {
	
    long countByExample(TerminalEntityExample example);

    int deleteByExample(TerminalEntityExample example);

    int deleteByPrimaryKey(Integer terminalId);

    int insert(TerminalEntity record);

    int insertSelective(TerminalEntity record);

    List<TerminalEntity> selectByExample(TerminalEntityExample example);

    TerminalEntity selectByPrimaryKey(Integer terminalId);

    int updateByExampleSelective(@Param("record") TerminalEntity record, @Param("example") TerminalEntityExample example);

    int updateByExample(@Param("record") TerminalEntity record, @Param("example") TerminalEntityExample example);

    int updateByPrimaryKeySelective(TerminalEntity record);

    int updateByPrimaryKey(TerminalEntity record);
}